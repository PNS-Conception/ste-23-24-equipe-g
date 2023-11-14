package fr.unice.polytech.app;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupOrder extends Order {

    private List<Order> subOrders;
    private UUID groupID;
    private CampusUser owner;
    private List<CampusUser> members;
    private OrderStatus status;

    private String deliveryAddress;



    GroupOrder(CampusUser owner) {
        super();
        this.groupID = UUID.randomUUID();
        this.subOrders=new ArrayList<>();
        this.members=new ArrayList<>();
        addMember(owner);
        this.owner = owner;

    }

    public List<Order> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<Order> subOrders) {
        this.subOrders = subOrders;
    }

    public UUID getGroupID() {
        return groupID;
    }

    public void setGroupID(UUID groupID) {
        this.groupID = groupID;
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

    public void addOrder(Order order) {
        subOrders.add(order);
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
            subOrders.clear();
            return true;
        }
        return false;
    }

    public void cancelOrder(Order order,CampusUser user,int minutesPassed) {
        user.cancelOrder(order,minutesPassed);
        subOrders.remove(order);

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
}
