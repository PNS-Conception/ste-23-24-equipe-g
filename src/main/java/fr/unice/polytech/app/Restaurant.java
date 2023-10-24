package fr.unice.polytech.app;

import fr.unice.polytech.app.Menu;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String name;
    private String operatingHours;
    private Menu menu;

    private LocalTime openingTime;
    private LocalTime closingTime;

    private List<Order> orderList;

    public Restaurant(UUID id, String name) {
        this.id = id;
        this.name = name;
        orderList = new ArrayList<>();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getOperatingHours() {
        return operatingHours;
    }


    public boolean cancel(Order order, long minutesPassed) {
        if (orderList.contains(order)) {
            LocalTime currentTime = LocalTime.now();
            LocalTime acceptedTime = order.getAcceptedTime();
            /*long minutesPassed = java.time.Duration.between(acceptedTime, currentTime).toMinutes();
            order.setStatus(OrderStatus.Cancelled);*/
            return minutesPassed <= 30;
        }

        return false;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

}
