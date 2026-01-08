package com.sunny.fooddelivery.repository;

import com.sunny.fooddelivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // No need to write any code here. JpaRepository gives all basic CRUD methods automatically!
}
