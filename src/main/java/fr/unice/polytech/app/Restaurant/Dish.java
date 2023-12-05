package fr.unice.polytech.app.Restaurant;

import java.util.List;

/**
 * Represents a dish in a restaurant menu.
 */
public class Dish {
    private String name;
    private double price;
    private double notRegularPrice;
    private List<String> ingredients;

    /**
     * Constructs a dish with the specified name, price, and not regular price.
     * 
     * @param name            the name of the dish
     * @param price           the regular price of the dish
     * @param notRegularPrice the not regular price of the dish
     */
    public Dish(String name, double price, double notRegularPrice) {
        this.name = name;
        this.price = price;
        this.notRegularPrice = notRegularPrice;
    }

    /**
     * Constructs a dish with the specified name, ingredients, and price.
     * 
     * @param name       the name of the dish
     * @param ingredients the list of ingredients in the dish
     * @param price      the price of the dish
     */
    public Dish(String name, List<String> ingredients, double price) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    /**
     * Constructs a dish with the specified name and price.
     * 
     * @param name  the name of the dish
     * @param price the price of the dish
     */
    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
        this.notRegularPrice = price;
    }

    /**
     * Returns the name of the dish.
     * 
     * @return the name of the dish
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the regular price of the dish.
     * 
     * @return the regular price of the dish
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the not regular price of the dish.
     * 
     * @return the not regular price of the dish
     */
    public double getNotRegularPrice() {
        return notRegularPrice;
    }

    /**
     * Sets the regular price and not regular price of the dish.
     * 
     * @param price           the regular price of the dish
     * @param notRegularPrice the not regular price of the dish
     */
    public void setPrices(double price, double notRegularPrice) {
        this.price = price;
        this.notRegularPrice = notRegularPrice;
    }

    /**
     * Returns the list of ingredients in the dish.
     * 
     * @return the list of ingredients in the dish
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /**
     * Returns a string representation of the ingredients in the dish.
     * 
     * @return a string representation of the ingredients in the dish
     */
    public String getIngredientString() {
        return String.join(", ", ingredients);
    }
}
