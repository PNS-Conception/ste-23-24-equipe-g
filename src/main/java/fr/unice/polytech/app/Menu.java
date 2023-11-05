package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Dish> dishes; // Assumes that a Dish class exists with appropriate attributes.

    public Menu() {
        this.dishes = new ArrayList<>();
    }

    public Menu(List<Dish> asList) {
        this.dishes = asList;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public List<Dish> getDishes() {
        return new ArrayList<>(dishes); // Return a copy of the list to prevent external modification.
    }

    // If there's functionality to remove a dish
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }


    // Additional functionality can be added as needed
}
