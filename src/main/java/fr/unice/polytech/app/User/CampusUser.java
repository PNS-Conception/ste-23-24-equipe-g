package fr.unice.polytech.app.User;

import fr.unice.polytech.app.Orders.Cart;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.CapacityManager;
import fr.unice.polytech.app.Restaurant.Dish;
import fr.unice.polytech.app.Restaurant.Item;
import fr.unice.polytech.app.Restaurant.Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class CampusUser extends User implements PaiementProxy {
    private UUID id;
    private String name;
    private String password;
    private String email;
    private List<SingleOrder> History;
    private Cart cart;
    private double balance;
    private UserType type;

    private String deliveryPersonIdReceived;
    private String deliveryPersonPhoneNumberReceived;
    private String notifiedDeliveryPersonId;
    private String notifiedDeliveryPersonPhoneNumber;
    private List<Integer> deliveryPersonRatings;

    private PaiementSystem paiementSystem;


    public CampusUser( String name, String password, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = UserType.CLIENT;
        this.History = new ArrayList<>();
        this.cart = new Cart(new ArrayList<>());
        this.balance = 0;
        this.deliveryPersonRatings = new ArrayList<>();
        this.paiementSystem = new PaiementSystem(this);

    }



    public CampusUser() {
        this.id = UUID.randomUUID();
        this.name = "mockUser";
        this.type = UserType.CLIENT;
        this.History = new ArrayList<>();
        this.cart = new Cart(new ArrayList<>());
        this.balance = 0;
        this.deliveryPersonRatings = new ArrayList<>();
        this.paiementSystem = new PaiementSystem(this);
    }


    public void createItem(Dish dish, int quantity) {
        Item newItem = new Item(dish, quantity);
        cart.add(newItem);
    }

    public void createItem(Item item) {
        cart.add(item);
    }

    public void deleteItem(Item item) {
        cart.remove(item);
    }

    public SingleOrder order(Restaurant restaurant) throws Exception {
        SingleOrder newSingleOrder = new SingleOrder(this, restaurant);
        History.add(newSingleOrder);
        return newSingleOrder;
    }

    public SingleOrder order(Restaurant restaurant, LocalDateTime orderTime) throws Exception {
        int totalQuantity = cart.getItems().stream().mapToInt(Item::getQuantity).sum();
        CapacityManager capacityManager = CapacityManager.getInstance();
        boolean canPlaceOrder = capacityManager.canPlaceOrder(restaurant, orderTime, totalQuantity);

        if (canPlaceOrder) {
            SingleOrder newSingleOrder = new SingleOrder(this, restaurant);
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

    public void refund(SingleOrder singleOrder) {
        paiementSystem.refund(singleOrder);
    }

    /**
     * Reçoit les détails de la livraison et les traite d'une certaine manière.
     * @param deliveryPersonId L'ID du livreur.
     * @param deliveryPersonPhoneNumber Le numéro de téléphone du livreur.
     */
    public void receiveDeliveryDetails(String deliveryPersonId, String deliveryPersonPhoneNumber) {
        System.out.println("Delivery Person ID: " + deliveryPersonId);
        System.out.println("Delivery Person Phone: " + deliveryPersonPhoneNumber);
    }

    public void setBalance(double price) {
        this.balance = price;
    }




    public List<SingleOrder> getHistory() {
        return History;
    }


    public Restaurant selectRestaurant(Restaurant restaurant) {
        return restaurant;
    }


    public Cart getCart() {
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

    public void getNotified() {

    }

    public double getBalance() {
        return balance;
    }

    public SingleOrder getLastOrder(){
        return History.get(History.size()-1);
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

    public PaiementSystem getPaiementSystem() {
        return paiementSystem;
    }
}

