package fr.unice.polytech.app.State;
import fr.unice.polytech.app.Orders.Order;

/**
 * Represents the Paid state of an order.
 * In this state, the order has been paid for but not yet ready for delivery.
 */
public class PaidIState implements IState {
    /**
     * Throws an exception indicating that the order has already been placed.
     * @param order The order to place.
     * @throws Exception If the order has already been placed.
     */
    @Override
    public void placeOrder(Order order) throws Exception {
        throw new Exception("Order already placed");
    }

    /**
     * Throws an exception indicating that the order has already been paid.
     * @param order The order to pay.
     * @throws Exception If the order has already been paid.
     */
    @Override
    public void pay(Order order) throws Exception {
        throw new Exception("Order already paid");
    }

    /**
     * Cancels the order and sets its status to Cancelled.
     * @param order The order to cancel.
     * @throws Exception If an error occurs while cancelling the order.
     */
    @Override
    public void cancelOrder(Order order) throws Exception {
        order.setStatus(new CancelledIState());
    }

    /**
     * Accepts the order and sets its status to Accepted.
     * @param order The order to accept.
     * @throws Exception If an error occurs while accepting the order.
     */
    @Override
    public void acceptOrder(Order order) throws Exception {
        order.setStatus(new AcceptedIState());
    }

    /**
     * Rejects the order and sets its status to Rejected.
     * @param order The order to reject.
     * @throws Exception If an error occurs while rejecting the order.
     */
    @Override
    public void rejectOrder(Order order) throws Exception {
        order.setStatus(new RejectedIState());
    }

    /**
     * Throws an exception indicating that the order can't be marked as ready.
     * @param order The order to mark as ready.
     * @throws Exception If the order can't be marked as ready.
     */
    @Override
    public void readyOrder(Order order) throws Exception {
        throw new Exception("Order can't be ready");
    }

    /**
     * Throws an exception indicating that the order can't be validated.
     * @param order The order to validate.
     * @throws Exception If the order can't be validated.
     */
    @Override
    public void validate(Order order) throws Exception {
        throw new Exception("Order can't be validated");
    }

    /**
     * Throws an exception indicating that the order can't be delivered.
     * @param order The order to deliver.
     * @throws Exception If the order can't be delivered.
     */
    @Override
    public void delivery(Order order) throws Exception {
        throw new Exception("Order can't be delivered");
    }

    /**
     * Throws an exception indicating that the order can't be assigned.
     * @param order The order to assign.
     * @throws Exception If the order can't be assigned.
     */
    @Override
    public void assign(Order order) throws Exception {
        throw new Exception("Order can't be assigned");
    }
}
