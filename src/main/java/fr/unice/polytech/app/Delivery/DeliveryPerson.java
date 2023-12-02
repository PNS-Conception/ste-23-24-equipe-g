package fr.unice.polytech.app.Delivery;

import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Orders.Order;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Users.UserType;

import java.time.LocalTime;
import java.util.List;

public class DeliveryPerson extends CampusUser {

    private String phoneNumber;
    private Order currentSingleOrder;
    private boolean isAvailable;
    private String routeDetails;
    private LocalTime pickupTime;
    private List<Restaurant> restaurants;

    private Restaurant restaurant;
    private String deliveryLocation;
    private static List<DeliveryPerson> deliveryPeople;



    public DeliveryPerson( String name,String email,String phoneNumber) {
        super(name,email);
        setType(UserType.DELIVERY_PERSON);
        this.phoneNumber = phoneNumber;
        this.currentSingleOrder = null;
        this.isAvailable = true;

    }

    public static List<DeliveryPerson> getDeliveryPeople() {
        return deliveryPeople;
    }

    public boolean assignOrder(SingleOrder singleOrder) throws Exception {
        if (this.isAvailable && this.currentSingleOrder == null) {
            this.currentSingleOrder = singleOrder;
            this.isAvailable = false; // Le livreur n'est plus disponible après avoir accepté une commande
            singleOrder.assign();
            return true;
        }
        return false;
    }

    public void markOrderAsDelivered() throws Exception {
        if (this.currentSingleOrder != null) {
            this.currentSingleOrder.deliver();
            this.currentSingleOrder = null;
            this.isAvailable = true;
        }
    }
    public void finalizeDeliveryWithoutUserConfirmation(SingleOrder singleOrder) throws Exception {
        if (!singleOrder.canUserConfirmReceipt() && this.currentSingleOrder.equals(singleOrder)) {
            this.markOrderAsDelivered();
        }
    }
    public void receiveOrderDetails(Order singleOrder) {
        this.routeDetails = singleOrder.getRouteDetails();
        this.pickupTime = singleOrder.getPickupTime();
        this.restaurants = singleOrder.getRestaurants();
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


    public void validateOrder(Order order) throws Exception {
        setCurrentOrder(order);
        order.pickUp();
    }


    public void deliverOrder(Order groupOrder) throws Exception {
        groupOrder.deliver();
        setCurrentOrder(null);
    }

    public void rateUser(CampusUser user, int rate) throws Exception {
        if (rate >= 0 && rate <= 5) {
            user.addRate(rate);
        } else {
            throw new Exception("Rate must be between 0 and 5");
        }
    }



}
