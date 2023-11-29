package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ApplyingDiscountByNumberOfOrdersStepdefs {


    private CampusUser user;
    private Restaurant restaurant;
    private SingleOrder singleOrder;
    private Map<CampusUser,Integer> numberOfOrdersPerUser=new HashMap<>();
    @Given("{string} has ordered {int} items from restaurant {string}")
    public void hasOrderedItemsFromRestaurant(String arg0, int arg1, String arg2) {
        user = new CampusUser(arg0, "password", "email");
        restaurant = new Restaurant(arg2, new RestaurantManager("test", "test", "test"), "test");
        numberOfOrdersPerUser.put(user, arg1);
        restaurant.setNumberOfDishesPerUser(numberOfOrdersPerUser);
    }

    @And("{string} is offering a discount of {int}% on the {int}th order")
    public void isOfferingADiscountOfOnTheThOrder(String arg0, int arg1, int arg2) {
        restaurant.setNumberOfDishesForDiscount(arg2);
        restaurant.setPercentageDiscountByNbOfDishes(arg1);
    }

    @When("{string} orders {int} dish from {string} for {int} and pays")
    public void ordersItemsFromRestaurantFor(String arg0, int arg1, String arg2, int arg3) throws Exception {
        Dish dish = new Dish(arg2, arg3);
        user.selectRestaurant(restaurant);
        user.createItem(dish, arg1);
        singleOrder =user.order(user.getCart(), restaurant);
        restaurant.addNbDishesToUser(user, singleOrder);
    }



    @And("the number of orders should be set to {int} for {string}")
    public void theNumberOfOrdersShouldBeSetToFor(int arg0, String arg1) {
        numberOfOrdersPerUser.put(user, arg0);
        restaurant.setNumberOfDishesPerUser(numberOfOrdersPerUser);
        assertEquals(arg0, restaurant.getNumberOfDishesForUser(user));
    }

    @Then("the order total should be {string}")
    public void theOrderTotalShouldBe(String arg0) {
        double numericValue = Double.parseDouble(arg0.replace(",", "."));
        assertEquals(numericValue, singleOrder.getPrice(), 0.01);
    }
}
