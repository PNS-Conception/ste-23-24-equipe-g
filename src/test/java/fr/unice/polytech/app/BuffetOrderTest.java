package fr.unice.polytech.app;

import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.State.PaidIState;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuffetOrderTest {

    private RestaurantService restaurantService = new RestaurantService();
    @Test
    void testOrderInitialization() throws Exception {
        Restaurant restaurant = new Restaurant("Restaurant Test", createMenu());
        CampusUser staff = new CampusUser("Alice", "Université");
        Formule formule = new Formule("Formule1", 100.0,createItems());
        Buffet buffet = new Buffet(formule, 5);

        BuffetOrder order = new BuffetOrder(restaurant, staff, "Bob", 30, buffet);

        assertNotNull(order.getOrderId(), "L'ID de la commande doit être initialisé");
        assertEquals("Bob", order.getContactPerson(), "La personne de contact doit être Bob");
        assertEquals(30, order.getNumberOfPeople(), "Le nombre de personnes doit être 30");
        assertEquals(buffet, order.getBuffet(), "Le buffet doit correspondre à celui qui est commandé");
    }

    @Test
    void testChangeOrder() throws Exception {
        // Initialiser BuffetOrder
        Restaurant restaurant = new Restaurant("Restaurant Test", createMenu());
        CampusUser staff = new CampusUser("Alice", "Université");
        Formule formule = new Formule("Formule1", 100.0, createItems());
        Buffet buffet = new Buffet(formule, 5);
        BuffetOrder order = new BuffetOrder(restaurant, staff, "Bob", 30, buffet);

        // Modifier la commande
        Formule newFormule = new Formule("Formule2", 200.0,createItems());
        order.changeOrder(newFormule, 40);

        // Vérifier les changements
        assertEquals(40, order.getNumberOfPeople(), "Le nombre de personnes doit être mis à jour à 40");
        assertEquals(newFormule, order.getBuffet().getFormule(), "La formule du buffet doit être mise à jour");

        // Autres tests ici pour les méthodes pay, accept, reject, etc.
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
    @Test
    void testGetRouteDetails() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.setRouteDetails("Route Test");
        assertEquals("Route Test", order.getRouteDetails(), "Le détail de l'itinéraire doit être correct");
    }

    @Test
    void testGetPickupTime() throws Exception {
        BuffetOrder order = createBuffetOrder();
        LocalTime pickupTime = LocalTime.now();
        order.setPickupTime(pickupTime);
        assertEquals(pickupTime, order.getPickupTime(), "L'heure de collecte doit être correcte");
    }

    @Test
    void testGetRestaurants() throws Exception {
        BuffetOrder order = createBuffetOrder();
        assertEquals(1, order.getRestaurants().size(), "La liste des restaurants doit contenir un élément");
    }

    @Test
    void testSetStatus() throws Exception {
        BuffetOrder order = createBuffetOrder();
        IState status = new PaidIState();
        order.setStatus(status);
        assertEquals(status, order.getStatus(), "Le statut doit être mis à jour correctement");
    }

    @Test
    void testGetRestaurant() throws Exception {
        BuffetOrder order = createBuffetOrder();
        assertNotNull(order.getRestaurant(), "Doit retourner un restaurant");
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
    @Test
    void testGetOwner() throws Exception {
        BuffetOrder order = createBuffetOrder();
        assertNotNull(order.getOwner(), "Doit retourner un propriétaire");
    }

    @Test
    void testSetDeliveryAddress() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.setDeliveryAddress("123 Main St");
        assertEquals("123 Main St", order.getDeliveryAddress(), "L'adresse de livraison doit être correctement mise à jour");
    }

    @Test
    void testIsOrdersPaid() throws Exception {
        BuffetOrder order =createBuffetOrder();
        order.pay();
        assertTrue(order.isOrdersPaid(), "La commande doit être marquée comme payée");
    }

    @Test
    void testIsOrderCancelled() throws Exception {
        BuffetOrder order =createBuffetOrder();
        order.cancelOrder();

        assertTrue(order.isOrderCancelled(), "La commande doit être marquée comme annulée");
    }

    @Test
    void testDelete() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.delete();
        assertNull(order.getOwner(), "Le propriétaire doit être supprimé");
    }

    @Test
    void testIsOrderReady() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.ready();
        assertFalse(order.isOrderReady(), "La commande doit être marquée comme prête");
    }

    @Test
    void testIsOrderPickedUp() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.pickUp();
        assertTrue(order.isOrderPickedUp(), "La commande doit être marquée comme récupérée");
    }

    @Test
    void testValidateForDelivery() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.validateForDelivery();
        assertTrue(order.isOrderPickedUp(), "La commande doit être validée pour la livraison");
    }

    @Test
    void testGetId() throws Exception {
        BuffetOrder order =createBuffetOrder();
        assertNotNull(order.getId(), "Doit retourner un ID");
    }

    @Test
    void testSetRestaurant() throws Exception {
        BuffetOrder order = createBuffetOrder();
        Restaurant restaurant = restaurantService.createRestaurant("Simple Buffet ccc", createMenu());;
        order.setRestaurant(restaurant);
        assertEquals(restaurant.getName(), order.getRestaurant().getName(), "Le restaurant doit être correctement mis à jour");
    }

    @Test
    void testPay() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.pay();
        assertTrue(order.isOrdersPaid(), "La commande doit être marquée comme payée");
    }

    @Test
    void testAccept() throws Exception {
        BuffetOrder order =createBuffetOrder();
        order.accept();
    }

    @Test
    void testReject() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.reject();
    }

    @Test
    void testReady() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.ready();
        assertFalse(order.isOrderReady(), "La commande doit être marquée comme prête");
    }

    @Test
    void testAssign() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.assign();
    }

    @Test
    void testDeliver() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.deliver();
    }

    @Test
    void testCancel() throws Exception {
        BuffetOrder order = createBuffetOrder();
        order.cancel();
        assertTrue(order.isOrderCancelled(), "La commande doit être marquée comme annulée");
    }

    @Test
    void testPickUp() throws Exception {
        BuffetOrder order =createBuffetOrder();
        order.pickUp();
        assertTrue(order.isOrderPickedUp(), "La commande doit être marquée comme récupérée");
    }
}
