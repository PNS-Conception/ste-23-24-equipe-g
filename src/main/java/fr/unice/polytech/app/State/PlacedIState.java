package fr.unice.polytech.app.State;

import fr.unice.polytech.app.Orders.Order;

/**
 * Represents the "Placed" state of an order in the application.
 * In this state, the order has been placed but not yet paid for.
 * The order cannot be canceled, accepted, rejected, ready, validated, delivered, or assigned.
 * The order can only be paid for.
 */
public class PlacedIState implements IState {

    /**
     * Constructs a new PlacedIState object.
     */
    public PlacedIState() {
    }

    /**
     * Throws an exception indicating that the order has already been placed.
     * @param order The order to be placed.
     * @throws Exception An exception indicating that the order has already been placed.
     */
    @Override
    public void placeOrder(Order order) throws Exception {
        throw new Exception("Order already placed");
    }

    /**
     * Changes the status of the order to "Paid".
     * @param order The order to be paid.
     * @throws Exception An exception indicating that the order cannot be paid.
     */
    @Override
    public void pay(Order order) throws Exception{
        order.setStatus(new PaidIState());
    }

    /**
     * Throws an exception indicating that the order cannot be canceled.
     * @param order The order to be canceled.
     * @throws Exception An exception indicating that the order cannot be canceled.
     */
    @Override
    public void cancelOrder(Order order) throws Exception{
        throw new Exception("Order can't be canceled");
    }

    /**
     * Throws an exception indicating that the order cannot be accepted.
     * @param order The order to be accepted.
     * @throws Exception An exception indicating that the order cannot be accepted.
     */
    @Override
    public void acceptOrder(Order order) throws Exception{
        throw new Exception("Order can't be accepted");
    }

    /**
     * Throws an exception indicating that the order cannot be rejected.
     * @param order The order to be rejected.
     * @throws Exception An exception indicating that the order cannot be rejected.
     */
    @Override
    public void rejectOrder(Order order) throws Exception{
        throw new Exception("Order can't be rejected");
    }

    /**
     * Throws an exception indicating that the order cannot be marked as ready.
     * @param order The order to be marked as ready.
     * @throws Exception An exception indicating that the order cannot be marked as ready.
     */
    @Override
    public void readyOrder(Order order) throws Exception{
        throw new Exception("Order can't be ready");
    }

    /**
     * Throws an exception indicating that the order cannot be validated.
     * @param order The order to be validated.
     * @throws Exception An exception indicating that the order cannot be validated.
     */
    @Override
    public void validate(Order order) throws Exception{
        throw new Exception("Order can't be validated");
    }

    /**
     * Throws an exception indicating that the order cannot be delivered.
     * @param order The order to be delivered.
     * @throws Exception An exception indicating that the order cannot be delivered.
     */
    @Override
    public void delivery(Order order)throws Exception {
        throw new Exception("Order can't be delivered");
    }

    /**
     * Throws an exception indicating that the order cannot be assigned.
     * @param order The order to be assigned.
     * @throws Exception An exception indicating that the order cannot be assigned.
     */
    @Override
    public void assign(Order order) throws Exception {
        throw new Exception("Order can't be assigned");
    }
}
