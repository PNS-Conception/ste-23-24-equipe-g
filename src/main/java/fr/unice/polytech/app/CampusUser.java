package fr.unice.polytech.app;

/*public class Client {
    private String name;




    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}*/

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CampusUser {
    private UUID id;
    private String name;
    private String password;
    private String address;
    private String email;
    private UserType user;
    private List<Order> orders;
    private List<Item> cart;

    private final UserType type;

    public CampusUser(UUID id, String name, String password, String address, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.type = UserType.Client;
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>();
    }

    public void createItem(Dish dish, int quantity) {
        Item newItem = new Item(dish, quantity);
        cart.add(newItem);
    }

    public void deleteItem(Item item) {
        cart.remove(item);
    }

    public Order order(List<Item> items) {
        Order newOrder = new Order(items);
        orders.add(newOrder);
        cart.clear(); // Clear the cart after creating an order
        return newOrder;
    }

    public boolean cancelOrder(Order order,int minutesPassed) {
        if (minutesPassed > 30) {
            return false;
        }
        orders.remove(order);
        order.setStatus(OrderStatus.Cancelled);
        return true;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public List<Order> getHistory() {
        return orders;
    }

    public List<Restaurant> getAvailableRestaurants() {
        // Implement logic to get available restaurants (you need to define this method in Restaurant class)
        return new ArrayList<>();
    }

    public void selectRestaurant(Restaurant restaurant) {
        // Implement logic to select a restaurant
    }

    public void makePayment(Order order) {
        // Implement logic to make a payment for the order
    }

    public Menu selectRestaurant(UUID restaurantId) {
        // Implement logic to select a restaurant and get its menu
        return new Menu(); // Assuming Menu is a class you've defined
    }

    public List<Item> getCart() {
        return cart;

    }
}

