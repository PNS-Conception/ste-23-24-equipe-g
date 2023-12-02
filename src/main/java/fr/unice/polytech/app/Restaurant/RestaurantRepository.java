package fr.unice.polytech.app.Restaurant;

import fr.unice.polytech.app.Restaurant.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> findAll();
    // Other data access methods like findById, save, delete, etc., can be declared here.
}

