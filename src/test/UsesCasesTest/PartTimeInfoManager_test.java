package test.UsesCasesTest;


import main.UsesCases.PartTimeInfoManager;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class PartTimeInfoManager_test {
    PartTimeInfoManager PTE1;
    PartTimeInfoManager PTE2;

    @Before
    public void Setup(){
        String user1 = new User("1122", "123456", "Andy", "4377778880",
                "5 King St.", "1234");
        String user2 = new User("999", "098765", "Kyle", "1234456789",
                "10 King St.", "66677");

        PartTimeEmployee pte1 = new PartTimeEmployee("HR", 8000, 2, "1234");
        HashMap<String, String[]> hp = new HashMap<>();
        String[] t = {"9:00", "10:00", "11:00"};
        hp.put("Tuesday", t);
        PartTimeEmployee pted2 = new PartTimeEmployee("IT", 5000, 6, "66677", hp);

        PTE1 = new PartTimeInfoManager(user1, pte1);
        PTE2 = new PartTimeInfoManager(user2, pted2);
    }

    @Test(timeout = 100)
    public void testGetNameFromUser(){
        assertEquals(PTE1.getNameFromUser(), "Andy");
        assertEquals(PTE2.getNameFromUser(), "Kyle");
    }

    @Test(timeout = 100)
    public void testUsernameFromUser(){
        assertEquals(PTE1.getUsernameFromUser(), "1122");
        assertEquals(PTE2.getUsernameFromUser(), "999");
    }

    @Test(timeout = 100)
    public void testGetIDFromUser(){
        assertEquals(PTE1.getIDFromUser(), "1234");
        assertEquals(PTE2.getIDFromUser(), "66677");
    }

    @Test(timeout = 100)
    public void testGetPasswordFromUser(){
        assertEquals(PTE1.getPasswordFromUser(), "123456");
        assertEquals(PTE2.getPasswordFromUser(), "098765");
    }

    @Test(timeout = 100)
    public void testGetPhoneFromUser(){
        assertEquals(PTE1.getPhoneFromUser(), "4377778880");
        assertEquals(PTE2.getPhoneFromUser(), "1234456789");
    }

    @Test(timeout = 100)
    public void testGetAddressFromUser(){
        assertEquals(PTE1.getAddressFromUser(), "5 King St.");
        assertEquals(PTE2.getAddressFromUser(), "10 King St.");
    }

    @Test(timeout = 100)
    public void testGetIDFromEmployee(){
        assertEquals(PTE1.getIDFromEmployee(), "1234");
        assertEquals(PTE2.getIDFromEmployee(), "66677");
    }

    @Test(timeout = 100)
    public void testGetDepartmentFromEmployee(){
        assertEquals(PTE1.getDepartmentFromEmployee(), "HR");
        assertEquals(PTE2.getDepartmentFromEmployee(), "IT");
    }

    @Test(timeout = 100)
    public void testGetWageFromEmployee(){
        assertEquals(PTE1.getWageFromEmployee(), 8000);
        assertEquals(PTE2.getWageFromEmployee(), 5000);
    }

    // TODO: Attendence methods to be finished.
//    @Test(timeout = 100)
//    public void testGetAttendenceFromEmployee(){
//        assertEquals(PTE1.getAttendenceFromEmployee(),"");
//        assertEquals(PTE2.getAttendenceFromEmployee(),"");
//    }

    @Test(timeout = 100)
    public void testGetLevelFromEmployee(){
        assertEquals(PTE1.getLevelFromEmployee(),2);
        assertEquals(PTE2.getLevelFromEmployee(),6);
    }

}
