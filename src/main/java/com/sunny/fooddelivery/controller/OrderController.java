package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.model.Order;
import com.sunny.fooddelivery.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(@RequestParam("menuItemIds") List<Long> menuItemIds,
                             HttpSession session,
                             Model model) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/customer/login";
        }

        Order order = orderService.placeOrder(menuItemIds, customer);
        model.addAttribute("order", order);
        return "order-confirmation";
    }

    @GetMapping("/{orderId}")
    public String viewOrderStatus(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "order_status";
    }
}
