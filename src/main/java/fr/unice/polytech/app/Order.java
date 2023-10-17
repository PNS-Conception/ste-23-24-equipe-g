package fr.unice.polytech.app;

public class Order {

    private String restaurant ;
    private Client client;
    private String item;
    private int quantity;
    private Cart cart;

    public Order() {
    }
    public Order(Client client, String item, int quantity) {
        this.client = client;
        this.item = item;
        this.quantity = quantity;
    }

    public Order(Client client,Cart cart) {
        this.client = client;
        this.cart = cart;
    }


    public String getRestaurant() {
        return restaurant;
    }

    public Client getClient() {
        return client;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "client=" + client +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                '}';
    }





}
