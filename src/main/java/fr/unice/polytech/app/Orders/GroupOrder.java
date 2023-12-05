package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.Users.CampusUser;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.State.PlacedIState;
import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.State.ReadyIState;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class GroupOrder implements Order {

    private List<Order> subSingleOrders;
    private UUID groupID;
    private CampusUser owner;
    private List<CampusUser> members;
    private IState status;

    private String routeDetails;
    private LocalTime pickupTime;
    private List<Restaurant> restaurants;
    private String deliveryLocation;
    private String deliveryAddress;

    public GroupOrder(CampusUser owner) throws Exception {
        super();
        this.groupID = UUID.randomUUID();
        this.subSingleOrders =new ArrayList<>();
        this.members=new ArrayList<>();
        addMember(owner);
        placeOrder();
        this.owner = owner;

    }

    public List<Order> getSubOrders() {
        return subSingleOrders;
    }

    public void setSubOrders(List<Order> subSingleOrders) {
        this.subSingleOrders = subSingleOrders;
    }

    public UUID getGroupID() {
        return groupID;
    }


    public CampusUser getOwner() {
        return owner;
    }

    public void setOwner(CampusUser owner) {
        this.owner = owner;
    }

    public void addMember(CampusUser user,CampusUser owner) {
        if (owner.equals(this.owner)) {
            members.add(user);
        }
    }

    public void addMember(CampusUser user) {
        members.add(user);
    }

    public void addOrder(SingleOrder singleOrder) {
        subSingleOrders.add(singleOrder);
    }

    public void setStatus(IState orderStatus) {
        this.status = orderStatus;
    }

    public IState getStatus() {
        return status;
    }

    @Override
    public LocalTime getAcceptedTime() {
        return getAcceptedTime();
    }

    @Override
    public LocalTime getDeliveryTime() {
        return null;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public UUID getId() {
        return this.groupID;
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {

    }

    @Override
    public void pay() throws Exception {
        status.pay(this);
        for (Order singleOrder : subSingleOrders) {
            singleOrder.pay();
        }
    }

    @Override
    public void accept() throws Exception {
        status.acceptOrder(this);
    }

    @Override
    public void reject() throws Exception {
        status.rejectOrder(this);
    }

    @Override
    public void ready() throws Exception {
        int count = 0;
        for (Order singleOrder : subSingleOrders) {
            if (singleOrder.getStatus() instanceof ReadyIState) {
                System.out.println(singleOrder.getStatus());
                count++;
            }
        }
        if (count == subSingleOrders.size()) {
            status.readyOrder(this);
        }
    }

    @Override
    public void assign() throws Exception {
        status.assign(this);
    }

    @Override
    public void deliver() throws Exception {
        status.delivery(this);
    }

    @Override
    public void cancel() throws Exception {
        status.cancelOrder(this);
    }

    @Override
    public void pickUp() throws Exception {
        status.validate(this);
    }

    @Override
    public void placeOrder() throws Exception {
        status= new PlacedIState();
    }

    public Boolean deleteGroup(CampusUser owner) {
        System.out.println(status);
        System.out.println(status instanceof PlacedIState);
        if (owner.equals(this.owner) && ((status instanceof PlacedIState)||(status ==null))) {
            members.clear();
            subSingleOrders.clear();
            return true;
        }
        return false;
    }

    public void cancelOrder(SingleOrder singleOrder, CampusUser user, int minutesPassed) throws Exception {
        user.cancelOrder(singleOrder,minutesPassed);
        subSingleOrders.remove(singleOrder);
    }

    public void cancelOrder(SingleOrder singleOrder, CampusUser user) throws Exception {
        if (user==singleOrder.user && singleOrder.getStatus() instanceof PlacedIState) {
            subSingleOrders.remove(singleOrder);
        }
    }

    public boolean ismember(CampusUser user) {
        return members.contains(user);
    }

    public void quit(CampusUser bob) {
        members.remove(bob);
    }

    public List<CampusUser> getMembers() {
        return members;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void exclude(CampusUser alice, CampusUser bob) {
        if (alice.equals(this.owner)) {
            members.remove(bob);
        }
    }

    public Restaurant getRestaurant() {
        return restaurants.get(0);
    }

    @Override
    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryAddress = deliveryLocation;
    }

    @Override
    public String getRouteDetails() {
        return routeDetails;
    }

    @Override
    public LocalTime getPickupTime() {
        return pickupTime;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        for (Order singleOrder : subSingleOrders) {
            if (singleOrder.getRestaurant() != null) {
                 restaurants.add( singleOrder.getRestaurant());
            }
        }
        return restaurants;
    }

    @Override
    public String getDeliveryLocation() {
        return deliveryLocation;
    }
}
