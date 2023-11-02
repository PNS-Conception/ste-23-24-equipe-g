package fr.unice.polytech.app;

public class RestaurantManager extends CampusUser{

    public RestaurantManager(String name, String password, String address, String email) {
        super(name, password, address, email);
        setType(UserType.Manager);
    }
}
