package test.UsesCasesTest;

import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeList_test {
    EmployeeList EL;

    @Before
    public void Setup(){
        EL = new EmployeeList();
    }


    @Test(timeout = 100)
    public void testAddEmployee(){
        EL.addEmployee("IT", "3000", "0", "4", "F", "Andy");
        assertEquals(EL.getSize(), 1);
        for (Employee e: EL) {
            assertEquals(e.getLevel(), 4);
            assertEquals(e.getDepartment(), "IT");
            assertEquals(e.getWage(), "3000");
            assertEquals(e.getID(), "Andy0");

        }
    }


    @Test(timeout = 100)
    public void testDeleteEmployee(){
        EL.deleteEmployee("3056");
        for (Employee e: EL) {
            assertNotEquals("3056", e.getID());
        }
        assertEquals(EL.getSize(), 0);
    }


    @Test(timeout = 100)
    public void testGetSize(){
        EL.addEmployee("IT", "3000", "", "4", "P", "Luke");
        assertEquals(0, EL.getSize());
    }
}
