package com.food.goods_fridge.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sekulicmarko10@gmail.com on 5/10/2025
 * @project goods-fridge
 */

@Data
@Builder
public class FridgeProductResponse {

    private Long fridgeProductId;
    private String productName;

    private String category;
    private LocalDateTime dateStored;
    private LocalDateTime bestBeforeDate;
    private Integer quantity;

    private Long userId;
}
