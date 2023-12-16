package fr.unice.polytech.app;

import fr.unice.polytech.app.Delivery.DeliveryPerson;
import fr.unice.polytech.app.Orders.Order;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.Statistic.DataCollector;
import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Users.RestaurantManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OperationalDataCollectionSteps {

    private DataCollector dataCollector;
    private Order testOrder;
    private DeliveryPerson testDeliveryPerson;
    private Item testMenuItem;
    private CampusUser testUser;

    @Given("the system is interfacing with the point of sale")
    public void the_system_is_interfacing_with_the_point_of_sale() throws Exception {
        dataCollector = new DataCollector();
        Dish dish = new Dish("Pizza", 10.0);
        Item item = new Item(dish, 2);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        testOrder = new SingleOrder( new CampusUser("user123","null", "User123"), new Restaurant("test", new RestaurantManager("test", "test", "test"), "test"));
    }

    @When("an order is placed")
    public void an_order_is_placed() {
        dataCollector.collectOrderData(testOrder);
    }

    @Then("the order volume data should be collected")
    public void the_order_volume_data_should_be_collected() {
        List<Order> orders = dataCollector.getOrders();
        assertTrue(orders.contains(testOrder));
    }

    @Given("the system is interfacing with the delivery system")
    public void the_system_is_interfacing_with_the_delivery_system() {
        dataCollector = new DataCollector();
        testDeliveryPerson = new DeliveryPerson("id", "Delivery Person", "123456789");
    }

    @When("a delivery is completed")
    public void a_delivery_is_completed() {
        dataCollector.collectDeliveryData(testDeliveryPerson);
    }

    @Then("the delivery efficiency data should be collected")
    public void the_delivery_efficiency_data_should_be_collected() {
        List<DeliveryPerson> deliveryPeople = dataCollector.getDeliveryPeople();
        assertTrue(deliveryPeople.contains(testDeliveryPerson));
    }

    @Given("the system is interfacing with the menu ordering platform")
    public void the_system_is_interfacing_with_the_menu_ordering_platform() {
        dataCollector = new DataCollector();
        testMenuItem = new Item(new Dish("Test Dish", 10.0), 1);
    }

    @When("an item is ordered")
    public void an_item_is_ordered() {
        dataCollector.collectMenuItemPopularityData(testMenuItem);
    }

    @Then("the popularity data of the menu item should be collected")
    public void the_popularity_data_of_the_menu_item_should_be_collected() {
        Map<Item, Integer> menuItemPopularity = dataCollector.getMenuItemPopularity();
        assertTrue(menuItemPopularity.containsKey(testMenuItem));
    }

    @Given("the system is interfacing with the user ordering platform")
    public void the_system_is_interfacing_with_the_user_ordering_platform() throws Exception {
        dataCollector = new DataCollector();
        testUser = new CampusUser();
        Dish dish = new Dish("Pizza", 10.0);
        Item item = new Item(dish, 2);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        testOrder = new SingleOrder( testUser, new Restaurant("test", new RestaurantManager("test", "test", "test"), "test"));
    }

    @When("a user places an order")
    public void a_user_places_an_order() {
        dataCollector.collectUserBehaviorData(testUser, testOrder);
    }

    @Then("the user's ordering behavior data should be collected")
    public void the_users_ordering_behavior_data_should_be_collected() {
        Map<CampusUser, List<Order>> userOrders = dataCollector.getUserOrders();
        assertTrue(userOrders.containsKey(testUser) && userOrders.get(testUser).contains(testOrder));
    }
}
