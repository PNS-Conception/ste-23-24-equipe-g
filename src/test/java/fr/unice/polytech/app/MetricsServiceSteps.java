package fr.unice.polytech.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import java.time.LocalTime;
import java.util.*;

public class MetricsServiceSteps {

    private MetricsService metricsService;
    private Map<LocalTime, Long> orderVolumeOverTime;
    private Map<Restaurant, List<Order>> ordersByRestaurant;
    private Map<String, Long> deliveryLocationMetrics;
    private Map<Item, Integer> popularMenuItems;
    private Map<Restaurant, Double> deliveryEfficiencyMetrics;
    RestaurantManager manager;
    Restaurant restaurant;
    private Map<CampusUser, Map<String, Object>> userBehaviorMetrics;


    @Given("order volume data has been collected")
    public void order_volume_data_has_been_collected() {
        DataCollector dataCollector = new DataCollector();
        metricsService = new MetricsService(dataCollector);
        Dish dish1 = new Dish("Pizza", 10.0);
        Dish dish2 = new Dish("burger", 12.0);
        Item item1 = new Item(dish1, 2);
        Item item2 = new Item(dish2, 3);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        LocalTime deliveryTime = LocalTime.now(); // Définir un temps de livraison pour les commandes

        SingleOrder order1 = new SingleOrder(items);
        order1.setDeliveryTime(deliveryTime); // Définir le temps de livraison
        dataCollector.collectOrderData(order1);

        SingleOrder order2 = new SingleOrder(items);
        order2.setDeliveryTime(deliveryTime); // Définir le temps de livraison
        dataCollector.collectOrderData(order2);
    }


    @When("I request to view order volume metrics")
    public void i_request_to_view_order_volume_metrics() {
        orderVolumeOverTime = metricsService.getOrderVolumeOverTime();
    }

    @Then("the system should provide time series metrics of order volumes")
    public void the_system_should_provide_time_series_metrics_of_order_volumes() {
        assertNotNull(orderVolumeOverTime);
        assertFalse(orderVolumeOverTime.isEmpty());
    }



    @Given("restaurant-specific data has been collected")
    public void restaurant_specific_data_has_been_collected() {
        Dish dish = new Dish("Pizza", 10.0);
        Item item = new Item(dish, 2);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);

        Restaurant testRestaurant = new Restaurant("TestRestaurant", "TestAddress");
        SingleOrder testOrder = new SingleOrder(items);
        testRestaurant.addOrder(testOrder);
        DataCollector dataCollector = new DataCollector();
        dataCollector.collectOrderData(testOrder);
        metricsService = new MetricsService(dataCollector);
    }

    @Given("I am logged in as a restaurant manager")
    public void managerOfRestaurantLoggedIn() {
        // Créer un nouveau manager
        manager = new RestaurantManager("testManager", "testPass",  "testEmail");

        Admin.addRestaurant("My Restaurant", "Some address", manager);
        restaurant = Admin.getRestaurants().stream()
                .filter(r -> r.getName().equals("My Restaurant") && r.getAddress().equals("Some address"))
                .findFirst()
                .orElse(null);

        // Si le restaurant est trouvé, associez-le au manager
        if (restaurant != null) {
            restaurant.setOwner(manager);
        } else {
            // Si aucun restaurant correspondant n'est trouvé, lancez une exception ou gérez cette situation
            throw new IllegalStateException("Restaurant not found");
        }

        // Initialiser DataCollector et collecter des données pour le restaurant
        DataCollector dataCollector = new DataCollector();
        Dish dish = new Dish("Pizza", 10.0);
        Item item = new Item(dish, 2);
        SingleOrder order = new SingleOrder(Arrays.asList(item));
        order.setRestaurant(restaurant);
        dataCollector.collectOrderData(order);

        metricsService = new MetricsService(dataCollector);
    }

    @When("I request metrics for my restaurant")
    public void i_request_metrics_for_my_restaurant() {
        ordersByRestaurant = metricsService.getOrdersByRestaurant(restaurant); // Utiliser le même restaurant
    }


    @Then("the system should provide metrics on order volumes, delivery efficiency, and popular menu items")
    public void the_system_should_provide_metrics_on_order_volumes_delivery_efficiency_and_popular_menu_items() {
        assertNotNull("Metrics for order volumes should not be null", ordersByRestaurant);

        deliveryEfficiencyMetrics = metricsService.getDeliveryEfficiencyMetrics();
        assertNotNull("deliveryEfficiencyMetrics metrics should not be null", deliveryEfficiencyMetrics);
        popularMenuItems = metricsService.getPopularMenuItems();
        assertNotNull("popularMenuItems should not be null", popularMenuItems);
    }

        @Given("delivery location data has been collected")
        public void delivery_location_data_has_been_collected() {
            DataCollector dataCollector = new DataCollector();
            metricsService = new MetricsService(dataCollector);

            Dish dish1 = new Dish("Pizza", 10.0);
            Dish dish2 = new Dish("burger", 12.0);
            Item item1 = new Item(dish1, 2);
            Item item2 = new Item(dish2, 3);

            // Première commande avec 'Location A'
            ArrayList<Item> items1 = new ArrayList<>();
            items1.add(item1);
            Order order1 = new Order(items1);
            order1.setDeliveryLocation("Location A");
            dataCollector.collectOrderData(order1);

            // Deuxième commande avec 'Location B'
            ArrayList<Item> items2 = new ArrayList<>();
            items2.add(item2);
            Order order2 = new Order(items2);
            order2.setDeliveryLocation("Location B");
            dataCollector.collectOrderData(order2);
        }


    @When("I request to view delivery location metrics")
    public void i_request_to_view_delivery_location_metrics() {
        deliveryLocationMetrics = metricsService.getDeliveryLocationMetrics();
    }

    @Then("the system should provide metrics representing delivery locations")
    public void the_system_should_provide_metrics_representing_delivery_locations() {
        assertNotNull("Delivery location metrics should not be null", deliveryLocationMetrics);

        Long countAtLocationA = deliveryLocationMetrics.get("Location A");
        Long countAtLocationB = deliveryLocationMetrics.get("Location B");

        assertNotNull("Count for Location A should not be null", countAtLocationA);
        assertTrue("Count for Location A should be positive", countAtLocationA > 0);

        assertNotNull("Count for Location B should not be null", countAtLocationB);
        assertTrue("Count for Location B should be positive", countAtLocationB > 0);
    }

    @Given("user behavior data has been collected")
    public void user_behavior_data_has_been_collected() {
        DataCollector dataCollector = new DataCollector();
        metricsService = new MetricsService(dataCollector);

        // Créer des données utilisateur et de commande
        CampusUser user = new CampusUser("name", "password", "", "email@example.com");
        Dish dish = new Dish("Burger", 15.0);
        Item item = new Item(dish, 1);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        Order order = new Order(items);

        // Collecter des données de comportement utilisateur
        dataCollector.collectUserBehaviorData(user, order);
    }

    @When("I request to view user behavior metrics")
    public void i_request_to_view_user_behavior_metrics() {
        userBehaviorMetrics = metricsService.getUserBehaviorMetrics();
    }

    @Then("the system should provide metrics detailing user ordering patterns and preferences")
    public void the_system_should_provide_metrics_detailing_user_ordering_patterns_and_preferences() {
        assertNotNull("User behavior metrics should not be null", userBehaviorMetrics);
        assertFalse("User behavior metrics should not be empty", userBehaviorMetrics.isEmpty());
    }



}


