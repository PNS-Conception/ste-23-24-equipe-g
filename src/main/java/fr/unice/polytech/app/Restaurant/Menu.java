package fr.unice.polytech.app.Restaurant;


import fr.unice.polytech.app.Users.RestaurantManager;
import fr.unice.polytech.app.Users.UserType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a menu in a restaurant.
 */
public class Menu {

    private List<Dish> menu;
    private boolean isAfterworkMenu;

    /**
     * Constructs an empty menu.
     */
    public Menu() {
        menu = new ArrayList<>();
    }

    /**
     * Constructs a menu with the specified list of dishes.
     *
     * @param asList the list of dishes to initialize the menu with
     */
    public Menu(List<Dish> asList) {
        this.menu = asList;
    }

    /**
     * Adds a dish to the menu.
     *
     * @param dish    the dish to add
     * @param manager the restaurant manager
     * @return true if the dish was successfully added, false otherwise
     */
    public boolean addDish(Dish dish, RestaurantManager manager) {
        if (manager.getType() == UserType.MANAGER) {
            menu.add(dish);
            return true;
        }
        return false;
    }

    /**
     * Removes a dish from the menu.
     *
     * @param dish    the dish to remove
     * @param manager the restaurant manager
     * @return true if the dish was successfully removed, false otherwise
     */
    public boolean removeDish(Dish dish, RestaurantManager manager) {
        if (manager.getType() == UserType.MANAGER) {
            menu.remove(dish);
            return true;
        }
        return false;
    }

    /**
     * Returns the list of dishes in the menu.
     *
     * @return the list of dishes
     */
    public List<Dish> getMenu() {
        return menu;
    }

    /**
     * Sets the menu to the specified list of dishes.
     *
     * @param menu    the list of dishes to set as the menu
     * @param manager the restaurant manager
     * @return true if the menu was successfully set, false otherwise
     */
    public boolean setMenu(List<Dish> menu, RestaurantManager manager) {
        if (manager.getType() == UserType.MANAGER) {
            this.menu = menu;
            return true;
        }
        return false;
    }

    /**
     * Checks if the menu contains a dish with the specified name.
     *
     * @param dishName the name of the dish to check
     * @return true if the menu contains the dish, false otherwise
     */
    public boolean contains(String dishName) {
        for (Dish dish : menu) {
            if (dish.getName().equals(dishName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the menu is an afterwork menu.
     *
     * @return true if the menu is an afterwork menu, false otherwise
     */
    public boolean isAfterworkMenu() {
        return isAfterworkMenu;
    }

    /**
     * Sets whether the menu is an afterwork menu or not.
     *
     * @param isAfterworkMenu true if the menu is an afterwork menu, false otherwise
     */
    public void setAfterworkMenu(boolean isAfterworkMenu) {
        this.isAfterworkMenu = isAfterworkMenu;
    }
}
