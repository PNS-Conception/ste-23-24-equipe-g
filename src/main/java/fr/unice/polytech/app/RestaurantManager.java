package fr.unice.polytech.app;

public class RestaurantManager extends CampusUser{

    public RestaurantManager(String name, String password, String email) {
        super(name, password, email);
        setType(UserType.MANAGER);
    }
}
