package com.food.goods_fridge.controller.api;

import com.food.goods_fridge.Consts;
import com.food.goods_fridge.model.UserRequest;
import com.food.goods_fridge.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@RestController
@RequestMapping(value = Consts.API + Consts.VERSION_1,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {

        userService.addNewUser(userRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
