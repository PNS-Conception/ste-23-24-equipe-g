package fr.unice.polytech.app;

import fr.unice.polytech.app.System.AuthenticationSystem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class LoginStepdefs {


    private AuthenticationSystem authenticationSystem=new AuthenticationSystem();
    private boolean loginResult;

    @Given("the user with email {string} and password {string} in database")
    public void givenUserInDatabase(String email, String password) {
        authenticationSystem.signUp("name",email, password);
    }

    @When("User enters email {string} and password {string}")
    public void whenUserEntersCredentials(String email, String password) {
        loginResult = authenticationSystem.logIn(email, password);
    }

    @Then("the user is logged in")
    public void thenUserIsLoggedIn() {
        assertTrue(loginResult);
    }

    @Then("the user is not logged in")
    public void thenUserIsNotLoggedIn() {
        assertFalse(loginResult);
    }

}
