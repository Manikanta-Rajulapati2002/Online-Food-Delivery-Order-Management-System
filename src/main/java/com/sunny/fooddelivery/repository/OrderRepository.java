package com.sunny.fooddelivery.repository;

import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
}
