package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantService {

    private RestaurantRepository restaurantRepository; // Assuming there's a repository for data access

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    public RestaurantService() {}

    // Method to get all restaurants
    // Method to get all restaurants
    public List<Restaurant> getAllRestaurants() {
        // Return a pre-populated list of mocked restaurants
        // This is just for testing; in a real app, you'd query the database
        if (this.restaurantRepository == null) { // Check if repository is mocked
            // Return mocked data
            return Arrays.asList(
                    new Restaurant("Pizza Place", new Menu(Arrays.asList(
                            new Dish("Margherita", Arrays.asList("Tomato", "Mozzarella", "Basil"), 7.99),
                            new Dish("Pepperoni", Arrays.asList("Tomato", "Mozzarella", "Pepperoni"), 8.99)
                    ))),
                    new Restaurant("Burger Bar", new Menu(Arrays.asList(
                            new Dish("Classic Burger", Arrays.asList("Beef", "Lettuce", "Tomato", "Cheese"), 6.99),
                            new Dish("Veggie Burger", Arrays.asList("Black Bean Patty", "Lettuce", "Tomato", "Cheese"), 5.99)
                    )))
            );
        } else {
            // In production, you'd do something like:
            // return restaurantRepository.findAll();
            return new ArrayList<>();
        }
    }

    // Additional methods related to restaurant operations can be added here
    // For example, finding a restaurant by ID, updating restaurant details, etc.
}

