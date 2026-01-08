package com.sunny.fooddelivery.config;

import com.sunny.fooddelivery.model.Restaurant;
import com.sunny.fooddelivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;

    @Override
    public void run(String... args) throws Exception {
        if (restaurantRepository.count() == 0) {
            Restaurant r1 = new Restaurant();
            r1.setName("Rajadhani Indian Cuisine");
            r1.setAddress("12030 Blue Valley Pkwy, Overland Park, KS, 66213");

            Restaurant r2 = new Restaurant();
            r2.setName("Aaha Modern Indian Cuisine");
            r2.setAddress("7328 W 119th St, Overland Park, KS, 66213");

            Restaurant r3 = new Restaurant();
            r3.setName("Bawarchi Restaurant");
            r3.setAddress("7284 W 121st St, Overland Park, KS, 66213");

            Restaurant r4 = new Restaurant();
            r4.setName("Kushi Indian Restaurant");
            r4.setAddress("14856 Metcalf Avenue, Overland Park, KS, 66213");

            restaurantRepository.save(r1);
            restaurantRepository.save(r2);
            restaurantRepository.save(r3);
            restaurantRepository.save(r4);

            System.out.println("✅ Your custom restaurants inserted into database.");
        }
    }
}
