package fr.unice.polytech.app.System;

import fr.unice.polytech.app.Delivery.DeliveryPerson;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Restaurant.RestaurantManager;
import fr.unice.polytech.app.Users.CampusUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin {

    static List<Restaurant> restaurants;
    List<DeliveryPerson> deliveryPersons;

    List<CampusUser> campusUsers;




    public Admin(){
        restaurants= new ArrayList<>();
        deliveryPersons= new ArrayList<>();
        campusUsers= new ArrayList<>();

    }
    public void addRestaurant(String name, String address, RestaurantManager owner) {
        Restaurant restaurant = new Restaurant(name,owner,address );
        restaurants.add(restaurant);
    }

    public void removeRestaurant(String restaurant) {
        restaurants.remove(getRestaurantByName(restaurant));
    }

    public static Restaurant getRestaurantByName(String name) {
        for (Restaurant restaurant : restaurants) {
            if (Objects.equals(restaurant.getName(), name)) {
                return restaurant;
            }
        }
        return null;
    }

    public void addDeliveryPerson(String name, String ownerEmail,String phoneNumber) {
        DeliveryPerson deliveryPerson = new DeliveryPerson( name, ownerEmail,phoneNumber);
        deliveryPersons.add(deliveryPerson);
    }

    public void removeDeliveryPerson(String Email) {

        deliveryPersons.remove(getDeliveryPersonByEmail(Email));
    }


    public DeliveryPerson getDeliveryPersonByEmail(String email) {
        for (DeliveryPerson deliveryPerson : deliveryPersons) {
            if (Objects.equals(deliveryPerson.getEmail(), email)) {
                return deliveryPerson;
            }
        }
        return null;
    }

    public void addCampusUser(String name, String password, String email) {
        CampusUser campusUser = new CampusUser( name, password, email);
        campusUsers.add(campusUser);
    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<DeliveryPerson> getDeliveryPersons() {
        return deliveryPersons;
    }

    public List<CampusUser> getCampusUsers() {
        return campusUsers;
    }

    public RestaurantManager getUserByEmail(String email) {
        for (CampusUser user : campusUsers) {

            if (Objects.equals(user.getEmail(), email)) {
                return new RestaurantManager(user.getName(),user.getPassword(),user.getEmail());
            }
        }
        return null;
    }







}
