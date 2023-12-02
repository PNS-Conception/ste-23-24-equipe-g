package fr.unice.polytech.app;

import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.State.*;
import fr.unice.polytech.app.Users.CampusUser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PaymentStepdefs {

    private CampusUser client;
    private SingleOrder singleOrder;

    @Given("a client {string}")
    public void a_client_with_a_cart(String name) throws Exception {
        client = new CampusUser( name, "password", "email@example.com");
        singleOrder = new SingleOrder(new ArrayList<>());
    }

    @Given("having a cart of {int} {string} and {int} {string} price {double}€ and {double}€")
    public void having_items_in_cart(int pizzaQuantity, String pizza, int pastaQuantity, String pasta, double pizzaPrice, double pastaPrice) {
        client.createItem(new Dish(pizza, pizzaPrice), pizzaQuantity);
        client.createItem(new Dish(pasta, pastaPrice), pastaQuantity);
    }

    @When("Alice orders a pizza and a pasta")
    public void alice_orders_items() throws Exception {
        singleOrder = client.order(client.getCart(),new Restaurant("test", new RestaurantManager("test", "test", "test"), "test"));
    }

    @When("Alice pays {double}€")
    public void alice_pays(double amount) throws Exception {
        RandomGenerator mockRandomGenerator = Mockito.mock(RandomGenerator.class);
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        client.setRandomGenerator(mockRandomGenerator);
        assertTrue(client.makePaymentmock(singleOrder, client));
        //assertEquals(amount, singleOrder.getPrice(), 0.01);
    }

    @When("Alice has a balance of {double}€")
    public void alice_has_balance(double balance) {
        client.setBalance(balance);
    }

    @Then("the order's status should be {string}")
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

    @Then("Alice's orders should have an order with {int} pizza and {int} pasta and a total of {double}€")
    public void check_alice_orders(int pizzaCount, int pastaCount, double expectedPrice) {
        //Récupérez la dernière commande d'Alice
        SingleOrder lastSingleOrder = client.getLastOrder();
        // Vérifiez la dernière commande d'Alice
        assertEquals(singleOrder, lastSingleOrder);
        // Vérifiez que la dernière commande d'Alice contient les bons plats
        lastSingleOrder.getItems().forEach(item -> {
            if (item.getDish().getName().equals("pizza")) {
                assertEquals(pizzaCount, item.getQuantity());
            } else if (item.getDish().getName().equals("pasta")) {
                assertEquals(pastaCount, item.getQuantity());
            }
        });
        // Vérifiez que la dernière commande d'Alice a le bon prix
        assertEquals(expectedPrice, lastSingleOrder.getPrice(), 0.01);
    }

    @Then("the payment should fail")
    public void the_payment_should_fail() {
        assertNotEquals( singleOrder.getStatus() instanceof PaidIState, true);
    }

    @Then("Alice's cart should still have {int} pizza and {int} pasta")
    public void check_alice_cart(int pizzaCount, int pastaCount) {
        client.getCart().forEach(item -> {
            if (item.getDish().getName().equals("pizza")) {
                assertEquals(pizzaCount, item.getQuantity());
            } else if (item.getDish().getName().equals("pasta")) {
                assertEquals(pastaCount, item.getQuantity());
            }
        });
    }

    @Then("the balance should be {double}€")
    public void check_balance(double expectedBalance) {
        // Vérifiez le solde d'Alice
        assertEquals(expectedBalance, client.getBalance(), 0.01);
    }

    @Then("Alice's cart should be empty")
    public void check_alice_cart_empty() {
        // Vérifiez que le panier d'Alice est vide
        assertEquals(0, client.getCart().size());
    }

    @And("Alice attempts to pay")
    public void aliceAttemptsToPay() throws Exception {
        RandomGenerator mockRandomGenerator = Mockito.mock(RandomGenerator.class);
        when(mockRandomGenerator.nextDouble()).thenReturn(1.0); // Force l'échec
        client.setRandomGenerator(mockRandomGenerator);
        assertFalse(client.makePaymentmock(singleOrder, client));
    }

    @And("the order's status should not be {string}")
    public void theOrderSStatusShouldNotBe(String state) {
        assertNotEquals(state, singleOrder.getStatus().toString());
    }
}

