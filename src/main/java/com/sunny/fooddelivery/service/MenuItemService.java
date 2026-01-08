package com.sunny.fooddelivery.service;

import com.sunny.fooddelivery.model.MenuItem;
import com.sunny.fooddelivery.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    // ✅ For customer menu view
    public List<MenuItem> getMenuItemsByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    // ✅ For cart and editing menu item
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found with ID: " + id));
    }

    // ✅ For adding or updating menu item from admin dashboard
    public void saveMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    // ✅ For deleting menu item from admin dashboard
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    // ✅ For listing all menu items in admin dashboard
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }
}
