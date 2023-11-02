package fr.unice.polytech.app;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CampusUser {
    private UUID id;
    private String name;
    private String password;
    private String address;
    private String email;
    private List<Order> orders;
    private List<Item> cart;
    private double balance;
    private UserType type;

    public CampusUser( String name, String password, String address, String email) {
        this.id = UUID.randomUUID();
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
        cart.clear();
        return newOrder;
    }

    public boolean cancelOrder(Order order,int minutesPassed) {
        if (minutesPassed > 30) {
            return false;
        }
        order.setStatus(OrderStatus.Cancelled);
        orders.remove(order);

        return true;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public List<Order> getHistory() {
        return orders;
    }

    public List<Restaurant> getAvailableRestaurants() {
        // todo
        return new ArrayList<>();
    }

    public void selectRestaurant(Restaurant restaurant) {
        // todo
    }

    public void makePayment(Order order) {
        //todo
    }

    public Menu selectRestaurant(UUID restaurantId) {
        //todo
        return new Menu(); // Assuming Menu is a class you've defined
    }

    public List<Item> getCart() {
        return cart;

    }

    public String getAddress() {
        return address;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserType getType(){
        return type;
    }

    public String getEmail() {
        return email;
    }


    public void getRefund() {

    }

    public void notifyUser() {
    }
}

