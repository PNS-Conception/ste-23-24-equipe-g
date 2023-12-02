package fr.unice.polytech.app.Restaurant;



public class Item {
    private Dish dish;
    private int quantity;
    private double price;
    private Boolean isForAfterWork;

    private double notRegularPrice;

    public Item(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.price = calculatePrice();
        this.notRegularPrice = calculateNotRegularPrice();
        this.isForAfterWork = false;
    }

    public Item(Dish dish, int quantity, Boolean isForAfterWork) {
        this.dish = dish;
        this.quantity = quantity;
        this.price = calculatePrice();
        this.notRegularPrice = calculateNotRegularPrice();
        this.isForAfterWork = isForAfterWork;
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
    public Boolean getIsForAfterWork() {
        return isForAfterWork;
    }

}
