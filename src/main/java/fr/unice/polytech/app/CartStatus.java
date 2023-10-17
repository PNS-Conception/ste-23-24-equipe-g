package fr.unice.polytech.app;

/**
 * Énumération représentant les différents états possibles d'un panier.
 */
public enum CartStatus {

    /** Le panier est vide. */
    EMPTY,

    /** Des articles ont été ajoutés au panier. */
    ITEMS_ADDED,

    /** Le panier a été validé et attend le paiement ou la validation. */
    CHECKED_OUT,

    /** Le panier a été validé et une commande a été créée. */
    VALIDATED,

    /** Le panier ou la commande a été annulé par l'utilisateur. */
    CANCELLED
}
