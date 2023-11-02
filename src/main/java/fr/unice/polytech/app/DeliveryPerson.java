package fr.unice.polytech.app;

import java.util.UUID;

public class DeliveryPerson extends CampusUser{

    public DeliveryPerson( String name, String password, String address, String email) {
        super( name, password, address, email);
        setType(UserType.DeliveryPerson);
    }

    public void validateOrder(GroupOrder groupOrder) {
        groupOrder.setStatus(OrderStatus.PickedUp);
    }

    public void deliverOrder(GroupOrder groupOrder) {
        groupOrder.setStatus(OrderStatus.Delivered);
    }
}
