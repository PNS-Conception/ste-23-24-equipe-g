package fr.unice.polytech.app;

public class StandardRestaurantFactory implements RestaurantFactory {
    @Override
    public Restaurant createRestaurant(String name, Menu menu, RestaurantType type) {
        switch (type) {
            case FAST_FOOD:
                return new FastFoodRestaurant(name, menu);
            case GASTRONOMIC:
                return new GastronomicRestaurant(name, menu);
            default:
                return new Restaurant(name, menu);
        }
    }
}
