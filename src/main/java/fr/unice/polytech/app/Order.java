package fr.unice.polytech.app;

import org.mockito.internal.matchers.Or;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface Order {


    String getRouteDetails();


    LocalTime getPickupTime();

    List<Restaurant> getRestaurants();

    String getDeliveryLocation();

    void setStatus(OrderStatus orderStatus);

    Restaurant getRestaurant();

    void setDeliveryLocation(String deliveryLocation);

    OrderStatus getStatus();

    LocalTime getAcceptedTime();

    LocalTime getDeliveryTime();

    double getPrice();

    UUID getId();

    //void getPaid();


}
