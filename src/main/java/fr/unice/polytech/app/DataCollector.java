/*
package fr.unice.polytech.app;

import fr.unice.polytech.app.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;

public class DataCollector {

    private List<Order> orders;
    private List<DeliveryPerson> deliveryPeople;
    private Map<Item, Integer> menuItemPopularity;
    private Map<CampusUser, List<Order>> userOrders;
    private String csvDirectory; // Répertoire pour stocker les fichiers CSV

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
        writeDeliveriesToCSV();
    }

    public void collectMenuItemPopularityData(Item menuItem) {
        menuItemPopularity.merge(menuItem, 1, Integer::sum);
        writeMenuItemPopularityToCSV();
    }

    public void collectUserBehaviorData(CampusUser user, Order order) {
        userOrders.computeIfAbsent(user, k -> new ArrayList<>()).add(order);
        writeUserOrdersToCSV();
    }

    // Méthodes pour écrire les données dans les fichiers CSV
    private void writeOrdersToCSV() {
        String filePath = Paths.get(csvDirectory, "orders.csv").toString();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Order ID"))) {

            for (Order order : orders) {
                csvPrinter.printRecord(order.getId());

            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeMenuItemPopularityToCSV() {
        String filePath = Paths.get(csvDirectory, "menu_items_popularity.csv").toString();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Menu Item", "Popularity"))) {

            for (Map.Entry<Item, Integer> entry : menuItemPopularity.entrySet()) {
                csvPrinter.printRecord(entry.getKey().getDish().getName(), entry.getValue());

            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeUserOrdersToCSV() {
        String filePath = Paths.get(csvDirectory, "user_orders.csv").toString();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("User ID", "Order IDs"))) {

            for (Map.Entry<CampusUser, List<Order>> entry : userOrders.entrySet()) {
                String orderIds = entry.getValue().stream()
                        .map(Order::getId)
                        .reduce("", (a, b) -> a + ", " + b);
                csvPrinter.printRecord(entry.getKey().getId(), orderIds);
                // Supposons que CampusUser a une méthode getUserId
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeDeliveriesToCSV() {

        String filePath = Paths.get(csvDirectory, "deliveries.csv").toString();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Delivery Person ID", "Delivery History"))) {

            for (DeliveryPerson person : deliveryPeople) {
                String deliveryHistory = person.getDeliveryHistory().stream()
                        .map(Order::getId)
                        .reduce("", (a, b) -> a + ", " + b);
                csvPrinter.printRecord(person.getId(), deliveryHistory);
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
*/
