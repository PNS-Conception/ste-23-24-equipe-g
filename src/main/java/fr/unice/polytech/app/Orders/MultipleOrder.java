package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.User.CampusUser;

import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.State.*;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MultipleOrder implements Order,DecoratorOrder {

    private List<Order> subOrders;
    DecoratorOrder order;
    UUID id;


    public MultipleOrder(CampusUser owner) throws Exception {
         id= UUID.randomUUID();
        this.order = new SingleOrder();
        order.setOwner(owner);
        placeOrder();
        this.subOrders = new ArrayList<>();
    }

    @Override
    public String getRouteDetails() {
        return order.getRouteDetails();
    }
    @Override
    public LocalTime getPickupTime() {
        return order.getPickupTime();
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
        return order.getDeliveryLocation();
    }
    @Override
    public void setStatus(IState orderStatus) {
        order.setStatus(orderStatus);
    }

    @Override
    public Restaurant getRestaurant() {
        return getRestaurants().get(0);
    }

    @Override
    public void setDeliveryLocation(String deliveryLocation) {
        order.setDeliveryLocation(deliveryLocation);
    }

    @Override
    public IState getStatus() {
        return order.getStatus();
    }

    @Override
    public LocalTime getAcceptedTime() {
        return getAcceptedTime();
    }

    @Override
    public LocalTime getDeliveryTime() {
        return order.getDeliveryTime();
    }

    @Override
    public double getPrice() {
        return getPrice();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {

    }

    @Override
    public void pay() throws Exception {
        order.pay();
    }

    @Override
    public void accept() throws Exception {
        order.accept();
    }

    @Override
    public void reject() throws Exception {
        order.reject();
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
            order.ready();
        }
    }


    @Override
    public void assign() throws Exception {
        order.assign();
    }

    @Override
    public void deliver() throws Exception {
        order.deliver();
    }

    @Override
    public void cancel() throws Exception {
        order.cancel();
    }

    @Override
    public void pickUp() throws Exception {
        order.pickUp();
    }

    @Override
    public void placeOrder() throws Exception {
        order.placeOrder();
    }

    public void addSubOrder(Order order){
        subOrders.add(order);
    }
    public CampusUser getOwner() {
        return order.getOwner();
    }

    @Override
    public void setOwner(CampusUser owner) {
        order.setOwner(null);
    }

    @Override
    public void setRouteDetails(String routeDetails) {
        order.setRouteDetails(routeDetails);
    }

    @Override
    public void setPickupTime(LocalTime pickupTime) {
        order.setPickupTime(pickupTime);
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
