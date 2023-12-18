/**
 * Represents the assigned state of an order.
 * In this state, the order has been assigned to a delivery person.
 * The order cannot be placed, paid, cancelled, accepted, rejected, or delivered.
 * However, it can be validated and marked as ready for delivery.
 */
package fr.unice.polytech.app.State;

import fr.unice.polytech.app.Orders.Order;


public class AssignedIState implements IState {

    @Override
    public void placeOrder(Order order) throws Exception {
        throw new Exception("Order already placed");
    }

    @Override
    public void pay(Order order) throws Exception {
        throw new Exception("Order already paid");
    }

    @Override
    public void cancelOrder(Order order) throws Exception {
        throw new Exception("Order already cancelled");
    }

    @Override
    public void acceptOrder(Order order) throws Exception {
        throw new Exception("Order already accepted");
    }

    @Override
    public void rejectOrder(Order order) throws Exception {
        throw new Exception("Order already rejected");
    }

    @Override
    public void readyOrder(Order order) throws Exception {
        throw new Exception("Order already ready");
    }

    @Override
    public void validate(Order order) throws Exception {
        order.setStatus(new ValidatedIState());
    }

    @Override
    public void delivery(Order order) throws Exception {
        throw new Exception("Order can't be delivered");
    }

    @Override
    public void assign(Order order) throws Exception {
        throw new Exception("Order already assigned");
    }
}
