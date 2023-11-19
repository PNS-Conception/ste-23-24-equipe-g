package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

public class OrderAssignmentSteps {

    private Order order;
    private DeliveryPerson deliveryPerson;
    private DeliverySystem deliverySystem;
        @Given("there is an order ready for delivery")
        public void there_is_an_order_ready_for_delivery() {

            Dish dish = new Dish("Pizza", 10,0); // Name and price
            Item item = new Item(dish, 2); // Dish and quantity
            ArrayList<Item> items = new ArrayList<>();
            items.add(item);


            Restaurant restaurant = new Restaurant("Chez Luigi", new RestaurantManager("","",""),"123 Pizza Street"); // Name and address
            ArrayList<Restaurant> restaurants = new ArrayList<>();
            restaurants.add(restaurant);

            CampusUser user = new CampusUser("John Doe", "password", "johndoe@example.com");


            order = new Order(items);
            order.setStatus(OrderStatus.READY);
        }

    @Given("a delivery person is available")
    public void a_delivery_person_is_available() {

        deliveryPerson = new DeliveryPerson( "John Doe", null,"555-1234");
        deliveryPerson.setAvailable(true);

        deliverySystem = new DeliverySystem();
        deliverySystem.addDeliveryPerson(deliveryPerson);
    }

    @When("the system assigns the order to the delivery person")
    public void the_system_assigns_the_order_to_the_delivery_person() {
        Optional<DeliveryPerson> assignedDeliveryPerson = deliverySystem.assignOrderToDeliveryPerson(order);
        assertTrue("A delivery person should have been assigned", assignedDeliveryPerson.isPresent());
        deliveryPerson = assignedDeliveryPerson.get();
    }


    @Then("the order status should be ASSIGNED")
    public void the_order_status_should_be_assigned() {
        assertEquals(OrderStatus.ASSIGNED, order.getStatus());
    }

    @Then("the delivery person should be unavailable")
    public void the_delivery_person_should_be_unavailable() {
        assertFalse(deliveryPerson.isAvailable());
    }

}
