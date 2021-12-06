package test.EntityTest;

import main.Entity.PartTimeEmployee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartTimeEmployee_test {
    PartTimeEmployee Part_time_employee_1;
    PartTimeEmployee Part_time_employee_2;

    @Before
    public void setUp(){
        // Regular FullTimeEmployee constructor
        Part_time_employee_1 = new PartTimeEmployee("HR","4000", 4, "Lily124");
        Part_time_employee_2 = new PartTimeEmployee("IT", "5000", 3, "Cathy123");
    }

    @Test(timeout = 100)
    public void testDepartment() {
        assertEquals(Part_time_employee_1.getDepartment(), "HR");
        assertEquals(Part_time_employee_2.getDepartment(), "IT");
        Part_time_employee_1.setDepartment("IT");
        assertEquals(Part_time_employee_1.getDepartment(), "IT");

    }


    @Test(timeout = 100)
    public void testWage() {


    }

    @Test(timeout = 100)
    public void testLevel() {
        assertEquals(Part_time_employee_1.getLevel(), 4);
        assertEquals(Part_time_employee_2.getLevel(), 3);
        Part_time_employee_2.setLevel(2);
        assertEquals(Part_time_employee_2.getLevel(), 2);


    }

    @Test(timeout = 100)
    public void testId() {
        assertEquals(Part_time_employee_1.getID(), "Lily124");
        assertEquals(Part_time_employee_2.getID(), "Cathy123");
    }

    @Test(timeout = 100)
    public void testAttendance() {
        assertEquals(Part_time_employee_1.getAttendance(), 0);
        assertEquals(Part_time_employee_2.getAttendance(), 0);
        Part_time_employee_1.setAttendance();
        Part_time_employee_1.setAttendance();
        Part_time_employee_2.setAttendance();
        Part_time_employee_2.setAttendance();
        Part_time_employee_2.setAttendance();
        assertEquals(Part_time_employee_1.getAttendance(), 2);
        assertEquals(Part_time_employee_2.getAttendance(), 3);
    }

}
