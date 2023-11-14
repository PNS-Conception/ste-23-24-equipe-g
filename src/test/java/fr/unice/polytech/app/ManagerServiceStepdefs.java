package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ManagerServiceStepdefs {

    Restaurant restaurant;
    Dish dish;

    RestaurantManager manager;



    @Given("manager of Restaurant logged in")
    public void managerOfRestaurantLoggedIn() {
        manager = new RestaurantManager("test", "test", "test", "test");
        restaurant = new Restaurant("test");
        restaurant.setOwner(manager);
    }

    @Given("a empty menu")
    public void aEmptyMenu() {
        restaurant.setMenu(new Menu(), manager);
    }


    @Then("the dish should be in the menu of Restaurant")
    public void shouldBeInTheMenuOfRestaurant() {
        boolean b = restaurant.getMenu().getMenu().contains(dish);
        assertTrue(b);
    }

    @Given("a menu that contain dish with name {string} and price {int}")
    public void aMenuThatContain(String dishName, int price) {
        dish = new Dish(dishName, price);
        restaurant.getMenu().addDish(dish, manager);
    }

    @Then("the {string} should be in the menu of Restaurant")
    public void theShouldBeInTheMenuOfRestaurant(String dishName) {
        boolean b = restaurant.getMenu().contains(dishName);
        assertTrue(b);
    }

    @When("the admin remove dish with name {string}")
    public void theAdminRemoveDishWithName(String arg0) {
        dish = new Dish(arg0, 10);
        boolean b=restaurant.getMenu().removeDish(dish, manager);
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

    @When("the admin add oping hours with day {string} from {string} to {string}")
    public void the_admin_add_oping_hours_with_day_from_to(String day, String open, String close) {
        Day dayEnum = Day.valueOf(day);
        LocalTime openingTime = LocalTime.parse(open);
        LocalTime closingTime = LocalTime.parse(close);
        boolean b=restaurant.addShift(openingTime, closingTime, dayEnum, manager);
        assertTrue(b);

    }

    @Then("{string} from {string} to {string} should be in the oping hours of Restaurant")
    public void fromToShouldBeInTheOpingHoursOfRestaurant(String day, String startTime, String endTime) {
        LocalTime openingTime = LocalTime.parse(startTime);
        LocalTime closingTime = LocalTime.parse(endTime);
        boolean isInOpeningHours = restaurant.scheduleContains(new Shift(openingTime, closingTime, Day.valueOf(day)));
        assertTrue(isInOpeningHours);
    }

    @Given("schedule contain {string} from {string} to {string}")
    public void scheduleContainFromTo(String day, String startTime, String endTime) {
        LocalTime openingTime = LocalTime.parse(startTime);
        LocalTime closingTime = LocalTime.parse(endTime);

        // Add the shift to the schedule
        restaurant.addShift(openingTime, closingTime, Day.valueOf(day), manager);
    }


    @When("the admin add dish with name {string} and price {int}")
    public void the_admin_add_dish_with_name_and_price(String dishName, double price) {
        dish = new Dish(dishName, price);
        restaurant.getMenu().addDish(dish, manager);
    }
}

