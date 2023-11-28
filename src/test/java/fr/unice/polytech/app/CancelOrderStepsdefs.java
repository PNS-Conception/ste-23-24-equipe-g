package fr.unice.polytech.app;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CancelOrderStepsdefs {

    private CampusUser client;
    private Restaurant restaurant=new Restaurant("test", new RestaurantManager("test", "test", "test"), "test");
    private SingleOrder singleOrder;

    private LocalTime currentTime;

    @Given("^a client \"([^\"]*)\" with order$")
    public void aClientWithOrder(String clientName) {
        client = new CampusUser( clientName, "password", "email@example.com");
        singleOrder = new SingleOrder(new ArrayList<>(), client,restaurant);
    }


    @Given("^having (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" price (\\d+) delivery$")
    public void havingItems(int quantity1, String dish1, int quantity2, String dish2, int price) {
        Dish pizza = new Dish(dish1, price);
        Dish pasta = new Dish(dish2, price);

        assertNotNull("Client is not initialized", client);

        client.selectRestaurant(restaurant);
        client.createItem(pizza, quantity1);
        client.createItem(pasta, quantity2);

        singleOrder = client.order(client.getCart(),restaurant);

        singleOrder.setStatus(OrderStatus.PLACED);
    }

    @Given("^a restaurant \"([^\"]*)\"$")
    public void aRestaurant(String restaurantName) {
        restaurant = new Restaurant(restaurantName, new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni", 8.99))));
        restaurant.addOrder(singleOrder);
    }

    @Given("^\"([^\"]*)\" is open at (\\d+):(\\d+) and close at (\\d+):(\\d+)$")
    public void restaurantIsOpenAndCloseAt(String restaurantName, int openHour, int openMinute, int closeHour, int closeMinute) {
        assertNotNull("Restaurant is not initialized", restaurant);
        assertEquals("Restaurant name does not match", restaurantName, restaurant.getName());
        restaurant.addShift(LocalTime.of( openHour, openMinute), LocalTime.of( closeHour, closeMinute),Day.Friday, new RestaurantManager("test", "test", "test"));
    }

    @When("^the order is placed, paid, and accepted at (\\d+):(\\d+)$")
    public void order_is_placed_paid_and_accepted_at(int hours, int minutes) {
        singleOrder.setPlacedTime(LocalTime.of(hours, minutes));
        singleOrder.setStatus(OrderStatus.PAID);
        singleOrder.setStatus(OrderStatus.ACCEPTED);
    }



    @When("^(\\d+) minutes have passed$")
    public void minutesHavePassed(int minutes) {
        singleOrder.setAcceptedTime(singleOrder.getPlacedTime().plusMinutes(minutes));
    }

    @When("^it is still accepted$")
    public void itIsStillAccepted() {
        // The order is still accepted
    }

    @When("^the current time is (\\d+:\\d+)$")
    public void currentTimeIs(String currentTime) {
        this.currentTime = (parseTime(currentTime));
    }

    @Then("^client can cancel order$")
    public void clientCanCancelOrder() {
        assertTrue(client.cancelOrder(singleOrder,0));
    }

    @Then("^the status of the order is cancelled$")
    public void orderStatusIsCancelled() {
        singleOrder.setStatus(OrderStatus.CANCELLED);
        assertEquals(OrderStatus.CANCELLED, singleOrder.getStatus());
    }

    @Then("^the client cannot cancel the order$")
    public void clientCannotCancelOrder() {
        assertFalse(client.cancelOrder(singleOrder,31));
    }

    @Then("^the restaurant can cancel the order$")
    public void restaurantCanCancelOrder() {
        assertTrue(restaurant.cancel(singleOrder,29));
    }

    @Then("^the restaurant cannot cancel the order$")
    public void restaurantCannotCancelOrder() {
        assertFalse(restaurant.cancel(singleOrder, 31));
    }

    @Then("^the status of the order is still accepted$")
    public void orderStatusIsStillAccepted() {
        singleOrder.setStatus(OrderStatus.ACCEPTED);
        assertEquals(OrderStatus.ACCEPTED, singleOrder.getStatus());
    }

    private LocalTime parseTime(String time) {
        return LocalTime.parse(time);
    }


    @And("the user is refunded")
    public void theUserIsRefunded() {
        assertEquals(singleOrder.getPrice(),client.getBalance(), 0);
    }
}
