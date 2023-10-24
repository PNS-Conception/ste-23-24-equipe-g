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

    public void pay() {
        status = OrderStatus.Paid;
    }

    public void accept() {
        status = OrderStatus.Accepted;
        acceptedTime = LocalTime.now();
    }

    public void pickUp() {
        status = OrderStatus.PickedUp;
    }

    public void deliver() {
        status = OrderStatus.Delivered;
    }

    public void validate() {
        status = OrderStatus.Ready;
    }

    public void reject() {
        status = OrderStatus.Cancelled;
    }

    public boolean isRejected() {
        return status == OrderStatus.Cancelled;
    }

    public boolean isAccepted() {
        return status == OrderStatus.Accepted;
    }

    public boolean isPickedUp() {
        return status == OrderStatus.PickedUp;
    }
    public boolean isDelivered() {
        return status == OrderStatus.Delivered;
    }
    public boolean isPlaced() {
        return status == OrderStatus.Placed;
    }
    public boolean isPaid() {
        return status == OrderStatus.Paid;
    }
    public boolean isReady() {
        return status == OrderStatus.Ready;
    }

    public boolean isClosed() {
        return status == OrderStatus.Cancelled || status == OrderStatus.Delivered;
    }
}
