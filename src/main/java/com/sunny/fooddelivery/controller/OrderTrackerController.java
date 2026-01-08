package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.model.Order;
import com.sunny.fooddelivery.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderTrackerController {

    private final OrderService orderService;

    // ✅ Used to track the latest order
    @GetMapping("/order/track")
    public String trackLatestOrder(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");

        if (customer == null) {
            return "redirect:/customer/login";
        }

        List<Order> orders = orderService.getOrdersByCustomer(customer);
        if (orders.isEmpty()) {
            model.addAttribute("message", "No recent order found.");
            return "order_tracker";
        }

        Order latestOrder = orders.get(orders.size() - 1); // Latest order
        model.addAttribute("order", latestOrder);
        return "order_tracker";
    }

    // ✅ This replaces /order/{id} to avoid conflict
    @GetMapping("/order/track/{id}")
    public String viewTrackedOrder(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order_tracker";
    }
}
