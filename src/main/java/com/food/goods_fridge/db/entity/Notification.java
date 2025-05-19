package com.food.goods_fridge.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author sekulicmarko10@gmail.com on 5/7/2025
 * @project goods-fridge
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification { // belongs one product

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fridgeProductId")
    private FridgeProduct fridgeProduct;
}
