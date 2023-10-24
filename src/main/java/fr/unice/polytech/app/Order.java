package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


public class Order {
    private List<Item> items;
    private String clientAddress;


    private LocalTime placedTime;

    private UUID id;
    private OrderStatus status;
    private LocalTime acceptedTime;

    private LocalTime deliveryTime;

    public Order(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
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
}