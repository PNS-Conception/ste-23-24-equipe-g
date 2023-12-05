package fr.unice.polytech.app.Restaurant;

/**
 * Represents an item in a restaurant menu.
 */
public class Item {
    private Dish dish;
    private int quantity;
    private double price;
    private Boolean isForAfterWork;

    private double notRegularPrice;

    /**
     * Constructs an Item object with the specified dish and quantity.
     * The price and notRegularPrice are calculated based on the dish and quantity.
     * The isForAfterWork flag is set to false by default.
     *
     * @param dish     the dish associated with the item
     * @param quantity the quantity of the item
     */
    public Item(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.price = calculatePrice();
        this.notRegularPrice = calculateNotRegularPrice();
        this.isForAfterWork = false;
    }

    /**
     * Constructs an Item object with the specified dish, quantity, and isForAfterWork flag.
     * The price and notRegularPrice are calculated based on the dish and quantity.
     *
     * @param dish            the dish associated with the item
     * @param quantity        the quantity of the item
     * @param isForAfterWork  a flag indicating if the item is for after work
     */
    public Item(Dish dish, int quantity, Boolean isForAfterWork) {
        this.dish = dish;
        this.quantity = quantity;
        this.price = calculatePrice();
        this.notRegularPrice = calculateNotRegularPrice();
        this.isForAfterWork = isForAfterWork;
    }

    /**
     * Returns the dish associated with the item.
     *
     * @return the dish associated with the item
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * Returns the quantity of the item.
     *
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the price of the item.
     *
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the not regular price of the item.
     *
     * @return the not regular price of the item
     */
    public double getNotRegularPrice() {
        return notRegularPrice;
    }

    /**
     * Calculates and returns the price of the item based on the dish and quantity.
     *
     * @return the calculated price of the item
     */
    public double calculatePrice() {
        return dish.getPrice() * quantity;
    }

    /**
     * Calculates and returns the not regular price of the item based on the dish and quantity.
     *
     * @return the calculated not regular price of the item
     */
    public double calculateNotRegularPrice() {
        return dish.getNotRegularPrice() * quantity;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity the new quantity of the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a flag indicating if the item is for after work.
     *
     * @return true if the item is for after work, false otherwise
     */
    public Boolean getIsForAfterWork() {
        return isForAfterWork;
    }
}
