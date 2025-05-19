package com.food.goods_fridge.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@Data
public class UserRequest {

    @NotBlank(message = "firstName field is required")
    private String firstName;

    @Email(message = "Invalid email format.")
    @NotBlank(message = "email field is required")
    private String email;
}
