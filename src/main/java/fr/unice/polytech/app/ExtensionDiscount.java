package fr.unice.polytech.app;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ExtensionDiscount {
    CampusUser client;
    int numberOfOrders;
    private boolean isDiscountvalid;
    private LocalDate date;

    public ExtensionDiscount(CampusUser client, int numberOfOrders, int numberOfDaysForDiscount) {
        this.client = client;
        this.numberOfOrders = numberOfOrders;
        this.date = LocalDate.now().plusDays(numberOfDaysForDiscount);
    }

    public void setClient(CampusUser client) {
        this.client = client;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public CampusUser getClient() {
        return client;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDatePlusNDays(LocalDate NDays) {
        this.date = NDays;
    }


    public void addNDays(int NDays) {
        date = date.plusDays(NDays);
    }
    public LocalDate getDatePlusNDays() {
        return date;
    }

    public void setIsDiscountValid(boolean isDiscountvalid) {
        this.isDiscountvalid= isDiscountvalid;
    }

    public void setIsDiscountValid() {
        isDiscountvalid= LocalDate.now().isBefore(date);
    }

    public boolean getIsDiscountValid() {
        return isDiscountvalid;
    }
    public void extendDiscount() {
        //date = date.plusDays(15);
        date=LocalDate.now().plusDays(15);
    }

    public boolean isValid() {
        return ChronoUnit.DAYS.between(LocalDate.now(), date) > 0;
    }

}
