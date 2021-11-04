package test.EntityTest;

import main.Entity.FullTimeEmployee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FullTimeEmployee_test {
    FullTimeEmployee Full_time_employee_1;
    FullTimeEmployee Full_time_employee_2;

    @Before
    public void setUp() {
        // Regular FullTimeEmployee constructor
        Full_time_employee_1 = new FullTimeEmployee("IT", "Manager", 10000, 0, "1");
        // Overload FullTimeEmployee constructor
        Full_time_employee_2 = new FullTimeEmployee("HR", 5000, 1, "2");
    }

    @Test(timeout = 100)
    public void testDepartment() {
        assertEquals(Full_time_employee_1.getDepartment(), "IT");
        assertEquals(Full_time_employee_2.getDepartment(), "HR");
        Full_time_employee_1.setDepartment("HR");
        assertEquals(Full_time_employee_1.getDepartment(), "HR");

    }

    @Test(timeout = 100)
    public void testPosition() {
        assertEquals(Full_time_employee_1.getPosition(), "Manager");
        assertEquals(Full_time_employee_2.getPosition(), "");
        Full_time_employee_2.setPosition("Manager");
        assertEquals(Full_time_employee_2.getPosition(), "Manager");

    }

    @Test(timeout = 100)
    public void testWage() {
        assertEquals(Full_time_employee_1.getWage(), 10000);
        assertEquals(Full_time_employee_2.getWage(), 5000);
        Full_time_employee_1.setWage(20000);
        assertEquals(Full_time_employee_1.getWage(), 20000);

    }

    @Test(timeout = 100)
    public void testLevel() {
        assertEquals(Full_time_employee_1.getLevel(), 0);
        assertEquals(Full_time_employee_2.getLevel(), 1);
        Full_time_employee_2.setLevel(2);
        assertEquals(Full_time_employee_2.getLevel(), 2);


    }

    @Test(timeout = 100)
    public void testId() {
        assertEquals(Full_time_employee_1.getId(), "1");
        assertEquals(Full_time_employee_2.getId(), "2");
    }

    @Test(timeout = 100)
    public void testAttendance() {
        assertEquals(Full_time_employee_1.getAttendance(), 0);
        assertEquals(Full_time_employee_2.getAttendance(), 0);
        Full_time_employee_1.setAttendance();
        Full_time_employee_1.setAttendance();
        Full_time_employee_2.setAttendance();
        Full_time_employee_2.setAttendance();
        Full_time_employee_2.setAttendance();
        assertEquals(Full_time_employee_1.getAttendance(), 2);
        assertEquals(Full_time_employee_2.getAttendance(), 3);
    }

    @Test(timeout = 100)
    public void testTotalVacationWithSalary() {
        assertEquals(Full_time_employee_1.getTotalVacationWithSalary(), 0);
        assertEquals(Full_time_employee_2.getTotalVacationWithSalary(), 0);
        Full_time_employee_1.setTotalVacationWithSalary(5);
        Full_time_employee_2.setTotalVacationWithSalary(2);
        assertEquals(Full_time_employee_1.getTotalVacationWithSalary(), 5);
        assertEquals(Full_time_employee_2.getTotalVacationWithSalary(), 2);
    }

    @Test(timeout = 100)
    public void testVacationUsed() {
        assertEquals(Full_time_employee_1.getVacationUsed(), 0);
        assertEquals(Full_time_employee_2.getVacationUsed(), 0);
        Full_time_employee_1.setVacationUsed(3);
        Full_time_employee_2.setVacationUsed(1);
        assertEquals(Full_time_employee_1.getVacationUsed(), 3);
        assertEquals(Full_time_employee_2.getVacationUsed(), 1);
    }

    @Test(timeout = 100)
    public void testVacationUsedState() {
        assertEquals(Full_time_employee_1.getState(), "Working");
        assertEquals(Full_time_employee_2.getState(), "Working");
        Full_time_employee_1.setState("Vacation");
        assertEquals(Full_time_employee_1.getState(), "Vacation");
    }

}
