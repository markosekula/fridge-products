package com.food.goods_fridge.db.dao;

import com.food.goods_fridge.db.entity.FridgeProduct;
import com.food.goods_fridge.db.entity.User;
import com.food.goods_fridge.model.SortedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@Repository
public interface FridgeProductRepository extends JpaRepository<FridgeProduct, Long> {

    Optional<FridgeProduct> findByProductNameAndUser(String productName, User user);

    @Query("SELECT new com.food.goods_fridge.model.SortedProduct(" +
            "fp.productName, fp.dateStored, fp.bestBeforeDate, fp.quantity, fp.category, u.id, u.firstName, u.email) " +
            "FROM FridgeProduct fp " +
            "JOIN fp.user u " +
            "WHERE u.id = :userId " +
            "ORDER BY fp.dateStored DESC")
    List<SortedProduct> getSortedFridgeProducts(@Param("userId") Long userId);

    @Query("SELECT p FROM FridgeProduct p WHERE p.bestBeforeDate BETWEEN :now AND :upperBound AND p.user.userId = :userId")
    List<FridgeProduct> findExpiringSoon(@Param("now") LocalDateTime now,
            @Param("upperBound") LocalDateTime upperBound, @Param("userId") Long userId);

    @Query("SELECT p FROM FridgeProduct p WHERE p.bestBeforeDate <= :now")
    List<FridgeProduct> findExpiredProducts(@Param("now") LocalDateTime now);
}
