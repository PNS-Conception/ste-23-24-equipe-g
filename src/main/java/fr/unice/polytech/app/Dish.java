package fr.unice.polytech.app;

import java.util.List;

public class Dish {
    private String name;
    private double price;
    private List<String> ingredients;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Dish(String name, List<String> ingredients, double price) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public List<String> getIngredients() {
        return ingredients;
    }

    // If ingredients are stored as a String, you might have a method like this:
    public String getIngredientString() {
        return String.join(", ", ingredients);
    }
}
