package test.UsesCasesTest;

import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WorkList_test {
    WorkList WL;

    @Before
    public void Setup(){
        WL = new WorkList();

    }


    @Test
    public void testAddWork(){
        WL.addWork("Project 1", "1111", "IT", "2", 2, "2022-01-01");
        assertEquals(WL.getSize(), 1);
        for (Workable w: WL){
            if (w.getID().equals("1111")){
                assertEquals(w.getName(), "Project 1");
                assertEquals(w.getLevel(), 2);
                assertEquals(w.getDepartment(), "IT");
            }
        }
    }


    @Test
    public void testGetSize(){
        assertEquals(WL.getSize(), 0);
    }

    @Test
    public void testCheckWorkExists(){
        WL.addWork("Project 1", "1111", "IT", "2", 2, "2022-01-01");
        assertEquals(WL.getSize(), 1);
        assertTrue(WL.checkWorkExist("1111"));
        assertFalse(WL.checkWorkExist("1112"));

    }

    @Test
    public void testFindWorkLevel(){
        WL.addWork("Project 1", "1111", "IT", "2", 2, "2022-01-01");
        assertEquals("2", WL.FindWorkLevel("1111"));
    }

}
