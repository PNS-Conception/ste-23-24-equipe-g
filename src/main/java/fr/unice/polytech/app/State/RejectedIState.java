package fr.unice.polytech.app.State;

import fr.unice.polytech.app.Orders.Order;

public class RejectedIState implements IState {
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
        throw new Exception("Order can't be ready");
    }

    @Override
    public void validate(Order order) throws Exception {
        throw new Exception("Order can't be validated");
    }

    @Override
    public void delivery(Order order) throws Exception {
        throw new Exception("Order can't be delivered");
    }

    @Override
    public void assign(Order order) throws Exception {

    }
}
