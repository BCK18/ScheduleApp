package com.example.scheduleapp;

import java.util.ArrayList;
import java.time.LocalDate;

public class Day {

    public LocalDate date;
    public String weekday;
    public String day_type;
    public ArrayList<Shift> shifts = new ArrayList<Shift>();
    public ArrayList<Employee> workers = new ArrayList<Employee>();

    // constructor
    public Day(LocalDate new_date, String new_weekday, String new_day_type){

        this.date = new_date;
        this.weekday = new_weekday;
        this.day_type = new_day_type;
    }

    // toString method
    public String toString(){

        String shifts_string = "\tShifts:\n";
        String workers_string = "\tWorkers:\n";

        for (int i = 0; i < shifts.size(); i++){
            shifts_string = shifts_string + shifts.get(i).toString() + "\n";
        }

        for (int i = 0; i < workers.size(); i++){
            workers_string = workers_string + "\t" + workers.get(i).getFirstName() + " " + workers.get(i).getLastName() + "\n";
        }

        return String.format("Date: %s,", date) +
                String.format(" Weekday: %s,", weekday) +
                String.format(" Day Type: %s", day_type) +
                String.format("\n%s", shifts_string) +
                String.format("%s", workers_string);
    }


    // check if an employee is in the workers list.
    public boolean workerCheck(Employee employee){

        boolean worked = false;

        for (int i = 0; i < workers.size(); i++){

            if (employee.id == workers.get(i).id){

                worked = true;
            }
        }

        return worked;
    }


    // add methods for shifts and workers
    public void addShift(Shift new_shift){

        this.shifts.add(new_shift);


    }

    public void addWorker(Employee new_worker){

        this.workers.add(new_worker);
    }

    // getter methods
    public LocalDate getDate(){

        return date;
    }

    public String getWeekday(){

        return weekday;
    }

    public String getDayType(){

        return day_type;
    }

    public ArrayList<Shift> getShifts(){

        return shifts;
    }

    public ArrayList<Employee> getWorkers(){

        return workers;
    }
}
