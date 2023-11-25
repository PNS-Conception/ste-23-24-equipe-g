package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SingleOrder implements Order  {
    private boolean requiresSignatureAndVerification;
    private List<Item> items;
    private String clientAddress;
    private LocalTime placedTime;

    CampusUser user;
    private UUID id;
    private OrderStatus status;
    private LocalTime acceptedTime;
    private LocalTime deliveryTime;
    private double price;
    private boolean userConfirmationPossible = true;
    private String routeDetails;
    private LocalTime pickupTime;
    private Restaurant restaurant;
    private String deliveryLocation;


    public SingleOrder(List<Item> items, CampusUser user, Restaurant restaurant) {
        this.id = UUID.randomUUID();
        this.restaurant = restaurant;
        this.items = items;
        this.user = user;
        this.status = OrderStatus.PLACED;
        price = calculatePrice();
        this.requiresSignatureAndVerification = false;
    }
    public SingleOrder(List<Item> items) {
        this.id = UUID.randomUUID();
        this.items = items;
        //price= calculatePrice();
        this.status = OrderStatus.PLACED; // La commande est initialisée avec le statut PLACED
        this.requiresSignatureAndVerification = false; // Initialement, pas besoin de signature ni de vérification
    }

    public SingleOrder(){}


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

    @Override
    public Restaurant getRestaurant() {
        return restaurant;
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

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant);
        return restaurants;
    }


    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
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
    public boolean isAccepted() {
        return status == OrderStatus.ACCEPTED;
    }
    public boolean isPlaced() {
        return status == OrderStatus.PLACED;
    }
    public boolean isClosed() {
        return status == OrderStatus.CANCELLED || status == OrderStatus.DELIVERED;
    }
    public void reject() {
        status = OrderStatus.REJECTED;
    }

    public CampusUser getClient() {
        return user;
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

    public double calculatePrice() {
        double price = 0;

        for (Item item : items) {
            double itemPrice = (user.getType() == UserType.CLIENT) ? item.getPrice() : item.getNotRegularPrice();

            if (restaurant.isEligibleForDiscountByNbOfDishes(user)) {
                itemPrice *= 1 - ((double) restaurant.getPercentageDiscountByNbOfDishes() / 100);
            }

            if (restaurant.isEligibleForDiscountByNbOfOrders(user)) {
                itemPrice *= 1 - (restaurant.getPercentageDiscountByNbOfOrders() / 100);
            }

            price += itemPrice;
        }

        return price;
    }

    public void getRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }




    public int getNumberOfDishes(){
        int numberOfDishes = 0;
        for(Item item : items){
            numberOfDishes += item.getQuantity();
        }
        return numberOfDishes;
    }


    public void getPaid() {
        restaurant.addNbDishesToUser(user,this);
        restaurant.addNbOrderToUser(user);
        if (!user.makePayment(this,user)) {
            restaurant.removeNbDishesToUser(user,this);
            restaurant.removeNbOrderToUser(user);
        }
        if (restaurant.getExtensionDiscount(user)!=null){
            restaurant.getExtensionDiscount(user).setIsDiscountValid(true);
            restaurant.getExtensionDiscount(user).setNumberOfOrders(0);
        }
    }
}
