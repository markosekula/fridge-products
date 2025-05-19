package com.food.goods_fridge.service;

import com.food.goods_fridge.db.dao.FridgeProductRepository;
import com.food.goods_fridge.db.dao.NotificationRepository;
import com.food.goods_fridge.db.entity.FridgeProduct;
import com.food.goods_fridge.db.entity.Notification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sekulicmarko10@gmail.com on 5/17/2025
 * @project goods-fridge
 */

@Service
public class NotificationService {

    private final FridgeProductRepository fridgeProductRepository;
    private final NotificationRepository notificationRepository;

    public NotificationService(FridgeProductRepository fridgeProductRepository,
            NotificationRepository notificationRepository) {
        this.fridgeProductRepository = fridgeProductRepository;
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(cron = "0 */2 * * * *") // on every 2 minutes
    public void checkExpiringProductsAndNotify() {
        LocalDateTime now = LocalDateTime.now();

        List<FridgeProduct> expiredProducts = fridgeProductRepository.findExpiredProducts(now);

        // TODO: add checking for duplicates
        for (FridgeProduct product : expiredProducts) {
            Notification notification = Notification.builder()
                    .message("Your product '" + product.getProductName() + "' has expired.")
                    .createdAt(now)
                    .fridgeProduct(product)
                    .build();

            notificationRepository.save(notification);
        }
    }
}
