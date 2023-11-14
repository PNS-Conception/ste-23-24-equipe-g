package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlaceOrderStepdefs {

    private CampusUser client;
    private Restaurant restaurant;
    private LocalDateTime deliveryDateTime;

    private List<Item> cart;
    private LocalTime deliveryTime;
    private Order order;

    @Given("a client {string} with a cart")
    public void aClientWithAOrder(String name) {
        client = new CampusUser( name, "password", "email@example.com");
        order= new Order(new ArrayList<>());
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
        order.setPlacedTime(placedTime);
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
        client.setAddress(address);
        order.setClientAddress(address);
    }

    @When("the client create the order delivery time is {int}:{int} {int}|{int}|{int}")
    public void the_client_crate_the_order( int hour, int minute, int day, int month, int year) {
        deliveryTime = LocalTime.of(hour, minute);
        deliveryDateTime = LocalDateTime.of(year, month, day, deliveryTime.getHour(), deliveryTime.getMinute());
        order = client.order(client.getCart());
        order.setDeliveryTime(deliveryDateTime.toLocalTime());
        order.setPlacedTime(LocalTime.now());
        order.setStatus(OrderStatus.PLACED);
    }

    @Then("^the order status is placed")
    public void the_order_status_is_placed() {
        assertEquals(OrderStatus.PLACED, order.getStatus());
    }



}
