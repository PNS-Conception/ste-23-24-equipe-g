package fr.unice.polytech.app;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupOrder implements Order{

    private List<SingleOrder> subSingleOrders;
    private UUID groupID;
    private CampusUser owner;
    private List<CampusUser> members;
    private OrderStatus status;

    private String routeDetails;
    private LocalTime pickupTime;
    private Restaurant restaurant;
    private String deliveryLocation;
    private String deliveryAddress;



    GroupOrder(CampusUser owner) {
        super();
        this.groupID = UUID.randomUUID();
        this.subSingleOrders =new ArrayList<>();
        this.members=new ArrayList<>();
        addMember(owner);
        this.owner = owner;

    }

    public List<SingleOrder> getSubOrders() {
        return subSingleOrders;
    }

    public void setSubOrders(List<SingleOrder> subSingleOrders) {
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

    public void setStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Boolean deleteGroup(CampusUser owner) {
        if (owner.equals(this.owner) && ((status ==OrderStatus.PLACED)||(status ==null))) {
            members.clear();
            subSingleOrders.clear();
            return true;
        }
        return false;
    }

    public void cancelOrder(SingleOrder singleOrder, CampusUser user, int minutesPassed) {
        user.cancelOrder(singleOrder,minutesPassed);
        subSingleOrders.remove(singleOrder);

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

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        for (SingleOrder singleOrder : subSingleOrders) {
            restaurants.add(singleOrder.getRestaurant());
        }
        return restaurants;
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
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public String getDeliveryLocation() {
        return deliveryLocation;
    }
}
