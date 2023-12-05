package fr.unice.polytech.app.Restaurant;

import fr.unice.polytech.app.Restaurant.Restaurant;

import java.util.List;

/**
 * The RestaurantRepository interface represents a repository for managing restaurants.
 * It provides methods for accessing and manipulating restaurant data.
 */
public interface RestaurantRepository {
    /**
     * Retrieves a list of all restaurants.
     *
     * @return a list of Restaurant objects representing all the restaurants.
     */
    List<Restaurant> findAll();
    // Other data access methods like findById, save, delete, etc., can be declared here.
}

