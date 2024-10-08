package fr.unice.polytech.app.Orders;
import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.State.PlacedIState;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AfterWorkOrder implements DecoratorOrder {
    private UUID id;
    DecoratorOrder order;
    private List<Item> items;
    private List<CampusUser> participants;
    private int numberOfParticipants;

    public AfterWorkOrder(CampusUser organizer, Restaurant restaurant, List<Item> items, int numberOfParticipants) throws Exception {
        this.id = UUID.randomUUID();
        //this.organizer = organizer;
        //this.restaurant = restaurant;
        order = new SingleOrder();
        order.setOwner(organizer);
        order.setRestaurant(restaurant);
        this.items = new ArrayList<>(items);
        this.participants = new ArrayList<>(numberOfParticipants);
        this.numberOfParticipants = numberOfParticipants;
        order.setPickupTime(LocalTime.now());
        placeOrder();

    }

    // Méthodes pour gérer les participants
    public void addParticipant(CampusUser participant) {
        if (!participants.contains(participant)) {
            participants.add(participant);
        }
    }

    /*public void removeParticipant(CampusUser participant) {
        participants.remove(participant);
    }*/

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    // Méthode pour vérifier si le restaurant offre des menus afterwork
    /*public boolean hasAfterWorkMenu() {
        return restaurant.offersAfterworkMenus();
    }*/

    @Override
    public void placeOrder() {
       order.setStatus(new PlacedIState());
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
    public CampusUser getOwner() {
        return order.getOwner();
    }

    @Override
    public void setOwner(CampusUser owner) {
        order.setOwner(owner);
    }

    @Override
    public void setRouteDetails(String routeDetails) {
        order.setRouteDetails(routeDetails);
    }

    @Override
    public void setPickupTime(LocalTime pickupTime) {
        order.setPickupTime(pickupTime);
    }

    @Override
    public void setStatus(IState orderStatus) {
        order.setStatus(orderStatus);
    }

    @Override
    public void cancel() throws Exception {
        order.cancel();
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
        return order.getRestaurant();
    }

    @Override
    public void setDeliveryLocation(String deliveryLocation) {

    }

    public IState getStatus() {
        return order.getStatus();
    }

    @Override
    public void pay() {
        // De même, comme il n'y a pas de paiement immédiat pour les commandes
        // afterwork,
        // cette méthode peut être laissée vide ou lancer une exception.
        throw new UnsupportedOperationException("Le paiement n'est pas applicable pour les commandes afterwork.");
    }

    @Override
    public void accept() throws Exception {
        order.accept();

    }

    @Override
    public void reject() throws Exception {
        order.reject();
    }

    @Override
    public void ready() throws Exception {
        throw new UnsupportedOperationException("Les commandes afterwork ne peuvent pas être prêtes.");
    }

    @Override
    public void assign() throws Exception {
        throw new UnsupportedOperationException("Les commandes afterwork ne peuvent pas être assignées.");
    }

    public void setNumberOfParticipants(int i) {
        this.numberOfParticipants = i;
    }

}
