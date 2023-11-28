package fr.unice.polytech.app;

import java.time.LocalTime;

public class RestaurantManager extends CampusUser{


    Restaurant restaurant;

    public RestaurantManager(String name, String password, String email) {
        super(name, password, email);
        setType(UserType.MANAGER);
    }

    public void setMenu(Menu menu) {
        restaurant.setMenu(menu,this);
    }

    public void addShift(LocalTime openingTime, LocalTime closingTime, Day day) {
            restaurant.addShift(openingTime,closingTime,day,this);
    }

    public void removeShift(Shift shift) {
            restaurant.removeShift(shift);
    }

    public void addDish(Dish dish) {
            restaurant.addDish(dish,this);
    }

    public void removeDish(Dish dish) {
            restaurant.removeDish(dish,this);
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}
