package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.*;

public class Restaurant {
    private UUID id;
    private String name;
    private Menu menu;
    private String address;
    private CampusUser owner;
    private List<Shift> schedule;
    private List<SingleOrder> singleOrderList;
    private boolean full;
    private Map<CampusUser,Integer> numberOfDishesPerUser = new HashMap<>();
    private int numberOfOrdersPerUser;
    private int numberOfOrdersForDiscount;

    private List<ExtensionDiscount> extensionDiscounts = new ArrayList<>();
    private int NumberOfDaysForDiscount;
    private int percentageDiscount;
    private int percentageDiscountByNbOfOrders;
    private int numberOfDishesForDiscount;

    public Restaurant(String name, RestaurantManager manager,String address){
        this.id = UUID.randomUUID();
        this.name = name;
        this.singleOrderList = new ArrayList<>();
        schedule = new ArrayList<>();
        this.menu = new Menu();
        //System.out.println(manager.getEmail());
        this.owner = manager;
        this.address = address;

    }

    public Restaurant(String name, Menu menu) {
        this.id = UUID.randomUUID();
        this.name = name;
        singleOrderList = new ArrayList<>();
        schedule = new ArrayList<>();
        this.menu = menu;
    }

    public void setMenu(Menu menu, RestaurantManager manager) {
        if (manager == owner) {
            this.menu = menu;
        }
    }

    public Menu getMenu() {
        return menu;
    }


    public boolean cancel(SingleOrder singleOrder, long minutesPassed) {
        if (singleOrderList.contains(singleOrder)) {
            userRefund(singleOrder);
            singleOrder.setStatus(OrderStatus.CANCELLED);
            return minutesPassed <= 30;
        }

        return false;
    }

    public void acceptOrder(SingleOrder singleOrder) {
        if (singleOrderList.contains(singleOrder)) {
            singleOrder.setStatus(OrderStatus.ACCEPTED);
            singleOrder.setAcceptedTime(LocalTime.now());
        }
    }


    private void userRefund(SingleOrder singleOrder) {
        singleOrder.getClient().refund(singleOrder);
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


    public void addNbDishesToUser(CampusUser user, SingleOrder singleOrder){
        if(numberOfDishesPerUser.containsKey(user)){
            int currentOrders = numberOfDishesPerUser.get(user)+ singleOrder.getNumberOfDishes() ;
            if (currentOrders > numberOfDishesForDiscount) {
                numberOfDishesPerUser.put(user, 0);
            } else {
                numberOfDishesPerUser.put(user, currentOrders);
            }
        }else{
            numberOfDishesPerUser.put(user,1);
        }
    }

    public int getNumberOfDishesForUser(CampusUser user){
        return numberOfDishesPerUser.getOrDefault(user, 0);
    }

    public boolean isEligibleForDiscountByNbOfDishes(CampusUser user){
        if(numberOfDishesPerUser.containsKey(user)){
            int currentOrders = numberOfDishesPerUser.get(user);
            return currentOrders > numberOfDishesForDiscount;
        }
        return false;
    }



    public void setNumberOfDishesPerUser(Map<CampusUser,Integer> numberOfDishesPerUser){
        this.numberOfDishesPerUser = numberOfDishesPerUser;
    }

    public void setPercentageDiscountByNbOfDishes(int percentageDiscount){
        this.percentageDiscount = percentageDiscount;
    }

    public int getPercentageDiscountByNbOfDishes() {
        return percentageDiscount;
    }

    public void setNumberOfDishesPerUser(int numberOfOrdersPerUser){
        this.numberOfOrdersPerUser = numberOfOrdersPerUser;
    }

    public int getNumberOfDishesPerUser(){
        return numberOfOrdersPerUser;
    }

    public int getNumberOfDishesForDiscount(){
        return numberOfDishesForDiscount;
    }

    public void setNumberOfOrdersForDiscount(int numberOfOrdersForDiscount){
        this.numberOfOrdersForDiscount = numberOfOrdersForDiscount;
    }

    public void setNumberOfDishesForDiscount(int numberOfDishesForDiscount){
        this.numberOfDishesForDiscount = numberOfDishesForDiscount;
    }


    public void setNumberOfDaysForDiscount(int numberOfDaysForDiscount) {
        NumberOfDaysForDiscount = numberOfDaysForDiscount;
    }

    public void addNbOrderToUser(CampusUser user) {
        if (NumberOfDaysForDiscount==0 || numberOfOrdersForDiscount==0){
            return;
        }
        ExtensionDiscount extensionDiscount = getExtensionDiscount(user);
        if (extensionDiscount == null) {

            extensionDiscount = new ExtensionDiscount(user, numberOfOrdersForDiscount, NumberOfDaysForDiscount);
            extensionDiscounts.add(extensionDiscount);
        }else {
            extensionDiscount.setNumberOfOrders(numberOfOrdersForDiscount);
        }

    }

    public ExtensionDiscount getExtensionDiscount(CampusUser user) {
        for (ExtensionDiscount extensionDiscount : extensionDiscounts) {
            if (extensionDiscount.getClient() == user) {
                return extensionDiscount;
            }
        }
        return null;
    }

    public void removeNbDishesToUser(CampusUser user, SingleOrder singleOrder) {
        if(numberOfDishesPerUser.containsKey(user)){
            int currentOrders = numberOfDishesPerUser.get(user)- singleOrder.getNumberOfDishes() ;
            if (currentOrders > numberOfDishesForDiscount) {
                numberOfDishesPerUser.put(user, 0);
            } else {
                numberOfDishesPerUser.put(user, currentOrders);
            }
        }else{
            numberOfDishesPerUser.put(user,0);
        }
    }

    public void removeNbOrderToUser(CampusUser user) {
        if (NumberOfDaysForDiscount==0 || numberOfOrdersForDiscount==0){
            return;
        }
        ExtensionDiscount extensionDiscount = getExtensionDiscount(user);
        if (extensionDiscount != null) {
            extensionDiscount.setNumberOfOrders(extensionDiscount.getNumberOfOrders()-1);
        }
    }

    public boolean isEligibleForDiscountByNbOfOrders(CampusUser user){
        if (NumberOfDaysForDiscount==0 || numberOfOrdersForDiscount==0){
            return false;
        }
        ExtensionDiscount extensionDiscount = getExtensionDiscount(user);
        if (extensionDiscount != null && extensionDiscount.getIsDiscountValid()) {
            return extensionDiscount.isValid();
        }
        return false;
    }

    public double getPercentageDiscountByNbOfOrders() {
        return percentageDiscountByNbOfOrders;
    }

    public void setPercentageDiscountByNbOfOrders(int percentageDiscountByNbOfOrders) {
        this.percentageDiscountByNbOfOrders = percentageDiscountByNbOfOrders;
    }

    public void removeShift(Shift shift) {
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
}
