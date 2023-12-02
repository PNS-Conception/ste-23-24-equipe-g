package fr.unice.polytech.app.Restaurant;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CapacityManager {
    private static CapacityManager instance;
    private Map<Restaurant, Map<LocalDateTime, Integer>> capacityMap;
    public static final int DEFAULT_CAPACITY_PER_HOUR = 20; // Exemple de capacité par défaut

    private CapacityManager() {
        capacityMap = new HashMap<>();
    }

    public static synchronized CapacityManager getInstance() {
        if (instance == null) {
            instance = new CapacityManager();
        }
        return instance;
    }

    public void setCapacity(Restaurant restaurant, LocalDateTime dateTime, int capacity) {
        capacityMap.putIfAbsent(restaurant, new HashMap<>());
        capacityMap.get(restaurant).put(dateTime, capacity);
    }

    public int getCapacity(Restaurant restaurant, LocalDateTime dateTime) {
        return capacityMap.getOrDefault(restaurant, new HashMap<>()).getOrDefault(dateTime, 0);
    }


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
