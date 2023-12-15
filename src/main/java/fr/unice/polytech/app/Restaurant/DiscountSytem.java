package fr.unice.polytech.app.Restaurant;

import fr.unice.polytech.app.Orders.SingleOrder;
import fr.unice.polytech.app.Users.CampusUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountSytem {
    private Map<CampusUser,Integer> numberOfDishesPerUser = new HashMap<>();
    private int numberOfOrdersPerUser;
    private int numberOfOrdersForDiscount;

    private List<ExtensionDiscount> extensionDiscounts = new ArrayList<>();
    private int NumberOfDaysForDiscount;
    private int percentageDiscount;
    private int percentageDiscountByNbOfOrders;
    private int numberOfDishesForDiscount;

    public DiscountSytem() {
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
            return  numberOfDishesPerUser.get(user) == numberOfDishesForDiscount;
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
}
