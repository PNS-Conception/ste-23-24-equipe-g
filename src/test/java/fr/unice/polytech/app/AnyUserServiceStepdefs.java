package fr.unice.polytech.app;

import fr.unice.polytech.app.Restaurant.Menu;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.Users.RestaurantManager;
import fr.unice.polytech.app.System.Admin;
import fr.unice.polytech.app.Users.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AnyUserServiceStepdefs {

    User user=new User();
    Admin admin=new Admin();
    Restaurant restaurant;


    @Given("I am a user on the homepage")
    public void iAmAUser() {
        admin.addRestaurant("KFC","address",new RestaurantManager("name","email","password"));
        admin.addRestaurant("McDonalds","address",new RestaurantManager("name","email","password"));
        admin.addRestaurant("BurgerKing","address",new RestaurantManager("name","email","password"));

    }

    @Then("I should see {string}")
    public void iShouldSee(String arg0) {
        user.getRestaurants().forEach(restaurant -> {
            if(restaurant.getName().equals(arg0)){
                assert(true);
            }
        });
    }


    @When("I click on {string}")
    public void iClickOn(String arg0) {
        restaurant=user.getRestaurantByName(arg0);
    }

    @Then("I should see the menu")
    public void iShouldSeeTheMenu() {
        Menu menu =user.getRestaurantMenu(restaurant);
        assert (menu!=null);
    }
}
