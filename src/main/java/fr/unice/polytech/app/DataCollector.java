
package fr.unice.polytech.app;

import fr.unice.polytech.app.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class DataCollector {

    private List<Order> orders;
    private List<DeliveryPerson> deliveryPeople;
    private Map<Item, Integer> menuItemPopularity;
    private Map<CampusUser, List<Order>> userOrders;


    public DataCollector() {
        this.orders = new ArrayList<>();
        this.deliveryPeople = new ArrayList<>();
        this.menuItemPopularity = new HashMap<>();
        this.userOrders = new HashMap<>();

    }

    public void collectOrderData(Order order) {
        this.orders.add(order);
    }

    public void collectDeliveryData(DeliveryPerson deliveryPerson) {
        this.deliveryPeople.add(deliveryPerson);

    }

    public void collectMenuItemPopularityData(Item menuItem) {
        menuItemPopularity.merge(menuItem, 1, Integer::sum);

    }

    public void collectUserBehaviorData(CampusUser user, Order order) {
        userOrders.computeIfAbsent(user, k -> new ArrayList<>()).add(order);

    }


    public List<Order> getOrdersForRestaurant(Restaurant restaurant) {
        List<Order> ordersForRestaurant = new ArrayList<>();

        for (Order order : orders) {
            if (order.getRestaurant().equals(restaurant)) {
                ordersForRestaurant.add(order);
            }
        }

        return ordersForRestaurant;
    }

    // Getters pour accéder aux collections
    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }



    public Map<Item, Integer> getMenuItemPopularity() {
        return Collections.unmodifiableMap(menuItemPopularity);
    }

    public Map<CampusUser, List<Order>> getUserOrders() {
        return Collections.unmodifiableMap(userOrders);
    }
    public List<DeliveryPerson> getDeliveryPeople() {
        return Collections.unmodifiableList(deliveryPeople);
    }
}

