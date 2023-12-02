package fr.unice.polytech.app;

import fr.unice.polytech.app.Orders.Formule;
import fr.unice.polytech.app.Restaurant.Dish;
import fr.unice.polytech.app.Restaurant.Item;
import fr.unice.polytech.app.State.ReadyIState;
import fr.unice.polytech.app.Orders.Formule;
import fr.unice.polytech.app.Restaurant.Item;
import fr.unice.polytech.app.Restaurant.Dish;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FormuleTest {

    @Test
    void testFormuleInitialization() throws Exception {
        Formule formule = new Formule("Formule1", 100.0, createItems());
        assertEquals("Formule1", formule.getName(), "Le nom de la formule doit être Formule1");
        assertEquals(100.0, formule.getPrix(), "Le prix doit être de 100.0");
        // Testez les autres getters et setters si nécessaire
    }

    @Test
    void testReadyStatus() throws Exception {
        Formule formule = new Formule("Formule1", 100.0, createItems());
        formule.ready();
        assertTrue(formule.getStatus() instanceof ReadyIState, "Le statut de la formule doit être Ready");
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

    // Ajoutez plus de tests pour d'autres méthodes et comportements
}
