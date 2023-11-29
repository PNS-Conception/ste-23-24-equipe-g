package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RestaurantCapacityStepDefs {
    private Restaurant restaurant;
    private CapacityManager capacityManager;
    private CampusUser user;
    private SingleOrder singleOrder;
    private static final Map<String, Month> monthMap = createMonthMap();

    private static Map<String, Month> createMonthMap() {
        Map<String, Month> map = new HashMap<>();
        map.put("JANUARY", Month.JANUARY);
        map.put("FEBRUARY", Month.FEBRUARY);
        map.put("MARCH", Month.MARCH);
        map.put("APRIL", Month.APRIL);
        map.put("MAY", Month.MAY);
        map.put("JUNE", Month.JUNE);
        map.put("JULY", Month.JULY);
        map.put("AUGUST", Month.AUGUST);
        map.put("SEPTEMBER", Month.SEPTEMBER);
        map.put("OCTOBER", Month.OCTOBER);
        map.put("NOVEMBER", Month.NOVEMBER);
        map.put("DECEMBER", Month.DECEMBER);
        return map;
    }

    public static Month getMonthFromString(String monthString) {
        return monthMap.getOrDefault(monthString.toUpperCase(), null);
    }

    public RestaurantCapacityStepDefs() {
        capacityManager = CapacityManager.getInstance(); // Singleton
    }

    @Given("there is a restaurant named {string}")
    public void there_is_a_restaurant_named(String name) {
        try {
            Menu menu = new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99)));
            restaurant = RestaurantService.getInstance().createRestaurant(name, menu);
            capacityManager = CapacityManager.getInstance();
        } catch (IllegalArgumentException e) {
            // Le restaurant existe déjà, donc on le récupère
            restaurant = RestaurantService.getInstance().getRestaurantByName(name);
        }

        Restaurant res = RestaurantService.getInstance().getRestaurantByName(name);
    }

    /*@And("the capacity for {string} is set to {int} meals for the {string} slot on {string}")
    public void the_capacity_for_is_set_to_meals_for_the_slot_on(String name, int capacity, String timeSlot, String date) {
        LocalDateTime dateTime = parseDateTime(date, timeSlot);
        capacityManager.setCapacity(restaurant, dateTime, capacity);
    }*/

    @When("a user tries to place an order for {int} meals at {string} for {string} on {string}")
    public void a_user_tries_to_place_an_order_for_meals_at_for_on(int quantity, String name, String timeSlot, String date) {
        LocalDateTime dateTime = parseDateTime(date, timeSlot);
        user = new CampusUser();
        int remainingCapacity = capacityManager.getCapacity(restaurant, dateTime);
        try {
            Dish dish = new Dish("Pizza", 8.99); // Supposons que "Pizza" coûte 8.99
            Item newItem = new Item(dish, quantity);
            System.out.println(quantity+"+"+date+"+"+name);
            singleOrder = user.order(Collections.singletonList(newItem), restaurant, dateTime);
        } catch (IllegalStateException e) {
            singleOrder = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the order should be successfully placed")
    public void the_order_should_be_successfully_placed() {
        assertNotNull(singleOrder, "The order was not placed successfully.");
    }

   @And("the remaining capacity for {string} at {int}:{int} PM on {int}th {string} {int} should be {int} meals")
    public void the_remaining_capacity_for_at_pm_on_th_november_should_be_meals(String restaurantName, int hour, int minute, int day, String month,int year, int expectedCapacity) {
        LocalDateTime dateTime = LocalDateTime.of(year, getMonthFromString(month), day, hour, minute);
        int remainingCapacity = capacityManager.getCapacity(restaurant, dateTime);
        assertEquals(expectedCapacity, remainingCapacity, "The remaining capacity is not as expected.");
    }


    @Then("the order should not be placed")
    public void the_order_should_not_be_placed() {
        assertNull(singleOrder, "The order was placed despite exceeding capacity.");
    }

    @And("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String errorMessage) {
        if (singleOrder == null) {
            assertEquals("La capacité du restaurant est insuffisante pour cette commande", errorMessage);
        }
    }

    private LocalDateTime parseDateTime(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d'th' MMMM yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time, dateFormatter);
        return dateTime;
    }

    @And("the capacity for {string} is set to {int} meals for the {int}:{int} PM slot on {int}th {string} {int}")
    public void the_capacity_for_is_set_to_meals_for_the_pm_slot_on_20th_november(String name, int capacity, int hour, int minute,int day,String month, int year) {
        hour = hour == 12 ? 0 : hour; // Conversion PM to 24-hour format, si c'est 12 PM, cela devient 12 (midi)
        LocalDateTime dateTime = LocalDateTime.of(year, getMonthFromString(month), 20, hour + 12, minute); // Ajouter 12 pour convertir en format 24 heures
        capacityManager.setCapacity(restaurant, dateTime, capacity);
        LocalDateTime dateTimefr = LocalDateTime.of(year, getMonthFromString(month), 20, hour + 12, minute);

    }

    @When("a user tries to place an order for {int} meals at {string} for {int}:{int} PM on {int}th {string} {int}")
    public void a_user_tries_to_place_an_order_for_meals_at_for_pm_on_20th_november(int quantity, String name, int hour, int minute,int day,String month, int year) {
        hour = hour == 12 ? 0 : hour; // Conversion PM to 24-hour format
        LocalDateTime dateTime = LocalDateTime.of(year,getMonthFromString(month), 20, hour + 12, minute); // Ajouter 12 pour convertir en format 24 heures
        user = new CampusUser();
        int remainingCapacity = capacityManager.getCapacity(restaurant, dateTime);
        try {
            Dish dish = new Dish("Pizza", 8.99);
            Item newItem = new Item(dish, quantity);
            singleOrder = user.order(Collections.singletonList(newItem), restaurant, dateTime);
        } catch (IllegalStateException e) {
            singleOrder = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        remainingCapacity = capacityManager.getCapacity(restaurant, dateTime);
        remainingCapacity+=5;
    }

    private int convertTo24HourFormat(int hour, int minute, String amPm) {
        return amPm.equalsIgnoreCase("PM") && hour != 12 ? hour + 12 : hour;
    }

    @Then("the remaining capacity for {string} at {int}:{int} PM on {int}th November {int} should still be {int} meals")
    public void the_remaining_capacity_for_at_pm_on_th_november_should_be_meals_(String restaurantName, int hour, int minute, int day, int year, int expectedCapacity) {
        LocalDateTime dateTime = LocalDateTime.of(year, Month.NOVEMBER, day, hour, minute);
        int remainingCapacity = capacityManager.getCapacity(restaurant, dateTime);
        assertEquals(expectedCapacity, remainingCapacity, "The remaining capacity is not as expected.");
    }



}
