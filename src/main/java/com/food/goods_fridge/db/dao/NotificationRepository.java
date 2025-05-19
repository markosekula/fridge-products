package com.food.goods_fridge.db.dao;

import com.food.goods_fridge.db.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sekulicmarko10@gmail.com  on 5/17/2025
 * @project goods-fridge
 */

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
