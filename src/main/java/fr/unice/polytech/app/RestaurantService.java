package fr.unice.polytech.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static fr.unice.polytech.app.Day.getAllDayOfWeeks;

public class RestaurantService {

    private static RestaurantService instance;
    private Map<String, Restaurant> restaurants;
    private CapacityManager capacityManager;
    private RestaurantRepository restaurantRepository; // Assuming there's a repository for data access

    /*public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurants = new HashMap<>();
        this.restaurantRepository = restaurantRepository;
        capacityManager = CapacityManager.getInstance();
    }*/
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

    /*public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getName(), restaurant);
        initializeDefaultCapacity(restaurant);
    }*/
    /*private void initializeDefaultCapacity(Restaurant restaurant) {
        LocalDate today = LocalDate.now();
        for (DayOfWeek day : EnumSet.allOf(DayOfWeek.class)) {
            LocalDate nextDay = today.with(day); // Définir le jour de la semaine
            for (int hour = 0; hour < 24; hour++) {
                LocalDateTime dateTime = LocalDateTime.of(nextDay, LocalTime.of(hour, 0));
                capacityManager.setCapacity(restaurant, dateTime, CapacityManager.DEFAULT_CAPACITY_PER_HOUR);
            }
        }
    }*/


    private List<Shift> getDefaultShifts() {
        List<Shift> defaultShifts = new ArrayList<>();
        for (Day day : getAllDayOfWeeks()) {
            defaultShifts.add(new Shift(LocalTime.of(9, 0), LocalTime.of(17, 0),day));
        }
        return defaultShifts;
    }


    private void initializeDefaultCapacity(Restaurant restaurant) {
        boolean res =restaurant.getSchedule().isEmpty();
        List<Shift> shifts = restaurant.getSchedule().isEmpty() ? getDefaultShifts() : restaurant.getSchedule();

        LocalDate startDate = LocalDate.now().withDayOfMonth(1); // Début du mois
        LocalDate endDate = startDate.plusMonths(1).minusDays(1); // Fin du mois

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            for (Shift shift : shifts) {
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

