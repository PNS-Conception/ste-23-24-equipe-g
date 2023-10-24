
/*package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static fr.unice.polytech.app.OrderStatus.VALIDATED;
import static org.junit.Assert.*;
import java.util.List;

public class OrderManagementStepdefs {

    private List<String> restaurants;
    private String selectedRestaurant;
    private List<String> menuItems;
    private List<String> cartItems;
    private Cart cart;
    private CampusUser client ;
    private Order order ;
    private String clientName;


    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // Code to navigate to the login page

    }

    @When("I log in as {string}")
    public void iLogInAs(String username) {
        // Code to log in as the given user
        this.client = new CampusUser(username);
    }

    @Then("I should be logged in as {string}")
    public void iShouldBeLoggedInAs(String username) {
        // Code to verify the logged-in user
        assertEquals(username,this.client.getName());
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        // Code to verify the logged-in user
        assertEquals(username,this.client.getName());
    }

    @When("I view the list of restaurants")
    public void iViewTheListOfRestaurants() {
        restaurants = List.of("Le Délice", "Pizza Palace", "Burger Barn"); // Sample list of restaurants
    }

    @Then("I should see the list of available restaurants")
    public void iShouldSeeTheListOfAvailableRestaurants() {
        // Code to verify the list of restaurants
        assertEquals(restaurants.size(),3);
    }

    @Given("I am viewing the list of restaurants")
    public void iAmViewingTheListOfRestaurants() {
        assertEquals(this.restaurants.size(),3);
    }

    @When("I select the restaurant {string}")
    public void iSelectTheRestaurant(String restaurantName) {
        this.cart = new Cart(this.client);
        if ("Le Délice".equals(restaurantName)) {
            menuItems = List.of("Pizza Margherita", "Soda", "Salad"); // Sample menu for "Le Délice"
        }
        this.cart.setRestaurant(restaurantName);
        // Add code for other restaurants' menus if needed
    }

    @Then("I should see the menu of the restaurant {string}")
    public void iShouldSeeTheMenuOfTheRestaurant(String restaurantName) {
        // Code to verify the menu items of the selected restaurant
        assertEquals(restaurants.get(0),this.cart.getRestaurant());
        assertTrue(menuItems.size()!=0);
    }

    @Given("I am viewing the menu of the restaurant {string}")
    public void iAmViewingTheMenuOfTheRestaurant(String restaurant) {
        assertEquals(restaurants.get(0),this.cart.getRestaurant());
        assertTrue(menuItems.size()!=0);
    }
    @When("I add the item {string} with a quantity of {int} to my cart")
    public void iAddTheItemWithAQuantityToMyCart(String itemName, int quantity) {
        // Code to add the item to the cart
        this.cart.addItem(itemName,quantity); // Adding to the sample cart items list
    }

    @Then("my cart should contain the item {string} with a quantity of {int}")
    public void myCartShouldContainTheItemWithAQuantityOf(String itemName, int quantity) {
        // Code to verify the cart contents
        assertTrue(this.cart.containsThatItem(itemName));
        assertEquals(this.cart.getItemQuantity(itemName),quantity);
    }

    @Given("I have items in my cart")
    public void iHaveItemsInMyCart() {
        // Code to verify the cart contents
        assertFalse(this.cart.isEmpty());

    }

    @When("I validate my cart")
    public void iValidateMyCart() {
        this.cart.setCartStatus(VALIDATED);
        // Code to validate the cart and create an order
    }

    @Then("an order should be created with the items from my cart")
    public void anOrderShouldBeCreatedWithTheItemsFromMyCart() {
        assertEquals(this.cart.getCartStatus(),VALIDATED);
        this.order = new Order(this.client,this.cart);
        // Code to verify the created order
    }

}*/
