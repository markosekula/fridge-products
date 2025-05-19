package com.food.goods_fridge.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author sekulicmarko10@gmail.com on 5/7/2025
 * @project goods-fridge
 */

@Entity
@Table(name = "fridge_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User { // one user has more products

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<FridgeProduct> products;

}
