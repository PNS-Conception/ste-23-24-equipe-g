package fr.unice.polytech.app.Orders;

import fr.unice.polytech.app.State.PaidIState;
import fr.unice.polytech.app.User.CampusUser;
import fr.unice.polytech.app.Restaurant.Restaurant;
import fr.unice.polytech.app.State.PlacedIState;
import fr.unice.polytech.app.State.IState;
import fr.unice.polytech.app.State.ReadyIState;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class GroupOrder implements Order,DecoratorOrder {

    private List<Order> subSingleOrders;
    private UUID groupID;
    private List<CampusUser> members;
    private String routeDetails;
    private LocalTime pickupTime;
    private List<Restaurant> restaurants;
    private String deliveryLocation;
    private String deliveryAddress;
    private DecoratorOrder order;


    public GroupOrder(CampusUser owner) throws Exception {
        this.groupID = UUID.randomUUID();
        this.subSingleOrders =new ArrayList<>();
        this.members=new ArrayList<>();
        addMember(owner);
        order = new SingleOrder();
        setOwner(owner);
        placeOrder();

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
        return order.getOwner();
    }

    public void setOwner(CampusUser owner) {
        order.setOwner(owner);
    }

    public void addMember(CampusUser user,CampusUser owner) {
        if (owner.equals(order.getOwner())){
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
        order.setStatus(orderStatus);
    }

    public IState getStatus() {
        return order.getStatus();
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
        int count = 0;
        for (Order singleOrder : subSingleOrders) {
            if(singleOrder.getStatus() instanceof PaidIState){
                count++;
            }
        }
        if (count == subSingleOrders.size()) {
            order.pay();
        }
    }

    @Override
    public void accept() throws Exception {
        order.accept();
    }

    @Override
    public void reject() throws Exception {
        order.reject();
    }

    @Override
    public void ready() throws Exception {
        int count = 0;
        for (Order singleOrder : subSingleOrders) {
            if (singleOrder.getStatus() instanceof ReadyIState) {
                count++;
            }
        }
        if (count == subSingleOrders.size()) {
            order.ready();
        }
    }

    @Override
    public void assign() throws Exception {
        order.assign();
    }

    @Override
    public void deliver() throws Exception {
        order.deliver();
    }

    @Override
    public void cancel() throws Exception {
        order.cancel();
    }

    @Override
    public void pickUp() throws Exception {
        order.pickUp();
    }

    @Override
    public void placeOrder() throws Exception {
        order.placeOrder();
    }

    public Boolean deleteGroup(CampusUser owner) {
        if (owner.equals(getOwner()) && ((getStatus() instanceof PlacedIState)||(getStatus() ==null))) {
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
        if (user==singleOrder.getClient() && singleOrder.getStatus() instanceof PlacedIState) {
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
        if (alice.equals(getOwner())) {
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
