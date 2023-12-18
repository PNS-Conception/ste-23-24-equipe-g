package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.Restaurant.Item;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.User.UserType;

import java.util.List;

public class Cart {

    private List<Item> items;
    private SingleOrder singleOrder;


    public Cart(List<Item> items) {
        this.items = items;
    }


    public List<Item> getItems() {
        return items;
    }

    public double calculatePrice() {
        double price = 0;

        CampusUser user = singleOrder.getClient();
        Restaurant restaurant = singleOrder.getRestaurant();
        for (Item item : items) {

            double itemPrice = (user.getType() == UserType.CLIENT) ? item.getPrice() : item.getNotRegularPrice();

            if (restaurant.getDiscountSystem().isEligibleForDiscountByNbOfDishes(user)) {
                itemPrice *= 1 - ((double) restaurant.getDiscountSystem().getPercentageDiscountByNbOfDishes() / 100);
            }

            if (restaurant.getDiscountSystem().isEligibleForDiscountByNbOfOrders(user)) {
                itemPrice *= 1 - (restaurant.getDiscountSystem().getPercentageDiscountByNbOfOrders() / 100);
            }


            price += itemPrice;
        }

        return price;
    }

    public int getNumberOfDishes(){
        int numberOfDishes = 0;
        for(Item item : items){
            numberOfDishes += item.getQuantity();
        }
        return numberOfDishes;
    }


    public void add(Item newItem) {
        items.add(newItem);
    }

    public void clear() {
        items.clear();
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public void setSingleOrder(SingleOrder singleOrder) {
        this.singleOrder = singleOrder;
    }
}
