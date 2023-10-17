package fr.unice.polytech.app;

/**
 * Classe représentant un client.
 */
public class Client {

    /** Le nom du client. */
    private final String name;

    /**
     * Crée un nouveau client avec un nom donné.
     *
     * @param name le nom du client.
     */
    public Client(String name) {
        this.name = name;
    }

    /**
     * Récupère le nom du client.
     *
     * @return le nom du client.
     */
    public String getName() {
        return name;
    }
}
