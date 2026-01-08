package com.sunny.fooddelivery.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private double price;  // price = menuItem.price * quantity

    @ManyToOne
    private Order order;

    @ManyToOne
    private MenuItem menuItem;
}
