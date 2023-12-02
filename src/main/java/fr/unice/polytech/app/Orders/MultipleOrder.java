package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.Users.CampusUser;

import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.State.*;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MultipleOrder implements Order {

    private List<Order> subOrders;
    private String routeDetails;
    private LocalTime pickupTime;
    private String deliveryLocation;
    private String deliveryAddress;
    private IState status;
    private CampusUser owner;
    //private List<Restaurant> restaurants;


    public MultipleOrder(CampusUser owner) throws Exception {

        this.owner = owner;
        placeOrder();
        this.subOrders = new ArrayList<>();
    }

    @Override
    public String getRouteDetails() {
        return routeDetails;
    }
    @Override
    public LocalTime getPickupTime() {
        return pickupTime;
    }
    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        for (Order order : subOrders) {
            restaurants.add(order.getRestaurant());
        }
        return restaurants;
    }
    @Override
    public String getDeliveryLocation() {
        return deliveryLocation;
    }
    @Override
    public void setStatus(IState orderStatus) {
        this.status = orderStatus;
    }

    @Override
    public Restaurant getRestaurant() {
        return getRestaurants().get(0);
    }

    @Override
    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    @Override
    public IState getStatus() {
        return status;
    }

    @Override
    public LocalTime getAcceptedTime() {
        return getAcceptedTime();
    }

    @Override
    public LocalTime getDeliveryTime() {
        return getDeliveryTime();
    }

    @Override
    public double getPrice() {
        return getPrice();
    }

    @Override
    public UUID getId() {
        return getId();
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {

    }

    @Override
    public void pay() throws Exception {
        status.pay(this);
    }

    @Override
    public void accept() throws Exception {
        status.acceptOrder(this);
    }

    @Override
    public void reject() throws Exception {
        status.rejectOrder(this);
    }

    @Override
    public void ready() throws Exception {
        int count = 0;
        for (Order order : subOrders) {
            if ((order.getStatus() instanceof AcceptedIState)) {
                count++;
            }
        }
        if (count == subOrders.size()) {
            status.readyOrder(this);
        }
    }


    @Override
    public void assign() throws Exception {
        status.assign(this);
    }

    @Override
    public void deliver() throws Exception {
        status.delivery(this);
    }

    @Override
    public void cancel() throws Exception {
        status.cancelOrder(this);
    }

    @Override
    public void pickUp() throws Exception {
        status.validate(this);
    }

    @Override
    public void placeOrder() throws Exception {
        status=new PlacedIState();
    }

    public void addSubOrder(Order order){
        subOrders.add(order);
    }
    public CampusUser getOwner() {
        return owner;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void addOrder(Order order) {
        subOrders.add(order);
    }

    public List<Order> getSubOrders() {
        return subOrders;
    }


    public void clearSubOrders() {
        subOrders.clear();
    }
    /*public void (CampusUser owner) {
        this.owner = owner;
    }*/

    public boolean isOrdersPaid() {
        for (Order order : subOrders) {
            if (!(order.getStatus() instanceof PaidIState)) {
                return false;
            }
        }
        return true;
    }

    public boolean isOrderCancelled() {
        for (Order order : subOrders) {
            if (!(order.getStatus() instanceof CancelledIState)) {
                return false;
            }
        }
        return true;
    }


    public void delete() {
        owner = null;
    }

    public boolean isOrderReady() {
        for (Order order : subOrders) {
            if (!(order.getStatus() instanceof ReadyIState)) {
                return false;
            }
        }
        return true;
    }

    public boolean isOrderPickedUp() {
        for (Order order : subOrders) {
            if (!(order.getStatus() instanceof ValidatedIState)) {
                return false;
            }
        }
        return true;
    }

    public void validateForDelivery() throws Exception {
        pickUp();
    }
}
