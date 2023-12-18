package fr.unice.polytech.app.Restaurant;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the days of the week.
 */
public enum Day {
    Monday(DayOfWeek.MONDAY),
    Tuesday(DayOfWeek.TUESDAY),
    Wednesday(DayOfWeek.WEDNESDAY),
    Thursday(DayOfWeek.THURSDAY),
    Friday(DayOfWeek.FRIDAY),
    Saturday(DayOfWeek.SATURDAY),
    Sunday(DayOfWeek.SUNDAY);

    private final DayOfWeek dayOfWeek;

    /**
     * Constructs a Day object with the corresponding DayOfWeek value.
     * @param dayOfWeek the DayOfWeek value
     */
    Day(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Converts the Day enum value to DayOfWeek.
     * @return the DayOfWeek value
     */
    public DayOfWeek toDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Retrieves a list of all Day enum values.
     * @return a list of all Day enum values
     */
    public static List<Day> getAllDayOfWeeks() {
        List<Day> daysOfWeek = new ArrayList<>();
        for (Day day : Day.values()) {
            daysOfWeek.add(day);
        }
        return daysOfWeek;
    }

    /**
     * Represents a shift in the restaurant.
     */
    public static class Shift {

        private LocalTime openingTime;
        private LocalTime closingTime;
        private Day day;

        /**
         * Constructs a Shift object with the specified opening time, closing time, and day.
         * @param openingTime the opening time of the shift
         * @param closingTime the closing time of the shift
         * @param day the day of the shift
         */
        public Shift(LocalTime openingTime, LocalTime closingTime, Day day) {
            this.openingTime = openingTime;
            this.closingTime = closingTime;
            this.day = day;
        }

        /**
         * Retrieves the opening time of the shift.
         * @return the opening time of the shift
         */
        public LocalTime getOpeningTime() {
            return openingTime;
        }

        /**
         * Retrieves the closing time of the shift.
         * @return the closing time of the shift
         */
        public LocalTime getClosingTime() {
            return closingTime;
        }

        /**
         * Retrieves the day of the shift.
         * @return the day of the shift
         */
        public Day getDay() {
            return day;
        }

        /**
         * Sets the opening time of the shift.
         * @param openingTime the opening time of the shift
         */
        public void setOpeningTime(LocalTime openingTime) {
            this.openingTime = openingTime;
        }

        /**
         * Sets the closing time of the shift.
         * @param closingTime the closing time of the shift
         */
        public void setClosingTime(LocalTime closingTime) {
            this.closingTime = closingTime;
        }
    }
}
