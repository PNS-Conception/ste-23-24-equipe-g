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
    private String deliveryPersonIdReceived;
    private String deliveryPersonPhoneNumberReceived;
    private String notifiedDeliveryPersonId;
    private String notifiedDeliveryPersonPhoneNumber;


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

    public CampusUser( String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
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

    public Order order(List<Item> items, Restaurant restaurant) {
        Order newOrder = new Order(items, this, restaurant);
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
    /**
     * Reçoit les détails de la livraison et les traite d'une certaine manière.
     * @param deliveryPersonId L'ID du livreur.
     * @param deliveryPersonPhoneNumber Le numéro de téléphone du livreur.
     */
    public void receiveDeliveryDetails(String deliveryPersonId, String deliveryPersonPhoneNumber) {
        // Traitez les détails de la livraison ici
        // Par exemple, affichez-les à l'utilisateur ou enregistrez-les dans l'interface utilisateur
        System.out.println("Delivery Person ID: " + deliveryPersonId);
        System.out.println("Delivery Person Phone: " + deliveryPersonPhoneNumber);

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
            order.pay();
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

    public UUID getId() {
        return id;
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



    public String getDeliveryPersonIdReceived() {
        return deliveryPersonIdReceived;
    }

    public String getDeliveryPersonPhoneNumberReceived() {
        return deliveryPersonPhoneNumberReceived;
    }
    public void setNotifiedDeliveryPersonId(String id) {
        this.notifiedDeliveryPersonId = id;
    }

    public String getNotifiedDeliveryPersonId() {
        return notifiedDeliveryPersonId;
    }

    public void setNotifiedDeliveryPersonPhoneNumber(String phoneNumber) {
        this.notifiedDeliveryPersonPhoneNumber = phoneNumber;
    }

    public String getNotifiedDeliveryPersonPhoneNumber() {
        return notifiedDeliveryPersonPhoneNumber;
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

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }


}

