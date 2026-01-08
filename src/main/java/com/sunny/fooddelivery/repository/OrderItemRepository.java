package com.sunny.fooddelivery.repository;

import com.sunny.fooddelivery.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
