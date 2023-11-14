package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class OrderDeliveryConfirmationSteps {

    private DeliverySystem deliverySystem = new DeliverySystem();
    private DeliveryPerson deliveryPerson;
    private Order order;

    @Given("an order is {string} to a delivery person")
    public void an_order_is_assigned_to_a_delivery_person(String status) {
        Dish dish = new Dish("Pizza", 10.0);
        Item item = new Item(dish, 2);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);

        order = new Order(items);
        order.setStatus(OrderStatus.valueOf(status));

        deliveryPerson = new DeliveryPerson( "Delivery Person",null, "123456789");
        deliverySystem.addDeliveryPerson(deliveryPerson);
        deliveryPerson.assignOrder(order);
    }

    @When("the delivery person finalizes the delivery")
    public void the_delivery_person_finalizes_the_delivery() {
        deliveryPerson.markOrderAsDelivered();
    }

    @And("the user confirms receipt on their phone")
    public void the_user_confirms_receipt_on_their_phone() {
        order.confirmReceipt();
    }

    @Then("the order status should be {string}")
    public void the_order_status_should_be(String expectedStatus) {
        assertEquals(OrderStatus.valueOf(expectedStatus), order.getStatus());
    }

    @And("the delivery person's status should be {string}")
    public void the_delivery_person_s_status_should_be(String status) {
        boolean expectedAvailability = "AVAILABLE".equalsIgnoreCase(status);
        assertEquals(expectedAvailability, deliveryPerson.isAvailable());
    }

    @When("the delivery person provides an order without user confirmation")
    public void the_delivery_person_provides_an_order_without_user_confirmation() {
        order.deliverWithoutConfirmation();
    }

    @Then("the order should require a signature and identity verification")
    public void the_order_should_require_a_signature_and_identity_verification() {
        assertTrue(order.requiresSignatureAndVerification());
    }


    @Given("the user cannot confirm receipt")
    public void the_user_cannot_confirm_receipt() {
        // Simulez la situation où l'utilisateur ne peut pas confirmer la réception
        order.setUserUnableToConfirm();
    }

    @When("the delivery person finalizes the delivery without user confirmation")
    public void the_delivery_person_finalizes_the_delivery_without_user_confirmation() {
        // Simulez la finalisation de la livraison sans confirmation de l'utilisateur
        // Cette méthode doit définir le statut de la commande en DELIVERED et mettre à jour l'état du livreur
        deliveryPerson.finalizeDeliveryWithoutUserConfirmation(order);
    }
}
