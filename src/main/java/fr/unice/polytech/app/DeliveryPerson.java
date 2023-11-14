package fr.unice.polytech.app;

public class DeliveryPerson extends CampusUser{

    public DeliveryPerson( String name, String password, String email) {
        super( name, password, email);
        setType(UserType.DELIVERY_PERSON);
    }

    public void validateOrder(GroupOrder groupOrder) {
        groupOrder.setStatus(OrderStatus.PICKED_UP);
    }

    public void deliverOrder(GroupOrder groupOrder) {
        groupOrder.setStatus(OrderStatus.DELIVERED);
    }
}
