package fr.unice.polytech.app.User;

import fr.unice.polytech.app.System.Admin;
import fr.unice.polytech.app.Restaurant.Menu;
import fr.unice.polytech.app.Restaurant.Restaurant;

import java.util.List;

/**
 * Represents a user in the application.
 * Implements the IUser interface.
 */
public class User {

    /**
     * Retrieves the list of restaurants.
     * @return The list of restaurants.
     */
    public List<Restaurant> getRestaurants(){
       return Admin.getRestaurants();
    }

    /**
     * Retrieves a restaurant by its name.
     * @param name The name of the restaurant.
     * @return The restaurant with the specified name, or null if not found.
     */
    public Restaurant getRestaurantByName(String name){
        return Admin.getRestaurantByName(name);
    }

    /**
     * Retrieves the menu of a restaurant.
     * @param restaurant The restaurant.
     * @return The menu of the restaurant.
     */
    public Menu getRestaurantMenu(Restaurant restaurant){
        return restaurant.getMenu();
    }
}
