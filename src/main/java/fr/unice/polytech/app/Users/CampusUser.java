package fr.unice.polytech.app.Users;

import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.RandomGenerator;
import fr.unice.polytech.app.Restaurant.CapacityManager;
import fr.unice.polytech.app.Restaurant.Dish;
import fr.unice.polytech.app.Restaurant.Item;
import fr.unice.polytech.app.Restaurant.Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CampusUser {
    private UUID id;
    private String name;
    private String password;
    private String email;
    private List<SingleOrder> History;
    private List<Item> cart;
    private double balance;
    private UserType type;
    private RandomGenerator randomGenerator;
    private String deliveryPersonIdReceived;
    private String deliveryPersonPhoneNumberReceived;
    private String notifiedDeliveryPersonId;
    private String notifiedDeliveryPersonPhoneNumber;
    //Liste de notes des livreurs et float moyenne
    private List<Integer> deliveryPersonRatings;


    public CampusUser( String name, String password, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = UserType.CLIENT;
        this.History = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.balance = 0;
        this.deliveryPersonRatings = new ArrayList<>();
    }

    public CampusUser( String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.type = UserType.CLIENT;
        this.History = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.balance = 0;
        this.deliveryPersonRatings = new ArrayList<>();
    }

    public CampusUser() {
        this.id = UUID.randomUUID();
        this.name = "mockUser";
        this.type = UserType.CLIENT;
        this.History = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.balance = 0;
        this.deliveryPersonRatings = new ArrayList<>();
    }


    public void createItem(Dish dish, int quantity) {
        Item newItem = new Item(dish, quantity);
        cart.add(newItem);
    }

    public void deleteItem(Item item) {
        cart.remove(item);
    }

    public SingleOrder order(List<Item> items, Restaurant restaurant) throws Exception {
        SingleOrder newSingleOrder = new SingleOrder(items, this, restaurant);
        History.add(newSingleOrder);
        cart.clear();
        return newSingleOrder;
    }

    public SingleOrder order(List<Item> items, Restaurant restaurant, LocalDateTime orderTime) throws Exception {
        int totalQuantity = items.stream().mapToInt(Item::getQuantity).sum();
        CapacityManager capacityManager = CapacityManager.getInstance();
        boolean canPlaceOrder = capacityManager.canPlaceOrder(restaurant, orderTime, totalQuantity);

        if (canPlaceOrder) {
            SingleOrder newSingleOrder = new SingleOrder(items, this, restaurant);
            History.add(newSingleOrder);
            cart.clear();
            return newSingleOrder;
        } else {
            throw new IllegalStateException("La capacité du restaurant est insuffisante pour cette commande.");
        }
    }
    public boolean cancelOrder(SingleOrder singleOrder, int minutesPassed) throws Exception {
        if (minutesPassed > 30) {
            return false;
        }
        refund(singleOrder);
        singleOrder.cancel();
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

    public void setBalance(double price) {
        this.balance += price;
    }

    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }


    public List<SingleOrder> getHistory() {
        return History;
    }


    public Restaurant selectRestaurant(Restaurant restaurant) {
        return restaurant;
    }

    public boolean makePayment(SingleOrder singleOrder, CampusUser user) throws Exception {

        //9 fois sur 10, la commande est validée, 1 fois sur 10 il y a une erreur.
        //if (Math.random() < 0.9) {
        if (true) {
            if (balance > 0){
                if(balance >= singleOrder.getPrice()){
                    balance -= singleOrder.getPrice();
                }else{
                    singleOrder.setPrice(singleOrder.getPrice() - balance);
                    balance = 0;
                }
            }
            singleOrder.pay();
            History.add(singleOrder);
            return true;
        } else {
            return false;
        }
    }

    public boolean makePaymentmock(SingleOrder singleOrder, CampusUser client) throws Exception {
        //9 fois sur 10, la commande est validée, 1 fois sur 10 il y a une erreur.
        if (randomGenerator.nextDouble() < 0.9) {
            if (balance > 0){
                if(balance >= singleOrder.getPrice()){
                    balance -= singleOrder.getPrice();
                }else{
                    singleOrder.setPrice(singleOrder.getPrice() - balance);
                    balance = 0;
                }
            }
            singleOrder.pay();
            History.add(singleOrder);

            return true;
        } else {
            return false;
        }
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

    public SingleOrder getLastOrder(){
        return History.get(History.size()-1);
    }

    public void refund(SingleOrder singleOrder) {
        //if(order.getStatus() == OrderStatus.PAID && orders.contains(order)){
            setBalance(singleOrder.getPrice());
            History.remove(singleOrder);
        //}
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public void addRate(int rate){
        deliveryPersonRatings.add(rate);
    }

    public int getNbOfRates(){
        return deliveryPersonRatings.size();
    }

    public float getAverageRating(){
        float sum = 0;
        for (Integer rating : deliveryPersonRatings) {
            sum += rating;
        }
        return sum / deliveryPersonRatings.size();
    }
}

