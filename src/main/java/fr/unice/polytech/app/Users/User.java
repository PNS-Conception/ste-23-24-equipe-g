package fr.unice.polytech.app.Users;

import fr.unice.polytech.app.Admin;
import fr.unice.polytech.app.Restaurant.Menu;
import fr.unice.polytech.app.Restaurant.Restaurant;

import java.util.List;

public class User implements IUser{


    public List<Restaurant> getRestaurants(){
       return Admin.getRestaurants();
    }

    public Restaurant getRestaurantByName(String name){
        return Admin.getRestaurantByName(name);
    }

    public Menu getRestaurantMenu(Restaurant restaurant){
        return restaurant.getMenu();
    }
}
