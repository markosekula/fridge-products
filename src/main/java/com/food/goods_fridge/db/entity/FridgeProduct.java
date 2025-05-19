package com.food.goods_fridge.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sekulicmarko10@gmail.com on 5/7/2025
 * @project goods-fridge
 */

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FridgeProduct { // belongs one user, have multiple notifications

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fridgeProductId;

    @Column(nullable = false)
    private String productName;

    private String category;
    private LocalDateTime dateStored;
    private LocalDateTime bestBeforeDate;
    private Integer quantity;

    @OneToMany(mappedBy = "fridgeProduct")
    private List<Notification> notifications;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
