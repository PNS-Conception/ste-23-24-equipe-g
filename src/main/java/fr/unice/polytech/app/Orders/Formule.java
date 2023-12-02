package fr.unice.polytech.app.Orders;

import javax.swing.plaf.nimbus.State;

import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.State.PlacedIState;
import fr.unice.polytech.app.State.ReadyIState;
import fr.unice.polytech.app.Restaurant.Item;

import java.util.List;

public class Formule {
    private String name;
    private double prix;
    private List<Item> items;

    private IState status;

    public Formule(String name,double prix, List<Item> items) throws Exception {
        this.name = name;
        this.prix = prix;
        this.items = items;
        this.init();

    }

    public void init() throws Exception {
        status = new PlacedIState();
    }

    public void ready() throws Exception {
        status = new ReadyIState();
    }

    public double getPrix() {
        return prix;
    }

    public IState getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setStatus(IState status) {
        this.status = status;
    }
    // Getters and setters for prix and items
}
