package com.food.goods_fridge.service;

import com.food.goods_fridge.db.dao.UserRepository;
import com.food.goods_fridge.model.UserRequest;
import com.food.goods_fridge.db.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(UserRequest userRequest) {

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .email(userRequest.getEmail())
                .build();

        userRepository.save(user);
    }
}
