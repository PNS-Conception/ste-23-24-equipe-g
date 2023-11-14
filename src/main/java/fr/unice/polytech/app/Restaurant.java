package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String name;
    private Menu menu;

    private String address;

    private CampusUser owner;
    private List<Shift> schedule;
    private List<Order> orderList;
    private boolean full;

    public Restaurant(String name){
        this.id = UUID.randomUUID();
        this.name = name;
        orderList = new ArrayList<>();
        schedule = new ArrayList<>();
        this.menu = new Menu();
    }

    public Restaurant(String name, Menu menu) {
        this.id = UUID.randomUUID();
        this.name = name;
        orderList = new ArrayList<>();
        schedule = new ArrayList<>();
        this.menu = menu;
    }
    public Restaurant(String name, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        orderList = new ArrayList<>();
        schedule = new ArrayList<>();
        this.address = address;
    }
    public void setMenu(Menu menu, RestaurantManager manager) {
        if (manager.getType() == UserType.Manager) {
            this.menu = menu;
        }
    }

    public Menu getMenu() {
        return menu;
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

    public boolean addShift(LocalTime openingTime, LocalTime closingTime, Day day,RestaurantManager manager) {
        if (manager == owner) {
            Shift shift = new Shift(openingTime, closingTime,day);
            schedule.add(shift);
            return true;
        }
        return false;
    }



    public List<Shift> getSchedule() {
        return schedule;
    }

    public boolean scheduleContains( Shift shift) {
        for (Shift s : schedule) {
            if (s.getDay() == shift.getDay() && s.getOpeningTime() == shift.getOpeningTime() && s.getClosingTime() == shift.getClosingTime()) {
                return true;
            }
        }
        return false;

    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean isFull() {
        return full;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setOwner(RestaurantManager owner) {
        this.owner = owner;
    }

    public CampusUser getOwner() {
        return owner;
    }

    public void clearOrderList() {
        orderList.clear();
    }

    public List<Order> getOrderList() {
        return orderList;
    }


}
