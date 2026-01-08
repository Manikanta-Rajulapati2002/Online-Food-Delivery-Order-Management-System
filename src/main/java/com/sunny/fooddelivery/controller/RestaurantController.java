package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.model.Order;
import com.sunny.fooddelivery.repository.MenuItemRepository;
import com.sunny.fooddelivery.repository.RestaurantRepository;
import com.sunny.fooddelivery.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

    private final MenuItemRepository menuItemRepository;
    private final OrderService orderService;
    private final  RestaurantRepository restaurantRepository;

    @PostMapping("/order")
    public String placeOrder(@RequestParam List<Long> menuItemIds,
                             HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/customer/login"; // Redirect to login if not logged in
        }

        Order order = orderService.placeOrder(menuItemIds, customer);
        model.addAttribute("order", order);
        return "order_status"; // show order status page
    }
    @GetMapping("/restaurants")
    public String showRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantRepository.findAll());
        return "restaurants";
    }


}
