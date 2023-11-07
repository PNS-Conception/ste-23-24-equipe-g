package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class ValidateStepdefs {

    private Order order;
    private Restaurant restaurant;

    @Given("I have an order with status {string}")
    public void i_have_an_order_with_status(String status) {
        order = new Order(new ArrayList<>());
        order.setStatus(OrderStatus.valueOf(status));
    }

    @Given("the restaurant is full")
    public void the_restaurant_is_full() {
        restaurant = new Restaurant(null, null);
        restaurant.setFull(true);
    }

    @When("the staff accepts the order")
    public void the_staff_accepts_the_order() {
        order.accept();
    }

    @When("the cashier rejects the order")
    public void the_cashier_rejects_the_order() {
        if (restaurant.isFull()) {
            order.reject();
        }
    }

    @When("the cashier validates the order")
    public void the_cashier_validates_the_order() {
        order.validate();
    }

    @When("the delivery person validates the pick up")
    public void the_delivery_person_validates_the_pick_up() {
        order.pickUp();
    }

    @When("the delivery person validates the delivery")
    public void the_delivery_person_validates_the_delivery() {
        order.deliver();
    }

    @Then("the status of the order should be {string}")
    public void the_status_of_the_order_should_be(String expectedStatus) {
        assertEquals(OrderStatus.valueOf(expectedStatus), order.getStatus());
    }

    @Then("the order should be closed")
    public void the_order_should_be_closed() {
        assertTrue(order.isClosed());
    }

}
