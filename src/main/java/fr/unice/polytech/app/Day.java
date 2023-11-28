package fr.unice.polytech.app;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

}
