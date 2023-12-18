package fr.unice.polytech.app.Restaurant;

import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.User.UserType;

import java.time.LocalTime;

/**
 * Represents a staff user in a restaurant.
 * Extends the CampusUser class.
 */
public class StaffUser extends CampusUser {

    private Restaurant restaurant;

    /**
     * Constructs a StaffUser object with the specified name, restaurant, email, and password.
     * Sets the user type to UserType.STAFF.
     *
     * @param name      the name of the staff user
     * @param restaurant    the restaurant associated with the staff user
     * @param email     the email of the staff user
     * @param password  the password of the staff user
     */
    public StaffUser(String name, Restaurant restaurant, String email, String password) {
        super(name, email, password);
        this.restaurant = restaurant;
        this.setType(UserType.STAFF);
    }

    /**
     * Accepts an order by adding it to the restaurant's order list.
     * If the order is already in the list, it is accepted.
     *
     * @param singleOrder   the order to accept
     */
    public void acceptOrder(SingleOrder singleOrder) {
        if (restaurant.getOrderList().contains(singleOrder)) {
            restaurant.acceptOrder(singleOrder);
        }
    }

    /**
     * Refuses an order by canceling it and calculating the time passed since it was accepted.
     * If the order is in the restaurant's order list, it is canceled and the minutes passed since acceptance are calculated.
     *
     * @param singleOrder   the order to refuse
     */
    public void refuseOrder(SingleOrder singleOrder) {
        if (restaurant.getOrderList().contains(singleOrder)) {
            LocalTime time = LocalTime.now();
            long minutesPassed = time.getMinute() - singleOrder.getAcceptedTime().getMinute();
            restaurant.cancel(singleOrder,minutesPassed);
        }
    }

}
