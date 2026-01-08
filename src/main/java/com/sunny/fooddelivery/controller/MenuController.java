package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.MenuItem;
import com.sunny.fooddelivery.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuItemService menuItemService; // Only MenuItemService needed

    @GetMapping("/menu/{restaurantId}")
    public String viewMenu(@PathVariable Long restaurantId, Model model) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByRestaurant(restaurantId);
        model.addAttribute("menuItems", menuItems);
        return "menu"; // It will look for 'menu.html' inside templates
    }
}
