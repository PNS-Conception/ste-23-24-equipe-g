package fr.unice.polytech.biblio;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Client client;
    private Map<String, Integer> items;  // Mapez les noms des items à leurs quantités.

    public Cart(Client client) {
        this.client = client;
        this.items = new HashMap<>();
    }

    public void emptyCart() {
        items.clear();
    }

    public void addItem(String item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(String item, int quantity) {
        items.put(item, Math.max(0, items.getOrDefault(item, 0) - quantity));
    }

    public int getItemQuantity(String item) {
        return items.getOrDefault(item, 0);
    }

    public Order validateCart(String item, int quantity) {
        return new Order(client, item, quantity);
    }
}
