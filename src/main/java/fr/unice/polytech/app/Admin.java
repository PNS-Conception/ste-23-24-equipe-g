package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Admin {

    List<Restaurant> restaurants;
    List<DeliveryPerson> deliveryPersons;


    public Admin(){
        restaurants= new ArrayList<>();
        deliveryPersons= new ArrayList<>();
    }
    public void addRestaurant(String name, String address, String ownerEmail) {
        Restaurant restaurant = new Restaurant(name, new Menu(Arrays.asList(new Dish("Margherita", Arrays.asList("Tomato", "Mozzarella", "Basil"), 7.99), new Dish("Pepperoni", Arrays.asList("Tomato", "Mozzarella", "Pepperoni"), 8.99))));
        restaurant.setAddress(address);
        restaurants.add(restaurant);
        restaurant.setOwner(new RestaurantManager(null,null,ownerEmail));
    }

    public void addDeliveryPerson(String name, String ownerEmail) {
        DeliveryPerson deliveryPerson = new DeliveryPerson(UUID.randomUUID().toString(), name, ownerEmail);
        deliveryPersons.add(deliveryPerson);
    }


    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<DeliveryPerson> getDeliveryPersons() {
        return deliveryPersons;
    }

}
