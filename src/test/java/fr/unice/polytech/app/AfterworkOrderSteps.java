package fr.unice.polytech.app;

import fr.unice.polytech.app.State.AcceptedIState;
import fr.unice.polytech.app.State.CancelledIState;
import fr.unice.polytech.app.State.PlacedIState;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AfterworkOrderSteps {

    private CampusUser organizer;
    private Restaurant restaurant;
    private AfterWorkOrder order;
    private Dish dishDetails;
    private Menu afterworkMenu;


@Before
public void setup() {
    if (restaurant == null) {
        Admin admin = new Admin();
        RestaurantManager restaurantManager = new RestaurantManager("name", "pwd", "mail");
        // Vérifiez si le restaurant existe déjà par son nom
        restaurant = RestaurantService.getInstance().getRestaurantByName("The Gourmet");
        if (restaurant == null) {
            // Le restaurant n'existe pas, donc nous pouvons le créer
            Menu afterworkMenu = new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni", 8.99)));
            afterworkMenu.setAfterworkMenu(true);
            restaurant = RestaurantService.getInstance().createRestaurant("The Gourmet", afterworkMenu);
            restaurant.setAfterworkMenu(true);
        }
    }
}


    @Given("there are restaurants offering afterwork menus")
    public void there_are_restaurants_offering_afterwork_menus() {
        RestaurantService service = RestaurantService.getInstance();
        assertNotNull(service);
        restaurant = service.getRestaurantByName("The Gourmet");
        assertNotNull(restaurant);
        Menu afterworkMenu = new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99)));
        afterworkMenu.setAfterworkMenu(true);
        restaurant.setAfterworkMenu(true);
        assertNotNull(afterworkMenu);
        RestaurantManager restaurantManager = new RestaurantManager("name", "pwd", "mail");
        restaurant.setMenu(afterworkMenu,restaurantManager);
        assertTrue(restaurant.offersAfterworkMenus());
    }


    @Given("user {string} is logged in as an organizer")
    public void user_is_logged_in_as_an_organizer(String userName) {
        organizer = new CampusUser(userName, "password", "Organizer");
        Dish dish = new Dish("Pizza", 10.0);
        Item item = new Item(dish, 5);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        order = new AfterWorkOrder(organizer,restaurant, items, 5);
        order.setOrganizer(organizer);
    }
    @When("he creates an afterwork order for {int} expected participants at {string} restaurant")
    public void he_creates_an_afterwork_order_for_expected_participants_at_restaurant(int participants, String restaurantName) {
        // Création de la commande afterwork
        Dish dish = new Dish("Pizza", 10.0);
        Item item = new Item(dish, 5);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        order = new AfterWorkOrder(organizer, restaurant, items, participants);
        order.setRestaurant(restaurant);
        order.setNumberOfParticipants(5);
        order.placeOrder();
    }
    @Then("the afterWork order should be created")
    public void theAfterWorkOrderShouldBeCreated() {
        assertNotEquals(order,null);
        assertTrue(order.getStatus() instanceof PlacedIState);
    }
    @Given("an afterwork order is already created")
    public void anAfterworkOrderIsAlreadyCreated() {

        // Création de la commande afterwork
        Dish dish = new Dish("Pizza", 10.0);
        Item item = new Item(dish, 5);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        order = new AfterWorkOrder(organizer, restaurant, items, 5);
        order.setRestaurant(restaurant);
        order.setNumberOfParticipants(5);
    }

    @When("{string} cancels this afterwork order")
    public void cancels_this_afterwork_order(String userName) throws Exception {
        order.setStatus(new AcceptedIState());
        order.cancel();
    }

    @Then("the order is marked as cancelled")
    public void orderStatusIsCancelled() throws Exception {

        assertTrue(order.getStatus() instanceof CancelledIState);
    }

    @When("he views the available menu for afterworks at {string} restaurant")
    public void heViewsTheAvailableMenuForAfterworksAtRestaurant(String name) {
        RestaurantService service = RestaurantService.getInstance();
        assertNotNull(service);
        restaurant = service.getRestaurantByName("The Gourmet");
        assertNotNull(restaurant);
        Menu afterworkMenu = new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99)));
        afterworkMenu.setAfterworkMenu(true);
        restaurant.setAfterworkMenu(true);
        assertNotNull(afterworkMenu);
        assertNotNull(afterworkMenu.getMenu());
        assertFalse(afterworkMenu.getMenu().isEmpty());
    }
    @Then("the available afterwork menu is presented with details")
    public void the_available_afterwork_menu_is_presented_with_details() {
        RestaurantService service = RestaurantService.getInstance();
        assertNotNull(service);
        restaurant = service.getRestaurantByName("The Gourmet");
        assertNotNull(restaurant);
        Menu afterworkMenu = new Menu(Arrays.asList(new Dish("Margherita", 7.99), new Dish("Pepperoni",  8.99)));
        afterworkMenu.setAfterworkMenu(true);
        restaurant.setAfterworkMenu(true);
        dishDetails = afterworkMenu.getMenu().get(0);
        assertNotNull(dishDetails.getName());
        //assertNotNull(dishDetails.getIngredients());
        assertTrue(dishDetails.getPrice() > 0);

}


//
//    @Then("the available afterwork menus are presented with details")
//    public void the_available_afterwork_menus_are_presented_with_details() {
//        // Vérification que les menus sont non seulement disponibles, mais aussi qu'ils contiennent des détails
//        for (Menu menu : availableAfterworkMenus) {
//            assertNotNull(menu.getDetails());
//            assertFalse(menu.getDetails().isEmpty()); // Les détails ne doivent pas être vides
//        }
//    }
    @Given("an afterwork order is already created for {int} participants")
    public void an_afterwork_order_is_already_created_for_participants(Integer participants) {
        // Créer une liste d'éléments pour ajouter à la commande, avec au moins un élément
        Dish dish = new Dish("Dish example", 9.99);
        Item item = new Item(dish, 1); // Suppose that each participant will have this item
        List<Item> items = new ArrayList<>();
        items.add(item);

        organizer = new CampusUser("organizerUserName", "organizerPassword", "Organizer");
        restaurant = RestaurantService.getInstance().getRestaurantByName("The Gourmet");
        order = new AfterWorkOrder(organizer, restaurant, items, participants);
        for (int i = 0; i < participants; i++) {
            CampusUser participant = new CampusUser("participant" + i, "password", "Participant");
            order.addParticipant(participant);
        }
    }
    @When("{string} adds {int} more participants to the order")
    public void addsMoreParticipantsToTheOrder(String name, int additionalParticipants) {
        order.setNumberOfParticipants(order.getNumberOfParticipants() + additionalParticipants);
        for (int i = 0; i < additionalParticipants; i++) {
            CampusUser newParticipant = new CampusUser("participant" + i, "password", "Participant");
            order.addParticipant(newParticipant);
        }
    }

    @Then("the total number of participants for the afterwork order is now {int}")
    public void the_total_number_of_participants_for_the_afterwork_order_is_now(int expectedTotal) {
        assertEquals(expectedTotal, order.getNumberOfParticipants());
    }

    @And("John should be the organizer of the afterwork order")
    public void johnShouldBeTheOrganizerOfTheAfterworkOrder() {
        assertEquals(organizer, order.getOrganizer());
    }

    @And("the afterwork order should be created for {int} participants")
    public void theAfterworkOrderShouldBeCreatedForParticipants(int arg0) {
        assertEquals(arg0, order.getNumberOfParticipants());
    }
}




