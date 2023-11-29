package fr.unice.polytech.app;

import fr.unice.polytech.app.State.*;

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
    //private OrderStatus status;

    private IState status ;
    private LocalTime acceptedTime;
    private LocalTime deliveryTime;
    private double price;
    private boolean userConfirmationPossible = true;
    private String routeDetails;
    private LocalTime pickupTime;
    private Restaurant restaurant;
    private String deliveryLocation;


    public SingleOrder(List<Item> items, CampusUser user, Restaurant restaurant) throws Exception {
        this.id = UUID.randomUUID();
        this.restaurant = restaurant;
        this.items = new ArrayList<>(items);
        this.user = user;
        placeOrder();
        price = calculatePrice();
        this.requiresSignatureAndVerification = false;
    }
    public SingleOrder(List<Item> items) throws Exception {
        this.id = UUID.randomUUID();
        this.items = items;
        placeOrder();
        price= calculatePrice();
        this.requiresSignatureAndVerification = false; // Initialement, pas besoin de signature ni de vérification
    }

    public SingleOrder(){}


    public List<Item> getItems() {
        return items;
    }

    public double getPrice() {
        setPrice(calculatePrice());
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

    public IState getStatus() {
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

    public void pay() throws Exception {
        status.pay(this);
    }

    public void accept() throws Exception {
        status.acceptOrder(this);
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

    @Override
    public void setStatus(IState orderStatus) {
        this.status = orderStatus;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void pickUp() throws Exception {
        status.validate(this);
    }
    public void deliver() throws Exception {
        status.delivery(this);
    }
    public void cancel() throws Exception {
        status.cancelOrder(this);
    }
    public void ready() throws Exception {
        status.readyOrder(this);
    }

    public void assign() throws Exception {
        status.assign(this);
    }

    /*public boolean isAccepted() {
        return status == OrderStatus.ACCEPTED;
    }*/
    public boolean isPlaced() {
        return status instanceof PlacedIState;
    }
    public boolean isClosed() {
        return (status instanceof CancelledIState || status instanceof DelivredIState || status instanceof RejectedIState);
    }
    public void reject() throws Exception {
        status.rejectOrder(this);
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
    public void confirmReceipt() throws Exception {
        if (this.canUserConfirmReceipt() && this.status instanceof ValidatedIState) {
            deliver();
            this.deliveryTime = LocalTime.now();
        }
    }
    public void deliverWithoutConfirmation() throws Exception {
        if (!this.canUserConfirmReceipt() && this.status instanceof ValidatedIState) {
            deliver();
            this.deliveryTime = LocalTime.now();
            this.requiresSignatureAndVerification = true;
        }
    }

    public boolean requiresSignatureAndVerification() {
        return !(this.canUserConfirmReceipt() && this.status instanceof ValidatedIState);
    }


    public boolean needsSignatureAndVerification() {
        return requiresSignatureAndVerification;
    }

    public double calculatePrice() {
        double price = 0;

        for (Item item : items) {

            double itemPrice = (user.getType() == UserType.CLIENT) ? item.getPrice() : item.getNotRegularPrice();

            if (restaurant.isEligibleForDiscountByNbOfDishes(user)) {
                System.out.println("Discount by nb of dishes");
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


    public void getPaid() throws Exception {
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

    public void placeOrder() throws Exception {
        status= new PlacedIState();
    }
}
