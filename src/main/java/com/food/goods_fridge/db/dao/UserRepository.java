package com.food.goods_fridge.db.dao;

import com.food.goods_fridge.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
