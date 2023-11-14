package fr.unice.polytech.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AuthenticationSystem {
    private Map<String, String> userCredentials;

    public AuthenticationSystem() {
        this.userCredentials = new HashMap<>();
    }

    public boolean signUp(String email, String password) {
        if (!userCredentials.containsKey(email)) {
            userCredentials.put(email, password);
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

}
