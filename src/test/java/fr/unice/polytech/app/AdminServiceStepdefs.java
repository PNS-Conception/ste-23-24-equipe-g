package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class AdminServiceStepdefs {

    Admin admin;

    @Given("admin logged in")
    public void adminLoggedIn() {
         admin=new Admin();
    }


    @When("the admin add delivery person with email {string}")
    public void the_admin_add_delivery_person_with_email(String email) {
        admin.addDeliveryPerson("name",email);

    }
    @Then("{string} should be in the list of delivery persons")
    public void should_be_in_the_list_of_delivery_persons(String email) {
        assertEquals(email, admin.getDeliveryPersons().get(0).getEmail());

    }

    @When("the admin add restaurant with name {string} and address {string} and a restaurant manager with email {string}")
    public void theAdminAddRestaurantWithNameAndAddressAndARestaurantManagerWithEmail(String restaurantName, String restaurantAddress, String restaurantOwnerEmail) {
        admin.addRestaurant(restaurantName,restaurantAddress,restaurantOwnerEmail);

    }


    @Then("{string} should be in the list of restaurants with address {string} and manager {string}")
    public void shouldBeInTheListOfRestaurantsWithAddressAndManager(String restaurantName, String restaurantAddress, String restaurantOwnerEmail) {
        assertEquals(restaurantName, admin.getRestaurants().get(0).getName());
        assertEquals(restaurantAddress, admin.getRestaurants().get(0).getAddress());
        assertEquals(restaurantOwnerEmail, admin.getRestaurants().get(0).getOwner().getEmail());

    }
}
