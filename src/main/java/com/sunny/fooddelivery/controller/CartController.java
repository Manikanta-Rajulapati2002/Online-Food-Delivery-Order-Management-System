package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.CartItem;
import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.model.MenuItem;
import com.sunny.fooddelivery.service.MenuItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final MenuItemService menuItemService;

    @PostMapping("/add/{itemId}")
    public String addToCart(@PathVariable Long itemId, HttpSession session) {
        MenuItem menuItem = menuItemService.getMenuItemById(itemId);

        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/customer/login"; // ✅ Block unauthenticated cart access
        }
        // Get existing cart from session
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Check if item already exists in cart
        boolean itemExists = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId().equals(menuItem.getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        // If item doesn't exist, add new
        if (!itemExists) {
            cartItems.add(new CartItem(menuItem.getId(), menuItem.getName(), menuItem.getPrice(), 1));
        }

        // Save updated cart back to session
        session.setAttribute("cartItems", cartItems);

        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);

        double totalPrice = 0;
        for (CartItem item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cartItems");
        return "redirect:/cart";
    }
}
