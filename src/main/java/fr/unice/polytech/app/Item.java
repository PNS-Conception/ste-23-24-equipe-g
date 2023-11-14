package fr.unice.polytech.app;
public class Item {
    private Dish dish;
    private int quantity;
    private double price;

    public Item(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.price = dish.getPrice() * quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
