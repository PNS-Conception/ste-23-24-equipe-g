package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.State.PlacedIState;
import fr.unice.polytech.app.State.ReadyIState;
import fr.unice.polytech.app.Restaurant.Item;

import java.util.List;

/**
 * Represents a formula in the ordering system.
 */
public class formula {
    private String name;
    private double prix;
    private List<Item> items;

    private IState status;

    /**
     * Constructs a new formula with the specified name, price, and items.
     *
     * @param name  the name of the formula
     * @param prix  the price of the formula
     * @param items the items included in the formula
     * @throws Exception if an error occurs during initialization
     */
    public formula(String name, double prix, List<Item> items) throws Exception {
        this.name = name;
        this.prix = prix;
        this.items = items;
        this.init();
    }

    /**
     * Initializes the formula's status to "Placed".
     *
     * @throws Exception if an error occurs during initialization
     */
    public void init() throws Exception {
        status = new PlacedIState();
    }

    /**
     * Sets the formula's status to "Ready".
     *
     * @throws Exception if an error occurs while setting the status
     */
    public void ready() throws Exception {
        status = new ReadyIState();
    }

    /**
     * Returns the price of the formula.
     *
     * @return the price of the formula
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Returns the status of the formula.
     *
     * @return the status of the formula
     */
    public IState getStatus() {
        return status;
    }

    /**
     * Returns the name of the formula.
     *
     * @return the name of the formula
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the formula.
     *
     * @param name the name of the formula
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the formula.
     *
     * @param prix the price of the formula
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Returns the items included in the formula.
     *
     * @return the items included in the formula
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets the items included in the formula.
     *
     * @param items the items included in the formula
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Sets the status of the formula.
     *
     * @param status the status of the formula
     */
    public void setStatus(IState status) {
        this.status = status;
    }

    // Getters and setters for prix and items
}
