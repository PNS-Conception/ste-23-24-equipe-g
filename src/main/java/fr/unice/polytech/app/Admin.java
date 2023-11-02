package fr.unice.polytech.app;

import org.mockito.internal.matchers.Null;

import java.util.ArrayList;
import java.util.List;

public class Admin {

    List<Restaurant> restaurants;
    List<DeliveryPerson> deliveryPersons;


    public Admin(){
        restaurants= new ArrayList<>();
        deliveryPersons= new ArrayList<>();
    }
    public void addRestaurant(String name, String address, String ownerEmail) {
        Restaurant restaurant = new Restaurant(name);
        restaurant.setAddress(address);
        restaurants.add(restaurant);
        restaurant.setOwner(new RestaurantManager(null,null,null,ownerEmail));
    }

    public void addDeliveryPerson(String name, String ownerEmail) {
        DeliveryPerson deliveryPerson = new DeliveryPerson(null,null,null,ownerEmail);
        deliveryPersons.add(deliveryPerson);

    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<DeliveryPerson> getDeliveryPersons() {
        return deliveryPersons;
    }

}
