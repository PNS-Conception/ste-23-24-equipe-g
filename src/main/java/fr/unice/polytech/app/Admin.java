package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin {

    List<Restaurant> restaurants;
    List<DeliveryPerson> deliveryPersons;

    List<CampusUser> campusUsers;




    public Admin(){
        restaurants= new ArrayList<>();
        deliveryPersons= new ArrayList<>();
        campusUsers= new ArrayList<>();
    }
    public static void addRestaurant(String name, String address, RestaurantManager owner) {
        Restaurant restaurant = new Restaurant(name,owner,address );
        restaurants.add(restaurant);
    }

    public void addDeliveryPerson(String name, String ownerEmail,String phoneNumber) {
        DeliveryPerson deliveryPerson = new DeliveryPerson( name, ownerEmail,phoneNumber);
        deliveryPersons.add(deliveryPerson);
    }

    public void addCampusUser(String name, String password, String email) {
        CampusUser campusUser = new CampusUser( name, password, email);
        campusUsers.add(campusUser);
    }

    public List<Restaurant> getRestaurants() {
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
