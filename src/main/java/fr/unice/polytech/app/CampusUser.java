package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CampusUser {
    private UUID id;
    private String name;
    private String password;

    private String email;
    private List<Order> orders;
    private List<Item> cart;
    private double balance;
    private UserType type;
    private RandomGenerator randomGenerator;

    public CampusUser( String name, String password, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = UserType.CLIENT;
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.balance = 0;
    }

    public CampusUser() {
        this.id = UUID.randomUUID();
        this.name = "mockUser";
        this.type = UserType.CLIENT;
    }

    public void createItem(Dish dish, int quantity) {
        Item newItem = new Item(dish, quantity);
        cart.add(newItem);
    }

    public void deleteItem(Item item) {
        cart.remove(item);
    }

    public Order order(List<Item> items) {
        Order newOrder = new Order(items, this);
        orders.add(newOrder);
        cart.clear();
        return newOrder;
    }

    public boolean cancelOrder(Order order,int minutesPassed) {
        if (minutesPassed > 30) {
            return false;
        }
        refund(order);
        order.setStatus(OrderStatus.CANCELLED);
        return true;
    }

    void setBalance(double price) {
        this.balance += price;
    }

    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
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

    public boolean makePayment(Order order, CampusUser user) {
        //9 fois sur 10, la commande est validée, 1 fois sur 10 il y a une erreur.
        if (Math.random() < 0.9) {
            if (balance > 0){
                if(balance >= order.getPrice()){
                    balance -= order.getPrice();
                }else{
                    order.setPrice(order.getPrice() - balance);
                    balance = 0;
                }
            }
            order.setStatus(OrderStatus.PAID);
            orders.add(order);
            return true;
        } else {
            return false;
        }
    }

    public boolean makePaymentmock(Order order, CampusUser client) {
        //9 fois sur 10, la commande est validée, 1 fois sur 10 il y a une erreur.
        if (randomGenerator.nextDouble() < 0.9) {
            if (balance > 0){
                if(balance >= order.getPrice()){
                    balance -= order.getPrice();
                }else{
                    order.setPrice(order.getPrice() - balance);
                    balance = 0;
                }
            }
            order.setStatus(OrderStatus.PAID);
            orders.add(order);
            return true;
        } else {
            return false;
        }
    }

    public Menu selectRestaurant(UUID restaurantId) {
        //todo
        return new Menu(); // Assuming Menu is a class you've defined
    }

    public List<Item> getCart() {
        return cart;

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

    public void notifyUser() {
    }

    public double getBalance() {
        return balance;
    }

    public Order getLastOrder(){
        return orders.get(orders.size()-1);
    }

    public void refund(Order order) {
        //if(order.getStatus() == OrderStatus.PAID && orders.contains(order)){
            setBalance(order.getPrice());
            orders.remove(order);
        //}
    }
}

