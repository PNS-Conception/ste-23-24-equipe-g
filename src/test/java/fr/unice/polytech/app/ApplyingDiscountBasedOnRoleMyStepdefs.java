package fr.unice.polytech.app;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApplyingDiscountBasedOnRoleMyStepdefs {

    Dish dish;
    Dish dish2;
    Item item;
    Item item2;
    CampusUser user;
    RestaurantManager manager;
    DeliveryPerson deliveryPerson;
    StaffUser staff;
    Order order;

    Restaurant restaurant;
    @Given("a Item contains {int} {string} that costs {int} each for regular customer and {int} each for premium customer")
    public void aItemContainsThatCostsEachForRegularCustomerAndEachForPremiumCustomer(int arg0, String arg1, int arg2, int arg3) {
        dish = new Dish(arg1, arg2, arg3);
        item = new Item(dish, arg0);

    }

    @And("another Item contains {int} {string} that costs {int} each for regular customer and {int} each for premium customer")
    public void anotherItemContainsThatCostsEachForRegularCustomerAndEachForPremiumCustomer(int arg0, String arg1, int arg2, int arg3) {
        dish2 = new Dish(arg1, arg2, arg3);
        item2 = new Item(dish2, arg0);
    }

    @Given("A regular customer")
    public void aRegularCustomer() {
        user = new CampusUser("regular", "password", "email");
    }

    @When("the customer validates the cart")
    public void theCustomerValidatesTheCart() {
        user = new CampusUser("regular", "password", "email");
        order = user.order( new ArrayList<>(Arrays.asList(item, item2)), restaurant);
    }

    @Given("A staff")
    public void aStaff() {
        staff = new StaffUser("staff",restaurant, "email", "password");
    }

    @Given("A manager")
    public void aManager() {
        manager = new RestaurantManager("manager", "password", "email");
    }

    @Given("A delivery person")
    public void aDeliveryPerson() {
        deliveryPerson = new DeliveryPerson("delivery", "password", "phone");
    }


    @Then("the order total should be {int}")
    public void theOrderTotalShouldBe(double arg0) {
        assertEquals( arg0,order.getPrice(), 0.0);
    }

    @When("the staff validates the cart")
    public void theStaffValidatesTheCart() {
        order = staff.order(new ArrayList<>(Arrays.asList(item, item2)), restaurant);
    }

    @When("the manager validates the cart")
    public void theManagerValidatesTheCart() {
        order = manager.order(new ArrayList<>(Arrays.asList(item, item2)), restaurant);
    }

    @When("the delivery person validates the cart")
    public void theDeliveryPersonValidatesTheCart() {
        order = deliveryPerson.order(new ArrayList<>(Arrays.asList(item, item2)), restaurant);
    }

    @And("from a restaurant {string}")
    public void fromARestaurant(String arg0) {
        restaurant = new Restaurant(arg0, manager, "test");
    }
}
