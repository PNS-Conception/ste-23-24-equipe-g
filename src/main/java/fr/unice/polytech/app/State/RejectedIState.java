package fr.unice.polytech.app.State;

import fr.unice.polytech.app.Orders.Order;

/**
 * This class represents the Rejected state of an order in the application.
 * In this state, the order has been rejected and cannot undergo any further changes.
 */
public class RejectedIState implements IState {
    /**
     * Throws an exception indicating that the order has already been placed.
     * @param order The order to be placed.
     * @throws Exception If the order has already been placed.
     */
    @Override
    public void placeOrder(Order order) throws Exception {
        throw new Exception("Order already placed");
    }

    /**
     * Throws an exception indicating that the order has already been paid.
     * @param order The order to be paid.
     * @throws Exception If the order has already been paid.
     */
    @Override
    public void pay(Order order) throws Exception {
        throw new Exception("Order already paid");
    }

    /**
     * Throws an exception indicating that the order has already been cancelled.
     * @param order The order to be cancelled.
     * @throws Exception If the order has already been cancelled.
     */
    @Override
    public void cancelOrder(Order order) throws Exception {
        throw new Exception("Order already cancelled");
    }

    /**
     * Throws an exception indicating that the order has already been accepted.
     * @param order The order to be accepted.
     * @throws Exception If the order has already been accepted.
     */
    @Override
    public void acceptOrder(Order order) throws Exception {
        throw new Exception("Order already accepted");
    }

    /**
     * Throws an exception indicating that the order has already been rejected.
     * @param order The order to be rejected.
     * @throws Exception If the order has already been rejected.
     */
    @Override
    public void rejectOrder(Order order) throws Exception {
        throw new Exception("Order already rejected");
    }

    /**
     * Throws an exception indicating that the order cannot be ready in the rejected state.
     * @param order The order to be marked as ready.
     * @throws Exception If the order cannot be ready.
     */
    @Override
    public void readyOrder(Order order) throws Exception {
        throw new Exception("Order can't be ready");
    }

    /**
     * Throws an exception indicating that the order cannot be validated in the rejected state.
     * @param order The order to be validated.
     * @throws Exception If the order cannot be validated.
     */
    @Override
    public void validate(Order order) throws Exception {
        throw new Exception("Order can't be validated");
    }

    /**
     * Throws an exception indicating that the order cannot be delivered in the rejected state.
     * @param order The order to be delivered.
     * @throws Exception If the order cannot be delivered.
     */
    @Override
    public void delivery(Order order) throws Exception {
        throw new Exception("Order can't be delivered");
    }

    /**
     * Does nothing as the order is already in the rejected state.
     * @param order The order to be assigned.
     */
    @Override
    public void assign(Order order) throws Exception {

    }
}
