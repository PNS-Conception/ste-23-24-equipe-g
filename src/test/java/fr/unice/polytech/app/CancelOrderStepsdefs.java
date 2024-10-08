package fr.unice.polytech.app;


import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.Day;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.Restaurant.RestaurantManager;
import fr.unice.polytech.app.State.AcceptedIState;
import fr.unice.polytech.app.State.CancelledIState;
import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.Util.RandomGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CancelOrderStepsdefs {

    private CampusUser client;
    private Restaurant restaurant=new Restaurant("test", new RestaurantManager("test", "test", "test"), "test");
    private SingleOrder singleOrder;

    private LocalTime currentTime;

    RandomGenerator mockRandomGenerator = Mockito.mock(RandomGenerator.class);

    @Given("^a client \"([^\"]*)\" with order$")
    public void aClientWithOrder(String clientName) throws Exception {
        client = new CampusUser( clientName, "password", "email@example.com");
        singleOrder = new SingleOrder( client,restaurant);
    }


    @Given("^having (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" price (\\d+) delivery$")
    public void havingItems(int quantity1, String dish1, int quantity2, String dish2, int price) throws Exception {
        Dish pizza = new Dish(dish1, price);
        Dish pasta = new Dish(dish2, price);

        assertNotNull("Client is not initialized", client);

        client.selectRestaurant(restaurant);
        client.createItem(pizza, quantity1);
        client.createItem(pasta, quantity2);

        singleOrder = client.order(restaurant);

        singleOrder.placeOrder();
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
        restaurant.addShift(LocalTime.of( openHour, openMinute), LocalTime.of( closeHour, closeMinute), Day.Friday, new RestaurantManager("test", "test", "test"));
    }

    @When("^the order is placed, paid, and accepted at (\\d+):(\\d+)$")
    public void order_is_placed_paid_and_accepted_at(int hours, int minutes) throws Exception {
        singleOrder.setPlacedTime(LocalTime.of(hours, minutes));
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        singleOrder.getClient().getPaiementSystem().setRandomGenerator(mockRandomGenerator);
        singleOrder.getPaidMock();
        singleOrder.accept();
    }



    @When("^(\\d+) minutes have passed$")
    public void minutesHavePassed(int minutes) {
        singleOrder.setAcceptedTime(singleOrder.getPlacedTime().plusMinutes(minutes));
    }

    @When("^it is still accepted$")
    public void itIsStillAccepted() {

        // The order is still accepted
        singleOrder.setStatus(new AcceptedIState());
    }

    @When("^the current time is (\\d+:\\d+)$")
    public void currentTimeIs(String currentTime) {
        this.currentTime = (parseTime(currentTime));
    }

    @Then("^client can cancel order$")
    public void clientCanCancelOrder() throws Exception {
        assertTrue(client.cancelOrder(singleOrder,0));
    }

    @Then("^the status of the order is cancelled$")
    public void orderStatusIsCancelled() throws Exception {

        assertTrue(singleOrder.getStatus() instanceof CancelledIState);
    }

    @Then("^the client cannot cancel the order$")
    public void clientCannotCancelOrder() throws Exception {
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
    public void orderStatusIsStillAccepted() throws Exception {
        System.out.println(singleOrder.getStatus());
        assertTrue(singleOrder.getStatus() instanceof AcceptedIState);
    }

    private LocalTime parseTime(String time) {
        return LocalTime.parse(time);
    }


    @And("the user is refunded")
    public void theUserIsRefunded() {
        assertEquals(singleOrder.getPrice(),client.getBalance(), 0);
    }
}
