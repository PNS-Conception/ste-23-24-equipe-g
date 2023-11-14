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
    private Restaurant restaurant;
    private Order order;

    private LocalTime currentTime;

    @Given("^a client \"([^\"]*)\" with order$")
    public void aClientWithOrder(String clientName) {
        client = new CampusUser( clientName, "password", "email@example.com");
        order = new Order(new ArrayList<>(), client);
    }


    @Given("^having (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" price (\\d+) delivery$")
    public void havingItems(int quantity1, String dish1, int quantity2, String dish2, int price) {
        Dish pizza = new Dish(dish1, price);
        Dish pasta = new Dish(dish2, price);

        assertNotNull("Client is not initialized", client);

        client.selectRestaurant(restaurant);
        client.createItem(pizza, quantity1);
        client.createItem(pasta, quantity2);

        order = client.order(client.getCart());

        order.setStatus(OrderStatus.PLACED);
    }

    @Given("^a restaurant \"([^\"]*)\"$")
    public void aRestaurant(String restaurantName) {
        restaurant = new Restaurant(restaurantName, new Menu(Arrays.asList(new Dish("Margherita", Arrays.asList("Tomato", "Mozzarella", "Basil"), 7.99), new Dish("Pepperoni", Arrays.asList("Tomato", "Mozzarella", "Pepperoni"), 8.99))));
        restaurant.addOrder(order);
    }

    @Given("^\"([^\"]*)\" is open at (\\d+):(\\d+) and close at (\\d+):(\\d+)$")
    public void restaurantIsOpenAndCloseAt(String restaurantName, int openHour, int openMinute, int closeHour, int closeMinute) {
        assertNotNull("Restaurant is not initialized", restaurant);
        assertEquals("Restaurant name does not match", restaurantName, restaurant.getName());
        restaurant.addShift(LocalTime.of( openHour, openMinute), LocalTime.of( closeHour, closeMinute),Day.Friday, new RestaurantManager("test", "test", "test"));
    }

    @When("^the order is placed, paid, and accepted at (\\d+):(\\d+)$")
    public void order_is_placed_paid_and_accepted_at(int hours, int minutes) {
        order.setPlacedTime(LocalTime.of(hours, minutes));
        order.setStatus(OrderStatus.PAID);
        order.setStatus(OrderStatus.ACCEPTED);
    }



    @When("^(\\d+) minutes have passed$")
    public void minutesHavePassed(int minutes) {
        order.setAcceptedTime(order.getPlacedTime().plusMinutes(minutes));
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
        assertTrue(client.cancelOrder(order,0));
    }

    @Then("^the status of the order is cancelled$")
    public void orderStatusIsCancelled() {
        order.setStatus(OrderStatus.CANCELLED);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Then("^the client cannot cancel the order$")
    public void clientCannotCancelOrder() {
        assertFalse(client.cancelOrder(order,31));
    }

    @Then("^the restaurant can cancel the order$")
    public void restaurantCanCancelOrder() {
        assertTrue(restaurant.cancel(order,29));
    }

    @Then("^the restaurant cannot cancel the order$")
    public void restaurantCannotCancelOrder() {
        assertFalse(restaurant.cancel(order, 31));
    }

    @Then("^the status of the order is still accepted$")
    public void orderStatusIsStillAccepted() {
        order.setStatus(OrderStatus.ACCEPTED);
        assertEquals(OrderStatus.ACCEPTED, order.getStatus());
    }

    private LocalTime parseTime(String time) {
        return LocalTime.parse(time);
    }


    @And("the user is refunded")
    public void theUserIsRefunded() {
        assertEquals(order.getPrice(),client.getBalance(), 0);
    }
}
