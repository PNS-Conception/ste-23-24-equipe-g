package fr.unice.polytech.app;

public enum RestaurantType {
    FAST_FOOD,
    GASTRONOMIC,
    CAFE,
    BUFFET,
    CASUAL_DINING,
    FINE_DINING,
    PIZZERIA,
    SEAFOOD,
    VEGETARIAN,
    OTHER;

    @Override
    public String toString() {
        // Remplacer les underscores par des espaces et mettre en majuscule la première lettre
        String name = name().toLowerCase().replace('_', ' ');
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
