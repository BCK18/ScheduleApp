package com.example.scheduleapp;

import org.junit.Test;
import org.junit.Assert;

public class EmployeeUnitTest {

    public Employee createTestEmployee() {

        return new Employee(1,
                "Test",
                "Employee",
                "1234567890",
                "test@email.com",
                true,
                true,
                false,
                true,
                false,
                true);
    }

    @Test
    public void EmployeeInitTest () {

        Employee test_employee = createTestEmployee();

        Assert.assertEquals(test_employee.id, 1);
        Assert.assertEquals(test_employee.first_Name, "Test");
        Assert.assertEquals(test_employee.last_Name, "Employee");
        Assert.assertEquals(test_employee.phone_Number, "1234567890");
        Assert.assertEquals(test_employee.email, "test@email.com");
        Assert.assertEquals(test_employee.opening, true);
        Assert.assertEquals(test_employee.closing, true);
        Assert.assertEquals(test_employee.mornings, false);
        Assert.assertEquals(test_employee.afternoons, true);
        Assert.assertEquals(test_employee.evenings, false);
        Assert.assertEquals(test_employee.weekends, true);
    }

    @Test
    public void EmployeeToStringTest() {

        Employee test_employee = createTestEmployee();

        String test_employee_string = test_employee.toString();

        String expected_string = "Employee(id=1, " +
                "First_Name=Test, " +
                "Last_Name=Employee, " +
                "Phone_Number=1234567890, " +
                "Email=test@email.com, " +
                "Opening=true, " +
                "Closing=true, " +
                "Mornings=false, " +
                "Afternoons=true, " +
                "Evenings=false, " +
                "Weekends=true";

        Assert.assertEquals(test_employee_string, expected_string);
    }

    @Test
    public void EmployeeGetterMethodsTest() {

        Employee test_employee = createTestEmployee();

        Assert.assertEquals(test_employee.getId(), 1);
        Assert.assertEquals(test_employee.getFirstName(), "Test");
        Assert.assertEquals(test_employee.getLastName(), "Employee");
        Assert.assertEquals(test_employee.getPhoneNumber(), "1234567890");
        Assert.assertEquals(test_employee.getEmail(), "test@email.com");
        Assert.assertEquals(test_employee.getOpening(), true);
        Assert.assertEquals(test_employee.getClosing(), true);
        Assert.assertEquals(test_employee.getMornings(), false);
        Assert.assertEquals(test_employee.getAfternoons(), true);
        Assert.assertEquals(test_employee.getEvenings(), false);
        Assert.assertEquals(test_employee.getWeekends(), true);
    }

    @Test
    public void EmployeeSetterMethodsTest() {

        Employee test_employee = createTestEmployee();

        test_employee.setId(2);
        Assert.assertEquals(test_employee.id, 2);

        test_employee.setFirstName("New");
        Assert.assertEquals(test_employee.first_Name, "New");

        test_employee.setLastName("Name");
        Assert.assertEquals(test_employee.last_Name, "Name");

        test_employee.setPhoneNumber("0987654321");
        Assert.assertEquals(test_employee.phone_Number, "0987654321");

        test_employee.setEmail("example@test.com");
        Assert.assertEquals(test_employee.email, "example@test.com");

        test_employee.setOpening(false);
        Assert.assertEquals(test_employee.opening, false);

        test_employee.setClosing(false);
        Assert.assertEquals(test_employee.closing, false);

        test_employee.setMornings(true);
        Assert.assertEquals(test_employee.mornings, true);

        test_employee.setAfternoons(false);
        Assert.assertEquals(test_employee.afternoons, false);

        test_employee.setEvenings(true);
        Assert.assertEquals(test_employee.evenings, true);

        test_employee.setWeekends(false);
        Assert.assertEquals(test_employee.weekends, false);
    }
}
