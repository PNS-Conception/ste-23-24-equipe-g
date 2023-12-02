package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.State.IState;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface Order {


    String getRouteDetails();


    LocalTime getPickupTime();

    List<Restaurant> getRestaurants();

    String getDeliveryLocation();

    void setStatus(IState orderStatus);

    Restaurant getRestaurant();



    void setDeliveryLocation(String deliveryLocation);

    IState getStatus();

    void pay() throws Exception;
    void accept() throws Exception;
    void reject() throws Exception;
    void ready() throws Exception;
    void assign() throws Exception;
    void deliver() throws Exception;
    void cancel() throws Exception;
    void pickUp() throws Exception;
    void placeOrder() throws Exception;


    LocalTime getAcceptedTime();

    LocalTime getDeliveryTime();

    double getPrice();

    UUID getId();

    void setRestaurant(Restaurant restaurant);

    //void getPaid();


}
