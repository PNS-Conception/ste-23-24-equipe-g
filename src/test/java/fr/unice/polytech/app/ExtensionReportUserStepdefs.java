package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import fr.unice.polytech.app.Users.CampusUser;

import fr.unice.polytech.app.Delivery.DeliveryPerson;
import fr.unice.polytech.app.Orders.*;

import static org.junit.Assert.*;

public class ExtensionReportUserStepdefs {

    CampusUser client;
    Order order;

    DeliveryPerson deliveryPerson;

    @Given("that it is the customer's first order")
    public void thatItIsTheCustomerSFirstOrder() {
        client = new CampusUser("Client", "test", "test");
    }

    @When("the delivery person rates the customer with a score of {int}")
    public void theDeliveryPersonRatesTheCustomerWithAScoreOf(int rate) throws Exception {
        deliveryPerson = new DeliveryPerson("Livreur", "test", "test");
        deliveryPerson.getDeliverySystem().rateUser(client, rate);
    }

    @Then("the customer's average rating becomes {double}")
    public void theCustomerSAverageRatingBecomes(double rate) {
        assertEquals(rate , client.getAverageRating(), 0.0);
    }

    @Given("that the customer already has {int} rate of {int}")
    public void thatTheCustomerAlreadyHasAnAverageRatingOf(int nbOfRates, int rate) {
        client = new CampusUser("Client", "test", "test");
        for (int i = 0; i < nbOfRates; i++) {
            client.addRate(rate);
        }
        assertEquals(rate , client.getAverageRating(), 0.0);
    }

    @Then("the customer's number of ratings becomes 1")
    public void theCustomerSNumberOfRatingsBecomes() {
        assertEquals(1, client.getNbOfRates());
    }

}
