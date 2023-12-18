package fr.unice.polytech.app;

import fr.unice.polytech.app.Orders.Cart;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.State.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ValidateStepdefs {

    private SingleOrder singleOrder;
    private Restaurant restaurant;

    @Given("I have an order with status {string}")
    public void i_have_an_order_with_status(String status) throws Exception {
        singleOrder = new SingleOrder();
        switch (status.toLowerCase()) {
            case "placed":
                singleOrder.placeOrder();
            case "paid":
                singleOrder.setStatus(new PlacedIState());
                singleOrder.pay();
                break;
            case "accepted":
                singleOrder.setStatus(new PaidIState());
                singleOrder.accept();
                break;
            case "rejected":
                singleOrder.setStatus(new PaidIState());
                singleOrder.reject();
                break;
            case "ready":
                singleOrder.setStatus(new AcceptedIState());
                singleOrder.ready();
                break;
            case "assigned":
                singleOrder.setStatus(new ReadyIState());
                singleOrder.assign();
                break;
            case "canceled":
                singleOrder.setStatus(new PaidIState());
                singleOrder.cancel();
                break;
            case "pickedup":
                singleOrder.setStatus(new AssignedIState());
                singleOrder.pickUp();
                break;
            case "delivered":
                singleOrder.setStatus(new ValidatedIState());
                singleOrder.deliver();
                break;
            default:
                break;
        }
    }

    @Given("the restaurant is full")
    public void the_restaurant_is_full() {
        restaurant = new Restaurant( null,new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni", 8.99))));
        restaurant.setFull(true);
    }

    @When("the staff accepts the order")
    public void the_staff_accepts_the_order() throws Exception {
        singleOrder.accept();
        assertTrue(singleOrder.getStatus() instanceof AcceptedIState);
    }

    @When("the cashier rejects the order")
    public void the_cashier_rejects_the_order() throws Exception {
        if (restaurant.isFull()) {
            singleOrder.reject();
            assertTrue(singleOrder.getStatus() instanceof RejectedIState);
        }
    }

    @When("the cashier validates the order")
    public void the_cashier_validates_the_order() throws Exception {
        singleOrder.ready();
        assertTrue(singleOrder.getStatus() instanceof ReadyIState);
    }

    @When("the delivery person validates the pick up")
    public void the_delivery_person_validates_the_pick_up() throws Exception {
        singleOrder.pickUp();
        assertTrue(singleOrder.getStatus() instanceof ValidatedIState);
    }

    @When("the delivery person validates the delivery")
    public void the_delivery_person_validates_the_delivery() throws Exception {
        singleOrder.deliver();
        assertTrue(singleOrder.getStatus() instanceof DelivredIState);

    }

    @Then("the status of the order should be {string}")
    public void the_status_of_the_order_should_be(String status) throws Exception {
        switch (status.toLowerCase()) {
            case "placed":
                assertTrue( singleOrder.getStatus() instanceof PlacedIState);
            case "paid":
                assertTrue( singleOrder.getStatus() instanceof PaidIState);
                break;
            case "accepted":
                assertTrue( singleOrder.getStatus() instanceof AcceptedIState);
                break;
            case "rejected":
                assertTrue( singleOrder.getStatus() instanceof RejectedIState);
                break;
            case "ready":
                assertTrue( singleOrder.getStatus() instanceof ReadyIState);
                break;
            case "Assigned":
                assertTrue( singleOrder.getStatus() instanceof AssignedIState);
                break;
            case "canceled":
                assertTrue( singleOrder.getStatus() instanceof CancelledIState);
                break;
            case "pickedup":
                assertTrue( singleOrder.getStatus() instanceof ValidatedIState);
                break;
            case "delivered":
                assertTrue( singleOrder.getStatus() instanceof DelivredIState);
                break;
            default:
                break;
        }
    }

    @Then("the order should be closed")
    public void the_order_should_be_closed() {
        assertTrue(singleOrder.isClosed());
    }

    @When("the staff validates the pick up")
    public void theStaffValidatesThePickUp() throws Exception {
        singleOrder.assign();
        assertTrue(singleOrder.getStatus() instanceof AssignedIState);

    }
}
