package fr.unice.polytech.app;

import java.util.HashMap;
import java.util.Map;

import static fr.unice.polytech.app.CartStatus.EMPTY;

public class Cart {
    private Client client;
    private Map<String, Integer> items;  // Mapez les noms des items à leurs quantités.
    private String Restaurant;

    private CartStatus CartStatus;

    public Cart(Client client) {
        this.client = client;
        this.items = new HashMap<>();
        this.CartStatus =EMPTY;
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

    public Boolean containsThatItem(String item)
    {
        return this.items.containsKey(item);
    }
    public int getItemQuantity(String item) {
        return items.getOrDefault(item, 0);
    }

    public Order validateCart(String item, int quantity) {
        return new Order(client, item, quantity);
    }

    public String getRestaurant() {
        return Restaurant;
    }

    public void setRestaurant(String restaurant) {
        Restaurant = restaurant;
    }

    public boolean isEmpty(){
        return this.items.isEmpty();
    }

    public fr.unice.polytech.app.CartStatus getCartStatus() {
        return CartStatus;
    }

    public void setCartStatus(fr.unice.polytech.app.CartStatus cartStatus) {
        CartStatus = cartStatus;
    }
}
