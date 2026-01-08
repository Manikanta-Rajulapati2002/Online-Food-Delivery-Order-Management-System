package com.sunny.fooddelivery.seeder;

import com.sunny.fooddelivery.model.MenuCategory;
import com.sunny.fooddelivery.model.MenuItem;
import com.sunny.fooddelivery.model.Restaurant;
import com.sunny.fooddelivery.repository.RestaurantRepository;
import com.sunny.fooddelivery.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuSeeder implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        for (Restaurant restaurant : restaurants) {
            seedMenuForRestaurant(restaurant);
        }
    }

    private void seedMenuForRestaurant(Restaurant restaurant) {
        if (menuItemRepository.findByRestaurantId(restaurant.getId()).isEmpty()) {
            List<MenuItem> menuItems = Arrays.asList(

                    // Appetizers
                    new MenuItem("Aloo 65", 11.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Gobi/Paneer 65", 12.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Schezwan Aloo", 12.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Gobi/Veg/Paneer Manchurian", 12.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Chili Gobi/Paneer", 12.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Karapodi/CurryLeaf Gobi", 12.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Chili BabyCorn", 13.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Pepper BabyCorn", 13.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Chicken 65/Manchurian", 13.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Chicken Majestic", 13.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Chili/Habanero Chicken", 13.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Temper Chicken", 13.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Karapodi/Spicy Pepper Chicken", 14.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("CurryLeaf/Karivepaku Chicken", 14.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Karapodi Goat/Habanero Goat", 16.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Goat Pepper Fry", 18.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Gongura Goat", 18.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Lamb Pepper Fry", 18.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Karivepaku Fish", 16.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Fish 65 (Apollo)", 16.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Chili/Schezwan Fish", 16.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Pepper Shrimp", 15.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("555 Shrimp", 15.99, MenuCategory.APPETIZER, restaurant),
                    new MenuItem("Chili Shrimp/Habanero Shrimp", 15.99, MenuCategory.APPETIZER, restaurant),

                    // Biryani/Pulao
                    new MenuItem("Veg Dum Biryani/Pulao", 12.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Paneer Biryani/Pulao", 13.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Kaju Paneer Biryani/Pulao", 14.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Gutti Vankaya Biryani/Pulao", 14.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Ulava Charu Veg Biryani/Pulao", 14.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Gongura Paneer Biryani/Pulao", 14.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Gobi 65 Biryani/Pulao", 14.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Egg Dum Biryani", 12.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Chicken Pulao", 12.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Kushi Spl Boneless Biryani/Pulao", 13.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Gongura Chicken Biryani/Pulao", 14.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Fry Piece Chicken Biryani/Pulao", 13.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Vijayawada Chicken Biryani/Pulao", 13.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Wing Chicken Biryani/Pulao", 15.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Goat Dum Biryani", 17.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Goat Pulao", 17.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Gongura Goat Biryani", 18.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Mutton Ghee Roast Biryani/Pulao", 18.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Shrimp Biryani/Pulao", 17.99, MenuCategory.BIRYANI, restaurant),
                    new MenuItem("Fish Biryani", 18.99, MenuCategory.BIRYANI, restaurant),

                    // Curries
                    new MenuItem("Dal Fry/Dal Thadka", 11.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Mixed Veg Curry/Korma", 12.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Mixed Veg Korma", 12.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Paneer Butter Masala", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Paneer Tikka Masala", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Saag Paneer", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Malai Kofta", 14.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Aloo Gobi Curry", 11.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Chana Masala", 11.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Gutti Vankaya Curry", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Butter Chicken", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Andhra Chicken Curry", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Chicken Tikka Masala", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Kadai Chicken", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Saag Chicken", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Chicken Vindaloo", 13.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Gongura Chicken", 14.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Goat Curry", 16.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Goat Korma", 16.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Kadai Goat", 16.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Lamb Korma", 16.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Lamb Curry", 16.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Lamb Tikka Masala", 16.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Shrimp Curry", 15.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Shrimp Tikka Masala", 15.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Kadai Shrimp", 15.99, MenuCategory.CURRY, restaurant),
                    new MenuItem("Fish Curry", 15.99, MenuCategory.CURRY, restaurant),

                    // Beverages
                    new MenuItem("Irani Chai (Mini)", 1.29, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Irani Chai (Max)", 1.79, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Masala Chai (Mini)", 1.29, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Masala Chai (Max)", 1.79, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Filter Coffee", 2.49, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Horlicks/Boost", 2.99, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Mango Lassi", 2.99, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Rose Milk", 2.99, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Thums Up/Limca", 1.99, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Cool Drinks", 1.49, MenuCategory.BEVERAGE, restaurant),
                    new MenuItem("Energy Drinks (Redbull)", 2.99, MenuCategory.BEVERAGE, restaurant),

                    // Desserts
                    new MenuItem("Jalebi (3 Pcs)", 2.99, MenuCategory.DESSERT, restaurant),
                    new MenuItem("Jalebi (6 Pcs)", 4.99, MenuCategory.DESSERT, restaurant),
                    new MenuItem("Jalebi + Rabdi", 4.99, MenuCategory.DESSERT, restaurant),
                    new MenuItem("Rasamali", 4.99, MenuCategory.DESSERT, restaurant),
                    new MenuItem("Double Ka Meetha", 4.99, MenuCategory.DESSERT, restaurant),
                    new MenuItem("Carrot Halwa", 4.99, MenuCategory.DESSERT, restaurant),
                    new MenuItem("Gulab Jamun (4 Pcs)", 4.99, MenuCategory.DESSERT, restaurant),

                    // Ice Cream
                    new MenuItem("Mango Ice Cream (1 Pc)", 3.99, MenuCategory.ICE_CREAM, restaurant),
                    new MenuItem("Mango Ice Cream (2 Pc)", 5.99, MenuCategory.ICE_CREAM, restaurant),
                    new MenuItem("Paan Ice Cream (1 Pc)", 3.99, MenuCategory.ICE_CREAM, restaurant),
                    new MenuItem("Paan Ice Cream (2 Pc)", 5.99, MenuCategory.ICE_CREAM, restaurant),
                    new MenuItem("Kulfi (Mango/Malai)", 2.79, MenuCategory.ICE_CREAM, restaurant)

            );

            menuItemRepository.saveAll(menuItems);
        }
    }
}
