
package com.example.scheduleapp;
import java.io.Serializable;

public class Employee  implements Serializable {

    public int id;
    public String first_Name;
    public String last_Name;
    public String phone_Number;
    public String email;
    public boolean opening;
    public boolean closing;
    public boolean mornings;
    public boolean afternoons;
    public boolean evenings;
    public boolean weekends;
    public boolean status;

    // Constructor for Employee class.
    public Employee(int id, String first_Name, String last_Name, String phone_Number, String email,
                    boolean opening, boolean closing, boolean mornings, boolean afternoons, boolean evenings, boolean weekends,
    boolean status
    ){
        this.id = id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.phone_Number = phone_Number;
        this.email = email;
        this.opening = opening;
        this.closing = closing;
        this.mornings = mornings;
        this.afternoons = afternoons;
        this.evenings = evenings;
        this.weekends = weekends;
        this.status = status;
    }

    // Empty class constructor.
    public Employee(){
    }

    // to String method for printing contents of class.
    @Override
    public String toString(){
        // String formatting used for readability of code.
        return String.format("Employee(id=%d", id) +
                String.format(", First_Name=%s", first_Name) +
                String.format(", Last_Name=%s", last_Name) +
                String.format(", Phone_Number=%s", phone_Number) +
                String.format(", Email=%s", email) +
                String.format(", Opening=%b", opening) +
                String.format(", Closing=%b", closing) +
                String.format(", Mornings=%b", mornings) +
                String.format(", Afternoons=%b", afternoons) +
                String.format(", Evenings=%b", evenings) +
                String.format(", Weekends=%b", weekends) +
                String.format(", Status=%b", status)
                ;
    }

    // Getter and setter methods.

    // id getter + setter.
    public int getId() {return id;};

    public void setId(int id) {this.id = id;}

    // first_Name getter + setter.
    public String getFirstName() {return first_Name;};

    public void setFirstName (String first_Name) {this.first_Name = first_Name;};

    // last_Name getter + setter.
    public String getLastName() {return last_Name;};

    public void setLastName (String last_Name) {this.last_Name = last_Name;};

    // phone_Number getter + setter.
    public String getPhoneNumber() {return phone_Number;};

    public void setPhoneNumber(String phone_Number) {this.phone_Number = phone_Number;};

    // email getter + setter.
    public String getEmail() {return email;};

    public void setEmail (String email) {this.email = email;};

    // opening getter + setter.
    public boolean getOpening() {return opening;};

    public void setOpening(boolean opening) {this.opening = opening;};

    // closing getter + setter.
    public boolean getClosing() {return closing;};

    public void setClosing(boolean closing) {this.closing = closing;};

    // mornings getter + setter.
    public boolean getMornings() {return mornings;};

    public void setMornings(boolean mornings) {this.mornings = mornings;};

    // afternoons getter + setter.
    public boolean getAfternoons() {return afternoons;};

    public void setAfternoons(boolean afternoons) {this.afternoons = afternoons;};

    // evenings getter + setter.
    public boolean getEvenings() {return evenings;};

    public void setEvenings(boolean evenings) {this.evenings = evenings;};

    // weekends getter + setter.
    public boolean getWeekends() {return weekends;};

    public void setWeekends(boolean weekends) {this.weekends = weekends;};

    public boolean getStatus() {return status;};

    public void setStatus(boolean status) {this.status = status;};

}

