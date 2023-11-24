package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ManageGroupOrderStepdefs {

    CampusUser alice;

    CampusUser bob;
    GroupOrder groupOrder;
    SingleOrder aliceSingleOrder;

    SingleOrder bobSingleOrder;
    Restaurant restaurant1;
    Restaurant restaurant2;
    Restaurant restaurant3;

    //DeliveryPerson deliveryPerson=new DeliveryPerson("deliveryPerson",null,null,null);
    DeliveryPerson deliveryPerson = new DeliveryPerson( "Nom du livreur", null,"Numéro de téléphone");

    @Given("a order group with {string} the owner")
    public void aOrderGroupWithTheOwner(String name) {
        alice= new CampusUser(name,null,null);
        groupOrder = new GroupOrder(alice);
    }

    @And("Bob is a member of the order group")
    public void bobIsAMemberOfTheOrderGroup() {
        bob= new CampusUser("Bob",null,null);
        groupOrder.addMember(bob,alice);
    }


    @When("Alice has placed order from the restaurant {string} and paid for it")
    public void hasAPlaceOrderFromTheRestaurantAndPaidForIt( String restaurant) {
        restaurant1=new Restaurant(restaurant, new Menu(Arrays.asList(new Dish("Margherita",  7.99), new Dish("Pepperoni",8.99))));
        alice.createItem(new Dish("pizza",10), 2);
        aliceSingleOrder = new SingleOrder(alice.getCart(),alice,restaurant1 );
        groupOrder.addOrder(aliceSingleOrder);
        aliceSingleOrder.setStatus(OrderStatus.PLACED);
        alice.makePayment(aliceSingleOrder, alice);
    }

    @And("Bob has placed order form {string} and paid for it")
    public void bobHasPlacedOrderFormAndPaidForIt(String restaurant) {
        restaurant2=new Restaurant(restaurant, new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99))));
        bob.createItem(new Dish("pasta",11), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob,restaurant2);
        groupOrder.addOrder(bobSingleOrder);
        bobSingleOrder.setStatus(OrderStatus.PLACED);
        bob.makePayment(bobSingleOrder, bob);

    }

    @Then("the order group status should be Placed")
    public void theOrderGroupStatusShouldBe() {
        assertEquals(bobSingleOrder.getStatus(),OrderStatus.PAID);
        assertEquals(aliceSingleOrder.getStatus(),OrderStatus.PAID);
        groupOrder.setStatus(OrderStatus.PAID);
        assertEquals(groupOrder.getStatus(),OrderStatus.PAID);
    }

    @And("the restaurants le déclice and Vapiano receive the order")
    public void theRestaurantsAndReciveTheOrder() {
        restaurant1.addOrder(aliceSingleOrder);
        restaurant2.addOrder(bobSingleOrder);
    }

    @And("Bob has a place order form {string} and not paid for it")
    public void bobHasAPlaceOrderFormAndNotPaidForIt(String restaurant) {
        restaurant3=new Restaurant(restaurant, new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni", 8.99))));
        bob.createItem(new Dish("pasta",11), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob,restaurant3 );
        groupOrder.addOrder(bobSingleOrder);
        bobSingleOrder.setStatus(OrderStatus.PLACED);
    }

    @And("the restaurants does not receive the order")
    public void theRestaurantsDoesNotReceiveTheOrder() {
        assertEquals(restaurant3.getOrderList().size(),0);
    }

    @Given("group order is paid")
    public void groupOrderIsPaid() {
        groupOrder.setStatus(OrderStatus.PAID);
        restaurant1 = new Restaurant("restaurant", new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99))));
        restaurant3 = new Restaurant("restaurant", new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni", 8.99))));
    }

    @When("Bob cancels his order")
    public void bobCancelsHisOrder() {
        bob.createItem(new Dish("pasta",11), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob,restaurant3 );
        groupOrder.cancelOrder(bobSingleOrder,bob,2);
        groupOrder.quit(bob);
    }

    @Then("the order group still should be Paid")
    public void theOrderGroupStillShouldBe() {
        assertEquals(groupOrder.getStatus(),OrderStatus.PAID);
    }

    @And("Bob should not be a member of the order group")
    public void shouldNotBeAMemberOfTheOrderGroup() {
        boolean b= groupOrder.ismember(bob);
        assertFalse(b);
    }

    @When("all members cancels their order")
    public void allMembersCancelsTheirOrder() {
        bob.createItem(new Dish("pasta",11,0), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob, restaurant3);
        alice.createItem(new Dish("pasta",11,0), 1);
        aliceSingleOrder = new SingleOrder(alice.getCart(), alice, restaurant1);
        groupOrder.cancelOrder(bobSingleOrder,bob,2);
        groupOrder.quit(bob);
        groupOrder.cancelOrder(aliceSingleOrder,alice,2);
        groupOrder.quit(alice);
        groupOrder.setStatus(OrderStatus.CANCELLED);
    }

    @And("all members should be refunded")
    public void allMembersShouldBeRefunded() {
        assertEquals(11, alice.getBalance(),0);
        assertEquals(11,bob.getBalance(),0);
    }

    @And("the group should be deleted")
    public void theGroupShouldBeDeleted() {
        assertEquals(groupOrder.getSubOrders().size(),0);
    }

    @Given("all sub orders are accepted")
    public void allSubOrdersAreAccepted() {
        restaurant2=new Restaurant("restaurant", new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99))));
        restaurant1=new Restaurant("restaurant", new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99))));
        alice.createItem(new Dish("pasta",11,0), 1);
        aliceSingleOrder = new SingleOrder(alice.getCart(), alice, restaurant1);
        aliceSingleOrder.setStatus(OrderStatus.ACCEPTED);
        bob.createItem(new Dish("pasta",11,0), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob , restaurant2);
        bobSingleOrder.setStatus(OrderStatus.ACCEPTED);
    }

    @When("all sub orders are ready")
    public void allSubOrdersAreReady() {
        aliceSingleOrder.setStatus(OrderStatus.READY);
        bobSingleOrder.setStatus(OrderStatus.READY);
        groupOrder.setStatus(OrderStatus.READY);
    }

    @And("all members should be notified")
    public void allMembersShouldBeNotified() {
        alice.notifyUser();
        bob.notifyUser();
    }

    @Given("group order is ready")
    public void groupOrderIsReady() {
        groupOrder.setStatus(OrderStatus.READY);
    }

    @When("the delivery person validates group order")
    public void theDeliveryPersonValidatesGroupOrder() {
        deliveryPerson.validateOrder(groupOrder);
    }

    @Given("group order is picked up")
    public void groupOrderIsPickedUp() {
        groupOrder.setStatus(OrderStatus.PICKED_UP);
    }

    @When("the delivery person delivers the order")
    public void theDeliveryPersonDeliversTheOrder() {
        deliveryPerson.deliverOrder(groupOrder);
    }


    @Then("the order group status should be not paid")
    public void theOrderGroupStatusShouldBeNotPaid() {
        assertNotEquals(groupOrder.getStatus(),OrderStatus.PAID);
    }


    @Then("the order group status should be Canceled")
    public void theOrderGroupStatusShouldBeCanceled() {
        assertEquals(groupOrder.getStatus(),OrderStatus.CANCELLED);
    }


    @Then("the order group status should be Ready")
    public void theOrderGroupStatusShouldBeReady() {
        assertEquals(groupOrder.getStatus(),OrderStatus.READY);
    }

    @Then("the order group status should be Picked up")
    public void theOrderGroupStatusShouldBePickedUp() {

        assertEquals(OrderStatus.PICKED_UP,groupOrder.getStatus());
    }

    @Then("the order group status should be Delivered")
    public void theOrderGroupStatusShouldBeDelivered() {
        assertEquals(groupOrder.getStatus(),OrderStatus.DELIVERED);
    }

    @And("Bob should be refunded")
    public void bobShouldBeRefunded() {
        assertEquals(11,bob.getBalance(),0);
    }
}
