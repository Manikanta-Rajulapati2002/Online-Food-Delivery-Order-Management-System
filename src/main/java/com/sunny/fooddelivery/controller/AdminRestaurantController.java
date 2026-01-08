package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.Restaurant;
import com.sunny.fooddelivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/restaurants")
@RequiredArgsConstructor
public class AdminRestaurantController {

    private final RestaurantRepository restaurantRepository;

    @GetMapping
    public String viewAllRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);
        return "admin_restaurants";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "admin_add_restaurant";
    }

    @PostMapping("/add")
    public String saveRestaurant(@ModelAttribute Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return "redirect:/admin/restaurants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));
        model.addAttribute("restaurant", restaurant);
        return "admin_edit_restaurant";
    }

    @PostMapping("/edit/{id}")
    public String updateRestaurant(@PathVariable Long id, @ModelAttribute Restaurant restaurant) {
        restaurant.setId(id);
        restaurantRepository.save(restaurant);
        return "redirect:/admin/restaurants";
    }

    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
        return "redirect:/admin/restaurants";
    }
}
