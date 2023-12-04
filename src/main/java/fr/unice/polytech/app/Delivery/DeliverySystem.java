
package fr.unice.polytech.app.Delivery;

import fr.unice.polytech.app.System.Admin;
import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Orders.SingleOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliverySystem {

    private List<DeliveryPerson> availableDeliveryPeople;
    private List<DeliveryPerson> deliveryPeople;

    public DeliverySystem() {
        this.deliveryPeople = new ArrayList<>(Admin.getDeliveryPersons());
        this.availableDeliveryPeople = new ArrayList<>();
    }

    public void addDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPeople.add(deliveryPerson);
    }

    public boolean removeDeliveryPerson(DeliveryPerson deliveryPerson) {
        return this.deliveryPeople.remove(deliveryPerson);
    }

    public Optional<DeliveryPerson> assignOrderToDeliveryPerson(SingleOrder singleOrder) throws Exception {
        getAvailableDeliveryPeople();
        for (DeliveryPerson deliveryPerson : availableDeliveryPeople) {
                boolean assigned = deliveryPerson.assignOrder(singleOrder);
                if (assigned) {
                    return Optional.of(deliveryPerson);
                }
            }
        return Optional.empty(); // Aucun livreur disponible
    }

    public void getAvailableDeliveryPeople() {
        for (DeliveryPerson deliveryPerson : this.deliveryPeople) {
            if (deliveryPerson.isAvailable()) {
                availableDeliveryPeople.add(deliveryPerson);
            }
        }
    }
//    /**
//     * Notifie l'utilisateur avec les détails de la livraison.
//     * @param user L'utilisateur à notifier.
//     * @param deliveryPerson Le livreur dont les détails doivent être envoyés.
//     */
//    public void notifyUserWithDeliveryDetails(CampusUser user, DeliveryPerson deliveryPerson) {
//        if(user != null && deliveryPerson != null) {
//
//            user.receiveDeliveryDetails(deliveryPerson.getId().toString(), deliveryPerson.getPhoneNumber());
//        }
//    }
    public List<DeliveryPerson> getDeliveryPeople() {
        return new ArrayList<>(this.deliveryPeople);
    }
    public void notifyUserWithDeliveryDetails(CampusUser user, DeliveryPerson deliveryPerson) {
        System.out.println("Delivery Person ID: " + deliveryPerson.getId());
        user.setNotifiedDeliveryPersonId(deliveryPerson.getId().toString());
        user.setNotifiedDeliveryPersonPhoneNumber(deliveryPerson.getPhoneNumber());
    }
}

