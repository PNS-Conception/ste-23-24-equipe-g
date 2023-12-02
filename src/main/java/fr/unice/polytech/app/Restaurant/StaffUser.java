package fr.unice.polytech.app.Restaurant;

import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Users.UserType;

import java.time.LocalTime;

public class StaffUser extends CampusUser {

    private Restaurant restaurant;

    public StaffUser(String name, Restaurant restaurant, String email, String password) {
        super(name, email, password);
        this.restaurant = restaurant;
        this.setType(UserType.STAFF);
    }

    public void acceptOrder(SingleOrder singleOrder) {
        if (restaurant.getOrderList().contains(singleOrder)) {
            restaurant.acceptOrder(singleOrder);
        }
    }

    public void refuseOrder(SingleOrder singleOrder) {
        if (restaurant.getOrderList().contains(singleOrder)) {
            LocalTime time = LocalTime.now();
            //difference between the time of the order and the current time
            long minutesPassed = time.getMinute() - singleOrder.getAcceptedTime().getMinute();
            restaurant.cancel(singleOrder,minutesPassed);
        }
    }

}
