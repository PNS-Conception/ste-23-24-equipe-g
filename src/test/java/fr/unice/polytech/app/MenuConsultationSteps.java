package fr.unice.polytech.app;

import fr.unice.polytech.app.CampusUser;
import fr.unice.polytech.app.Dish;
import fr.unice.polytech.app.Menu;
import fr.unice.polytech.app.Restaurant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import java.util.List;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuConsultationSteps {

    private CampusUser user;
    private List<Restaurant> availableRestaurants;
    private Menu selectedMenu;
    private Dish dishDetails;
    
    // Mocking services and repositories that would be used in the actual implementation
    private RestaurantService restaurantService =new RestaurantService();

    @Given("I am an internet user")
    public void i_am_an_internet_user() {
        user = new CampusUser(); // Assuming CampusUser has a constructor for internet users
        user.setType(UserType.Client);
    }

    @When("I visit the campus food ordering system")
    public void i_visit_the_campus_food_ordering_system() {
        availableRestaurants = restaurantService.getAllRestaurants(); // Assuming such a method exists
        System.out.println( availableRestaurants );
    }

    @Then("I should see a list of all available restaurants on campus")
    public void i_should_see_a_list_of_all_available_restaurants_on_campus() {
        availableRestaurants = restaurantService.getAllRestaurants();
        assertNotNull(availableRestaurants);
        assertFalse(availableRestaurants.isEmpty());
    }

    @Given("I am on the list of campus restaurants")
    public void i_am_on_the_list_of_campus_restaurants() {
        availableRestaurants = restaurantService.getAllRestaurants(); // Re-using the previously mocked method
    }

    @When("I select a restaurant")
    public void i_select_a_restaurant() {
        availableRestaurants = restaurantService.getAllRestaurants();
        Restaurant restaurant = availableRestaurants.get(0); // Selecting the first restaurant for simplicity
        selectedMenu = restaurant.getMenu(); // Assuming Restaurant class has a getMenu method
    }

    @Then("I should see the full menu offered by that restaurant")
    public void i_should_see_the_full_menu_offered_by_that_restaurant() {
        assertNotNull(selectedMenu);
        assertNotNull(selectedMenu.getDishes()); // Assuming Menu has a getDishes method
        assertFalse(selectedMenu.getDishes().isEmpty());
    }

    @Given("I am viewing a restaurant's menu")
    public void i_am_viewing_a_restaurant_menu() {
        i_select_a_restaurant(); // Re-use the step from above
    }

    @When("I look at a specific dish")
    public void i_look_at_a_specific_dish() {
        dishDetails = selectedMenu.getDishes().get(0); // Getting the details of the first dish
    }

    @Then("I should see the dish's name, ingredients, and price")
    public void i_should_see_the_dish_s_name_ingredients_and_price() {
        dishDetails = selectedMenu.getDishes().get(0);
        assertNotNull(dishDetails.getName());
        assertNotNull(dishDetails.getIngredients());
        assertTrue(dishDetails.getPrice() > 0);
    }
}

// Assuming that there are services like RestaurantService with methods like getAllRestaurants.
// The actual implementation would depend on the exact code structure and frameworks used.
