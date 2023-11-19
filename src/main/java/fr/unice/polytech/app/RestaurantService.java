package fr.unice.polytech.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class RestaurantService {

    private static RestaurantService instance;
    private Map<String, Restaurant> restaurants;
    private CapacityManager capacityManager;
    private RestaurantRepository restaurantRepository; // Assuming there's a repository for data access

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurants = new HashMap<>();
        this.restaurantRepository = restaurantRepository;
        capacityManager = CapacityManager.getInstance();
    }
    public RestaurantService() {
        capacityManager = CapacityManager.getInstance();
        this.restaurants = new HashMap<>();
    }

    public static synchronized RestaurantService getInstance() {
        if (instance == null) {
            instance = new RestaurantService();
        }
        return instance;
    }
    public Restaurant createRestaurant(String name, Menu menu) {
        if (restaurants.containsKey(name)) {
            throw new IllegalArgumentException("Restaurant already exists with this name: " + name);
        }

        Restaurant newRestaurant = new Restaurant(name, menu);
        restaurants.put(name, newRestaurant);
        initializeDefaultCapacity(newRestaurant);
        return newRestaurant;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getName(), restaurant);
        initializeDefaultCapacity(restaurant);
    }
    private void initializeDefaultCapacity(Restaurant restaurant) {
        LocalDate today = LocalDate.now();
        for (DayOfWeek day : EnumSet.allOf(DayOfWeek.class)) {
            LocalDate nextDay = today.with(day); // Définir le jour de la semaine
            for (int hour = 0; hour < 24; hour++) {
                LocalDateTime dateTime = LocalDateTime.of(nextDay, LocalTime.of(hour, 0));
                capacityManager.setCapacity(restaurant, dateTime, CapacityManager.DEFAULT_CAPACITY_PER_HOUR);
            }
        }
    }
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

    public Restaurant getRestaurantByName(String name) {
        return restaurants.get(name);
    }

    // Additional methods related to restaurant operations can be added here
    // For example, finding a restaurant by ID, updating restaurant details, etc.
}

