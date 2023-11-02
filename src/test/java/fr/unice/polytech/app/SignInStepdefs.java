package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class SignInStepdefs {

    private AuthenticationSystem authenticationSystem;

    private boolean signInResult;
    private boolean logInResult;

    @Given("given users with email {string} and password {string}")
    public void givenUsersWithEmailAndPassword(String email, String password) {
        authenticationSystem = new AuthenticationSystem();
        logInResult =authenticationSystem.signUp(email, password);
    }


    @And("the <email> and <password> are stored in the database")
    public void theEmailAndPasswordAreStoredInTheDatabase(String email, String password) {
        assertTrue(authenticationSystem.isCredentialsStored(email, password));
    }


    @Then("the user is not signed up")
    public void theUserIsNotSignedUp() {
        assertFalse(signInResult);
    }

    @And("the email or password is incorrect")
    public void theEmailOrPasswordIsIncorrect() {
        assertFalse(signInResult);
    }


    @And("the {string} and {string} are stored in the database")
    public void theAndAreStoredInTheDatabase(String email ,String password) {
        signInResult = authenticationSystem.logIn(email, password);

    }

    @Then("the user is signed in")
    public void theUserIsSignedIn() {
        assertTrue(logInResult);
    }
}
