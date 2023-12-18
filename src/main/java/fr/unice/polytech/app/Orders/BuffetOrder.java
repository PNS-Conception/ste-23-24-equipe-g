package fr.unice.polytech.app.Orders;


import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.State.*;
import fr.unice.polytech.app.User.CampusUser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuffetOrder implements DecoratorOrder{
    private UUID orderId;
    private String deliveryAddress;
    private String contactPerson; // Usager destinataire
    private int numberOfPeople; // Nombre de personnes
    private Buffet buffet; // Type de buffet (Issa Nissa, Mignardises, etc.)
    private String routeDetails;
    private LocalTime pickupTime;

    DecoratorOrder order;


    public BuffetOrder(Restaurant restaurant, CampusUser universityStaff, String contactPerson,
                       int numberOfPeople, Buffet buffetType) throws Exception {
        this.orderId = UUID.randomUUID();
        order = new SingleOrder();
        setOwner(universityStaff);
        setRestaurant(restaurant);
        this.contactPerson = contactPerson;
        this.numberOfPeople = numberOfPeople;
        this.buffet= buffetType;
        this.placeOrder();// Assuming InitialState is a valid state
    }

    @Override
    public String getRouteDetails() {
        return order.getRouteDetails();
    }
    @Override
    public LocalTime getPickupTime() {
        return order.getPickupTime();
    }
    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(order.getRestaurant());
        return restaurants;
    }
    @Override
    public String getDeliveryLocation() {
        return order.getDeliveryLocation();
    }
    @Override
    public void setStatus(IState orderStatus) {
        order.setStatus(orderStatus);
    }

    @Override
    public Restaurant getRestaurant() {
        return getRestaurants().get(0);
    }

    @Override
    public void setDeliveryLocation(String deliveryLocation) {
        order.setDeliveryLocation(deliveryLocation);
    }

    @Override
    public IState getStatus() {
        return order.getStatus();
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
        if(order.getStatus() instanceof CancelledIState)
            return 0;
        return getBuffet().getFormule().getPrix()* buffet.getFormuleList().size();
    }

    @Override
    public UUID getId() {
        return this.orderId;
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {
        order.setRestaurant(restaurant);
    }

    @Override
    public void pay() throws Exception {
        order.pay();
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
         order.setStatus( buffet.ready()?new ReadyIState() :new PlacedIState());
    }


    @Override
    public void assign() throws Exception {
        order.assign();
    }

    @Override
    public void deliver() throws Exception {
        order.deliver();
    }

    @Override
    public void cancel() throws Exception {
        order.cancel();
    }

    @Override
    public void pickUp() throws Exception {
        order.pickUp();
    }

    @Override
    public void placeOrder() throws Exception {
        order.placeOrder();
    }
    public CampusUser getOwner() {
        return order.getOwner();
    }

    @Override
    public void setOwner(CampusUser owner) {
        order.setOwner(owner);
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public boolean isOrdersPaid() {
        return order.getStatus() instanceof PaidIState;
    }

    public boolean isOrderCancelled() {
        return order.getStatus() instanceof CancelledIState;
    }


    public boolean isOrderReady() {
        return order.getStatus() instanceof ReadyIState;
    }

    public boolean isOrderPickedUp() {
        return order.getStatus() instanceof DelivredIState;
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


    public void changeOrder(formula newFormula, int newNumberOfPeople) {
        this.buffet.setFormule(newFormula);
        this.numberOfPeople = newNumberOfPeople;
    }
}
