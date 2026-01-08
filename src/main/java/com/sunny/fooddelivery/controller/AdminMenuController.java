package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.MenuItem;
import com.sunny.fooddelivery.model.Restaurant;
import com.sunny.fooddelivery.repository.MenuItemRepository;
import com.sunny.fooddelivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/menu")
public class AdminMenuController {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    // Show all menu items
    @GetMapping
    public String showAllMenuItems(Model model) {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        model.addAttribute("menuItems", menuItems);
        return "admin_menu"; // new page coming!
    }

    // Show add menu item form
    @GetMapping("/add")
    public String showAddMenuItemForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("restaurants", restaurantRepository.findAll());
        return "admin_add_menu";
    }

    // Save new menu item
    @PostMapping("/add")
    public String addMenuItem(@ModelAttribute MenuItem menuItem) {
        menuItemRepository.save(menuItem);
        return "redirect:/admin/menu";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditMenuItemForm(@PathVariable Long id, Model model) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item id: " + id));
        model.addAttribute("menuItem", menuItem);
        model.addAttribute("restaurants", restaurantRepository.findAll());
        return "admin_edit_menu";
    }

    // Save edited menu item
    @PostMapping("/edit/{id}")
    public String updateMenuItem(@PathVariable Long id, @ModelAttribute MenuItem menuItem) {
        menuItem.setId(id);
        menuItemRepository.save(menuItem);
        return "redirect:/admin/menu";
    }

    // Delete a menu item
    @GetMapping("/delete/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        menuItemRepository.deleteById(id);
        return "redirect:/admin/menu";
    }

}
