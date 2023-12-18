package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.User.CampusUser;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface DecoratorOrder {
    String getRouteDetails();

    /**
     * Returns the pickup time for the order.
     *
     * @return the pickup time
     */
    LocalTime getPickupTime();

    /**
     * Returns the list of restaurants associated with the order.
     *
     * @return the list of restaurants
     */
    List<Restaurant> getRestaurants();

    /**
     * Returns the delivery location for the order.
     *
     * @return the delivery location
     */
    String getDeliveryLocation();

    /**
     * Sets the status of the order.
     *
     * @param orderStatus the status to set
     */
    void setStatus(IState orderStatus);

    /**
     * Returns the restaurant associated with the order.
     *
     * @return the restaurant
     */
    Restaurant getRestaurant();

    /**
     * Sets the delivery location for the order.
     *
     * @param deliveryLocation the delivery location to set
     */
    void setDeliveryLocation(String deliveryLocation);

    /**
     * Returns the status of the order.
     *
     * @return the status
     */
    IState getStatus();

    /**
     * Performs the payment for the order.
     *
     * @throws Exception if an error occurs during payment
     */
    void pay() throws Exception;

    /**
     * Accepts the order.
     *
     * @throws Exception if an error occurs while accepting the order
     */
    void accept() throws Exception;

    /**
     * Rejects the order.
     *
     * @throws Exception if an error occurs while rejecting the order
     */
    void reject() throws Exception;

    /**
     * Marks the order as ready for delivery.
     *
     * @throws Exception if an error occurs while marking the order as ready
     */
    void ready() throws Exception;

    /**
     * Assigns the order to a delivery person.
     *
     * @throws Exception if an error occurs while assigning the order
     */
    void assign() throws Exception;

    /**
     * Marks the order as delivered.
     *
     * @throws Exception if an error occurs while marking the order as delivered
     */
    void deliver() throws Exception;

    /**
     * Cancels the order.
     *
     * @throws Exception if an error occurs while canceling the order
     */
    void cancel() throws Exception;

    /**
     * Marks the order as picked up.
     *
     * @throws Exception if an error occurs while marking the order as picked up
     */
    void pickUp() throws Exception;

    /**
     * Places the order.
     *
     * @throws Exception if an error occurs while placing the order
     */
    void placeOrder() throws Exception;

    /**
     * Returns the time when the order was accepted.
     *
     * @return the accepted time
     */
    LocalTime getAcceptedTime();

    /**
     * Returns the delivery time for the order.
     *
     * @return the delivery time
     */
    LocalTime getDeliveryTime();

    /**
     * Returns the price of the order.
     *
     * @return the price
     */
    double getPrice();

    /**
     * Returns the ID of the order.
     *
     * @return the ID
     */
    UUID getId();

    /**
     * Sets the restaurant for the order.
     *
     * @param restaurant the restaurant to set
     */
    void setRestaurant(Restaurant restaurant);


    CampusUser getOwner();

    void setOwner(CampusUser owner);
}
