package fr.unice.polytech.biblio;

public class Order {
    private Client client;
    private String item;
    private int quantity;
    public Order(Client client, String item, int quantity) {
        this.client = client;
        this.item = item;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "Order{" +
                "client=" + client +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
