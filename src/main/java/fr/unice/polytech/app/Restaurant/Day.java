package fr.unice.polytech.app.Restaurant;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public enum Day {
    Monday(DayOfWeek.MONDAY),
    Tuesday(DayOfWeek.TUESDAY),
    Wednesday(DayOfWeek.WEDNESDAY),
    Thursday(DayOfWeek.THURSDAY),
    Friday(DayOfWeek.FRIDAY),
    Saturday(DayOfWeek.SATURDAY),
    Sunday(DayOfWeek.SUNDAY);

    private final DayOfWeek dayOfWeek;

    Day(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek toDayOfWeek() {
        return dayOfWeek;
    }

    public static List<Day> getAllDayOfWeeks() {
        List<Day> daysOfWeek = new ArrayList<>();
        for (Day day : Day.values()) {
            daysOfWeek.add(day);
        }
        return daysOfWeek;
    }

    public static class Shift {

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
}
