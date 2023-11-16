package fr.unice.polytech.app;

public class StaffUser extends CampusUser {

    private Restaurant restaurant;

    public StaffUser(String name, Restaurant restaurant, String email, String password) {
        super(name, email, password);
        this.restaurant = restaurant;
        this.setType(UserType.STAFF);
    }

    public void acceptOrder(Order order) {
        if (restaurant.getOrderList().contains(order)) {
            restaurant.acceptOrder(order);
        }
    }

    public void refuseOrder(Order order) {
        if (restaurant.getOrderList().contains(order)) {
            restaurant.cancelOrder(order);
        }
    }

}
