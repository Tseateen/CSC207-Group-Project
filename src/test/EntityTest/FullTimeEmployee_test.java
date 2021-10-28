package test.EntityTest;

import main.Entity.FullTimeEmployee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FullTimeEmployee_test {
    FullTimeEmployee Full_time_employee_1;
    FullTimeEmployee Full_time_employee_2;

    @Before
    public void setUp(){
        // Regular FullTimeEmployee constructor
        Full_time_employee_1 = new FullTimeEmployee("IT", "Manager", 10000, 0);
        // Overload FullTimeEmployee constructor
        Full_time_employee_2 = new FullTimeEmployee("HR",5000, 1);
    }

    @Test(timeout = 100)
    public void testDepartment(){
        assertEquals(Full_time_employee_1.getDepartment(), "IT");
        assertEquals(Full_time_employee_2.getDepartment(), "HR");
    }

    @Test(timeout = 100)
    public void testPosition() {
        assertEquals(Full_time_employee_1.getPosition(), "Manager");
        assertEquals(Full_time_employee_2.getPosition(), "");
    }

    @Test(timeout = 100)
    public void testWage(){
        assertEquals(Full_time_employee_1.getWage(), 10000);
        assertEquals(Full_time_employee_2.getWage(), 5000);
    }

    @Test(timeout = 100)
    public void testLevel(){
        assertEquals(Full_time_employee_1.getLevel(), 0);
        assertEquals(Full_time_employee_2.getLevel(), 1);
    }
}
