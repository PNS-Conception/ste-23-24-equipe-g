package fr.unice.polytech.app;

/**
 * Classe représentant une commande passée par un client.
 */
public class Order {

    /** Le restaurant associé à la commande. */
    private String restaurant;

    /** Le client ayant passé la commande. */
    private Client client;

    /** L'article commandé. */
    private String item;

    /** La quantité de l'article commandé. */
    private int quantity;

    /** Le panier associé à la commande (si applicable). */
    private Cart cart;

    /**
     * Constructeur par défaut pour une commande.
     */
    public Order() {
    }

    /**
     * Crée une commande avec un client, un article et une quantité donnés.
     *
     * @param client le client ayant passé la commande.
     * @param item l'article commandé.
     * @param quantity la quantité de l'article commandé.
     */
    public Order(Client client, String item, int quantity) {
        this.client = client;
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Crée une commande à partir d'un panier.
     *
     * @param client le client ayant passé la commande.
     * @param cart le panier associé à la commande.
     */
    public Order(Client client, Cart cart) {
        this.client = client;
        this.cart = cart;
    }

    /**
     * @return le restaurant associé à la commande.
     */
    public String getRestaurant() {
        return restaurant;
    }

    /**
     * @return le client ayant passé la commande.
     */
    public Client getClient() {
        return client;
    }

    /**
     * @return l'article commandé.
     */
    public String getItem() {
        return item;
    }

    /**
     * @return la quantité de l'article commandé.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Définit le restaurant associé à la commande.
     *
     * @param restaurant le nom du restaurant.
     */
    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * Définit le client ayant passé la commande.
     *
     * @param client le client.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Définit l'article commandé.
     *
     * @param item l'article.
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * Définit la quantité de l'article commandé.
     *
     * @param quantity la quantité.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Fournit une représentation sous forme de chaîne de caractères de la commande.
     *
     * @return la représentation de la commande.
     */
    @Override
    public String toString() {
        return "Order{" +
                "client=" + client +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
