package fr.unice.polytech.app.User;

import fr.unice.polytech.app.Orders.SingleOrder;


// cette interface est faite pour integrer le paton proxy
public interface PaiementProxy {
    void refund(SingleOrder singleOrder);
}
