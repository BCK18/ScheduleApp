package com.example.scheduleapp;

import java.util.ArrayList;
import java.time.LocalDate;

public class Week {

    public ArrayList<LocalDate> date_range;
    public ArrayList<Day> days = new ArrayList<Day>();

    // constructor
    public Week(ArrayList<LocalDate> new_date_range){

        this.date_range = new_date_range;
    }

    // toString method
    public String toString(){

        String days_string = "Days:\n";

        for (int i = 0; i < days.size(); i++){

            days_string = days_string + "\n" + days.get(i).toString();
        }

        return String.format("Date range: %s\n\n", date_range.get(0).toString() + " - " + date_range.get(6).toString()) + days_string;
    }

    // add method
    public void addDay(Day new_day){

        this.days.add(new_day);
    }

    // getter methods
    public String getDateRange(){

        return date_range.get(0).toString() + " - " + date_range.get(6).toString();
    }

    public ArrayList<Day> getDays(){

        return this.days;
    }
}
