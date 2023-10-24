/*package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.unice.polytech.app.OrderStatus.EMPTY;
import static fr.unice.polytech.app.OrderStatus.Placed;

public class Cart {
    private CampusUser client;
    private List<Item> items;  // Mapez les noms des items à leurs quantités.
    private Restaurant restaurant;

    private OrderStatus orderStatus;

    public Cart(List<Item> items) {
        this.items = items;
    }

    public Cart() {
        this.items = new ArrayList<Item>();
        this.orderStatus =Placed;
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

    public OrderStatus getCartStatus() {
        return OrderStatus;
    }

    public void setCartStatus(OrderStatus orderStatus) {
        OrderStatus = orderStatus;
    }
}*/
