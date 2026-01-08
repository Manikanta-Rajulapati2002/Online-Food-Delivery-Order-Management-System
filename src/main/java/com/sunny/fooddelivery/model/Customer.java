package com.sunny.fooddelivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
}
