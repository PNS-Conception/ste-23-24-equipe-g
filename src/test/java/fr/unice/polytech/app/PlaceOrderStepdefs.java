package fr.unice.polytech.app;

import fr.unice.polytech.app.State.PlacedIState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlaceOrderStepdefs {

    private CampusUser client;
    private Restaurant restaurant=new Restaurant("test", new RestaurantManager("test", "test", "test"), "test");
    private LocalDateTime deliveryDateTime;

    private List<Item> cart;
    private LocalTime deliveryTime;
    private SingleOrder singleOrder;

    @Given("a client {string} with a cart")
    public void aClientWithAOrder(String name) throws Exception {
        client = new CampusUser( name, "password", "email@example.com");
        singleOrder = new SingleOrder(new ArrayList<>());
    }

    @And("having {int} {string} and {int} {string} price {int}")
    public void havingAndPriceDeliveryTimeIs(int quantity1, String dish1, int quantity2, String dish2, int price) {
        Dish pizza = new Dish(dish1, price);
        Dish pasta = new Dish(dish2, price);

        client.selectRestaurant(restaurant);
        client.createItem(pizza, quantity1);
        client.createItem(pasta, quantity2);


    }

    @Given("^placed at (\\d+):(\\d+) (\\d+)\\|(\\d+)\\|(\\d+)$")
    public void placed_at(int hour, int minute, int day, int month, int year) {
        LocalTime placedTime = LocalTime.of(hour, minute);
        singleOrder.setPlacedTime(placedTime);
    }

    @Given("^le délice is open at (\\d+):(\\d+) and close at (\\d+):(\\d+)$")
    public void le_délice_is_open_at_and_close_at(int openHour, int openMinute, int closeHour, int closeMinute) {
        restaurant.addShift(LocalTime.of(openHour, openMinute), LocalTime.of(closeHour, closeMinute),Day.Friday ,new RestaurantManager("test", "test", "test"));
    }

    @Given("^the restaurant is open$")
    public void the_restaurant_is_open() {
        // Assume the restaurant is already set as open in the background
    }

    @Given("^client choose the address \"([^\"]*)\"$")
    public void client_choose_the_address(String address) {
        singleOrder.setClientAddress(address);
    }

    @When("the client create the order delivery time is {int}:{int} {int}|{int}|{int}")
    public void the_client_crate_the_order( int hour, int minute, int day, int month, int year) throws Exception {
        deliveryTime = LocalTime.of(hour, minute);
        deliveryDateTime = LocalDateTime.of(year, month, day, deliveryTime.getHour(), deliveryTime.getMinute());
        singleOrder = client.order(client.getCart(), restaurant);
        singleOrder.setDeliveryTime(deliveryDateTime.toLocalTime());
        singleOrder.setPlacedTime(LocalTime.now());
        singleOrder.placeOrder();
    }

    @Then("^the order status is placed")
    public void the_order_status_is_placed() {
        assertTrue(singleOrder.getStatus() instanceof PlacedIState);
    }



}
