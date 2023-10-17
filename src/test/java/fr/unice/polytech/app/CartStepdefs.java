package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class CartStepdefs {

    private Cart cart;
    private Client client;
    private Order order;

    @Given("I am a client {string}")
    public void i_am_a_client(String clientName) {
        this.client = new Client(clientName);
        this.cart = new Cart(this.client);
        this.order = new Order(this.client, "", 0);
    }

    @Given("I have an empty cart")
    public void i_have_an_empty_cart() {
        this.cart.emptyCart();
    }

    @Given("I have a cart that already contains {int} {string}")
    public void i_have_a_cart_that_already_contains(int quantity, String item) {
        this.cart.addItem(item, quantity);
    }

    @When("I choose a {string} with {int} quantity")
    public void i_choose_a_with_quantity(String item, int quantity) {
        this.cart.addItem(item, quantity);
    }

    @When("I want to add {int} more {string}")
    public void i_want_to_add_more(int quantity, String item) {
        this.cart.addItem(item, quantity);
    }

    @When("I want to remove {int} {string}")
    public void i_want_to_remove(int quantity, String item) {
        this.cart.removeItem(item, quantity);
    }

    @When("I validate my cart of {int} {string}")
    public void i_validate_my_cart_of(int quantity, String item) {
        order = this.cart.validateCart(item, quantity);
    }

    @Then("cart contains {int} item {string} with {int} as the quantity")
    public void cart_contains_item_with_as_the_quantity(int itemCount, String item, int quantity) {
        assertEquals(quantity, this.cart.getItemQuantity(item));
    }

    @Then("my cart contains {int} {string} and {int} {string}")
    public void my_cart_contains_and(int quantity1, String item1, int quantity2, String item2) {
        assertEquals(quantity1, this.cart.getItemQuantity(item1));
        assertEquals(quantity2, this.cart.getItemQuantity(item2));
    }

    @Then("my order contains {int} {string}")
    public void my_order_contains(int quantity, String item) {
        Order order = this.cart.validateCart(item, quantity);
        System.out.println(order.toString());
        assertEquals(quantity, order.getQuantity());
        assertEquals(item, order.getItem());
    }

    @Then("the quantity of {string} is set to {int} in the basket")
    public void the_quantity_of_is_set_to_in_the_basket(String item, int quantity) {
        assertEquals(quantity, this.cart.getItemQuantity(item));
    }
}
