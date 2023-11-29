package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Dish> menu;
    private boolean isAfterworkMenu;

    public Menu() {
        menu = new ArrayList<>();
    }

    public Menu(List<Dish> asList) {
        this.menu = asList;
    }

    public boolean addDish(Dish dish,RestaurantManager manager) {
        if (manager.getType() == UserType.MANAGER) {
            menu.add(dish);
            return true;
        }
        return false;
    }

    public boolean removeDish(Dish dish,RestaurantManager manager) {
        if (manager.getType() == UserType.MANAGER){
            menu.remove(dish);
            return true;

        }
        return false;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public boolean setMenu(List<Dish> menu,RestaurantManager manager) {
        if (manager.getType() == UserType.MANAGER) {
            this.menu = menu;
            return true;
        }
        return false;
    }

    public boolean contains(String dishName) {
        for (Dish dish : menu) {
            if (dish.getName().equals(dishName)) {
                return true;
            }
        }
        return false;
    }
    public boolean isAfterworkMenu() {
        return isAfterworkMenu;
    }

    public void setAfterworkMenu(boolean isAfterworkMenu) {
        this.isAfterworkMenu = isAfterworkMenu;
    }
}
