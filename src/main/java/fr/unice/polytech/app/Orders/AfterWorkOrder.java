package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.Users.CampusUser;

import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.State.PlacedIState;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfterWorkOrder implements Order {
    private UUID id;

    private CampusUser organizer;
    private final Restaurant restaurant;
    private LocalTime placedTime;
    private List<Item> items;
    private List<CampusUser> participants;
    private int numberOfParticipants;
    private IState status ;
    public AfterWorkOrder(CampusUser organizer, Restaurant restaurant, List<Item> items, int numberOfParticipants) {
        this.id = UUID.randomUUID();
        this.organizer = organizer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>(items);
        this.participants = new ArrayList<>(numberOfParticipants);
        this.numberOfParticipants = numberOfParticipants;
        this.placedTime = LocalTime.now();
        placeOrder();

    }

    // Méthodes pour gérer les participants
    public void addParticipant(CampusUser participant) {
        if (!participants.contains(participant)) {
            participants.add(participant);
        }
    }

    public void removeParticipant(CampusUser participant) {
        participants.remove(participant);
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    // Méthode pour vérifier si le restaurant offre des menus afterwork
    public boolean hasAfterWorkMenu() {
        return restaurant.offersAfterworkMenus();
    }


    @Override
    public void placeOrder() {
        status= new PlacedIState();
    }

    @Override
    public LocalTime getAcceptedTime() {
        return null;
    }

    @Override
    public LocalTime getDeliveryTime() {
        return null;
    }

    @Override
    public double getPrice() {
        double price = 0;
        for (Item item : items) {
            price += item.getPrice();
        }
        return price;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {

    }
    @Override
    public void setStatus(IState orderStatus) {
        this.status = orderStatus;
    }

    @Override
    public void cancel() throws Exception {
        status.cancelOrder(this);
    }

    @Override
    public void pickUp() throws Exception {
        throw new UnsupportedOperationException("Les commandes afterwork ne peuvent pas être récupérées.");
    }

    @Override
    public void deliver() throws Exception {
        // Étant donné qu'il n'y a pas de livraison pour les commandes afterwork,
        throw new UnsupportedOperationException("Les commandes afterwork ne se livrent pas.");
    }

    @Override
    public String getRouteDetails() {
        return null;
    }

    @Override
    public LocalTime getPickupTime() {
        return null;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return null;
    }

    @Override
    public String getDeliveryLocation() {
        return null;
    }


    @Override
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public void setDeliveryLocation(String deliveryLocation) {

    }

    public IState getStatus() {
        return status;
    }

    @Override
    public void pay() {
        // De même, comme il n'y a pas de paiement immédiat pour les commandes afterwork,
        // cette méthode peut être laissée vide ou lancer une exception.
        throw new UnsupportedOperationException("Le paiement n'est pas applicable pour les commandes afterwork.");
    }

    @Override
    public void accept() throws Exception {
        status.acceptOrder(this);

    }

    @Override
    public void reject() throws Exception {
        status.rejectOrder(this);
    }

    @Override
    public void ready() throws Exception {
         throw new UnsupportedOperationException("Les commandes afterwork ne peuvent pas être prêtes.");
    }

    @Override
    public void assign() throws Exception {
        throw new UnsupportedOperationException("Les commandes afterwork ne peuvent pas être assignées.");
    }

    public CampusUser getOrganizer() {
        return organizer;
    }

    public void setOrganizer(CampusUser organizer) {
        this.organizer = organizer;
    }

    public void setNumberOfParticipants(int i) {
        this.numberOfParticipants=i;
    }

    public void addItems(Item item) {
        if (item.getIsForAfterWork()){
            this.items.add(item);
        }
    }

}
