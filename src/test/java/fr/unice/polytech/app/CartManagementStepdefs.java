package fr.unice.polytech.app;

import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Users.RestaurantManager;
import fr.unice.polytech.app.Users.CampusUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import fr.unice.polytech.app.Restaurant.Dish;
import fr.unice.polytech.app.Restaurant.Item;



import java.util.List;


public class CartManagementStepdefs {
    private CampusUser user;

    @Given("I am a client {string}")
    public void iAmAClient(String username) {
        user = new CampusUser( username, "password", username + "@example.com");
    }

    @Given("I have an empty cart")
    public void iHaveAnEmptyCart() {
        user.getCart().clear();
    }

    @When("I choose a {string} with {int} quantity")
    public void iChooseAWithQuantity(String itemName, int quantity) {
        user.createItem(new Dish(itemName, 0,0), quantity);
    }

    @Then("cart contains {int} item {string} with {int} as the quantity")
    public void cartContainsItemWithAsTheQuantity(int itemCount, String itemName, int quantity) {
        assertEquals(itemCount, user.getCart().getItems().size());
        Item item = findItemByName(user.getCart().getItems(), itemName);
        assertNotNull(item);
        assertEquals(quantity, item.getQuantity());
    }

    @Given("I have a cart that already contains {int} {string}")
    public void iHaveACartThatAlreadyContains(int existingQuantity, String existingItem) {
        user.createItem(new Dish(existingItem, 0,0), existingQuantity);
    }

    @When("I choose a different {string} with {int} quantity")
    public void iChooseADifferentWithQuantity(String newItem, int newQuantity) {
        user.createItem(new Dish(newItem, 0,0), newQuantity);
    }

    @Then("my cart contains {int} {string} and {int} {string}")
    public void myCartContainsAnd(int quantity1, String itemName1, int quantity2, String itemName2) {
        Item item1 = findItemByName(user.getCart().getItems(), itemName1);
        Item item2 = findItemByName(user.getCart().getItems(), itemName2);

        assertNotNull(item1);
        assertNotNull(item2);

        assertEquals(quantity1, item1.getQuantity());
        assertEquals(quantity2, item2.getQuantity());
    }

    @When("I validate my cart of {int} {string}")
    public void iValidateMyCartOf(int quantity, String itemName) throws Exception {

        user.createItem(new Dish(itemName, 0, 0), quantity);

        user.order(new Restaurant("test", new RestaurantManager("test", "test", "test"), "test"));
    }

    @Then("my order contains {int} {string}")
    public void myOrderContains(int quantity, String itemName) {
        SingleOrder lastSingleOrder = user.getHistory().get(user.getHistory().size() - 1);

        assertNotNull(lastSingleOrder);

        Item item = findItemByName(lastSingleOrder.getCart().getItems(), itemName);
        assertNotNull(item);
        assertEquals(quantity, item.getQuantity());
    }

    @When("I want to add {int} more {string}")
    public void iWantToAddMore(int additionalQuantity, String itemName) {
        Item existingItem = findItemByName(user.getCart().getItems(), itemName);
        assertNotNull(existingItem);

        int currentQuantity = existingItem.getQuantity();
        existingItem.setQuantity(currentQuantity + additionalQuantity);
    }


    @Then("the quantity of {string} is set to {int} in the basket")
    public void theQuantityOfIsSetToInTheBasket(String itemName, int newQuantity) {
        Item item = findItemByName(user.getCart().getItems(), itemName);
        assertNotNull(item);
        assertEquals(newQuantity, item.getQuantity());
    }

    @When("I want to remove {int} {string}")
    public void iWantToRemove(int removalQuantity, String itemName) {
        Item existingItem = findItemByName(user.getCart().getItems(), itemName);
        assertNotNull(existingItem);

        int currentQuantity = existingItem.getQuantity();
        int newQuantity = Math.max(currentQuantity - removalQuantity, 0);
        existingItem.setQuantity(newQuantity);
    }


    private Item findItemByName(List<Item> items, String itemName) {
        for (Item item : items) {
            if (item.getDish().getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
}
