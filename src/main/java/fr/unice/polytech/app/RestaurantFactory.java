package fr.unice.polytech.app;

public interface RestaurantFactory {
    Restaurant createRestaurant(String name, Menu menu, RestaurantType type);
}
