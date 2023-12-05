package fr.unice.polytech.app.State;

import fr.unice.polytech.app.Orders.Order;

/**
 * Represents the Accepted state of an order in the system.
 * In this state, the order has been accepted by the system but not yet ready for delivery.
 */
public class AcceptedIState implements IState {

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
     * Cancels the order and sets its status to Cancelled.
     * @param order The order to be cancelled.
     * @throws Exception If an error occurs while cancelling the order.
     */
    @Override
    public void cancelOrder(Order order) throws Exception {
        order.setStatus(new CancelledIState());
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
     * Throws an exception indicating that the order cannot be rejected.
     * @param order The order to be rejected.
     * @throws Exception If the order cannot be rejected.
     */
    @Override
    public void rejectOrder(Order order) throws Exception {
        throw new Exception("Order can't be rejected");
    }

    /**
     * Sets the status of the order to Ready.
     * @param order The order to be marked as ready.
     * @throws Exception If an error occurs while marking the order as ready.
     */
    @Override
    public void readyOrder(Order order) throws Exception {
        order.setStatus(new ReadyIState());
    }

    /**
     * Throws an exception indicating that the order cannot be validated.
     * @param order The order to be validated.
     * @throws Exception If the order cannot be validated.
     */
    @Override
    public void validate(Order order) throws Exception {
        throw new Exception("Order can't be validated");
    }

    /**
     * Throws an exception indicating that the order cannot be delivered.
     * @param order The order to be delivered.
     * @throws Exception If the order cannot be delivered.
     */
    @Override
    public void delivery(Order order) throws Exception {
        throw new Exception("Order can't be delivered");
    }

    /**
     * Throws an exception indicating that the order cannot be assigned.
     * @param order The order to be assigned.
     * @throws Exception If the order cannot be assigned.
     */
    @Override
    public void assign(Order order) throws Exception {
        throw new Exception("Order can't be assigned");
    }
}
