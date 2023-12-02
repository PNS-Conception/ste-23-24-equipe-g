package fr.unice.polytech.app;

import fr.unice.polytech.app.Restaurant.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class ManagerServiceStepdefs {

    Restaurant restaurant;
    Dish dish;

    RestaurantManager manager;



    @Given("manager of Restaurant logged in")
    public void managerOfRestaurantLoggedIn() {
        manager = new RestaurantManager("test", "test", "test");
        restaurant = new Restaurant("test",manager,null);
        manager.setRestaurant(restaurant);
        restaurant.setOwner(manager);
    }

    @Given("a empty menu")
    public void aEmptyMenu() {
        restaurant.setMenu(new Menu(), manager);
    }


    @Then("the dish should be in the menu of Restaurant")
    public void shouldBeInTheMenuOfRestaurant() {
        boolean b = manager.getRestaurant().getMenu().getMenu().contains(dish);
        boolean b2 = restaurant.getMenu().getMenu().contains(dish);
        assertTrue(b);
        assertTrue(b2);
    }

    @Given("a menu that contain dish with name {string} and price {int} and not regular price {int}")
    public void aMenuThatContain(String dishName, int price, int notRegularPrice) {
        dish = new Dish(dishName, price, notRegularPrice);
        restaurant.getMenu().addDish(dish, manager);
    }

    @Then("the {string} should be in the menu of Restaurant")
    public void theShouldBeInTheMenuOfRestaurant(String dishName) {
        boolean b = manager.getRestaurant().getMenu().contains(dishName);
        boolean b2 = restaurant.getMenu().getMenu().contains(dish);
        assertEquals(2, manager.getRestaurant().getMenu().getMenu().size());
        assertTrue(b);
        assertTrue(b2);

    }

    @When("the manager remove dish with name {string}")
    public void theAdminRemoveDishWithName(String arg0) {
        dish = new Dish(arg0, 10,0);
        boolean b=manager.getRestaurant().getMenu().removeDish(dish, manager);
        assertTrue(b);
    }

    @Then("{string} should not be in the menu of Restaurant")
    public void shouldNotBeInTheMenuOfRestaurant(String arg0) {
        boolean b = restaurant.getMenu().contains(arg0);
        assertFalse(b);
    }

    @Given("empty schedule")
    public void emptySchedule() {
        assertTrue(restaurant.getSchedule().isEmpty());
    }

    @When("the manager add oping hours with day {string} from {string} to {string}")
    public void the_admin_add_oping_hours_with_day_from_to(String day, String open, String close) {
        Day dayEnum = Day.valueOf(day);
        LocalTime openingTime = LocalTime.parse(open);
        LocalTime closingTime = LocalTime.parse(close);
        manager.addShift(openingTime, closingTime, dayEnum);

    }

    @Then("{string} from {string} to {string} should be in the oping hours of Restaurant")
    public void fromToShouldBeInTheOpingHoursOfRestaurant(String day, String startTime, String endTime) {
        LocalTime openingTime = LocalTime.parse(startTime);
        LocalTime closingTime = LocalTime.parse(endTime);
        boolean isInOpeningHours = restaurant.scheduleContains(new Day.Shift(openingTime, closingTime, Day.valueOf(day)));
        assertTrue(isInOpeningHours);
    }

    @Given("schedule contain {string} from {string} to {string}")
    public void scheduleContainFromTo(String day, String startTime, String endTime) {
        LocalTime openingTime = LocalTime.parse(startTime);
        LocalTime closingTime = LocalTime.parse(endTime);

        // Add the shift to the schedule
        restaurant.addShift(openingTime, closingTime, Day.valueOf(day), manager);
    }


    @When("the manager add dish with name {string} and price {int}, not regular price {int}")
    public void the_admin_add_dish_with_name_and_price(String dishName, double price, double notRegularPrice) {
        dish = new Dish(dishName, price,notRegularPrice);
        manager.addDish(dish);
    }
}

