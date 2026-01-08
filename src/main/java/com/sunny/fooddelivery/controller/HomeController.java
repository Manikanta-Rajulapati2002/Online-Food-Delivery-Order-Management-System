package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.model.Order;
import com.sunny.fooddelivery.repository.RestaurantRepository;
import com.sunny.fooddelivery.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RestaurantRepository restaurantRepository;
    private final OrderService orderService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("restaurants", restaurantRepository.findAll());
            model.addAttribute("orders", orderService.getOrdersByCustomer(customer));
            return "restaurants";
        } else {
            return "home";
        }
    }
}
