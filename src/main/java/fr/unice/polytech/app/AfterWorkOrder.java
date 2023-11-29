package fr.unice.polytech.app;

import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.State.PlacedIState;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfterWorkOrder implements Order {
    private UUID id;

    private static CampusUser organizer;
    private Restaurant restaurant;
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
        this.placedTime = LocalTime.now();

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
        return participants.size();
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
        return 0;
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

    }

    @Override
    public void deliver() {
        // Étant donné qu'il n'y a pas de livraison pour les commandes afterwork,
        throw new UnsupportedOperationException("La livraison n'est pas disponible pour les commandes afterwork.");
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
        return null;
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

    }

    @Override
    public void reject() throws Exception {

    }

    @Override
    public void ready() throws Exception {

    }

    @Override
    public void assign() throws Exception {

    }

    public static CampusUser getOrganizer() {
        return organizer;
    }

    public void setOrganizer(CampusUser organizer) {
        this.organizer = organizer;
    }

    public void setNumberOfParticipants(int i) {
        this.numberOfParticipants=i;
    }

}
