package com.food.goods_fridge.controller.api;

import com.food.goods_fridge.model.SortedProduct;
import com.food.goods_fridge.service.FridgeProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author sekulicmarko10@gmail.com on 5/17/2025
 * @project goods-fridge
 */

@Controller
public class ViewProductsController {

    private final FridgeProductService fridgeProductService;

    public ViewProductsController(FridgeProductService fridgeProductService) {
        this.fridgeProductService = fridgeProductService;
    }

    // open: http://localhost:1992/fridge/view/timeStored

    @GetMapping(value = "view/timeStored")
    public String retrieveDateStoredProductsByUser(Model model) {
        List<SortedProduct> timeStoredProducts = fridgeProductService.getDateStoredProductsByUser(1L);

        model.addAttribute("products", timeStoredProducts);
        return "products";
    }
}
