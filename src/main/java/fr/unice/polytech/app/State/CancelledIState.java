package fr.unice.polytech.app.State;

import fr.unice.polytech.app.Orders.Order;

public class CancelledIState implements IState {

    public void placeOrder(Order order) throws Exception {
        throw new Exception("Order already placed");
    }

    public void pay(Order order)throws Exception {
        throw new Exception("Order already paid");
    }

    public void cancelOrder(Order order)throws Exception {
        throw new Exception("Order already cancelled");
    }

    public void acceptOrder(Order order)throws Exception {
        throw new Exception("Order already accepted");
    }

    public void rejectOrder(Order order)throws Exception {
        throw new Exception("Order already rejected");
    }

    public void readyOrder(Order order)throws Exception {
        throw new Exception("Order already ready");
    }

    public void validate(Order order)throws Exception {
        throw new Exception("Order already validated");
    }

    public void delivery(Order order)throws Exception {
        throw new Exception("Order already delivered");
    }

    @Override
    public void assign(Order order) throws Exception {
        throw new Exception("Order can't be assigned");
    }
}
