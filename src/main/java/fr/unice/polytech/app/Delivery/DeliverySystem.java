package fr.unice.polytech.app.Delivery;

import fr.unice.polytech.app.System.Admin;
import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.Orders.SingleOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The DeliverySystem class represents a system for managing delivery operations.
 * It keeps track of available delivery people and assigns orders to them.
 */
public class DeliverySystem {
    
    private List<DeliveryPerson> availableDeliveryPeople;
    private List<DeliveryPerson> deliveryPeople;


    /**
     * Constructs a new DeliverySystem object.
     * Initializes the list of delivery people with the ones obtained from the Admin class.
     * Initializes the list of available delivery people as an empty list.
     */
    public DeliverySystem() {
        this.deliveryPeople = new ArrayList<>(Admin.getDeliveryPersons());
        this.availableDeliveryPeople = new ArrayList<>();
    }

    /**
     * Adds a delivery person to the list of delivery people.
     * @param deliveryPerson The delivery person to be added.
     */
    public void addDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPeople.add(deliveryPerson);
    }


    /**
     * Assigns an order to an available delivery person.
     * @param singleOrder The order to be assigned.
     * @return An Optional containing the assigned delivery person if successful, or an empty Optional if no delivery person is available.
     * @throws Exception if an error occurs during the assignment process.
     */
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

    /**
     * Retrieves the list of available delivery people.
     */
    public void getAvailableDeliveryPeople() {
        for (DeliveryPerson deliveryPerson : this.deliveryPeople) {
            if (deliveryPerson.isAvailable()) {
                availableDeliveryPeople.add(deliveryPerson);
            }
        }
    }

    /**
     * Notifies the user with the delivery details.
     * @param user The user to be notified.
     * @param deliveryPerson The delivery person whose details should be sent.
     */
    public void notifyUserWithDeliveryDetails(CampusUser user, DeliveryPerson deliveryPerson) {
        user.setNotifiedDeliveryPersonId(deliveryPerson.getId().toString());
        user.setNotifiedDeliveryPersonPhoneNumber(deliveryPerson.getPhoneNumber());
    }



    /**
     * Rates a user by adding a rate to their profile.
     *
     * @param user the user to rate
     * @param rate the rate to add (between 0 and 5)
     * @throws Exception if the rate is not between 0 and 5
     */
    public void rateUser(CampusUser user, int rate) throws Exception {
        if (rate >= 0 && rate <= 5) {
            user.addRate(rate);
        } else {
            throw new Exception("Rate must be between 0 and 5");
        }
    }

}

