package com.sunny.fooddelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deliveryPersonName;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne
    private Order order;
}
