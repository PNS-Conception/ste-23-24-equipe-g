
package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import java.util.List;

public class OrderManagementStepdefs {

    private List<String> restaurants;
    private String selectedRestaurant;
    private List<String> menuItems;
    private List<String> cartItems;

    private Order order;

    private Client client;

    private String clientName;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // Code to navigate to the login page
        this.clientName = "Alice";

    }

    @When("I log in as {string}")
    public void iLogInAs(String username) {
        // Code to log in as the given user
        this.client = new Client(clientName);
    }

    @Then("I should be logged in as {string}")
    public void iShouldBeLoggedInAs(String username) {
        // Code to verify the logged-in user
        assertEquals(this.clientName,this.client.getName());
    }

    @When("I view the list of restaurants")
    public void iViewTheListOfRestaurants() {
        restaurants = List.of("Le Délice", "Pizza Palace", "Burger Barn"); // Sample list of restaurants
    }

    @Then("I should see the list of available restaurants")
    public void iShouldSeeTheListOfAvailableRestaurants() {
        ths
        // Code to verify the list of restaurants
    }

    @When("I select the restaurant {string}")
    public void iSelectTheRestaurant(String restaurantName) {
        selectedRestaurant = restaurantName;
        if ("Le Délice".equals(restaurantName)) {
            menuItems = List.of("Pizza Margherita", "Soda", "Salad"); // Sample menu for "Le Délice"
        }
        // Add code for other restaurants' menus if needed
    }

    @Then("I should see the menu of the restaurant {string}")
    public void iShouldSeeTheMenuOfTheRestaurant(String restaurantName) {
        // Code to verify the menu items of the selected restaurant
    }

    @When("I add the item {string} with a quantity of {int} to my cart")
    public void iAddTheItemWithAQuantityToMyCart(String itemName, int quantity) {
        // Code to add the item to the cart
        cartItems.add(itemName); // Adding to the sample cart items list
    }

    @Then("my cart should contain the item {string} with a quantity of {int}")
    public void myCartShouldContainTheItemWithAQuantityOf(String itemName, int quantity) {
        // Code to verify the cart contents
    }

    @When("I validate my cart")
    public void iValidateMyCart() {
        // Code to validate the cart and create an order
    }

    @Then("an order should be created with the items from my cart")
    public void anOrderShouldBeCreatedWithTheItemsFromMyCart() {
        // Code to verify the created order
    }
}
