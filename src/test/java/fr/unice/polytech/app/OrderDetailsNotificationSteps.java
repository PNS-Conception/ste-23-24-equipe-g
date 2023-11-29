//package fr.unice.polytech.app;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import io.cucumber.java.en.Then;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.UUID;
//
//import static org.junit.Assert.*;
//
//public class OrderDetailsNotificationSteps {
//
//    private DeliverySystem deliverySystem;
//    private DeliveryPerson deliveryPerson;
//    private Order order;
//    private CampusUser user;
//
//    @Given("an order has been assigned to a delivery person")
//    public void an_order_has_been_assigned_to_a_delivery_person() {
//        deliverySystem = new DeliverySystem();
//        deliveryPerson = new DeliveryPerson(UUID.randomUUID().toString(), "Delivery Guy", "+334567890");
//        Dish dish = new Dish("Pizza", 10.0);
//        Item item = new Item(dish, 2);
//        order = new Order(Arrays.asList(item));
//        order.setStatus(OrderStatus.ASSIGNED);
//        deliveryPerson.setCurrentOrder(order);
//        deliverySystem.addDeliveryPerson(deliveryPerson);
//    }
//
//    @When("the delivery person receives the order details")
//    public void the_delivery_person_receives_the_order_details() {
//        // Simulate the delivery person receiving the order details
//        deliveryPerson.receiveOrderDetails(order);
//    }
//
//    @Then("the delivery person should have details including route, pickup time, restaurants, and delivery location")
//    public void the_delivery_person_should_have_details_including_route_pickup_time_restaurants_and_delivery_location() {
//        // Verify that the delivery person has all the details
//        assertEquals("Expected route details", deliveryPerson.getRouteDetails());
//        assertEquals("Expected pickup time", deliveryPerson.getPickupTime());
//        assertEquals("Expected list of restaurants", deliveryPerson.getRestaurants());
//        assertEquals("Expected delivery location", deliveryPerson.getDeliveryLocation());
//    }
//
//    @Given("an order is assigned to a delivery person")
//    public void an_order_is_assigned_to_a_delivery_person() {
//        // Reuse the first Given method as both scenarios start with this condition
//        an_order_has_been_assigned_to_a_delivery_person();
//        // Assume User class has an appropriate constructor or a setter for ID and phone number
//        user = new CampusUser("user123", "User123"); // Example of a possible User constructor
//        order.setRouteDetails("Expected route details");
//        order.setPickupTime(LocalTime.of(10, 0)); // Utilisez une heure de prise en charge réelle
//        order.setRestaurants(new ArrayList<>()); // Ajoutez des restaurants réels ici
//        order.setDeliveryLocation("Expected delivery location");
//
//        // Assign the order to the delivery person
//        deliverySystem.assignOrderToDeliveryPerson(order);
//    }
//
//    @When("the system sends the delivery details to the user")
//    public void the_system_sends_the_delivery_details_to_the_user() {
//        // Simulate the system sending the delivery details to the user
//        user.receiveDeliveryDetails(deliveryPerson.getId().toString(), deliveryPerson.getPhoneNumber());
//    }
//
//
//    @Then("the user should receive the delivery person's ID and phone number")
//    public void the_user_should_receive_the_delivery_person_s_ID_and_phone_number() {
//        // Les valeurs attendues devraient être celles que vous avez définies dans le scénario de test
//        String expectedId = deliveryPerson.getId().toString();
//        String expectedPhoneNumber = deliveryPerson.getPhoneNumber();
//
//        assertEquals(expectedId, user.getDeliveryPersonIdReceived());
//        assertEquals(expectedPhoneNumber, user.getDeliveryPersonPhoneNumberReceived());
//    }
//
//}
package fr.unice.polytech.app;

import fr.unice.polytech.app.State.ReadyIState;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderDetailsNotificationSteps {

    private DeliverySystem deliverySystem;
    private DeliveryPerson deliveryPerson;
    private SingleOrder singleOrder;

    private Restaurant restaurant;
    private CampusUser user;

    @Given("an order has been assigned to a delivery person")
    public void an_order_has_been_assigned_to_a_delivery_person() throws Exception {
        deliverySystem = new DeliverySystem();
        deliveryPerson = new DeliveryPerson( "Delivery Guy", null, "+334567890");
        Dish dish = new Dish("Pizza", 10,0);
        Item item = new Item(dish, 2);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        restaurant = new Restaurant("test", new RestaurantManager("test", "test", "test"), "test");
        user = new CampusUser("user123","null", "User123");
        singleOrder = new SingleOrder(items,user,restaurant);
        singleOrder.setStatus(new ReadyIState());
        singleOrder.assign();
        singleOrder.setRouteDetails("Some route");
        singleOrder.setPickupTime(LocalTime.now());
        singleOrder.setDeliveryLocation("Some delivery location");
        deliveryPerson.setCurrentOrder(singleOrder);
        deliverySystem.addDeliveryPerson(deliveryPerson);
    }

    @When("the delivery person receives the order details")
    public void the_delivery_person_receives_the_order_details() {
        deliveryPerson.receiveOrderDetails(singleOrder);
    }

    @Then("the delivery person should have details including route, pickup time, restaurants, and delivery location")
    public void the_delivery_person_should_have_details_including_route_pickup_time_restaurants_and_delivery_location() {
        assertEquals(singleOrder.getRouteDetails(), deliveryPerson.getRouteDetails());
        assertEquals(singleOrder.getPickupTime(), deliveryPerson.getPickupTime());
        assertEquals(singleOrder.getRestaurants(), deliveryPerson.getRestaurants());
        assertEquals(singleOrder.getDeliveryLocation(), deliveryPerson.getDeliveryLocation());
    }

    @Given("an order is assigned to a delivery person")
    public void an_order_is_assigned_to_a_delivery_person() {
        //an_order_has_been_assigned_to_a_delivery_person();
        user = new CampusUser("user123","null", "User123"); // Assume User is a defined class
        deliverySystem= new DeliverySystem();
        deliveryPerson = new DeliveryPerson( "Delivery Guy", null,"+334567890");
    }

    @When("the system sends the delivery details to the user")
    public void the_system_sends_the_delivery_details_to_the_user() {
        deliverySystem.notifyUserWithDeliveryDetails(user, deliveryPerson);
    }

    @Then("the user should receive the delivery person's ID and phone number")
    public void the_user_should_receive_the_delivery_person_s_ID_and_phone_number() {
        String expectedId = deliveryPerson.getId().toString(); // Convert UUID to String
        String expectedPhoneNumber = deliveryPerson.getPhoneNumber();

        assertEquals(expectedId, user.getNotifiedDeliveryPersonId());
        assertEquals(expectedPhoneNumber, user.getNotifiedDeliveryPersonPhoneNumber());
    }


}
