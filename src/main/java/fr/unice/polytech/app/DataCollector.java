
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
//import org.apache.commons.csv.CSVPrinter;
//import org.apache.commons.csv.CSVFormat;

public class DataCollector {

    private List<Order> orders;
    private List<DeliveryPerson> deliveryPeople;
    private Map<Item, Integer> menuItemPopularity;
    private Map<CampusUser, List<Order>> userOrders;
    private String csvDirectory;

    public DataCollector(String csvDirectory) {
        this.orders = new ArrayList<>();
        this.deliveryPeople = new ArrayList<>();
        this.menuItemPopularity = new HashMap<>();
        this.userOrders = new HashMap<>();
        this.csvDirectory = csvDirectory;
    }

    public void collectOrderData(Order order) {
        this.orders.add(order);
        writeOrdersToCSV();
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

    private void writeOrdersToCSV() {
        String filePath = Paths.get(csvDirectory, "orders.csv").toString();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            // Écriture de l'en-tête
            writer.println("Order ID");

            // Écriture des données
            for (Order order : orders) {
                writer.println(order.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//private void writeMenuItemPopularityToCSV() {
//    String filePath = Paths.get(csvDirectory, "menu_items_popularity.csv").toString();
//    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
//        writer.println("Menu Item, Popularity");
//
//        for (Map.Entry<Item, Integer> entry : menuItemPopularity.entrySet()) {
//            writer.println(entry.getKey().getDish().getName() + ", " + entry.getValue());
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}


//private void writeUserOrdersToCSV() {
//    String filePath = Paths.get(csvDirectory, "user_orders.csv").toString();
//    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
//        // Écriture de l'en-tête
//        writer.println("User ID, Order IDs");
//
//        // Écriture des données
//        for (Map.Entry<CampusUser, List<Order>> entry : userOrders.entrySet()) {
//            String orderIds = entry.getValue().stream()
//                    .map(order -> order.getId().toString())
//                    .collect(Collectors.joining(", "));
//
//            writer.println(entry.getKey().getId() + ", " + orderIds);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}
//
//private void writeDeliveriesToCSV() {
//    String filePath = Paths.get(csvDirectory, "deliveries.csv").toString();
//    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
//
//        writer.println("Delivery Person ID, Delivery History");
//
//        // Écriture des données
//        for (DeliveryPerson person : deliveryPeople) {
//            String deliveryHistory = person.getDeliveryHistory().stream()
//                    .map(order -> order.getId().toString())
//                    .collect(Collectors.joining(", "));
//
//            writer.println(person.getId() + ", " + deliveryHistory);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}
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
}

