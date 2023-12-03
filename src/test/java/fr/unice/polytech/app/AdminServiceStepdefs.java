package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AdminServiceStepdefs {

    Admin admin;

    @Given("admin logged in")
    public void adminLoggedIn() {
         admin=new Admin();
    }

    @And("a restaurant manager with email {string}")
    public void aRestaurantManagerWithEmail(String arg0) {
        admin.addCampusUser("name","password",arg0);
    }


    @When("the admin add delivery person with email {string} and phone number {string}")
    public void the_admin_add_delivery_person_with_email_and_phone_number(String phoneNumber, String email) {
        admin.addDeliveryPerson("name",phoneNumber, email);

    }
    @Then("{string} and {string} should be in the list of delivery persons")
    public void should_be_in_the_list_of_delivery_persons(String email, String phoneNumber) {
        assertEquals(phoneNumber, admin.getDeliveryPersons().get(0).getPhoneNumber());
        assertEquals(email, admin.getDeliveryPersons().get(0).getEmail());
    }

    @When("the admin add restaurant with name {string} and address {string} and a restaurant manager with email {string}")
    public void theAdminAddRestaurantWithNameAndAddressAndARestaurantManagerWithEmail(String restaurantName, String restaurantAddress, String restaurantOwnerEmail) {
        admin.addRestaurant(restaurantName,restaurantAddress,admin.getUserByEmail(restaurantOwnerEmail));

    }


    @Then("{string} should be in the list of restaurants with address {string} and manager {string}")
    public void shouldBeInTheListOfRestaurantsWithAddressAndManager(String restaurantName, String restaurantAddress, String restaurantOwnerEmail) {
        assertEquals(restaurantName, admin.getRestaurants().get(0).getName());
        assertEquals(restaurantAddress, admin.getRestaurants().get(0).getAddress());
        assertEquals(restaurantOwnerEmail, admin.getRestaurants().get(0).getOwner().getEmail());

    }


    @When("the admin remove delivery person with email {string}")
    public void theAdminRemoveDeliveryPersonWithEmail(String arg0) {
        admin.removeDeliveryPerson(arg0);
    }

    @Then("{string} should not be in the list of delivery persons")
    public void shouldNotBeInTheListOfDeliveryPersons(String arg0) {
        admin.getDeliveryPersons().forEach(deliveryPerson -> {
            assertNotEquals(deliveryPerson.getEmail(), arg0);
        });
    }

    @When("the admin remove restaurant with name {string}")
    public void theAdminRemoveRestaurantWithName(String arg0) {
        admin.removeRestaurant(arg0);

    }

    @Then("{string} should not be in the list of restaurants")
    public void shouldNotBeInTheListOfRestaurants(String arg0) {
    }
}
