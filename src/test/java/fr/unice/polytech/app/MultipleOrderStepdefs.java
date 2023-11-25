package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class MultipleOrderStepdefs {

    CampusUser alice = new CampusUser("alice",null,null);;
    MultipleOrder multipleOrder = new MultipleOrder(alice);;
    Restaurant restaurant;
    Restaurant restaurant2;
    SingleOrder aliceSingleOrder;
    SingleOrder aliceSingleOrder2;

    DeliveryPerson deliveryPerson = new DeliveryPerson( "Nom du livreur", null,"Numéro de téléphone");

    @When("{string} create a multiple order")
    public void createAMultipleOrder(String arg0) {
        alice= new CampusUser(arg0,null,null);
        multipleOrder = new MultipleOrder(alice);
    }

    @And("Alice set the delivery address to {string} to mulpiple order")
    public void aliceSetTheDeliveryAddressToToMulpipleOrder(String arg0) {
        multipleOrder.setDeliveryLocation(arg0);
    }

    @Then("the multiple order should be created")
    public void theMultipleOrderShouldBeCreated() {
        assertNotEquals(multipleOrder,null);
        assertEquals(multipleOrder.getStatus(), OrderStatus.PLACED);
    }

    @And("Alice should be the owner of the multiple order")
    public void aliceShouldBeTheOwnerOfTheMultipleOrder() {
        assertEquals(multipleOrder.getOwner(), alice);
    }

    @Given("alice create a order from restaurant {string}")
    public void aliceCreateAOrderFromRestaurant(String arg0) {
        restaurant=new Restaurant(arg0, new Menu());
        multipleOrder = new MultipleOrder(alice);
        alice = new CampusUser("alice",null,null);
        alice.selectRestaurant(restaurant);
        alice.createItem(new Dish("pizza",10), 2);
        aliceSingleOrder = new SingleOrder(alice.getCart(),alice,restaurant );
        multipleOrder.addOrder(aliceSingleOrder);
    }

    @When("she add a order to the multiple order")
    public void sheAddAOrderToTheMultipleOrder() {
        alice.createItem(new Dish("pasta",11), 1);
        aliceSingleOrder2 = new SingleOrder(alice.getCart(),alice,restaurant );
        multipleOrder.addOrder(aliceSingleOrder2);
    }

    @Then("the long of the list of orders should be {int} in multiple order")
    public void theLongOfTheListOfOrdersShouldBeInMultipleOrder(int arg0) {
        assertEquals(multipleOrder.getSubOrders().size(),arg0);
    }

    @Given("the order is paid")
    public void theOrderIsPaid() {
        alice = new CampusUser("alice",null,null);
        aliceSingleOrder = new SingleOrder(alice.getCart(),alice,restaurant );
        multipleOrder = new MultipleOrder(alice);
        multipleOrder.addOrder(aliceSingleOrder);
        alice.makePayment(aliceSingleOrder, alice);
    }

    @When("Alice cancel the order")
    public void aliceCancelTheOrder() {
        alice.cancelOrder(aliceSingleOrder,12);
    }

    @Then("the order should be canceled")
    public void theOrderShouldBeCanceled() {
        assertEquals(aliceSingleOrder.getStatus(),OrderStatus.CANCELLED);
    }

    @And("the order should be removed from the multiple order")
    public void theOrderShouldBeRemovedFromTheMultipleOrder() {
        assertEquals(multipleOrder.getSubOrders().size(),1);
    }

    @And("Alice should be refunded with order amount")
    public void aliceShouldBeRefundedWithOrderAmount() {
        assertEquals(alice.getBalance(),aliceSingleOrder.getPrice(),0.01);
        multipleOrder.clearSubOrders();
    }

    @When("Alice has placed order from the restaurant {string} and paid for it and added to multiple order")
    public void aliceHasPlacedOrderFromTheRestaurantAndPaidForItAndAddedToMultipleOrder(String arg0) {
        multipleOrder = new MultipleOrder(alice);
        alice.createItem(new Dish("pizza",10), 2);
        restaurant=new Restaurant(arg0, new Menu());
        aliceSingleOrder = new SingleOrder(alice.getCart(),alice,restaurant );
        alice.makePayment(aliceSingleOrder, alice);
        multipleOrder.addOrder(aliceSingleOrder);

    }

    @And("Alice has placed another order form {string} and paid for it and added to multiple order")
    public void aliceHasPlacedAnotherOrderFormAndPaidForItAndAddedToMultipleOrder(String arg0) {
        alice.createItem(new Dish("pasta",11), 1);
        restaurant2=new Restaurant(arg0, new Menu());
        aliceSingleOrder2 = new SingleOrder(alice.getCart(),alice,restaurant );
        alice.makePayment(aliceSingleOrder2, alice);
        multipleOrder.addOrder(aliceSingleOrder2);
    }

    @Then("the multiple order status should be paid")
    public void theMultipleOrderStatusShouldBePaid() {
        assertTrue(multipleOrder.isOrdersPaid());
    }

    @And("the restaurants le déclice and Vapiano receive the orders")
    public void theRestaurantsLeDecliceAndVapianoReceiveTheOrders() {
        restaurant.addOrder(aliceSingleOrder);
        restaurant2.addOrder(aliceSingleOrder2);
        restaurant.clearOrder();
        restaurant2.clearOrder();
    }

    @When("the order is sent to the restaurant")
    public void theOrderIsSentToTheRestaurant() {
        restaurant2 = new Restaurant("Vapiano", new Menu());
        restaurant2.addOrder(aliceSingleOrder);
    }


    @And("the other order from {string} is not paid yet")
    public void theOtherOrderFromIsNotPaidYet(String arg0) {
        restaurant2=new Restaurant(arg0, new Menu());
        alice.createItem(new Dish("pasta",11), 1);
        aliceSingleOrder2 = new SingleOrder(alice.getCart(),alice,restaurant );
        multipleOrder.addOrder(aliceSingleOrder2);
    }

    @Then("the multiple order status should be not paid")
    public void theMultipleOrderStatusShouldBeNotPaid() {
        assertFalse(multipleOrder.isOrdersPaid());
    }

    @And("the first restaurant receive the order")
    public void theFirstRestaurantReceiveTheOrder() {
        restaurant.addOrder(aliceSingleOrder);
    }

    @And("the second restaurant does not receive the order")
    public void theSecondRestaurantDoesNotReceiveTheOrder() {
        restaurant2.clearOrder();
        assertEquals(restaurant2.getOrderList().size(),0);
    }

    @Given("all sub orders paid")
    public void allSubOrdersPaid() {
        aliceSingleOrder = new SingleOrder(alice.getCart(),alice,restaurant );
        aliceSingleOrder2 = new SingleOrder(alice.getCart(),alice,restaurant );

        alice.makePayment(aliceSingleOrder, alice);
        alice.makePayment(aliceSingleOrder2, alice);
    }

    @When("the owner cancels all orders")
    public void theOwnerCancelsAllOrders() {
        alice.cancelOrder(aliceSingleOrder,12);
        alice.cancelOrder(aliceSingleOrder2,12);
    }

    @Then("the multiple order status should be Canceled")
    public void theMultipleOrderStatusShouldBeCanceled() {
        assertTrue(multipleOrder.isOrderCancelled());
    }

    @And("owner should be refunded")
    public void ownerShouldBeRefunded() {
        assertEquals(alice.getBalance(),aliceSingleOrder.getPrice()+aliceSingleOrder2.getPrice(),0.01);
    }

    @And("the multiple order should be deleted")
    public void theMultipleOrderShouldBeDeleted() {
        multipleOrder.delete();
        assertEquals(multipleOrder.getStatus(),OrderStatus.CANCELLED);

    }

    @Given("multiple order is ready")
    public void multipleOrderIsReady() {
        aliceSingleOrder = new SingleOrder(alice.getCart(),alice,restaurant );
        aliceSingleOrder2 = new SingleOrder(alice.getCart(),alice,restaurant );
        aliceSingleOrder.setStatus(OrderStatus.READY);
        aliceSingleOrder2.setStatus(OrderStatus.READY);
    }

    @When("the delivery person validates multiple order")
    public void theDeliveryPersonValidatesMultipleOrder() {
        deliveryPerson.validateOrder(multipleOrder);

    }

    @Then("the multiple order status should be Picked up")
    public void theMultipleOrderStatusShouldBePickedUp() {
        assertEquals(multipleOrder.getStatus(),OrderStatus.PICKED_UP);

    }

    @Given("multiple order is picked up")
    public void multipleOrderIsPickedUp() {

    }

    @When("the delivery person delivers the multiple order")
    public void theDeliveryPersonDeliversTheMultipleOrder() {
        deliveryPerson.deliverOrder(multipleOrder);
    }

    @Then("the multiple order status should be Delivered")
    public void theMultipleOrderStatusShouldBeDelivered() {
        assertEquals(multipleOrder.getStatus(),OrderStatus.DELIVERED);
    }
}
