package com.food.goods_fridge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@Data
@AllArgsConstructor
public class SortedProduct {

    private String productName;
    private LocalDateTime dateStored;
    private LocalDateTime bestBeforeDate;
    private Integer quantity;
    private String category;

    private Long userId;
    private String firstName;
    private String email;
}
