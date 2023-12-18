package fr.unice.polytech.app;

import fr.unice.polytech.app.Delivery.DeliveryPerson;
import fr.unice.polytech.app.Delivery.DeliverySystem;
import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.State.*;
import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.Restaurant.RestaurantManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class OrderDeliveryConfirmationSteps {

    private DeliverySystem deliverySystem = new DeliverySystem();
    private DeliveryPerson deliveryPerson;

    private SingleOrder singleOrder;

    @Given("an order is {string} to a delivery person")
    public void an_order_is_assigned_to_a_delivery_person(String status) throws Exception {
        Dish dish = new Dish("Pizza", 10,0);
        Item item = new Item(dish, 2);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);

        singleOrder = new SingleOrder( new CampusUser("user123","null", "User123"), new Restaurant("test", new RestaurantManager("test", "test", "test"), "test"));
        switch (status.toLowerCase()) {
            case "placed":
                singleOrder.placeOrder();
            case "paid":
                singleOrder.setStatus(new PlacedIState());
                singleOrder.pay();
                break;
            case "accepted":
                singleOrder.setStatus(new PaidIState());
                singleOrder.accept();
                break;
            case "rejected":
                singleOrder.setStatus(new PaidIState());
                singleOrder.reject();
                break;
            case "ready":
                singleOrder.setStatus(new AcceptedIState());
                singleOrder.ready();
                break;
            case "assigned":
                singleOrder.setStatus(new ReadyIState());
                singleOrder.assign();
                break;
            case "canceled":
                singleOrder.setStatus(new PaidIState());
                singleOrder.cancel();
                break;
            case "pickedup":
                singleOrder.setStatus(new AssignedIState());
                singleOrder.pickUp();
                break;
            case "delivered":
                singleOrder.setStatus(new ValidatedIState());
                singleOrder.deliver();
                break;
            default:
                break;
        }

        deliveryPerson = new DeliveryPerson( "Delivery Person",null, "123456789");
        deliverySystem.addDeliveryPerson(deliveryPerson);
        //deliveryPerson.assignOrder(singleOrder);
    }

    @When("the delivery person finalizes the delivery")
    public void the_delivery_person_finalizes_the_delivery() throws Exception {
        deliveryPerson.markOrderAsDelivered();
    }

    @And("the user confirms receipt on their phone")
    public void the_user_confirms_receipt_on_their_phone() throws Exception {
        singleOrder.confirmReceipt();
    }

    @Then("the order status should be {string}")
    public void the_order_status_should_be(String expectedStatus) {
        switch (expectedStatus.toLowerCase()) {
            case "placed":
                assertTrue( singleOrder.getStatus() instanceof PlacedIState);
            case "paid":
                assertTrue( singleOrder.getStatus() instanceof PaidIState);
                break;
            case "accepted":
                assertTrue( singleOrder.getStatus() instanceof AcceptedIState);
                break;
            case "rejected":
                assertTrue( singleOrder.getStatus() instanceof RejectedIState);
                break;
            case "ready":
                assertTrue( singleOrder.getStatus() instanceof ReadyIState);
                break;
            case "Assigned":
                assertTrue( singleOrder.getStatus() instanceof AssignedIState);
                break;
            case "canceled":
                assertTrue( singleOrder.getStatus() instanceof CancelledIState);
                break;
            case "pickedup":
                assertTrue( singleOrder.getStatus() instanceof ValidatedIState);
                break;
            case "delivered":
                assertTrue( singleOrder.getStatus() instanceof DelivredIState);
                break;
            default:
                break;
        }
    }

    @And("the delivery person's status should be {string}")
    public void the_delivery_person_s_status_should_be(String status) {
        boolean expectedAvailability = "AVAILABLE".equalsIgnoreCase(status);
        assertEquals(expectedAvailability, deliveryPerson.isAvailable());
    }

    @When("the delivery person provides an order without user confirmation")
    public void the_delivery_person_provides_an_order_without_user_confirmation() throws Exception {
        singleOrder.deliverWithoutConfirmation();
    }

    @Then("the order should require a signature and identity verification")
    public void the_order_should_require_a_signature_and_identity_verification() {
        assertTrue(singleOrder.requiresSignatureAndVerification());
    }


    @Given("the user cannot confirm receipt")
    public void the_user_cannot_confirm_receipt() throws Exception {
        // Simulez la situation où l'utilisateur ne peut pas confirmer la réception
        deliveryPerson = new DeliveryPerson( "Delivery Person",null, "123456789");
        singleOrder = new SingleOrder(new CampusUser("user123","null", "User123"), new Restaurant("test", new RestaurantManager("test", "test", "test"), "test"));
        singleOrder.setUserUnableToConfirm();
        singleOrder.setStatus(new ReadyIState());
        deliveryPerson.assignOrder(singleOrder);


    }

    @When("the delivery person finalizes the delivery without user confirmation")
    public void the_delivery_person_finalizes_the_delivery_without_user_confirmation() throws Exception {
        // Simulez la finalisation de la livraison sans confirmation de l'utilisateur
        // Cette méthode doit définir le statut de la commande en DELIVERED et mettre à jour l'état du livreur
        singleOrder.setStatus(new ValidatedIState());
        deliveryPerson.finalizeDeliveryWithoutUserConfirmation(singleOrder);
    }
}
