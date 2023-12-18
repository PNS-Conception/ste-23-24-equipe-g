package fr.unice.polytech.app.State;

import fr.unice.polytech.app.Orders.Order;

/**
 * This class represents the Delivered state of an order.
 * In this state, the order has already been delivered and cannot undergo any further changes.
 */
public class DelivredIState implements IState {
    /**
     * Throws an exception indicating that the order has already been placed.
     * @param order The order to be placed
     * @throws Exception if the order has already been placed
     */
    @Override
    public void placeOrder(Order order) throws Exception {
        throw new Exception("Order already placed");
    }

    /**
     * Throws an exception indicating that the order has already been paid.
     * @param order The order to be paid
     * @throws Exception if the order has already been paid
     */
    @Override
    public void pay(Order order)throws Exception {
        throw new Exception("Order already paid");
    }

    /**
     * Throws an exception indicating that the order has already been cancelled.
     * @param order The order to be cancelled
     * @throws Exception if the order has already been cancelled
     */
    @Override
    public void cancelOrder(Order order)throws Exception {
        throw new Exception("Order already cancelled");
    }

    /**
     * Throws an exception indicating that the order has already been accepted.
     * @param order The order to be accepted
     * @throws Exception if the order has already been accepted
     */
    @Override
    public void acceptOrder(Order order)throws Exception {
        throw new Exception("Order already accepted");
    }

    /**
     * Throws an exception indicating that the order has already been rejected.
     * @param order The order to be rejected
     * @throws Exception if the order has already been rejected
     */
    @Override
    public void rejectOrder(Order order)throws Exception {
        throw new Exception("Order already rejected");
    }

    /**
     * Throws an exception indicating that the order has already been marked as ready.
     * @param order The order to be marked as ready
     * @throws Exception if the order has already been marked as ready
     */
    @Override
    public void readyOrder(Order order)throws Exception {
        throw new Exception("Order already ready");
    }

    /**
     * Throws an exception indicating that the order has already been validated.
     * @param order The order to be validated
     * @throws Exception if the order has already been validated
     */
    @Override
    public void validate(Order order)throws Exception {
        throw new Exception("Order already validated");
    }

    /**
     * Throws an exception indicating that the order has already been delivered.
     * @param order The order to be delivered
     * @throws Exception if the order has already been delivered
     */
    @Override
    public void delivery(Order order)throws Exception {
        throw new Exception("Order already delivered");
    }

    /**
     * Throws an exception indicating that the order has already been assigned.
     * @param order The order to be assigned
     * @throws Exception if the order has already been assigned
     */
    @Override
    public void assign(Order order) throws Exception {
        throw new Exception("Order already assigned");
    }
}
