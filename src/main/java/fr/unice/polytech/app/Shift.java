package fr.unice.polytech.app;

import java.time.LocalTime;

public class Shift {

    private LocalTime openingTime;
    private LocalTime closingTime;

    private Day day;


    public Shift(LocalTime openingTime, LocalTime closingTime, Day day) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.day = day;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public Day getDay() {
        return day;
    }



    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
}
