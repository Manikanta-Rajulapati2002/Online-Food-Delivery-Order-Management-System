package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.MenuItem;
import com.sunny.fooddelivery.model.Order;
import com.sunny.fooddelivery.model.OrderStatus;
import com.sunny.fooddelivery.service.AdminService;
import com.sunny.fooddelivery.service.MenuItemService;
import com.sunny.fooddelivery.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final OrderService orderService;
    private final MenuItemService menuItemService;

    // --- Admin Login ---
    @GetMapping("/login")
    public String showLoginPage() {
        return "admin_login";
    }

    @PostMapping("/login")
    public String loginAdmin(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session,
                             Model model) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "admin_login";
        }
    }

    // --- Dashboard Home ---
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        List<Order> allOrders = orderService.getAllOrders();
        List<MenuItem> allMenuItems = menuItemService.getAllMenuItems();

        model.addAttribute("orders", allOrders);
        model.addAttribute("menuItems", allMenuItems);

        return "admin_dashboard";
    }

    // --- Order Status Update ---
    @PostMapping("/update-status")
    public String updateOrderStatus(@RequestParam Long orderId,
                                    @RequestParam OrderStatus status) {
        orderService.updateOrderStatusManually(orderId, status);
        return "redirect:/admin/dashboard";
    }


    // --- Logout ---
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
