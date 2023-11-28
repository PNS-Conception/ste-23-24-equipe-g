package fr.unice.polytech.app;

import org.mockito.internal.matchers.Or;

import java.time.LocalTime;
import java.util.List;

public interface Order {


    String getRouteDetails();


    LocalTime getPickupTime();

    List<Restaurant> getRestaurants();

    String getDeliveryLocation();

    void setStatus(OrderStatus orderStatus);

    Restaurant getRestaurant();

    void setDeliveryLocation(String deliveryLocation);

    OrderStatus getStatus();

    //void getPaid();


}
