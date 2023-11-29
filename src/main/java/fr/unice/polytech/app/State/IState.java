package fr.unice.polytech.app.State;

import fr.unice.polytech.app.Order;

public interface IState {
    public void placeOrder(Order order) throws Exception;
    public void pay(Order order)throws Exception;
    public void cancelOrder(Order order)throws Exception;
    public void acceptOrder(Order order)throws Exception;
    public void rejectOrder(Order order)throws Exception;
    public void readyOrder(Order order)throws Exception;
    public void validate(Order order)throws Exception;
    public void delivery(Order order)throws Exception;

    public void assign(Order order)throws Exception;
}
