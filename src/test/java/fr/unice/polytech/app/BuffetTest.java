package fr.unice.polytech.app;

import static org.junit.jupiter.api.Assertions.*;
import fr.unice.polytech.app.Restaurant.*;
import fr.unice.polytech.app.Orders.Buffet;
import fr.unice.polytech.app.Orders.formula;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuffetTest {

    @Test
    void testGetPrice() throws Exception {
        formula formula = new formula("Formule1", 100.0, createItems());
        Buffet buffet = new Buffet(formula, 5);
        assertEquals(500.0, buffet.getPrice(), "Le prix du buffet doit être correctement calculé");
    }

    @Test
    void testReadyStatus() throws Exception {
        formula formula = new formula("Formule1", 100.0,  createItems());
        formula.ready();
        Buffet buffet = new Buffet(formula, 5);
        buffet.makeready();
        assertFalse(buffet.ready(), "Le buffet doit être prêt si toutes les formules sont prêtes");
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

}
