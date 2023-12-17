package fr.unice.polytech.app.Users;

import fr.unice.polytech.app.Delivery.DeliverySystem;
import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Orders.Order;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Users.UserType;

import java.time.LocalTime;
import java.util.List;

/**
 * Represents a delivery person who can accept and deliver orders.
 * Extends the CampusUser class.
 */
public class DeliveryPerson extends CampusUser {

    private String phoneNumber;
    private Order currentSingleOrder;
    private boolean isAvailable;
    private String routeDetails;
    private LocalTime pickupTime;
    private List<Restaurant> restaurants;
    private Restaurant restaurant;
    private String deliveryLocation;
    private DeliverySystem deliverySystem;


    /**
     * Constructs a DeliveryPerson object with the specified name, email, and phone number.
     *
     * @param name        the name of the delivery person
     * @param email       the email of the delivery person
     * @param phoneNumber the phone number of the delivery person
     */
    public DeliveryPerson(String name, String email, String phoneNumber) {
        super(name,null, email);
        setType(UserType.DELIVERY_PERSON);
        this.phoneNumber = phoneNumber;
        this.currentSingleOrder = null;
        this.isAvailable = true;
        deliverySystem = new DeliverySystem();
    }



    /**
     * Assigns a single order to the delivery person.
     *
     * @param singleOrder the single order to be assigned
     * @return true if the order is successfully assigned, false otherwise
     * @throws Exception if the delivery person is not available or already has an assigned order
     */
    public boolean assignOrder(SingleOrder singleOrder) throws Exception {
        if (this.isAvailable && this.currentSingleOrder == null) {
            this.currentSingleOrder = singleOrder;
            this.isAvailable = false;
            singleOrder.assign();
            return true;
        }
        return false;
    }

    /**
     * Marks the current order as delivered.
     *
     * @throws Exception if there is no current order
     */
    public void markOrderAsDelivered() throws Exception {
        if (this.currentSingleOrder != null) {
            this.currentSingleOrder.deliver();
            this.currentSingleOrder = null;
            this.isAvailable = true;
        }
    }

    /**
     * Finalizes the delivery of a single order without user confirmation.
     *
     * @param singleOrder the single order to be finalized
     * @throws Exception if the single order cannot be confirmed by the user or if it is not the current order
     */
    public void finalizeDeliveryWithoutUserConfirmation(SingleOrder singleOrder) throws Exception {
        if (!singleOrder.canUserConfirmReceipt() && this.currentSingleOrder.equals(singleOrder)) {
            this.markOrderAsDelivered();
        }
    }

    /**
     * Receives the details of an order.
     *
     * @param singleOrder the order to receive details from
     */
    public void receiveOrderDetails(Order singleOrder) {
        this.routeDetails = singleOrder.getRouteDetails();
        this.pickupTime = singleOrder.getPickupTime();
        this.restaurants = singleOrder.getRestaurants();
        this.deliveryLocation = singleOrder.getDeliveryLocation();
    }

    /**
     * Returns the route details of the current order.
     *
     * @return the route details
     */
    public String getRouteDetails() {
        return routeDetails;
    }

    /**
     * Returns the pickup time of the current order.
     *
     * @return the pickup time
     */
    public LocalTime getPickupTime() {
        return pickupTime;
    }

    /**
     * Returns the list of restaurants in the current order.
     *
     * @return a list of Restaurant objects
     */
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * Returns the delivery location of the current order.
     *
     * @return the delivery location
     */
    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    /**
     * Returns the phone number of the delivery person.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the delivery person.
     *
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the current single order assigned to the delivery person.
     *
     * @return the current single order
     */
    public Order getCurrentOrder() {
        return currentSingleOrder;
    }

    /**
     * Sets the current single order assigned to the delivery person.
     *
     * @param currentSingleOrder the current single order to set
     */
    public void setCurrentOrder(Order currentSingleOrder) {
        this.currentSingleOrder = currentSingleOrder;
    }

    /**
     * Returns whether the delivery person is available or not.
     *
     * @return true if the delivery person is available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of the delivery person.
     *
     * @param available true if the delivery person is available, false otherwise
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Validates an order by setting it as the current order and marking it as picked up.
     *
     * @param order the order to validate
     * @throws Exception if the order is already assigned to another delivery person
     */
    public void validateOrder(Order order) throws Exception {
        setCurrentOrder(order);
        order.pickUp();
    }

    /**
     * Delivers a group order by marking it as delivered and setting the current order to null.
     *
     * @param groupOrder the group order to deliver
     * @throws Exception if there is no current order
     */
    public void deliverOrder(Order groupOrder) throws Exception {
        groupOrder.deliver();
        setCurrentOrder(null);
    }

    public DeliverySystem getDeliverySystem() {
        return deliverySystem;
    }

}
