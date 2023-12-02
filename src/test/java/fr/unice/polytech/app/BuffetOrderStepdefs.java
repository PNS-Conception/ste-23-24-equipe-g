package fr.unice.polytech.app;


import fr.unice.polytech.app.Orders.*;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.State.CancelledIState;
import fr.unice.polytech.app.State.ReadyIState;
import fr.unice.polytech.app.Users.CampusUser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


public class BuffetOrderStepdefs {

    private BuffetOrder buffetOrder;
    private RestaurantService restaurantService;
    private BuffetOrder buffetIssaNissa;
    @Given("^the predefined buffets are initialized$")
    public void the_predefined_buffets_are_initialized() throws Exception {
        // Initialisation du service de restaurant et des buffets
        restaurantService = new RestaurantService();
        buffetIssaNissa = createBuffetIssaNissa();
    }

    private BuffetOrder createBuffetIssaNissa() throws Exception {
        // Supposons que ces méthodes et constructeurs existent dans vos classes
        Restaurant restaurant = restaurantService.createRestaurant("Issa Nissa", createIssaNissaMenu());
        Formule formuleIssaNissa = new Formule("Issa Nissa", 30,createIssaNissaItems());
        CampusUser universityStaff = new CampusUser("Alice","au pays des merveilles");
        String contactPerson = "Bob";
        int numberOfPeople = 30;
        int numberOfItems = 10;
        Buffet buffet = new Buffet(formuleIssaNissa, numberOfItems);
        return new BuffetOrder(restaurant, universityStaff, contactPerson, numberOfPeople, buffet);
    }

    private BuffetOrder createBuffetOrder() throws Exception {
        // Supposons que ces méthodes et constructeurs existent dans vos classes
        Restaurant restaurant = restaurantService.createRestaurant("Simple Buffet", createMenu());
        Formule formuleIssaNissa = new Formule("Issa Nissa", 30,createItems());
        CampusUser universityStaff = new CampusUser("Alice","au pays des merveilles");
        String contactPerson = "Bob";
        int numberOfPeople = 54;
        int numberOfItems = 15;
        Buffet buffet = new Buffet(formuleIssaNissa, numberOfItems);
        return new BuffetOrder(restaurant, universityStaff, contactPerson, numberOfPeople, buffet);
    }

    private Menu createIssaNissaMenu() {
        List<Dish> items = new ArrayList<>();
        Dish dish = new Dish("Salade Niçoise", 10.0);
        Dish dish2 = new Dish("Pissaladière", 15.0);
        Dish dish3 = new Dish("Socca", 15.0);
        Dish dish4 = new Dish("Pan Bagnat", 15.0);
        items.add(dish);
        items.add(dish2);
        items.add(dish3);
        items.add(dish4);
        return new Menu(items);
    }

    private Menu createMenu() {
        List<Dish> items = new ArrayList<>();
        Dish dish = new Dish("Salade Niçoise", 10.0);
        Dish dish2 = new Dish("Pissaladière", 15.0);
        Dish dish3 = new Dish("Socca", 15.0);
        Dish dish4 = new Dish("Pan Bagnat", 15.0);
        Dish dish5 = new Dish("Salade Ceasar", 10.0);
        items.add(dish);
        items.add(dish2);
        items.add(dish3);
        items.add(dish4);
        items.add(dish5);
        return new Menu(items);
    }

    private List<Item> createIssaNissaItems() {
        // Créer des items pour le buffet Issa Nissa
        List<Item> items = new ArrayList<>();
        Dish dish = new Dish("Salade Niçoise", 10.0);
        Dish dish2 = new Dish("Pissaladière", 15.0);
        Dish dish3 = new Dish("Socca", 15.0);
        Dish dish4 = new Dish("Pan Bagnat", 15.0);
        items.add(new Item(dish, 1));
        items.add(new Item(dish2, 1));
        items.add(new Item(dish3, 1));
        // Ajouter plus d'items si nécessaire
        return items;
    }

    private List<Item> createItems() {
        // Créer des items pour le buffet Issa Nissa
        List<Item> items = new ArrayList<>();
        Dish dish = new Dish("Salade Niçoise", 10.0);
        Dish dish2 = new Dish("Pissaladière", 15.0);
        Dish dish3 = new Dish("Socca", 15.0);
        Dish dish4 = new Dish("Pan Bagnat", 15.0);
        Dish dish5 = new Dish("Salade Ceasar", 10.0);
        items.add(new Item(dish, 1));
        items.add(new Item(dish2, 1));
        items.add(new Item(dish3, 1));
        items.add(new Item(dish4, 1));
        items.add(new Item(dish5, 1));
        // Ajouter plus d'items si nécessaire
        return items;
    }

    @When("^we select the \"([^\"]*)\"$")
    public void we_select_the_buffet(String buffetName) {
        assertEquals("Issa Nissa", buffetIssaNissa.getBuffet().getFormule().getName());
    }

