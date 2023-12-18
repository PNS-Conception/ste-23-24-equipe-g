
package fr.unice.polytech.app.Statistic;

import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.Delivery.DeliveryPerson;
import fr.unice.polytech.app.Orders.Order;
import fr.unice.polytech.app.Restaurant.*;

import java.util.*;

/**
 * The DataCollector class is responsible for collecting and storing various data related to orders, delivery people,
 * menu item popularity, and user behavior.
 */
public class DataCollector {

    private List<Order> orders;
    private List<DeliveryPerson> deliveryPeople;
    private Map<Item, Integer> menuItemPopularity;
    private Map<CampusUser, List<Order>> userOrders;

    /**
     * Constructs a new DataCollector object with empty collections.
     */
    public DataCollector() {
        this.orders = new ArrayList<>();
        this.deliveryPeople = new ArrayList<>();
        this.menuItemPopularity = new HashMap<>();
        this.userOrders = new HashMap<>();
    }

    /**
     * Collects order data by adding the specified order to the list of orders.
     *
     * @param order The order to be collected.
     */
    public void collectOrderData(Order order) {
        this.orders.add(order);
    }

    /**
     * Collects delivery data by adding the specified delivery person to the list of delivery people.
     *
     * @param deliveryPerson The delivery person to be collected.
     */
    public void collectDeliveryData(DeliveryPerson deliveryPerson) {
        this.deliveryPeople.add(deliveryPerson);
    }

    /**
     * Collects menu item popularity data by updating the popularity count of the specified menu item.
     *
     * @param menuItem The menu item to be collected.
     */
    public void collectMenuItemPopularityData(Item menuItem) {
        menuItemPopularity.merge(menuItem, 1, Integer::sum);
    }

    /**
     * Collects user behavior data by adding the specified order to the list of orders associated with the user.
     *
     * @param user  The user for whom the behavior data is collected.
     * @param order The order to be collected.
     */
    public void collectUserBehaviorData(CampusUser user, Order order) {
        userOrders.computeIfAbsent(user, k -> new ArrayList<>()).add(order);
    }

    /**
     * Retrieves the list of orders placed for a specific restaurant.
     *
     * @param restaurant The restaurant for which the orders are retrieved.
     * @return The list of orders placed for the specified restaurant.
     */
    public List<Order> getOrdersForRestaurant(Restaurant restaurant) {
        List<Order> ordersForRestaurant = new ArrayList<>();

        for (Order order : orders) {
            if (order.getRestaurant().equals(restaurant)) {
                ordersForRestaurant.add(order);
            }
        }

        return ordersForRestaurant;
    }

    /**
     * Retrieves the list of all orders.
     *
     * @return The list of all orders.
     */
    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    /**
     * Retrieves the map of menu item popularity, where the keys are menu items and the values are their popularity counts.
     *
     * @return The map of menu item popularity.
     */
    public Map<Item, Integer> getMenuItemPopularity() {
        return Collections.unmodifiableMap(menuItemPopularity);
    }

    /**
     * Retrieves the map of user orders, where the keys are campus users and the values are lists of their orders.
     *
     * @return The map of user orders.
     */
    public Map<CampusUser, List<Order>> getUserOrders() {
        return Collections.unmodifiableMap(userOrders);
    }

    /**
     * Retrieves the list of all delivery people.
     *
     * @return The list of all delivery people.
     */
    public List<DeliveryPerson> getDeliveryPeople() {
        return Collections.unmodifiableList(deliveryPeople);
    }
}

