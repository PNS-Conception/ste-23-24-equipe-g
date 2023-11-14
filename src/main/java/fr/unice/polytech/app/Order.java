package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


public class Order {
    private List<Item> items;
    private String clientAddress;
    private LocalTime placedTime;

    CampusUser user;
    private UUID id;
    private OrderStatus status;
    private LocalTime acceptedTime;
    private LocalTime deliveryTime;
    private double price;

    public Order(List<Item> items, CampusUser user) {
        this.items = items;
        for (Item item : items) {
            price += item.getPrice();
        }
        this.user = user;
    }
    public Order(List<Item> items) {
        this.items = items;
        for (Item item : items) {
            price += item.getPrice();
        }
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

    public void accept() {
        status = OrderStatus.ACCEPTED;
        acceptedTime = LocalTime.now();
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
}
