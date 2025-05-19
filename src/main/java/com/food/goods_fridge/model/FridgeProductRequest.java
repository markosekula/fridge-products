package com.food.goods_fridge.model;

import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@Data
public class FridgeProductRequest {

    private String productName;

    @FutureOrPresent(message = "Field bestBeforeDate must be in the present or future.")
    @NotNull(message = "bestBeforeDate is required")
    private LocalDateTime bestBeforeDate;

    private String category;
    private Integer quantity;
    private Long userId;
}
