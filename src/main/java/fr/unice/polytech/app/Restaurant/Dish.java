package fr.unice.polytech.app.Restaurant;

import java.util.List;

public class Dish {
    private String name;
    private double price;
    private double notRegularPrice;
    private List<String> ingredients;


    public Dish(String name, double price ,double notRegularPrice) {
        this.name = name;
        this.price = price;
        this.notRegularPrice = notRegularPrice;
    }

    public Dish(String name, List<String> ingredients ,double price) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }


    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
        this.notRegularPrice = price;
    }

    /*public Dish(String name, List<String> ingredients, double price) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }*/

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getNotRegularPrice() {
        return notRegularPrice;
    }

    public void setPrices(double price, double notRegularPrice){
        this.price = price;
        this.notRegularPrice = notRegularPrice;
    }
    public List<String> getIngredients() {
        return ingredients;
    }

    // If ingredients are stored as a String, you might have a method like this:
    public String getIngredientString() {
        return String.join(", ", ingredients);
    }
}
