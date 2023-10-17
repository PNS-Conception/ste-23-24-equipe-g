package fr.unice.polytech.app;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant le panier d'un client.
 */
public class Cart {

    /** Le client associé au panier. */
    private Client client;

    /** Les articles dans le panier, mappés par leur nom à leur quantité. */
    private Map<String, Integer> items;

    /** Le restaurant associé au panier. */
    private String restaurant;

    /** L'état actuel du panier. */
    private CartStatus cartStatus;

    /**
     * Crée un nouveau panier pour un client donné.
     *
     * @param client le client associé au panier.
     */
    public Cart(Client client) {
        this.client = client;
        this.items = new HashMap<>();
        this.cartStatus = CartStatus.EMPTY;
    }

    /**
     * Vide le panier.
     */
    public void emptyCart() {
        items.clear();
    }

    /**
     * Ajoute un article au panier.
     *
     * @param item le nom de l'article.
     * @param quantity la quantité de l'article.
     */
    public void addItem(String item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    /**
     * Retire une certaine quantité d'un article du panier.
     *
     * @param item le nom de l'article.
     * @param quantity la quantité à retirer.
     */
    public void removeItem(String item, int quantity) {
        items.put(item, Math.max(0, items.getOrDefault(item, 0) - quantity));
    }

    /**
     * Vérifie si un article est présent dans le panier.
     *
     * @param item le nom de l'article.
     * @return true si l'article est présent, false sinon.
     */
    public Boolean containsThatItem(String item) {
        return this.items.containsKey(item);
    }

    /**
     * Récupère la quantité d'un article dans le panier.
     *
     * @param item le nom de l'article.
     * @return la quantité de l'article dans le panier, ou 0 si l'article n'est pas présent.
     */
    public int getItemQuantity(String item) {
        return items.getOrDefault(item, 0);
    }

    /**
     * Valide le panier et crée une commande.
     *
     * @param item le nom de l'article.
     * @param quantity la quantité de l'article.
     * @return une nouvelle commande.
     */
    public Order validateCart(String item, int quantity) {
        return new Order(client, item, quantity);
    }

    /**
     * @return le nom du restaurant associé au panier.
     */
    public String getRestaurant() {
        return restaurant;
    }

    /**
     * Définit le restaurant associé au panier.
     *
     * @param restaurant le nom du restaurant.
     */
    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * Vérifie si le panier est vide.
     *
     * @return true si le panier est vide, false sinon.
     */
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    /**
     * @return l'état actuel du panier.
     */
    public CartStatus getCartStatus() {
        return cartStatus;
    }

    /**
     * Définit l'état du panier.
     *
     * @param cartStatus le nouvel état du panier.
     */
    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
    }
}
