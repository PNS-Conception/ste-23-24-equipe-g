package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperationalDataCollectionSteps {

    private DataCollector dataCollector;
    private PointOfSaleSystem pointOfSaleSystem;
    private DeliverySystem deliverySystem;
    private MenuOrderingPlatform menuOrderingPlatform;
    private UserOrderingPlatform userOrderingPlatform;

    @Given("the system is interfacing with the point of sale")
    public void the_system_is_interfacing_with_the_point_of_sale() {
        pointOfSaleSystem = mock(PointOfSaleSystem.class);
        dataCollector = new DataCollector();
    }

    @When("an order is placed")
    public void an_order_is_placed() {
        Order order = new Order(); // Create a mock order
        when(pointOfSaleSystem.placeOrder()).thenReturn(order);
        dataCollector.collectOrderVolumeData(order); // Assuming this method exists
    }

    @Then("the order volume data should be collected")
    public void the_order_volume_data_should_be_collected() {
        assertTrue(dataCollector.hasOrderVolumeData());
    }

    @Given("the system is interfacing with the delivery system")
    public void the_system_is_interfacing_with_the_delivery_system() {
        deliverySystem = mock(DeliverySystem.class);
        dataCollector = new DataCollector();
    }

    @When("a delivery is completed")
    public void a_delivery_is_completed() {
        Delivery delivery = new Delivery(); // Create a mock delivery
        when(deliverySystem.completeDelivery()).thenReturn(delivery);
        dataCollector.collectDeliveryEfficiencyData(delivery); // Assuming this method exists
    }

    @Then("the delivery efficiency data should be collected")
    public void the_delivery_efficiency_data_should_be_collected() {
        assertTrue(dataCollector.hasDeliveryEfficiencyData());
    }

    @Given("the system is interfacing with the menu ordering platform")
    public void the_system_is_interfacing_with_the_menu_ordering_platform() {
        menuOrderingPlatform = mock(MenuOrderingPlatform.class);
        dataCollector = new DataCollector();
    }

    @When("an item is ordered")
    public void an_item_is_ordered() {
        MenuItem menuItem = new MenuItem(); // Create a mock menu item
        when(menuOrderingPlatform.orderItem()).thenReturn(menuItem);
        dataCollector.collectPopularMenuItemData(menuItem); // Assuming this method exists
    }

    @Then("the popularity data of the menu item should be collected")
    public void the_popularity_data_of_the_menu_item_should_be_collected() {
        assertTrue(dataCollector.hasPopularMenuItemData());
    }

    @Given("the system is interfacing with the user ordering platform")
    public void the_system_is_interfacing_with_the_user_ordering_platform() {
        userOrderingPlatform = mock(UserOrderingPlatform.class);
        dataCollector = new DataCollector();
    }

    @When("a user places an order")
    public void a_user_places_an_order() {
        Order order = new Order(); // Create a mock order
        CampusUser user = new CampusUser(); // Create a mock user
        when(userOrderingPlatform.placeOrder(user)).thenReturn(order);
        dataCollector.collectUserBehaviorData(user, order); // Assuming this method exists
    }

    @Then("the user's ordering behavior data should be collected")
    public void the_users_ordering_behavior_data_should_be_collected() {
        assertTrue(dataCollector.hasUserBehaviorData());
    }
}
