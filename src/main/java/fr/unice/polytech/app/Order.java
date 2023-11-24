package fr.unice.polytech.app;

import java.time.LocalTime;

public interface Order {


    String getRouteDetails();


    LocalTime getPickupTime();

    Restaurant getRestaurant();

    String getDeliveryLocation();

    void setStatus(OrderStatus orderStatus);
}
