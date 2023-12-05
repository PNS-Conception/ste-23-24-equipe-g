package fr.unice.polytech.app.Restaurant;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The CapacityManager class is responsible for managing the capacity of restaurants at different date and time slots.
 */
public class CapacityManager {
    private static CapacityManager instance;
    private Map<Restaurant, Map<LocalDateTime, Integer>> capacityMap;
    public static final int DEFAULT_CAPACITY_PER_HOUR = 20; // Exemple de capacité par défaut

    private CapacityManager() {
        capacityMap = new HashMap<>();
    }

    /**
     * Returns the singleton instance of the CapacityManager class.
     *
     * @return The singleton instance of the CapacityManager class.
     */
    public static synchronized CapacityManager getInstance() {
        if (instance == null) {
            instance = new CapacityManager();
        }
        return instance;
    }

    /**
     * Sets the capacity for a specific restaurant at a given date and time.
     *
     * @param restaurant The restaurant for which to set the capacity.
     * @param dateTime   The date and time for which to set the capacity.
     * @param capacity   The capacity to set.
     */
    public void setCapacity(Restaurant restaurant, LocalDateTime dateTime, int capacity) {
        capacityMap.putIfAbsent(restaurant, new HashMap<>());
        capacityMap.get(restaurant).put(dateTime, capacity);
    }

    /**
     * Returns the capacity for a specific restaurant at a given date and time.
     *
     * @param restaurant The restaurant for which to get the capacity.
     * @param dateTime   The date and time for which to get the capacity.
     * @return The capacity for the specified restaurant at the specified date and time.
     */
    public int getCapacity(Restaurant restaurant, LocalDateTime dateTime) {
        return capacityMap.getOrDefault(restaurant, new HashMap<>()).getOrDefault(dateTime, 0);
    }

    /**
     * Checks if an order can be placed at a specific restaurant, date, and time, given the quantity.
     * If the available capacity is greater than or equal to the quantity, the capacity is updated and true is returned.
     * Otherwise, false is returned.
     *
     * @param restaurant The restaurant to check.
     * @param dateTime   The date and time to check.
     * @param quantity   The quantity of the order.
     * @return True if the order can be placed, false otherwise.
     */
    public boolean canPlaceOrder(Restaurant restaurant, LocalDateTime dateTime, int quantity) {
        Map<LocalDateTime, Integer> restaurantCapacity = capacityMap.getOrDefault(restaurant, new HashMap<>());
        int availableCapacity = restaurantCapacity.getOrDefault(dateTime, 0);

        if (availableCapacity >= quantity) {
            restaurantCapacity.put(dateTime, availableCapacity - quantity);
            return true;
        } else {
            return false;
        }
    }
}
