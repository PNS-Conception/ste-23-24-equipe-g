package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


public class Order {
    private boolean requiresSignatureAndVerification;
    private List<Item> items;
    private String clientAddress;
    private LocalTime placedTime;
    private UUID id;
    private OrderStatus status;
    private LocalTime acceptedTime;
    private LocalTime deliveryTime;
    private double price;
    private boolean userConfirmationPossible = true;
    private String routeDetails;
    private LocalTime pickupTime;
    private List<Restaurant> restaurants;
    private String deliveryLocation;


    public Order(List<Item> items) {
        this.id = UUID.randomUUID();
        this.items = items;
        for (Item item : items) {
            price += item.getPrice();
        }
        this.status = OrderStatus.PLACED; // La commande est initialisée avec le statut PLACED
        this.requiresSignatureAndVerification = false; // Initialement, pas besoin de signature ni de vérification
    }

    public List<Item> getItems() {
        return items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPlacedTime(LocalTime placedTime) {
        this.placedTime = placedTime;
    }

    public void setAcceptedTime(LocalTime acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public LocalTime getPlacedTime() {
        return placedTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    UUID getId() {
        return id;
    }
     LocalTime getAcceptedTime() {
         return placedTime;
     }

     public void setDeliveryTime(LocalTime deliveredTime) {
        this.deliveryTime = deliveredTime;
     }
    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public void pay() {
        status = OrderStatus.PAID;
    }

    public void accept() {
        status = OrderStatus.ACCEPTED;
        acceptedTime = LocalTime.now();
    }
    public String getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
    }
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void pickUp() {
        status = OrderStatus.PICKED_UP;
    }

    public void deliver() {
        status = OrderStatus.DELIVERED;
    }

    public void validate() {
        status = OrderStatus.READY;
    }

    public void reject() {
        status = OrderStatus.REJECTED;
    }

    public boolean isRejected() {
        return status == OrderStatus.CANCELLED;
    }

    public boolean isAccepted() {
        return status == OrderStatus.ACCEPTED;
    }

    public boolean isPickedUp() {
        return status == OrderStatus.PICKED_UP;
    }
    public boolean isDelivered() {
        return status == OrderStatus.DELIVERED;
    }
    public boolean isPlaced() {
        return status == OrderStatus.PLACED;
    }
    public boolean isPaid() {
        return status == OrderStatus.PAID;
    }
    public boolean isReady() {
        return status == OrderStatus.READY;
    }

    public boolean isClosed() {
        return status == OrderStatus.CANCELLED || status == OrderStatus.DELIVERED;
    }
    public void setUserUnableToConfirm() {
        this.userConfirmationPossible = false;
    }
    public boolean canUserConfirmReceipt() {
        return userConfirmationPossible;
    }
    public void confirmReceipt() {
        if (this.canUserConfirmReceipt() && this.status == OrderStatus.PICKED_UP) {
            this.status = OrderStatus.DELIVERED;
            this.deliveryTime = LocalTime.now();
        }
    }
    public void deliverWithoutConfirmation() {
        if (!this.canUserConfirmReceipt() && this.status == OrderStatus.PICKED_UP) {
            this.status = OrderStatus.DELIVERED;
            this.deliveryTime = LocalTime.now();
            this.requiresSignatureAndVerification = true;
        }
    }

    public boolean requiresSignatureAndVerification() {
        return !this.canUserConfirmReceipt() && this.status == OrderStatus.DELIVERED;
    }


    public boolean needsSignatureAndVerification() {
        return requiresSignatureAndVerification;
    }





}
