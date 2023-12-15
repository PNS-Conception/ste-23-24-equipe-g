package fr.unice.polytech.app;

import fr.unice.polytech.app.Delivery.DeliveryPerson;
import fr.unice.polytech.app.Orders.GroupOrder;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.State.*;
import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Util.RandomGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ManageGroupOrderStepdefs {

    CampusUser alice = new CampusUser("alice",null,null);

    CampusUser bob = new CampusUser("bob",null,null);
    GroupOrder groupOrder;
    SingleOrder aliceSingleOrder;

    SingleOrder bobSingleOrder;
    Restaurant restaurant1;
    Restaurant restaurant2;
    Restaurant restaurant3;

    RandomGenerator mockRandomGenerator = Mockito.mock(RandomGenerator.class);

    //DeliveryPerson deliveryPerson=new DeliveryPerson("deliveryPerson",null,null,null);
    DeliveryPerson deliveryPerson = new DeliveryPerson( "Nom du livreur", null,"Numéro de téléphone");

    @Given("a order group with {string} the owner")
    public void aOrderGroupWithTheOwner(String name) throws Exception {
        alice= new CampusUser(name,null,null);
        groupOrder = new GroupOrder(alice);
    }

    @And("Bob is a member of the order group")
    public void bobIsAMemberOfTheOrderGroup() {
        bob= new CampusUser("Bob",null,null);
        groupOrder.addMember(bob,alice);
    }


    @When("Alice has placed order from the restaurant {string} and paid for it")
    public void hasAPlaceOrderFromTheRestaurantAndPaidForIt( String restaurant) throws Exception {
        restaurant1=new Restaurant(restaurant, new Menu(Arrays.asList(new Dish("Margherita",  7.99), new Dish("Pepperoni",8.99))));
        alice.createItem(new Dish("pizza",10), 2);
        aliceSingleOrder = new SingleOrder(alice.getCart(),alice,restaurant1 );
        groupOrder.addOrder(aliceSingleOrder);
        aliceSingleOrder.placeOrder();
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        alice.setRandomGenerator(mockRandomGenerator);
        alice.makePaymentmock(aliceSingleOrder, alice);
    }

    @And("Bob has placed order form {string} and paid for it")
    public void bobHasPlacedOrderFormAndPaidForIt(String restaurant) throws Exception {
        restaurant2=new Restaurant(restaurant, new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99))));
        bob.createItem(new Dish("pasta",11), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob,restaurant2);
        groupOrder.addOrder(bobSingleOrder);
        bobSingleOrder.placeOrder();
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        bob.setRandomGenerator(mockRandomGenerator);
        bob.makePaymentmock(bobSingleOrder, bob);

    }

    @Then("the order group status should be Paid")
    public void theOrderGroupStatusShouldBe() throws Exception {
        assertTrue(bobSingleOrder.getStatus() instanceof PaidIState);
        assertTrue(aliceSingleOrder.getStatus() instanceof PaidIState);
        groupOrder.pay();
        assertTrue(groupOrder.getStatus() instanceof PaidIState);
    }

    @And("the restaurants le déclice and Vapiano receive the order")
    public void theRestaurantsAndReciveTheOrder() {
        restaurant1.addOrder(aliceSingleOrder);
        restaurant2.addOrder(bobSingleOrder);
    }

    @And("Bob has a place order form {string} and not paid for it")
    public void bobHasAPlaceOrderFormAndNotPaidForIt(String restaurant) throws Exception {
        restaurant3=new Restaurant(restaurant, new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni", 8.99))));
        bob.createItem(new Dish("pasta",11), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob,restaurant3 );
        groupOrder.addOrder(bobSingleOrder);
        bobSingleOrder.placeOrder();
    }

    @And("the restaurants does not receive the order")
    public void theRestaurantsDoesNotReceiveTheOrder() {
        assertEquals(restaurant3.getOrderList().size(),0);
    }

    @Given("group order is paid")
    public void groupOrderIsPaid() throws Exception {
        groupOrder.pay();
        restaurant1 = new Restaurant("restaurant", new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99))));
        restaurant3 = new Restaurant("restaurant", new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni", 8.99))));
    }

    @When("Bob cancels his order")
    public void bobCancelsHisOrder() throws Exception {
        bob.createItem(new Dish("pasta",11), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob,restaurant3 );
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        bobSingleOrder.user.setRandomGenerator(mockRandomGenerator);
        bobSingleOrder.getPaidMock();
        groupOrder.cancelOrder(bobSingleOrder,bob,2);
        groupOrder.quit(bob);
    }

    @Then("the order group still should be Paid")
    public void theOrderGroupStillShouldBe() {
        assertTrue(groupOrder.getStatus() instanceof PaidIState);
    }

    @And("Bob should not be a member of the order group")
    public void shouldNotBeAMemberOfTheOrderGroup() {
        boolean b= groupOrder.ismember(bob);
        assertFalse(b);
    }

    @When("all members cancels their order")
    public void allMembersCancelsTheirOrder() throws Exception {
        bob.createItem(new Dish("pasta",11,0), 1);
        bobSingleOrder = new SingleOrder(bob.getCart(), bob, restaurant3);
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        bobSingleOrder.user.setRandomGenerator(mockRandomGenerator);
        bobSingleOrder.getPaidMock();
        alice.createItem(new Dish("pasta",11,0), 1);
        aliceSingleOrder = new SingleOrder(alice.getCart(), alice, restaurant1);
        aliceSingleOrder.user.setRandomGenerator(mockRandomGenerator);
        aliceSingleOrder.getPaidMock();
        groupOrder.cancelOrder(bobSingleOrder,bob,2);
        groupOrder.quit(bob);
        groupOrder.cancelOrder(aliceSingleOrder,alice,2);
        groupOrder.quit(alice);
        groupOrder.cancel();
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
    public void allSubOrdersAreAccepted() throws Exception {
        restaurant2=new Restaurant("restaurant", new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99))));
        restaurant1=new Restaurant("restaurant", new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99))));
        alice.createItem(new Dish("pasta",11,0), 1);
        aliceSingleOrder =alice.order(alice.getCart(),restaurant1);
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        aliceSingleOrder.user.setRandomGenerator(mockRandomGenerator);
        aliceSingleOrder.getPaidMock();
        aliceSingleOrder.accept();
        bob.createItem(new Dish("pasta",11,0), 1);
        bobSingleOrder = bob.order(bob.getCart(),restaurant2);
        bobSingleOrder.user.setRandomGenerator(mockRandomGenerator);
        bobSingleOrder.getPaidMock();
        bobSingleOrder.accept();
        groupOrder = new GroupOrder(alice);
        groupOrder.addMember(bob,alice);
        groupOrder.addOrder(aliceSingleOrder);
        groupOrder.addOrder(bobSingleOrder);
    }

    @When("all sub orders are ready")
    public void allSubOrdersAreReady() throws Exception {
        aliceSingleOrder.setStatus(new AcceptedIState());
        aliceSingleOrder.setStatus(new AcceptedIState());
        aliceSingleOrder.ready();
        bobSingleOrder.ready();
        groupOrder.setStatus(new AcceptedIState());
        groupOrder.ready();
    }

    @And("all members should be notified")
    public void allMembersShouldBeNotified() {
        alice.getNotified();
        bob.getNotified();
    }

    @Given("group order is assigned")
    public void groupOrderIsReady() throws Exception {
        groupOrder.setStatus(new AssignedIState());
        //groupOrder.ready();
    }

    @When("the delivery person validates group order")
    public void theDeliveryPersonValidatesGroupOrder() throws Exception {
        deliveryPerson.validateOrder(groupOrder);
    }

    @Given("group order is picked up")
    public void groupOrderIsPickedUp() throws Exception {
        groupOrder.setStatus(new ValidatedIState());
    }

    @When("the delivery person delivers the order")
    public void theDeliveryPersonDeliversTheOrder() throws Exception {
        deliveryPerson.deliverOrder(groupOrder);
    }


    @Then("the order group status should be not paid")
    public void theOrderGroupStatusShouldBeNotPaid() {
        assertNotEquals(groupOrder.getStatus() instanceof PaidIState,true);
    }


    @Then("the order group status should be Canceled")
    public void theOrderGroupStatusShouldBeCanceled() {
        assertTrue(groupOrder.getStatus() instanceof CancelledIState);

    }


    @Then("the order group status should be Ready")
    public void theOrderGroupStatusShouldBeReady() {

        assertTrue(groupOrder.getStatus() instanceof ReadyIState);
    }

    @Then("the order group status should be Picked up")
    public void theOrderGroupStatusShouldBePickedUp() {
        assertTrue(groupOrder.getStatus() instanceof ValidatedIState);
    }

    @Then("the order group status should be Delivered")
    public void theOrderGroupStatusShouldBeDelivered() {
        assertTrue(groupOrder.getStatus() instanceof DelivredIState);

    }

    @And("Bob should be refunded")
    public void bobShouldBeRefunded() {
        assertEquals(11,bob.getBalance(),0);
    }

}
