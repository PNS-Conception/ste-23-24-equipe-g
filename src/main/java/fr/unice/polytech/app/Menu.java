package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Dish> menu;

    public Menu() {
        menu = new ArrayList<>();
    }

    public Menu(List<Dish> asList) {
        this.dishes = asList;
    }

    public boolean addDish(Dish dish,RestaurantManager manager) {
        if (manager.getType() == UserType.Manager) {
            menu.add(dish);
            return true;
        }
        return false;
    }

    public boolean removeDish(Dish dish,RestaurantManager manager) {
        if (manager.getType() == UserType.Manager){
            menu.remove(dish);
            return true;

        }
        return false;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public boolean setMenu(List<Dish> menu,RestaurantManager manager) {
        if (manager.getType() == UserType.Manager) {
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

}
