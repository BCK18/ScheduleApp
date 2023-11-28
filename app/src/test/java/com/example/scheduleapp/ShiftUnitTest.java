package com.example.scheduleapp;

import org.junit.Test;
import org.junit.Assert;

public class ShiftUnitTest {

    public Shift createTestShift() {

        Shift test_shift = new Shift("Morning");

        test_shift.setYear("2023");
        test_shift.setMonth("November");
        test_shift.setDay("22");

        Employee test_1 = new Employee(1,"test_1_first", "test_1_last","1234567890","test_1@email.com",true,true,true,true,true,true);
        Employee test_2 = new Employee(2,"test_2_first","test_2_last","0987654321","test_2@email.com",false, false, true, true, true, true);

        test_shift.addSupervisor(test_1);
        test_shift.addSecond(test_2);

        return test_shift;
    }

    @Test
    public void ShiftInitTest() {

        Shift test_shift = new Shift("Morning");
        Assert.assertEquals(test_shift.shift_type, "Morning");
    }

    @Test
    public void ShiftToStringTest() {

        Shift test_shift = createTestShift();

        String test_shift_string = test_shift.toString();

        String expected_string = "\t\tShift Type: Morning," +
                "\n\t\t\tSupervisor: test_1_first test_1_last," +
                "\n\t\t\tSecond: test_2_first test_2_last";

        Assert.assertEquals(test_shift_string, expected_string);
    }

    @Test
    public void ShiftGetterMethodsTest() {

        Shift test_shift = createTestShift();

        Assert.assertEquals(test_shift.getShiftType(), "Morning");
        Assert.assertEquals(test_shift.getYear(), "2023");
        Assert.assertEquals(test_shift.getMonth(), "November");
        Assert.assertEquals(test_shift.getDay(), "22");

        // getSupervisor() and getSecond() return Employee objects,
        // so a test will be done to check that the ID fields of the returned objects are as expected.
        Assert.assertEquals(test_shift.getSupervisor().id, 1);
        Assert.assertEquals(test_shift.getSecond().id, 2);
    }

    @Test
    public void ShiftSetterMethodsTest() {

        Shift test_shift = createTestShift();

        test_shift.setYear("2024");
        Assert.assertEquals(test_shift.year, "2024");

        test_shift.setMonth("December");
        Assert.assertEquals(test_shift.month, "December");

        test_shift.setDay("25");
        Assert.assertEquals(test_shift.day, "25");

        Employee new_supervisor = new Employee(3,"new_supervisor_first", "new_supervisor_last","1234567890","test_1@email.com",true,true,true,true,true,true);
        Employee new_second = new Employee(4,"new_second_first","new_second_last","0987654321","test_2@email.com",false, false, true, true, true, true);

        test_shift.addSupervisor(new_supervisor);
        Assert.assertEquals(test_shift.supervisor.id, new_supervisor.id);

        test_shift.addSecond(new_second);
        Assert.assertEquals(test_shift.second.id, new_second.id);
    }
}
