package com.food.goods_fridge.controller.api;

import com.food.goods_fridge.Consts;
import com.food.goods_fridge.db.entity.FridgeProduct;
import com.food.goods_fridge.model.FridgeProductRequest;
import com.food.goods_fridge.model.FridgeProductResponse;
import com.food.goods_fridge.model.SortedProduct;
import com.food.goods_fridge.service.FridgeProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@RestController
@RequestMapping(value = Consts.API + Consts.VERSION_1,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class FridgeProductController {

    private final FridgeProductService fridgeProductService;

    public FridgeProductController(FridgeProductService fridgeProductService) {
        this.fridgeProductService = fridgeProductService;
    }

    @PostMapping(value = "createAndUpdateProduct")
    public ResponseEntity<?> createAndUpdateProduct(@Valid @RequestBody FridgeProductRequest fridgeProductRequest) {
        FridgeProductResponse response = fridgeProductService.addAndUpdateProduct(fridgeProductRequest);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "timeStored/{userId}")
    public ResponseEntity<?> retrieveDateStoredProductsByUser(@PathVariable Long userId) {
        List<SortedProduct> timeStoredProducts = fridgeProductService.getDateStoredProductsByUser(userId);

        return ResponseEntity.ok(timeStoredProducts);
    }

    @GetMapping(value = "expiring-soon/{userId}")
    public ResponseEntity<?> retrieveExpiringSoonProductsByUser(@PathVariable Long userId, @RequestParam int days) {
        List<FridgeProductResponse> expiringSoonProducts = fridgeProductService.expiringSoonProducts(days, userId);

        return ResponseEntity.ok(expiringSoonProducts);
    }
}
