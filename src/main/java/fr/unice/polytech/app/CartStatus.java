package fr.unice.polytech.app;

public enum CartStatus {
    EMPTY,          // Cart is empty
    ITEMS_ADDED,    // Items have been added to the cart
    CHECKED_OUT,    // Cart has been checked out and is awaiting payment/validation
    VALIDATED,      // Cart has been validated and an order has been created
    CANCELLED       // Cart or order has been cancelled by the user
}

