package fr.unice.polytech.app.Restaurant;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static fr.unice.polytech.app.Restaurant.Day.getAllDayOfWeeks;

/**
 * The RestaurantService class provides functionality for managing restaurants and their capacities.
 */
public class RestaurantService {

    private static RestaurantService instance;
    private Map<String, Restaurant> restaurants;
    private CapacityManager capacityManager;
    private RestaurantRepository restaurantRepository; // Assuming there's a repository for data access

    /**
     * Constructs a new RestaurantService instance.
     */
    public RestaurantService() {
        capacityManager = CapacityManager.getInstance();
        this.restaurants = new HashMap<>();
    }

    /**
     * Returns the singleton instance of RestaurantService.
     *
     * @return the singleton instance of RestaurantService
     */
    public static synchronized RestaurantService getInstance() {
        if (instance == null) {
            instance = new RestaurantService();
        }
        return instance;
    }

    /**
     * Creates a new restaurant with the given name and menu.
     *
     * @param name the name of the restaurant
     * @param menu the menu of the restaurant
     * @return the newly created restaurant
     * @throws IllegalArgumentException if a restaurant with the same name already exists
     */
    public Restaurant createRestaurant(String name, Menu menu) {
        if (restaurants.containsKey(name)) {
            throw new IllegalArgumentException("Restaurant already exists with this name: " + name);
        }
        Restaurant newRestaurant = new Restaurant(name, menu);
        restaurants.put(name, newRestaurant);
        initializeDefaultCapacity(newRestaurant); // Supposons que cette méthode existe pour initialiser la capacité
        return newRestaurant;
    }

    /**
     * Returns the default shifts for each day of the week.
     *
     * @return the list of default shifts
     */
    private List<Day.Shift> getDefaultShifts() {
        List<Day.Shift> defaultShifts = new ArrayList<>();
        for (Day day : getAllDayOfWeeks()) {
            defaultShifts.add(new Day.Shift(LocalTime.of(9, 0), LocalTime.of(17, 0),day));
        }
        return defaultShifts;
    }

    /**
     * Initializes the default capacity for the given restaurant.
     *
     * @param restaurant the restaurant to initialize the capacity for
     */
    private void initializeDefaultCapacity(Restaurant restaurant) {
        List<Day.Shift> shifts = restaurant.getSchedule().isEmpty() ? getDefaultShifts() : restaurant.getSchedule();

        LocalDate startDate = LocalDate.now().withDayOfMonth(1); // Début du mois
        LocalDate endDate = startDate.plusMonths(1).minusDays(1); // Fin du mois

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            for (Day.Shift shift : shifts) {
                if (shift.getDay().toDayOfWeek().equals(dayOfWeek)) {
                    LocalTime startTime = shift.getOpeningTime();
                    LocalTime endTime = shift.getClosingTime();

                    for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusHours(1)) {
                        LocalDateTime dateTime = LocalDateTime.of(date, time);
                        capacityManager.setCapacity(restaurant, dateTime, CapacityManager.DEFAULT_CAPACITY_PER_HOUR);
                    }
                }
            }
        }
    }

    /**
     * Returns a list of all restaurants.
     *
     * @return the list of all restaurants
     */
    public List<Restaurant> getAllRestaurants() {
        // Return a pre-populated list of mocked restaurants
        // This is just for testing; in a real app, you'd query the database
        if (this.restaurantRepository == null) { // Check if repository is mocked
            return Arrays.asList(
                    new Restaurant("Pizza Place", new Menu(Arrays.asList(
                            new Dish("Margherita", Arrays.asList("Tomato", "Mozzarella", "Basil"), 7.99),
                            new Dish("Pepperoni", Arrays.asList("Tomato", "Mozzarella", "Pepperoni"), 8.99)
                    ))),
                    new Restaurant("Burger Bar", new Menu(Arrays.asList(
                            new Dish("Classic Burger", Arrays.asList("Beef", "Lettuce", "Tomto", "Cheese"), 6.99),
                            new Dish("Veggie Burger", Arrays.asList("Black Bean Patty", "Lettuce", "Tomato", "Cheese"), 5.99)
                    )))
            );
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Returns the restaurant with the given name.
     *
     * @param name the name of the restaurant
     * @return the restaurant with the given name, or null if not found
     */
    public Restaurant getRestaurantByName(String name) {
        return restaurants.get(name);
    }

}
