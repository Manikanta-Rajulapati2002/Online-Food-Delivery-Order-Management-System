package com.sunny.fooddelivery.controller;

import com.sunny.fooddelivery.model.CartItem;
import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.model.Order;
import com.sunny.fooddelivery.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final OrderService orderService;

    @GetMapping("/checkout")
    public String checkoutPage(Model model, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");

        double totalPrice = 0;
        if (cartItems != null) {
            totalPrice = cartItems.stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("customer", customer);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam("paymentMethod") String paymentMethod,
                             @RequestParam("cardNumber") String cardNumber,
                             @RequestParam("cardHolder") String cardHolder,
                             @RequestParam("expiry") String expiry,
                             @RequestParam("cvv") String cvv,
                             HttpSession session,
                             Model model) {

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");

        if (customer == null || cartItems == null || cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        // Extract menuItemIds from cart
        List<Long> menuItemIds = new ArrayList<>();
        for (CartItem item : cartItems) {
            for (int i = 0; i < item.getQuantity(); i++) {
                menuItemIds.add(item.getId()); // Add multiple times for quantity
            }
        }

        // Save order
        Order order = orderService.placeOrder(menuItemIds, customer);

        // Prepare data for success page
        model.addAttribute("customerName", customer.getName());
        model.addAttribute("address", customer.getAddressLine1() + ", " + customer.getAddressLine2()
                + ", " + customer.getCity() + ", " + customer.getState() + " - " + customer.getZip() + ", USA");
        model.addAttribute("totalPrice", order.getTotalPrice());
        model.addAttribute("paymentMethod", "Online Payment (****" + cardNumber.substring(cardNumber.length() - 4) + ")");

        // Clear the cart after placing order
        session.removeAttribute("cartItems");

        return "order_success";
    }
}
