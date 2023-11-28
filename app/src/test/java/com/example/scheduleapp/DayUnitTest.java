package com.example.scheduleapp;

import org.junit.Test;
import org.junit.Assert;

public class DayUnitTest {

    public Day createTestDay() {

        Day test_day = new Day("22-11-2023", "Wednesday", "Weekday");

        Employee test_1 = new Employee(1,"test_1_first", "test_1_last","1234567890","test_1@email.com",true,true,true,true,true,true);
        Employee test_2 = new Employee(2,"test_2_first","test_2_last","0987654321","test_2@email.com",false, false, true, true, true, true);
        Employee test_3 = new Employee(3,"test_3_first", "test_3_last","1234567890","test_3@email.com",true,true,true,true,true,true);
        Employee test_4 = new Employee(4,"test_4_first","test_4_last","0987654321","test_4@email.com",false, false, true, true, true, true);

        Shift morning_shift = new Shift("Morning");

        morning_shift.setYear("2023");
        morning_shift.setMonth("November");
        morning_shift.setDay("22");

        morning_shift.addSupervisor(test_1);
        morning_shift.addSecond(test_2);

        test_day.addShift(morning_shift);

        Shift afternoon_shift = new Shift("Afternoon");

        afternoon_shift.setYear("2023");
        afternoon_shift.setMonth("November");
        afternoon_shift.setDay("22");

        afternoon_shift.addSupervisor(test_3);
        afternoon_shift.addSecond(test_4);

        test_day.addShift(afternoon_shift);

        Shift evening_shift = new Shift("Evening");

        evening_shift.setYear("2023");
        evening_shift.setMonth("November");
        evening_shift.setDay("22");

        evening_shift.addSupervisor(test_1);
        evening_shift.addSecond(test_2);

        test_day.addShift(evening_shift);

        return test_day;
    }

    @Test
    public void dayInitTest() {

        Day test_day = new Day("22-11-2023", "Wednesday", "Weekday");

        Assert.assertEquals(test_day.date, "22-11-2023");
        Assert.assertEquals(test_day.weekday, "Wednesday");
        Assert.assertEquals(test_day.day_type, "Weekday");
        Assert.assertTrue(test_day.shifts.isEmpty());
        Assert.assertTrue(test_day.workers.isEmpty());
    }

    @Test
    public void dayShiftAddTest() {

        Day test_day = new Day("22-11-2023", "Wednesday", "Weekday");

        Assert.assertTrue(test_day.shifts.isEmpty());

        Shift morning_shift = new Shift("Morning");
        test_day.addShift(morning_shift);

        Assert.assertEquals(test_day.shifts.size(), 1);
        Assert.assertEquals(test_day.shifts.get(0), morning_shift);
    }

    @Test
    public void dayAddWorkerTest() {

        Day test_day = new Day("22-11-2023", "Wednesday", "Weekday");

        Assert.assertTrue(test_day.workers.isEmpty());

        Employee test_1 = new Employee(1,"test_1_first", "test_1_last","1234567890","test_1@email.com",true,true,true,true,true,true);
        test_day.addWorker(test_1);

        Assert.assertEquals(test_day.workers.size(), 1);
        Assert.assertEquals(test_day.workers.get(0), test_1);
    }
}
