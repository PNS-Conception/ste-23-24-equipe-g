package fr.unice.polytech.app.Restaurant;

import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.State.AcceptedIState;
import fr.unice.polytech.app.State.CancelledIState;
import fr.unice.polytech.app.User.CampusUser;

import java.time.LocalTime;
import java.util.*;

public class Restaurant {
    private UUID id;
    private String name;
    private Menu menu;
    private List<Menu> menus = new ArrayList<>();
    private String address;
    private CampusUser owner;
    private List<Day.Shift> schedule;
    private List<SingleOrder> singleOrderList;
    private boolean full;

    private DiscountSytem discountSystem;

    public Restaurant(String name, RestaurantManager manager, String address){
        this.id = UUID.randomUUID();
        this.name = name;
        this.singleOrderList = new ArrayList<>();
        schedule = new ArrayList<>();
        this.menu = new Menu();
        this.owner = manager;
        this.address = address;
        discountSystem = new DiscountSytem();
    }

    public Restaurant(String name, Menu menu) {
        this.id = UUID.randomUUID();
        this.name = name;
        singleOrderList = new ArrayList<>();
        schedule = new ArrayList<>();
        this.menu = menu;
        discountSystem = new DiscountSytem();
    }

    public Menu getMenu() {
        return menu;
    }


    public boolean cancel(SingleOrder singleOrder, long minutesPassed) {
        if (singleOrderList.contains(singleOrder) && minutesPassed <= 30) {
            userRefund(singleOrder);
            singleOrder.setStatus(new CancelledIState());
            return true;
        }

        return false;
    }

    public void acceptOrder(SingleOrder singleOrder) {
        if (singleOrderList.contains(singleOrder)) {
            singleOrder.setStatus(new AcceptedIState());
            singleOrder.setAcceptedTime(LocalTime.now());

        }
    }


    private void userRefund(SingleOrder singleOrder) {
        singleOrder.getClient().getPaiementSystem().refund(singleOrder);
    }

    public boolean addShift(LocalTime openingTime, LocalTime closingTime, Day day, RestaurantManager manager) {
        if (manager == owner) {
            Day.Shift shift = new Day.Shift(openingTime, closingTime,day);
            schedule.add(shift);
            return true;
        }
        return false;
    }



    public List<Day.Shift> getSchedule() {
        return schedule;
    }

    public boolean scheduleContains( Day.Shift shift) {
        for (Day.Shift s : schedule) {
            if (s.getDay() == shift.getDay() && s.getOpeningTime() == shift.getOpeningTime() && s.getClosingTime() == shift.getClosingTime()) {
                return true;
            }
        }
        return false;

    }

    public String getName() {
        return name;
    }

    public void addOrder(SingleOrder singleOrder) {
        singleOrderList.add(singleOrder);
    }

    public void clearOrder() {
        singleOrderList.clear();
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


    public CampusUser getOwner() {
        return owner;
    }

    public void clearOrderList() {
        singleOrderList.clear();
    }

    public List<SingleOrder> getOrderList() {
        return singleOrderList;
    }


    public DiscountSytem getDiscountSystem() {
        return discountSystem;
    }


    public void removeShift(Day.Shift shift) {
        schedule.remove(shift);
    }

    public void addDish(Dish dish, RestaurantManager manager) {
            menu.addDish(dish, manager);
    }

    public void removeDish(Dish dish, RestaurantManager manager) {
            menu.removeDish(dish, manager);
    }

    public void setOwner(RestaurantManager manager) {
        this.owner= manager;
    }
    public boolean offersAfterworkMenus() {
            return menu != null && menu.isAfterworkMenu();
        }



    public boolean setAfterworkMenu(boolean b) {
        return b;
    }


    public void setMenu(Menu menu, RestaurantManager manager) {
        if (manager == owner) {
            this.menu = menu;
            menus.add(menu);
        }
    }

}
