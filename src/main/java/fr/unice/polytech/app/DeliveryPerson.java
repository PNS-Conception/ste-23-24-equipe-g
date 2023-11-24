package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.List;

import static fr.unice.polytech.app.OrderStatus.ASSIGNED;
import static fr.unice.polytech.app.OrderStatus.DELIVERED;

public class DeliveryPerson extends CampusUser {

    private String phoneNumber;
    private Order currentSingleOrder;
    private boolean isAvailable;
    private String routeDetails;
    private LocalTime pickupTime;
    private List<Restaurant> restaurants;

    private Restaurant restaurant;
    private String deliveryLocation;
    private List<DeliveryPerson> deliveryPeople;



    public DeliveryPerson( String name,String email,String phoneNumber) {
        super(name,email);
        setType(UserType.DELIVERY_PERSON);
        this.phoneNumber = phoneNumber;
        this.currentSingleOrder = null;
        this.isAvailable = true;

    }

    public boolean assignOrder(SingleOrder singleOrder) {
        if (this.isAvailable && this.currentSingleOrder == null) {
            this.currentSingleOrder = singleOrder;
            this.isAvailable = false; // Le livreur n'est plus disponible après avoir accepté une commande
            singleOrder.setStatus(ASSIGNED);
            return true;
        }
        return false;
    }

    public void markOrderAsDelivered() {
        if (this.currentSingleOrder != null) {
            this.currentSingleOrder.setStatus(DELIVERED);
            this.currentSingleOrder = null;
            this.isAvailable = true;
        }
    }
    public void finalizeDeliveryWithoutUserConfirmation(SingleOrder singleOrder) {
        if (!singleOrder.canUserConfirmReceipt() && this.currentSingleOrder.equals(singleOrder)) {
            this.markOrderAsDelivered();
        }
    }
    public void receiveOrderDetails(Order singleOrder) {
        this.routeDetails = singleOrder.getRouteDetails();
        this.pickupTime = singleOrder.getPickupTime();
        this.restaurant = singleOrder.getRestaurant();
        this.deliveryLocation = singleOrder.getDeliveryLocation();
    }

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
        return currentSingleOrder;
    }

    public void setCurrentOrder(Order currentSingleOrder) {
        this.currentSingleOrder = currentSingleOrder;
    }

    // Getters et setters pour isAvailable
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public void validateOrder(Order order) {
        setCurrentOrder(order);
        order.setStatus(OrderStatus.PICKED_UP);
    }

    public void validateOrder(SingleOrder groupSingleOrder) {

        setCurrentOrder(groupSingleOrder);
        groupSingleOrder.setStatus(OrderStatus.PICKED_UP);
    }

    public void deliverOrder(GroupOrder groupOrder) {

        groupOrder.setStatus(DELIVERED);
        setCurrentOrder(null);
    }



}
