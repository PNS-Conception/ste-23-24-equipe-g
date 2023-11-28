package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ManageGroupStepdefs {

    CampusUser alice;

    CampusUser bob;
    GroupOrder groupOrder;
    SingleOrder aliceSingleOrder;



    @When("{string} create a group order")
    public void createAGroupOrder(String arg0) {
        alice = new CampusUser(arg0, null, null);
        groupOrder = new GroupOrder(alice);

    }

    @When("Alice set the delivery address to {string}")
    public void set_the_delivery_address_to(String address) {
        groupOrder.setDeliveryAddress(address);
    }

    @Then("the group order should be created")
    public void the_group_order_should_be_created() {
        assertNotEquals(groupOrder.getMembers().size(), 0);
    }

    @Then("Alice should be the owner of the group order")
    public void should_be_the_owner_of_the_group_order() {
        assertEquals(groupOrder.getOwner(), alice);
    }

    @Then("Alice should be the only member of the group order")
    public void should_be_the_only_member_of_the_group_order() {
        assertEquals(groupOrder.getMembers().size(), 1);
        assertEquals(groupOrder.getMembers().get(0), alice);
    }

    @Given("a group is created")
    public void a_group_is_created() {
        alice = new CampusUser("Alice", null, null);
        groupOrder = new GroupOrder(alice);

    }

    @And("Alice is the owner of the group order add {string} to the group order")
    public void isTheOwnerOfTheGroupOrderAddToTheGroupOrder(String user) {
        bob = new CampusUser(user, null, null);
        groupOrder.addMember(bob, alice);
    }


    @Then("the group order should have {int} members Alice and Bob")
    public void theGroupOrderShouldHaveMembersAnd(int nbMembers) {
        assertEquals(groupOrder.getMembers().size(), nbMembers);
        assertTrue(groupOrder.ismember(alice));
        assertTrue(groupOrder.ismember(bob));
    }


    @And("Alice is the owner of the group order")
    public void aliceIsTheOwnerOfTheGroupOrder() {
        assertEquals(groupOrder.getOwner(), alice);
    }

    @And("Bob is a member of the group order")
    public void isAMemberOfTheGroupOrder() {
        groupOrder.addMember(bob, alice);

    }

    @When("Alice delete Bob from the group order")
    public void deleteFromTheGroupOrder() {
        groupOrder.exclude(alice, bob);
    }


    @Then("the group order should have {int} member Alice")
    public void theGroupOrderShouldHaveMember(int nbMembers) {
        assertEquals(groupOrder.getMembers().size(), nbMembers);
        assertTrue(groupOrder.ismember(alice));
    }

    @When("Bob leave the group order")
    public void bobLeaveTheGroupOrder() {
        groupOrder.quit(bob);
    }

    @And("Bob should not be a member of the group order")
    public void bobShouldNotBeAMemberOfTheGroupOrder() {
        assertFalse(groupOrder.ismember(bob));
    }

    @When("Alice delete the group order")
    public void aliceDeleteTheGroupOrder() {
        groupOrder.deleteGroup(alice);
        groupOrder.setOwner(null);
    }

    @Then("the group order should be deleted")
    public void theGroupOrderShouldBeDeleted() {
        assertEquals(groupOrder.getSubOrders().size(), 0);
    }

    @And("Alice should not be a member of the group order")
    public void aliceShouldNotBeAMemberOfTheGroupOrder() {
        assertFalse(groupOrder.ismember(alice));
    }

    @When("the group order is Accepted")
    public void theGroupOrderIsAccepted() {
        groupOrder.setStatus(OrderStatus.ACCEPTED);
    }

    @Then("Alice can not delete the group order")
    public void aliceCanNotDeleteTheGroupOrder() {
        boolean b = groupOrder.deleteGroup(alice);
        assertFalse(b);
    }

    @When("the group order is Rejected")
    public void theGroupOrderIsRejected() {
        groupOrder.setStatus(OrderStatus.REJECTED);
    }

    @Then("Alice can delete the group order")
    public void aliceCanDeleteTheGroupOrder() {
        groupOrder = new GroupOrder(alice);

        boolean b = groupOrder.deleteGroup(alice);
        assertTrue(b);
    }

    @And("Sam is a member of the group order")
    public void samIsAMemberOfTheGroupOrder() {
        CampusUser sam = new CampusUser("Sam", null, null);
        groupOrder.addMember(sam, alice);
    }

    @And("the group order is placed")
    public void theGroupOrderIsPlaced() {
        groupOrder.setStatus(OrderStatus.PLACED);
    }

    @When("Alice leave the group order")
    public void aliceLeaveTheGroupOrder() {
        alice.createItem(new Dish("pizza",10,0), 2);
        aliceSingleOrder = new SingleOrder(alice.getCart(), alice,new Restaurant("test", new RestaurantManager("test", "test", "test"), "test"));
        groupOrder.addOrder(aliceSingleOrder);
        groupOrder.quit(alice);
        groupOrder.setOwner(groupOrder.getMembers().get(0));
        groupOrder.cancelOrder(aliceSingleOrder, alice, 2);
    }

    @Then("Alice order should be deleted from the group order")
    public void herOrderShouldBeDeletedFromTheGroupOrder() {
        assertEquals(groupOrder.getSubOrders().size(), 0);
    }

    @And("Alice should be refunded")
    public void aliceShouldBeRefunded() {
        assertEquals(20, alice.getBalance(), 0);
    }

    @And("Bob should be the owner of the group order")
    public void bobShouldBeTheOwnerOfTheGroupOrder() {
        assertEquals(groupOrder.getOwner(), bob);

    }


}


