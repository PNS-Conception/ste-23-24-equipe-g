package fr.unice.polytech.app;


import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Restaurant.ExtensionDiscount;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.Restaurant.RestaurantManager;
import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Util.RandomGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ExtensionDiscountStepdefs {

    private Restaurant restaurant;
    private LocalDate currentDate = LocalDate.now();
    CampusUser user;
    SingleOrder singleOrder;

    RandomGenerator mockRandomGenerator = Mockito.mock(RandomGenerator.class);

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
        restaurant.getDiscountSystem().getExtensionDiscount(user).setIsDiscountValid(true);
        //System.out.println(restaurant.getExtensionDiscount(user).getIsDiscountValid());
    }

    @When("{string} orders {int} dish from the restaurant for {int} and pays")
    public void ordersItemsFromRestaurantFor(String arg0, int arg1, int arg2) throws Exception {
        Dish dish = new Dish("test", arg2);
        user.selectRestaurant(restaurant);
        user.createItem(dish, arg1);
        singleOrder =user.order(user.getCart(), restaurant);
        singleOrder.getPaid();
    }

    @Then("the order total is {int}")
    public void theOrderTotalShouldBe(double arg0) {
        assertEquals( arg0, singleOrder.getPrice(), 0.0);

    }

    @And("the number of orders set to {int} for {string}")
    public void theNumberOfOrdersShouldBeSetToFor(int arg0, String arg1) {
        assertEquals(arg0, restaurant.getDiscountSystem().getExtensionDiscount(user).getNumberOfOrders());
    }


    @When("the {int} days have passed")
    public void theDaysHavePassed(int arg0) {
        simulateTimePassage(arg0);
    }



    @When("less than {int} days have passed")
    public void lessThanDaysHavePassed(int arg0) {
        ExtensionDiscount discount =restaurant.getDiscountSystem().getExtensionDiscount(user);
        discount.setDatePlusNDays(discount.getDate().plusDays(-(arg0-5)));
        simulateTimePassage(arg0-5);
    }

    @Then("the discount will be prolonged for {int} days")
    public void theDiscountWillBeProlongedForDays(int arg0) {
        restaurant.getDiscountSystem().addNbOrderToUser(user);
        restaurant.getDiscountSystem().getExtensionDiscount(user).extendDiscount();
        LocalDate discountEndDate = restaurant.getDiscountSystem().getExtensionDiscount(user).getDate();
        long daysUntilDiscountExpires = ChronoUnit.DAYS.between(LocalDate.now(), discountEndDate);
        assertEquals(arg0, (int) daysUntilDiscountExpires);
    }

    @Given("{string} is offering a discount of {int}% on the {int}th order and it lasts for {int} days")
    public void isOfferingADiscountOfOnTheThOrderAndItLastsForDays(String arg0, int arg1, int arg2, int arg3) {
        restaurant = new Restaurant(arg0, new RestaurantManager("test", "test", "test"), "test");
        restaurant.getDiscountSystem().setNumberOfOrdersForDiscount(arg2);
        restaurant.getDiscountSystem().setPercentageDiscountByNbOfOrders(arg1);
        restaurant.getDiscountSystem().setNumberOfDaysForDiscount(arg3);
        restaurant.getDiscountSystem().addNbOrderToUser(user);


    }

    @Then("the discount is not applied anymore")
    public void theDiscountIsNotAppliedAnymore() {
        restaurant.getDiscountSystem().addNbOrderToUser(user);
        assertFalse(restaurant.getDiscountSystem().getExtensionDiscount(user).getIsDiscountValid());
    }

    @And("{string} has ordered {int} items from the restaurant")
    public void hasOrderedItemsFromTheRestaurant(String arg0, int arg1) throws Exception {
        user = new CampusUser("test", "password", "email");
        singleOrder =user.order(List.of(new Item(new Dish("test", 10, 10), 1)), restaurant);
        when(mockRandomGenerator.nextDouble()).thenReturn(0.0); // Force la réussite
        user.setRandomGenerator(mockRandomGenerator);
        user.makePaymentmock(singleOrder,user);
        restaurant.getDiscountSystem().addNbOrderToUser(user);
        restaurant.getDiscountSystem().getExtensionDiscount(user).setNumberOfOrders(arg1);
    }
}
