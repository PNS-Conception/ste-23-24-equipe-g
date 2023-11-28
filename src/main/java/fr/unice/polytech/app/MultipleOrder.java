package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MultipleOrder implements Order{

    private List<Order> subOrders;
    private String routeDetails;
    private LocalTime pickupTime;
    private String deliveryLocation;
    private String deliveryAddress;
    private OrderStatus status;
    private CampusUser owner;
    private List<Restaurant> restaurants;


    public MultipleOrder(CampusUser owner) {

        this.owner = owner;
        this.status = OrderStatus.PLACED;
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
        return restaurants;
    }
    @Override
    public String getDeliveryLocation() {
        return deliveryLocation;
    }
    @Override
    public void setStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    @Override
    public Restaurant getRestaurant() {
        return restaurants.get(0);
    }

    @Override
    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    @Override
    public OrderStatus getStatus() {
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
            if (order.getStatus() != OrderStatus.PAID) {
                return false;
            }
        }
        return true;
    }

    public boolean isOrderCancelled() {
        for (Order order : subOrders) {
            if (order.getStatus() == OrderStatus.CANCELLED) {
                return false;
            }
        }
        return true;
    }


    public void delete() {
        this.status = OrderStatus.CANCELLED;
    }

    public boolean isOrderReady() {
        for (Order order : subOrders) {
            if (order.getStatus() != OrderStatus.READY) {
                return false;
            }
        }
        return true;
    }

    public void validateForDelivery() {
        this.status = OrderStatus.PICKED_UP;
    }
}
