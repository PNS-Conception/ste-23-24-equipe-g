package fr.unice.polytech.app;

public enum OrderStatus {
    Placed,
    // Cart is empty
    Paid,    // Items have been added to the cart

    Accepted,    // Cart has been checked out and is awaiting payment/validation

    Ready,      // Cart has been validated and an order has been created

    PickedUp,       // Cart or order has been cancelled by the user

    Delivered      // Cart or order has been cancelled by the user
}

