package com.food.goods_fridge.service;

import com.food.goods_fridge.db.dao.FridgeProductRepository;
import com.food.goods_fridge.db.dao.UserRepository;
import com.food.goods_fridge.db.entity.FridgeProduct;
import com.food.goods_fridge.db.entity.User;
import com.food.goods_fridge.model.FridgeProductRequest;
import com.food.goods_fridge.model.FridgeProductResponse;
import com.food.goods_fridge.model.SortedProduct;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@Service
public class FridgeProductService {

    private final FridgeProductRepository fridgeProductRepository;
    private final UserRepository userRepository;

    public FridgeProductService(FridgeProductRepository fridgeProductRepository,
            UserRepository userRepository) {
        this.fridgeProductRepository = fridgeProductRepository;
        this.userRepository = userRepository;
    }

    public FridgeProductResponse addAndUpdateProduct(FridgeProductRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with ID " + request.getUserId() + " not found."));

        FridgeProduct fridgeProduct = fridgeProductRepository.findByProductNameAndUser(request.getProductName(), user)
                .map(existingProduct -> {
                    existingProduct.setBestBeforeDate(request.getBestBeforeDate());
                    existingProduct.setCategory(request.getCategory());
                    existingProduct.setDateStored(LocalDateTime.now());
                    existingProduct.setQuantity(existingProduct.getQuantity() + request.getQuantity());
                    return existingProduct;
                })
                .orElseGet(() -> FridgeProduct.builder()
                        .productName(request.getProductName())
                        .bestBeforeDate(request.getBestBeforeDate())
                        .category(request.getCategory())
                        .dateStored(LocalDateTime.now())
                        .quantity(request.getQuantity())
                        .user(user)
                        .build());

        FridgeProduct saved = fridgeProductRepository.save(fridgeProduct);

        return FridgeProductResponse.builder()
                .fridgeProductId(saved.getFridgeProductId())
                .productName(saved.getProductName())
                .category(saved.getCategory())
                .dateStored(saved.getDateStored())
                .bestBeforeDate(saved.getBestBeforeDate())
                .quantity(saved.getQuantity())
                .userId(saved.getUser().getUserId())
                .build();
    }

    public List<SortedProduct> getDateStoredProductsByUser(Long userId) {
        return fridgeProductRepository.getSortedFridgeProducts(userId);
    }

    public List<FridgeProductResponse> expiringSoonProducts(int days, Long userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime upperBound = now.plusDays(days);

        List<FridgeProduct> dbResponse = fridgeProductRepository.findExpiringSoon(now, upperBound, userId);

        return dbResponse.stream().map(product ->
                        FridgeProductResponse.builder()
                                .fridgeProductId(product.getFridgeProductId())
                                .productName(product.getProductName())
                                .category(product.getCategory())
                                .dateStored(product.getDateStored())
                                .bestBeforeDate(product.getBestBeforeDate())
                                .quantity(product.getQuantity())
                                .userId(product.getUser().getUserId())
                                .build())
                .collect(Collectors.toList());
    }
}
