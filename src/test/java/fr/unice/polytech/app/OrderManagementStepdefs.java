package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static fr.unice.polytech.app.CartStatus.VALIDATED;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Étapes de définition pour les scénarios Cucumber relatifs à la gestion des commandes.
 */
public class OrderManagementStepdefs {

    /** Liste des restaurants disponibles. */
    private List<String> restaurants;

    /** Restaurant sélectionné par le client. */
    private String selectedRestaurant;

    /** Éléments du menu du restaurant sélectionné. */
    private List<String> menuItems;

    /** Éléments du panier du client. */
    private List<String> cartItems;

    /** Panier du client. */
    private Cart cart;

    /** Client actuel utilisé dans les scénarios. */
    private Client client;

    /** Commande associée au panier dans les scénarios. */
    private Order order;

    /** Nom du client. */
    private String clientName;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // Code pour naviguer vers la page de connexion
    }

    @When("I log in as {string}")
    public void iLogInAs(String username) {
        this.client = new Client(username);
    }

    @Then("I should be logged in as {string}")
    public void iShouldBeLoggedInAs(String username) {
        assertEquals(username, this.client.getName());
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        assertEquals(username, this.client.getName());
    }

    @When("I view the list of restaurants")
    public void iViewTheListOfRestaurants() {
        restaurants = List.of("Le Délice", "Pizza Palace", "Burger Barn");
    }

    @Then("I should see the list of available restaurants")
    public void iShouldSeeTheListOfAvailableRestaurants() {
        assertEquals(restaurants.size(), 3);
    }

    @Given("I am viewing the list of restaurants")
    public void iAmViewingTheListOfRestaurants() {
        assertEquals(this.restaurants.size(), 3);
    }

    @When("I select the restaurant {string}")
    public void iSelectTheRestaurant(String restaurantName) {
        this.cart = new Cart(this.client);
        if ("Le Délice".equals(restaurantName)) {
            menuItems = List.of("Pizza Margherita", "Soda", "Salad");
        }
        this.cart.setRestaurant(restaurantName);
    }

    @Then("I should see the menu of the restaurant {string}")
    public void iShouldSeeTheMenuOfTheRestaurant(String restaurantName) {
        assertEquals(restaurants.get(0), this.cart.getRestaurant());
        assertTrue(menuItems.size() != 0);
    }

    @Given("I am viewing the menu of the restaurant {string}")
    public void iAmViewingTheMenuOfTheRestaurant(String restaurant) {
        assertEquals(restaurants.get(0), this.cart.getRestaurant());
        assertTrue(menuItems.size() != 0);
    }

    @When("I add the item {string} with a quantity of {int} to my cart")
    public void iAddTheItemWithAQuantityToMyCart(String itemName, int quantity) {
        this.cart.addItem(itemName, quantity);
    }

    @Then("my cart should contain the item {string} with a quantity of {int}")
    public void myCartShouldContainTheItemWithAQuantityOf(String itemName, int quantity) {
        assertTrue(this.cart.containsThatItem(itemName));
        assertEquals(this.cart.getItemQuantity(itemName), quantity);
    }

    @Given("I have items in my cart")
    public void iHaveItemsInMyCart() {
        assertFalse(this.cart.isEmpty());
    }

    @When("I validate my cart")
    public void iValidateMyCart() {
        this.cart.setCartStatus(VALIDATED);
    }

    @Then("an order should be created with the items from my cart")
    public void anOrderShouldBeCreatedWithTheItemsFromMyCart() {
        assertEquals(this.cart.getCartStatus(), VALIDATED);
        this.order = new Order(this.client, this.cart);
    }
}
