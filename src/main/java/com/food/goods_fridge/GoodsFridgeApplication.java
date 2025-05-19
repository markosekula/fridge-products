package com.food.goods_fridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoodsFridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsFridgeApplication.class, args);
    }

}
