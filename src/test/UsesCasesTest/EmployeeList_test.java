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
        EL.addEmployee("IT", 3000, "", 4, "Pending");
        for (Employee e: EL) {
            if (e.getID().equals("3056")){
                assertEquals(e.getLevel(), 4);
                assertEquals(e.getDepartment(), "IT");
                assertEquals(e.getWage(), 3000);
                assertEquals(e.getID(), "3056");
            }
        }
    }


    @Test(timeout = 100)
    public void testDeleteEmployee(){
        int a = EL.getSize();
        EL.deleteEmployee("3056");
        for (Employee e: EL) {
            assertNotEquals("3056", e.getID());
        }
        assertEquals(EL.getSize(), (a + 1));
    }


    @Test(timeout = 100)
    public void testGetSize(){
        int size = EL.getSize();
        EL.addEmployee("IT", 3000, "", 4, "Pending");
        assertEquals(EL.getSize(), (size + 1));
    }
}
