package fr.unice.polytech.app.Statistic;

import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.Orders.Order;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Restaurant.Item;
import fr.unice.polytech.app.Restaurant.RestaurantService;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.*;


public class MetricsService {

    private DataCollector dataCollector;


    public MetricsService(DataCollector dataCollector) {
        this.dataCollector = dataCollector;
    }

    public Map<LocalTime, Long> getOrderVolumeOverTime() {
        return dataCollector.getOrders().stream()
                .filter(order -> order.getDeliveryTime() != null) // Filter out orders with null delivery times
                .collect(Collectors.groupingBy(Order::getDeliveryTime, Collectors.counting()));
    }


    public Map<Restaurant, List<Order>> getOrdersByRestaurant(Restaurant targetRestaurant) {
        // Check if the target restaurant is null
        if (targetRestaurant == null) {
            return Collections.emptyMap();
        }

        return dataCollector.getOrders().stream()
                .filter(order -> order.getRestaurant() != null)
                .filter(order -> order.getRestaurant().equals(targetRestaurant))
                .collect(Collectors.groupingBy(Order::getRestaurant));
    }


    public Map<String, Long> getDeliveryLocationMetrics() {
        List<Order> orders = dataCollector.getOrders();
        if (orders == null) {
            return Collections.emptyMap();
        }

        return orders.stream()
                .filter(order -> order != null)
                .filter(order -> order.getDeliveryLocation() != null)
                .collect(Collectors.groupingBy(Order::getDeliveryLocation, Collectors.counting()));
    }

    public Map<Item, Integer> getPopularMenuItems() {
        return dataCollector.getMenuItemPopularity().entrySet().stream()
                .sorted(Map.Entry.<Item, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
    public Map<CampusUser, Double> getUserAverageOrderValue() {
        return dataCollector.getUserOrders().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToDouble(Order::getPrice)
                                .average()
                                .orElse(0.0)));
    }

    public Map<Restaurant, Double> getDeliveryEfficiencyMetrics() {
        Map<Restaurant, Double> deliveryEfficiency = new HashMap<>();
        List<Restaurant> restaurants = RestaurantService.getInstance().getAllRestaurants();

        if (restaurants == null) {
            return deliveryEfficiency; // Renvoyer une map vide si la liste est null
        }

        for (Restaurant restaurant : restaurants) {
            double efficiency = calculateDeliveryEfficiency(restaurant);
            deliveryEfficiency.put(restaurant, efficiency);
        }

        return deliveryEfficiency;
    }

    private double calculateDeliveryEfficiency(Restaurant restaurant) {
        List<Order> orders = dataCollector.getOrdersForRestaurant(restaurant); // Obtenez la liste des commandes pour ce restaurant

        if (orders.isEmpty()) {
            // Si le restaurant n'a aucune commande, considérez une efficacité de 0
            return 0.0;
        }

        int onTimeDeliveries = 0; // Compteur pour les livraisons à l'heure

        for (Order order : orders) {
            LocalTime orderTime = order.getAcceptedTime(); // Heure de commande
            LocalTime deliveryTime = order.getDeliveryTime(); // Heure de livraison réelle
            Duration deliveryDuration = Duration.between(orderTime, deliveryTime);
            long minutes = deliveryDuration.toMinutes();

            if (minutes <= 30) {
                // Si la livraison a été effectuée en moins de 30 minutes, elle est à l'heure
                onTimeDeliveries++;
            }
        }
        // Calculez le taux de livraison à l'heure
        double deliveryEfficiency = (double) onTimeDeliveries / orders.size();

        return deliveryEfficiency;
    }
    public Map<CampusUser, Map<String, Object>> getUserBehaviorMetrics() {
        Map<CampusUser, Map<String, Object>> userMetricsMap = new HashMap<>();

        for (Map.Entry<CampusUser, List<Order>> entry : dataCollector.getUserOrders().entrySet()) {
            CampusUser user = entry.getKey();
            List<Order> orders = entry.getValue();

            Map<String, Object> metrics = new HashMap<>();
            metrics.put("totalOrders", orders.size());
            metrics.put("averageOrderValue", getUserAverageOrderValue());
            metrics.put("mostOrderedItem",getPopularMenuItems());
            userMetricsMap.put(user, metrics);
        }

        return userMetricsMap;
    }

}


