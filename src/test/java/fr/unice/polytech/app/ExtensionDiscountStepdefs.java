package fr.unice.polytech.app;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.*;

public class ExtensionDiscountStepdefs {

    private Restaurant restaurant;
    private LocalDate currentDate = LocalDate.now();
    CampusUser user;
    Order order;

    private void simulateTimePassage(int days) {
        currentDate = currentDate.plusDays(days);
    }
    /*@Given("{string} has ordered {int} items from restaurant {string}")
    public void OrderedItemsFromRestaurant(String arg0,int nb, String arg2) {
        user = new CampusUser(arg0, "password", "email");
        restaurant = new Restaurant(arg2, new RestaurantManager("test", "test", "test"), "test");
        order=user.order(List.of(new Item(new Dish("test", 10, 10), 1)), restaurant);
        restaurant.getExtensionDiscount(user).setNumberOfOrders(nb);
    }*/

    /*@Given("{string} is offering a discount of {int}% on the {int}th order and it lasts for {int} days")
    public void isOfferingADiscountOfOnTheThOrderAndItLastsForDays( String arg0,int arg1, int arg2, int arg3) {
        restaurant.setNumberOfOrdersForDiscount(arg2);
        restaurant.setPercentageDiscountByNbOfDishes(arg1);
        restaurant.setNumberOfDaysForDiscount(arg3);
        user.makePayment(order,user);
    }*/

    @And("she have a valid discount")
    public void sheHaveAValidDiscount() {
        restaurant.getExtensionDiscount(user).setIsDiscountValid(true);
        //System.out.println(restaurant.getExtensionDiscount(user).getIsDiscountValid());
    }

    @When("{string} orders {int} dish from the restaurant for {int} and pays")
    public void ordersItemsFromRestaurantFor(String arg0, int arg1, int arg2) {
        Dish dish = new Dish("test", arg2);
        user.selectRestaurant(restaurant);
        user.createItem(dish, arg1);
        order =user.order(user.getCart(), restaurant);
        order.getPaid();
    }

    @Then("the order total is {int}")
    public void theOrderTotalShouldBe(double arg0) {
        assertEquals( arg0,order.getPrice(), 0.0);

    }

    @And("the number of orders set to {int} for {string}")
    public void theNumberOfOrdersShouldBeSetToFor(int arg0, String arg1) {
        assertEquals(arg0, restaurant.getExtensionDiscount(user).getNumberOfOrders());
    }


    @When("the {int} days have passed")
    public void theDaysHavePassed(int arg0) {
        simulateTimePassage(arg0);
    }



    @When("less than {int} days have passed")
    public void lessThanDaysHavePassed(int arg0) {
        ExtensionDiscount discount =restaurant.getExtensionDiscount(user);
        discount.setDatePlusNDays(discount.getDate().plusDays(-(arg0-5)));
        simulateTimePassage(arg0-5);
    }

    @Then("the discount will be prolonged for {int} days")
    public void theDiscountWillBeProlongedForDays(int arg0) {
        restaurant.addNbOrderToUser(user);
        restaurant.getExtensionDiscount(user).extendDiscount();
        LocalDate discountEndDate = restaurant.getExtensionDiscount(user).getDate();
        long daysUntilDiscountExpires = ChronoUnit.DAYS.between(LocalDate.now(), discountEndDate);
        assertEquals(arg0, (int) daysUntilDiscountExpires);
    }

    @Given("{string} is offering a discount of {int}% on the {int}th order and it lasts for {int} days")
    public void isOfferingADiscountOfOnTheThOrderAndItLastsForDays(String arg0, int arg1, int arg2, int arg3) {
        restaurant = new Restaurant(arg0, new RestaurantManager("test", "test", "test"), "test");
        restaurant.setNumberOfOrdersForDiscount(arg2);
        restaurant.setPercentageDiscountByNbOfOrders(arg1);
        restaurant.setNumberOfDaysForDiscount(arg3);
        restaurant.addNbOrderToUser(user);


    }

    @Then("the discount is not applied anymore")
    public void theDiscountIsNotAppliedAnymore() {
        restaurant.addNbOrderToUser(user);
        assertFalse(restaurant.getExtensionDiscount(user).getIsDiscountValid());
    }

    @And("{string} has ordered {int} items from the restaurant")
    public void hasOrderedItemsFromTheRestaurant(String arg0, int arg1) {
        //user = new CampusUser(arg0, "password", "email");
        //restaurant = new Restaurant("test", new RestaurantManager("test", "test", "test"), "test");
        //order=user.order(List.of(new Item(new Dish("test", 10, 10), 1)), restaurant);
        user = new CampusUser("test", "password", "email");
        order=user.order(List.of(new Item(new Dish("test", 10, 10), 1)), restaurant);
        user.makePayment(order,user);
        restaurant.addNbOrderToUser(user);
        restaurant.getExtensionDiscount(user).setNumberOfOrders(arg1);
    }
}
