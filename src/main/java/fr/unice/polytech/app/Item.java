package fr.unice.polytech.app;
public class Item {
    private Dish dish;
    private int quantity;
    private double price;

    private double notRegularPrice;

    public Item(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.price = calculatePrice();
        this.notRegularPrice = calculateNotRegularPrice();
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

    public double getNotRegularPrice() {
        return notRegularPrice;
    }

    public double calculatePrice() {
        return dish.getPrice() * quantity;
    }

    public double calculateNotRegularPrice() {
        return dish.getNotRegularPrice() * quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
