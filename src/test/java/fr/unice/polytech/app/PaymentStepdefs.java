package fr.unice.polytech.app;

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
    private Order order;

    @Given("a client {string}")
    public void a_client_with_a_cart(String name) {
        client = new CampusUser( name, "password", "email@example.com");
        order= new Order(new ArrayList<>());
    }

    @Given("having a cart of {int} {string} and {int} {string} price {double}€ and {double}€")
    public void having_items_in_cart(int pizzaQuantity, String pizza, int pastaQuantity, String pasta, double pizzaPrice, double pastaPrice) {
        client.createItem(new Dish(pizza, pizzaPrice), pizzaQuantity);
        client.createItem(new Dish(pasta, pastaPrice), pastaQuantity);
    }

    @When("Alice orders a pizza and a pasta")
    public void alice_orders_items() {
        order = client.order(client.getCart(),new Restaurant("test", new RestaurantManager("test", "test", "test"), "test"));
    }

    @When("Alice pays {double}€")
    public void alice_pays(double amount) {
        RandomGenerator mockRandomGenerator = Mockito.mock(RandomGenerator.class);
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        client.setRandomGenerator(mockRandomGenerator);
        assertTrue(client.makePaymentmock(order, client));
        assertEquals(amount, order.getPrice(), 0.01);
    }

    @When("Alice has a balance of {double}€")
    public void alice_has_balance(double balance) {
        client.setBalance(balance);
    }

    @Then("the order's status should be {string}")
    public void the_order_status_should_be(String expectedStatus) {
        // Vérifiez le statut de la commande
        assertEquals(OrderStatus.valueOf(expectedStatus), order.getStatus());
    }

    @Then("Alice's orders should have an order with {int} pizza and {int} pasta and a total of {double}€")
    public void check_alice_orders(int pizzaCount, int pastaCount, double expectedPrice) {
        //Récupérez la dernière commande d'Alice
        Order lastOrder = client.getLastOrder();
        // Vérifiez la dernière commande d'Alice
        assertEquals(order, lastOrder);
        // Vérifiez que la dernière commande d'Alice contient les bons plats
        lastOrder.getItems().forEach(item -> {
            if (item.getDish().getName().equals("pizza")) {
                assertEquals(pizzaCount, item.getQuantity());
            } else if (item.getDish().getName().equals("pasta")) {
                assertEquals(pastaCount, item.getQuantity());
            }
        });
        // Vérifiez que la dernière commande d'Alice a le bon prix
        assertEquals(expectedPrice, lastOrder.getPrice(), 0.01);
    }

    @Then("the payment should fail")
    public void the_payment_should_fail() {
        assertNotEquals(OrderStatus.PAID, order.getStatus());
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
    public void aliceAttemptsToPay() {
        RandomGenerator mockRandomGenerator = Mockito.mock(RandomGenerator.class);
        when(mockRandomGenerator.nextDouble()).thenReturn(1.0); // Force l'échec
        client.setRandomGenerator(mockRandomGenerator);
        assertFalse(client.makePaymentmock(order, client));
    }

    @And("the order's status should not be {string}")
    public void theOrderSStatusShouldNotBe(String state) {
        assertNotEquals(OrderStatus.valueOf(state), order.getStatus());
    }
}