    @Then("^the order should be for \"([^\"]*)\" people with Issa Nissa items$")
    public void the_order_should_be_for_people_with_issa_nissa_items(String numberOfPeople) {
        assertEquals(String.valueOf(numberOfPeople), String.valueOf(buffetIssaNissa.getNumberOfPeople()));
        // Vous pouvez ajouter d'autres assertions ici pour valider les items spécifiques à Issa Nissa
    }
    // ... (les importations et le début de la classe restent inchangés)

    // Ajouter une étape pour valider le contenu du buffet Issa Nissa
    @Then("^the buffet should contain specific Issa Nissa dishes$")
    public void the_buffet_should_contain_specific_issa_nissa_dishes() {
        List<Item> items = buffetIssaNissa.getBuffet().getFormule().getItems();
        assertTrue(items.stream().anyMatch(item -> item.getDish().getName().equals("Salade Niçoise")));
        assertTrue(items.stream().anyMatch(item -> item.getDish().getName().equals("Pissaladière")));
        assertTrue(items.stream().anyMatch(item -> item.getDish().getName().equals("Socca")));
        // Vous pouvez ajouter des assertions supplémentaires pour d'autres plats spécifiques
    }

    @Then("^the order should be for \"([^\"]*)\" people$")
    public void the_order_should_be_for_people(int numberOfPeople) {
        assertEquals(numberOfPeople, buffetIssaNissa.getNumberOfPeople());
    }

    @And("^the buffet should contain \"([^\"]*)\" formules$")
    public void the_buffet_should_contain_formules(int numberOfFormules) {
        assertEquals(numberOfFormules, buffetIssaNissa.getBuffet().getFormuleList().size());
    }

    @And("^the total price should be correctly calculated$")
    public void the_total_price_should_be_correctly_calculated() {
        double expectedPrice = 10* buffetIssaNissa.getBuffet().getFormule().getPrix();
                assertEquals(expectedPrice, buffetIssaNissa.getPrice());
    }

    @And("the total price should be {string} euros")
    public void theTotalPriceShouldBeEuros(String expectedPrice) {
        assertEquals(Double.parseDouble(expectedPrice), buffetIssaNissa.getPrice());
    }

    @And("^we set the delivery location to \"([^\"]*)\" and the contact person to \"([^\"]*)\"$")
    public void we_set_the_delivery_location_and_contact_person(String location, String contactPerson) {
        buffetIssaNissa.setDeliveryLocation(location);
        buffetIssaNissa.setContactPerson(contactPerson);
    }

    @Then("^the delivery location should be \"([^\"]*)\"$")
    public void the_delivery_location_should_be(String location) {
        assertEquals(location, buffetIssaNissa.getDeliveryLocation());
    }

    @And("^the contact person for delivery should be \"([^\"]*)\"$")
    public void the_contact_person_for_delivery_should_be(String contactPerson) {
        assertEquals(contactPerson, buffetIssaNissa.getContactPerson());
    }
    @And("^the order is cancelled$")
    public void the_order_is_cancelled() {
        buffetIssaNissa.cancelOrder();
    }

    @Then("^the status of the order should be definitively \"([^\"]*)\"$")
    public void the_status_of_the_order_should_be(String status) {
        if (status.equals("cancelled"))
            assertTrue(buffetIssaNissa.getStatus() instanceof CancelledIState);
            assertFalse(buffetIssaNissa.getStatus() instanceof ReadyIState);

        assertEquals(status, "Cancelled");
    }

    @And("^no charges should be applied$")
    public void no_charges_should_be_applied() {
        assertEquals(0.0, buffetIssaNissa.getPrice());
    }

    @Given("^\"([^\"]*)\" from university staff has ordered a \"([^\"]*)\" for \"([^\"]*)\" people for a university event$")
    public void alice_from_university_staff_has_ordered_a_buffet_for_people_for_a_university_event(String staffName, String buffetType, int numberOfPeople) throws Exception {
        restaurantService = new RestaurantService();
        CampusUser staffMember = new CampusUser(staffName, "University Staff");
        Restaurant restaurant = restaurantService.getRestaurantByName("University Restaurant");
        buffetOrder = createBuffetOrder();
    }

    @When("^she changes the order to \"([^\"]*)\" for \"([^\"]*)\" people$")
    public void she_changes_the_order_to_a_different_buffet_for_people(String newBuffetType, int newNumberOfPeople) throws Exception {
        Formule newFormule = new Formule(newBuffetType,newNumberOfPeople,createItems()); /* autres paramètres pour la formule HotPizza */;
        buffetOrder.changeOrder(newFormule, newNumberOfPeople);
    }

    @Then("^the buffet order should be updated to the \"([^\"]*)\" formula for \"([^\"]*)\" people$")
    public void the_buffet_order_should_be_updated(String expectedBuffetType, int expectedNumberOfPeople) {
        assertEquals(expectedBuffetType, buffetOrder.getBuffet().getFormule().getName());
        assertEquals(expectedNumberOfPeople, buffetOrder.getNumberOfPeople());
    }

    @And("^the updated order details should be confirmed with Alice$")
    public void the_updated_order_details_should_be_confirmed_with_Alice() {
        assertNotNull(buffetOrder.getContactPerson());
    }

}

