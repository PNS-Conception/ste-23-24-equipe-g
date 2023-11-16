package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.List;

import static fr.unice.polytech.app.OrderStatus.ASSIGNED;
import static fr.unice.polytech.app.OrderStatus.DELIVERED;

public class DeliveryPerson extends CampusUser {

    private String phoneNumber;
    private Order currentOrder;
    private boolean isAvailable;
    private String routeDetails;
    private LocalTime pickupTime;
    private List<Restaurant> restaurants;
    private String deliveryLocation;
    private List<DeliveryPerson> deliveryPeople;


    public DeliveryPerson(String id, String name, String email,  String phoneNumber) {
        super(id, name, email); // Appel au constructeur de la superclasse CampusUser
        this.phoneNumber = phoneNumber;
        this.currentOrder = null;
        this.isAvailable = true; // Le livreur est initialement disponible
    }

    public boolean assignOrder(Order order) {
        if (this.isAvailable && this.currentOrder == null) {
            this.currentOrder = order;
            this.isAvailable = false; // Le livreur n'est plus disponible après avoir accepté une commande
            order.setStatus(ASSIGNED);
            return true;
        }
        return false;
    }

    public void markOrderAsDelivered() {
        if (this.currentOrder != null) {
            this.currentOrder.setStatus(DELIVERED);
            this.currentOrder = null;
            this.isAvailable = true;
        }
    }
    public void finalizeDeliveryWithoutUserConfirmation(Order order) {
        if (!order.canUserConfirmReceipt() && this.currentOrder.equals(order)) {
            this.markOrderAsDelivered();
        }
    }
    public void receiveOrderDetails(Order order) {
        // Stocker les informations de la commande dans les attributs de la classe DeliveryPerson
        this.routeDetails = order.getRouteDetails(); // Cette méthode doit être implémentée dans la classe Order
        this.pickupTime = order.getPickupTime(); // Idem
        this.restaurants = order.getRestaurants(); // Idem
        this.deliveryLocation = order.getDeliveryLocation(); // Idem
    }


    // Getters pour les nouveaux attributs
    public String getRouteDetails() {
        return routeDetails;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    // Getters et setters pour phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getters et setters pour currentOrder
    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    // Getters et setters pour isAvailable
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public void validateOrder(GroupOrder groupOrder) {
        groupOrder.setStatus(OrderStatus.PICKED_UP);
    }

    public void deliverOrder(GroupOrder groupOrder) {
        groupOrder.setStatus(OrderStatus.DELIVERED);
    }



}
