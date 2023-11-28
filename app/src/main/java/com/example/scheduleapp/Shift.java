package com.example.scheduleapp;

import java.time.LocalDate;

public class Shift {

    public LocalDate date;
    public String shift_type;
    public Employee supervisor;
    public Employee second;

    // constructor
    public Shift(String new_shift_type, LocalDate new_shift_date){

        this.shift_type = new_shift_type;
        this.date = new_shift_date;
    }

    // toString method
    // toString method
    public String toString() {

        return String.format("\t\tDate: %s, Shift Type: %s,", date, shift_type) +
                String.format("\n\t\t\tSupervisor: %s,", supervisor) +
                String.format("\n\t\t\tSecond: %s", second);
    }

    // setter methods.

    public void addSupervisor(Employee new_supervisor){

        this.supervisor = new_supervisor;
    }

    public void addSecond(Employee new_second){

        this.second = new_second;
    }

    // getter methods
    public int getYear(){

        return date.getYear();
    }

    public int getMonth(){

        return date.getMonth().getValue();
    }

    public int getDay(){

        return date.getDayOfMonth();
    }

    public LocalDate getDate(){

        return date;
    }

    public String getShiftType(){

        return shift_type;
    }

    public Employee getSupervisor(){

        return supervisor;
    }

    public Employee getSecond(){

        return second;
    }
}
