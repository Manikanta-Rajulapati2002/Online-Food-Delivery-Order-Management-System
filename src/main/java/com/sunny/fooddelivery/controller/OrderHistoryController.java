package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.model.Order;
import com.sunny.fooddelivery.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderHistoryController {

    private final OrderService orderService;

    @GetMapping("/order/history")
    public String viewOrderHistory(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");

        if (customer == null) {
            return "redirect:/customer/login";  // force login
        }

        List<Order> orders = orderService.getOrdersByCustomer(customer);
        model.addAttribute("orders", orders);
        return "order_history";
    }
}
