package com.sunny.fooddelivery.service;

import com.sunny.fooddelivery.model.*;
import com.sunny.fooddelivery.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;

    public Order placeOrder(List<Long> menuItemIds, Customer customer) {
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);
        order.setCustomer(customer);

        List<OrderItem> orderItems = menuItemIds.stream().map(itemId -> {
            MenuItem menuItem = menuItemRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("MenuItem not found with ID: " + itemId));
            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(1);
            orderItem.setPrice(menuItem.getPrice());
            orderItem.setOrder(order);
            return orderItem;
        }).toList();

        double totalPrice = orderItems.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return savedOrder;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateOrderStatusManually(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        orderRepository.save(order);
    }

}
