package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.User.CampusUser;



import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Restaurant.Item;
import fr.unice.polytech.app.State.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SingleOrder implements Order,DecoratorOrder {
    private boolean requiresSignatureAndVerification;
    private Cart cart;
    private String clientAddress;
    private LocalTime placedTime;
    private CampusUser user;
    private UUID id;
    private IState status ;
    private LocalTime acceptedTime;
    private LocalTime deliveryTime;
    private double price;
    private boolean userConfirmationPossible = true;
    private String routeDetails;
    private LocalTime pickupTime;
    private Restaurant restaurant;
    private String deliveryLocation;


    public SingleOrder(CampusUser user, Restaurant restaurant) throws Exception {
        this.id = UUID.randomUUID();
        this.restaurant = restaurant;
        this.cart = new Cart(new ArrayList<>());
        for (Item item: user.getCart().getItems()) {
            cart.add(item);
        }
        cart.setSingleOrder(this);
        this.user = user;
        placeOrder();
        price = cart.calculatePrice();
        this.requiresSignatureAndVerification = false;
    }

    public SingleOrder(Cart cart,Restaurant restaurant) throws Exception {
        this.id = UUID.randomUUID();
        this.cart = new Cart(user.getCart().getItems());
        this.restaurant = restaurant;
        placeOrder();
        price = cart.calculatePrice();
        this.requiresSignatureAndVerification = false;
    }

    public SingleOrder() throws Exception {
        this.id = UUID.randomUUID();
        //this.cart = user.getCart();
        placeOrder();
        //price= cart.calculatePrice();
        this.requiresSignatureAndVerification = false; // Initialement, pas besoin de signature ni de vérification
    }

    public Cart getCart() {
        return cart;
    }

    public double getPrice() {
        setPrice(cart.calculatePrice());
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

    public UUID getId() {
        return id;
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public CampusUser getOwner() {
        return user;
    }

    @Override
    public void setOwner(CampusUser owner) {
        this.user = owner;
    }

    @Override
    public LocalTime getAcceptedTime() {
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


    public void getRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }






    public void getPaid() throws Exception {
        restaurant.getDiscountSystem().addNbDishesToUser(user,this);
        restaurant.getDiscountSystem().addNbOrderToUser(user);
        if (!user.getPaiementSystem().makePayment(this)) {
            restaurant.getDiscountSystem().removeNbDishesToUser(user,this);
            restaurant.getDiscountSystem().removeNbOrderToUser(user);
        }
        if (restaurant.getDiscountSystem().getExtensionDiscount(user)!=null){
            restaurant.getDiscountSystem().getExtensionDiscount(user).setIsDiscountValid(true);
            restaurant.getDiscountSystem().getExtensionDiscount(user).setNumberOfOrders(0);
        }
    }

    public void getPaidMock() throws Exception {
        restaurant.getDiscountSystem().addNbDishesToUser(user,this);
        restaurant.getDiscountSystem().addNbOrderToUser(user);
        if (!user.getPaiementSystem().makePaymentmock(this)) {
            restaurant.getDiscountSystem().removeNbDishesToUser(user,this);
            restaurant.getDiscountSystem().removeNbOrderToUser(user);
        }
        if (restaurant.getDiscountSystem().getExtensionDiscount(user)!=null){
            restaurant.getDiscountSystem().getExtensionDiscount(user).setIsDiscountValid(true);
            restaurant.getDiscountSystem().getExtensionDiscount(user).setNumberOfOrders(0);
        }
    }

    public void placeOrder() throws Exception {
        status= new PlacedIState();
    }



}
