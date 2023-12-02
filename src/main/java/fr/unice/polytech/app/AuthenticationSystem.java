package fr.unice.polytech.app;

import fr.unice.polytech.app.Users.CampusUser;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationSystem {
    private Map<String, String> userCredentials;
    Admin admin;

    public AuthenticationSystem() {
        this.userCredentials = new HashMap<>();
        admin = new Admin();
    }

    public boolean signUp(String name,String email, String password) {
        if (!userCredentials.containsKey(email)) {
            userCredentials.put(email, password);
            admin.addCampusUser(name, password, email);
            return true;
        }
        return false;

    }

    public boolean logIn(String username, String password) {
        return isCredentialsStored(username, password);
    }



    public boolean isCredentialsStored(String email, String password) {
        return userCredentials.containsKey(email) && userCredentials.get(email).equals(password);
    }

    public void userCredentials(){
        for (CampusUser user : admin.getCampusUsers()){
            userCredentials.put(user.getEmail(), user.getPassword());
        }
    }

}
