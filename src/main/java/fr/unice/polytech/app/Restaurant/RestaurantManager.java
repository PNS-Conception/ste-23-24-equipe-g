package fr.unice.polytech.app.Restaurant;

import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.User.UserType;

import java.time.LocalTime;

/**
 * The RestaurantManager class represents a manager of a restaurant.
 * It extends the CampusUser class and provides methods to manage the restaurant's menu, shifts, and dishes.
 */
public class RestaurantManager extends CampusUser {

    Restaurant restaurant;

    /**
     * Constructs a new RestaurantManager object with the specified name, password, and email.
     * Sets the user type to UserType.MANAGER.
     *
     * @param name     the name of the restaurant manager
     * @param password the password of the restaurant manager
     * @param email    the email of the restaurant manager
     */
    public RestaurantManager(String name, String password, String email) {
        super(name, password, email);
        setType(UserType.MANAGER);
    }

    /**
     * Sets the menu of the restaurant.
     *
     * @param menu the menu to be set
     */
    public void setMenu(Menu menu) {
        restaurant.setMenu(menu, this);
    }

    /**
     * Adds a shift to the restaurant's schedule.
     *
     * @param openingTime the opening time of the shift
     * @param closingTime the closing time of the shift
     * @param day         the day of the shift
     */
    public void addShift(LocalTime openingTime, LocalTime closingTime, Day day) {
        restaurant.addShift(openingTime, closingTime, day, this);
    }

    /**
     * Removes a shift from the restaurant's schedule.
     *
     * @param shift the shift to be removed
     */
    public void removeShift(Day.Shift shift) {
        restaurant.removeShift(shift);
    }

    /**
     * Adds a dish to the restaurant's menu.
     *
     * @param dish the dish to be added
     */
    public void addDish(Dish dish) {
        restaurant.addDish(dish, this);
    }

    /**
     * Removes a dish from the restaurant's menu.
     *
     * @param dish the dish to be removed
     */
    public void removeDish(Dish dish) {
        restaurant.removeDish(dish, this);
    }

    /**
     * Sets the restaurant associated with the manager.
     *
     * @param restaurant the restaurant to be set
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * Returns the restaurant associated with the manager.
     *
     * @return the restaurant associated with the manager
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

}
