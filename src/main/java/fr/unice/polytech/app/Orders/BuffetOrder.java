package fr.unice.polytech.app.Orders;


import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.State.*;
import fr.unice.polytech.app.Users.CampusUser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuffetOrder implements Order{
    private UUID orderId;
    private Restaurant restaurant;
    private String deliveryLocation;
    private String deliveryAddress;
    private LocalTime pickupTime;
    private IState status;
    private String contactPerson; // Usager destinataire
    private CampusUser universityStaff; // Membre du staff universitaire
    private int numberOfPeople; // Nombre de personnes
    private Buffet buffet; // Type de buffet (Issa Nissa, Mignardises, etc.)
    private String routeDetails;


    public BuffetOrder(Restaurant restaurant, CampusUser universityStaff, String contactPerson,
                       int numberOfPeople, Buffet buffetType) throws Exception {
        this.orderId = UUID.randomUUID();
        this.restaurant = restaurant;
        this.universityStaff = universityStaff;
        this.contactPerson = contactPerson;
        this.numberOfPeople = numberOfPeople;
        this.buffet= buffetType;
        this.placeOrder();// Assuming InitialState is a valid state
    }

    @Override
    public String getRouteDetails() {
        return routeDetails;
    }
    @Override
    public LocalTime getPickupTime() {
        return pickupTime;
    }
    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant);
        return restaurants;
    }
    @Override
    public String getDeliveryLocation() {
        return deliveryLocation;
    }
    @Override
    public void setStatus(IState orderStatus) {
        this.status = orderStatus;
    }

    @Override
    public Restaurant getRestaurant() {
        return getRestaurants().get(0);
    }

    @Override
    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    @Override
    public IState getStatus() {
        return status;
    }

    @Override
    public LocalTime getAcceptedTime() {
        return getAcceptedTime();
    }

    @Override
    public LocalTime getDeliveryTime() {
        return getDeliveryTime();
    }

    @Override
    public double getPrice() {
        if(status instanceof CancelledIState)
            return 0;
        return getBuffet().getFormule().getPrix()* buffet.getFormuleList().size();
    }

    @Override
    public UUID getId() {
        return this.orderId;
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void pay() throws Exception {
        status=new PaidIState();
    }

    @Override
    public void accept() throws Exception {
        status=new AcceptedIState();
    }

    @Override
    public void reject() throws Exception {
        status=new RejectedIState();
    }

    @Override
    public void ready() throws Exception {
         status=  buffet.ready()?new ReadyIState() :new PlacedIState();
    }


    @Override
    public void assign() throws Exception {
        status=new AssignedIState();
    }

    @Override
    public void deliver() throws Exception {
        status=new DelivredIState();
    }

    @Override
    public void cancel() throws Exception {
        status=new CancelledIState();
    }

    @Override
    public void pickUp() throws Exception {
        status=new DelivredIState();
    }

    @Override
    public void placeOrder() throws Exception {
        status=new PlacedIState();
    }
    public CampusUser getOwner() {
        return universityStaff;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public boolean isOrdersPaid() {
        return status instanceof PaidIState;
    }

    public boolean isOrderCancelled() {
        return status instanceof CancelledIState;
    }


    public void delete() {
        universityStaff = null;
    }

    public boolean isOrderReady() {
        return status instanceof ReadyIState;
    }

    public boolean isOrderPickedUp() {
        return status instanceof DelivredIState;
    }

    public void validateForDelivery() throws Exception {
        pickUp();
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public CampusUser getUniversityStaff() {
        return universityStaff;
    }

    public void setUniversityStaff(CampusUser universityStaff) {
        this.universityStaff = universityStaff;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Buffet getBuffet() {
        return buffet;
    }

    public void setBuffet(Buffet buffet) {
        this.buffet = buffet;
    }

    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    public void cancelOrder() {
        status=new CancelledIState();
    }

    public void changeOrder(formula newFormula, int newNumberOfPeople) {
        this.buffet.setFormule(newFormula);
        this.numberOfPeople = newNumberOfPeople;
    }
}
