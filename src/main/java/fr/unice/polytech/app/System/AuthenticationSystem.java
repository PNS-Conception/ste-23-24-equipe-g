package fr.unice.polytech.app.System;

import fr.unice.polytech.app.Users.CampusUser;

import java.util.HashMap;
import java.util.Map;

/**
 * The AuthenticationSystem class handles user authentication and registration.
 */
public class AuthenticationSystem {
    private Map<String, String> userCredentials;
    Admin admin;

    /**
     * Constructs an AuthenticationSystem object.
     * Initializes the userCredentials map and creates an Admin object.
     */
    public AuthenticationSystem() {
        this.userCredentials = new HashMap<>();
        admin = new Admin();
    }

    /**
     * Registers a new user with the provided name, email, and password.
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @return true if the user is successfully registered, false otherwise
     */
    public boolean signUp(String name, String email, String password) {
        if (!userCredentials.containsKey(email)) {
            userCredentials.put(email, password);
            admin.addCampusUser(name, password, email);
            return true;
        }
        return false;
    }

    /**
     * Logs in a user with the provided username and password.
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user is successfully logged in, false otherwise
     */
    public boolean logIn(String username, String password) {
        return isCredentialsStored(username, password);
    }

    /**
     * Checks if the provided email and password match the stored credentials.
     * @param email the email to check
     * @param password the password to check
     * @return true if the credentials match, false otherwise
     */
    public boolean isCredentialsStored(String email, String password) {
        return userCredentials.containsKey(email) && userCredentials.get(email).equals(password);
    }

    /**
     * Updates the userCredentials map with the credentials of all campus users.
     */
    public void userCredentials() {
        for (CampusUser user : admin.getCampusUsers()) {
            userCredentials.put(user.getEmail(), user.getPassword());
        }
    }
}
