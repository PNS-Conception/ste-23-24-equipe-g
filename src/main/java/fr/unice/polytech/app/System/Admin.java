package fr.unice.polytech.app.System;

import fr.unice.polytech.app.Delivery.DeliveryPerson;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Restaurant.RestaurantManager;
import fr.unice.polytech.app.Users.CampusUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Admin class represents the administrator of the system.
 * It manages restaurants, delivery persons, and campus users.
 */
public class Admin {

    static List<Restaurant> restaurants;
    static List<DeliveryPerson> deliveryPersons;

    List<CampusUser> campusUsers;



    /**
     * Constructs a new Admin object.
     * Initializes the lists of restaurants, delivery persons, and campus users.
     */
    public Admin(){
        restaurants= new ArrayList<>();
        deliveryPersons= new ArrayList<>();
        campusUsers= new ArrayList<>();
    }

    /**
     * Adds a new restaurant to the system.
     * @param name the name of the restaurant
     * @param address the address of the restaurant
     * @param owner the owner of the restaurant
     */
    public void addRestaurant(String name, String address, RestaurantManager owner) {
        Restaurant restaurant = new Restaurant(name,owner,address );
        restaurants.add(restaurant);
    }

    /**
     * Removes a restaurant from the system.
     * @param restaurant the name of the restaurant to be removed
     */
    public void removeRestaurant(String restaurant) {
        restaurants.remove(getRestaurantByName(restaurant));
    }

    /**
     * Retrieves a restaurant by its name.
     * @param name the name of the restaurant
     * @return the restaurant object if found, null otherwise
     */
    public static Restaurant getRestaurantByName(String name) {
        for (Restaurant restaurant : restaurants) {
            if (Objects.equals(restaurant.getName(), name)) {
                return restaurant;
            }
        }
        return null;
    }

    /**
     * Adds a new delivery person to the system.
     * @param name the name of the delivery person
     * @param ownerEmail the email of the delivery person's owner
     * @param phoneNumber the phone number of the delivery person
     */
    public void addDeliveryPerson(String name, String ownerEmail,String phoneNumber) {
        DeliveryPerson deliveryPerson = new DeliveryPerson( name, ownerEmail,phoneNumber);
        deliveryPersons.add(deliveryPerson);
    }

    /**
     * Removes a delivery person from the system.
     * @param Email the email of the delivery person to be removed
     */
    public void removeDeliveryPerson(String Email) {
        deliveryPersons.remove(getDeliveryPersonByEmail(Email));
    }

    /**
     * Retrieves a delivery person by their email.
     * @param email the email of the delivery person
     * @return the delivery person object if found, null otherwise
     */
    public DeliveryPerson getDeliveryPersonByEmail(String email) {
        for (DeliveryPerson deliveryPerson : deliveryPersons) {
            if (Objects.equals(deliveryPerson.getEmail(), email)) {
                return deliveryPerson;
            }
        }
        return null;
    }

    /**
     * Adds a new campus user to the system.
     * @param name the name of the campus user
     * @param password the password of the campus user
     * @param email the email of the campus user
     */
    public void addCampusUser(String name, String password, String email) {
        CampusUser campusUser = new CampusUser( name, password, email);
        campusUsers.add(campusUser);
    }

    /**
     * Retrieves the list of restaurants in the system.
     * @return the list of restaurants
     */
    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * Retrieves the list of delivery persons in the system.
     * @return the list of delivery persons
     */
    public static List<DeliveryPerson> getDeliveryPersons() {
        return deliveryPersons;
    }

    /**
     * Retrieves the list of campus users in the system.
     * @return the list of campus users
     */
    public List<CampusUser> getCampusUsers() {
        return campusUsers;
    }

    /**
     * Retrieves a restaurant manager by their email.
     * @param email the email of the restaurant manager
     * @return the restaurant manager object if found, null otherwise
     */
    public RestaurantManager getUserByEmail(String email) {
        for (CampusUser user : campusUsers) {
            if (Objects.equals(user.getEmail(), email)) {
                return new RestaurantManager(user.getName(),user.getPassword(),user.getEmail());
            }
        }
        return null;
    }
}
