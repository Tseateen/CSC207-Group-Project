package test.UsesCasesTest;


import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FullTimeInfoManager_test {
    FullTimeInfoManager FTE1;
    FullTimeInfoManager FTE2;

    @Before
    public void Setup(){
        String user1 = new User("111", "123456", "Lily", "4203456789",
                "10 King St.", "8888");
        String user2 = new User("222", "098765", "Cathy", "4301223456",
                "10 King St.", "9999");
        FullTimeEmployee fte1 = new FullTimeEmployee("HR", "Manager", 10000, 1, "8888");
        FullTimeEmployee fte2 = new FullTimeEmployee("IT", 2500, 5, "9999");
        FTE1 = new FullTimeInfoManager(user1, fte1);
        FTE2 = new FullTimeInfoManager(user2, fte2);

    }

    @Test(timeout = 100)
    public void testGetNameFromUser(){
        assertEquals(FTE1.getNameFromUser(), "Lily");
        assertEquals(FTE2.getNameFromUser(), "Cathy");
        FTE1.setNameForUser("Li");
        assertEquals(FTE1.getNameFromUser(), "Li");
    }

    @Test(timeout = 100)
    public void testGetIDFromUser(){
        assertEquals(FTE1.getIDFromUser(), "8888");
        assertEquals(FTE2.getIDFromUser(), "9999");
    }

    @Test(timeout = 100)
    public void testGetUsernameFromUser(){
        assertEquals(FTE1.getUsernameFromUser(), "111");
        assertEquals(FTE2.getUsernameFromUser(), "222");
        FTE1.setUsernameForUser("333");
        assertEquals(FTE1.getUsernameFromUser(), "333");
    }

    @Test(timeout = 100)
    public void testGetPasswordFromUser(){
        assertEquals(FTE1.getPasswordFromUser(), "123456");
        assertEquals(FTE2.getPasswordFromUser(), "098765");
        FTE1.setPasswordForUser("111111");
        assertEquals(FTE1.getPasswordFromUser(), "111111");
    }

    @Test(timeout = 100)
    public void testGetPhoneFromUser(){
        assertEquals(FTE1.getPhoneFromUser(), "4203456789");
        assertEquals(FTE2.getPhoneFromUser(), "4301223456");
        FTE1.setPhoneForUser("5464778900");
        assertEquals(FTE1.getPhoneFromUser(), "5464778900");
    }

    @Test(timeout = 100)
    public void testGetAddressFromUser(){
        assertEquals(FTE1.getAddressFromUser(), "10 King St.");
        assertEquals(FTE2.getAddressFromUser(), "10 King St.");
        FTE1.setAddressForUser("1 King St.");
        assertEquals(FTE1.getAddressFromUser(), "1 King St.");
    }

    @Test(timeout = 100)
    public void testGetIDFromEmployee(){
        assertEquals(FTE1.getIDFromEmployee(), "8888");
        assertEquals(FTE2.getIDFromEmployee(), "9999");
    }

    @Test(timeout = 100)
    public void testGetDepartmentFromEmployee(){
        assertEquals(FTE1.getDepartmentFromEmployee(), "HR");
        assertEquals(FTE2.getDepartmentFromEmployee(), "IT");
        FTE1.setDepartmentForEmployee("IT");
        assertEquals(FTE1.getDepartmentFromEmployee(), "IT");
    }

    @Test(timeout = 100)
    public void testGetWageFromEmployee(){
        assertEquals(FTE1.getWageFromEmployee(), 10000);
        assertEquals(FTE2.getWageFromEmployee(), 2500);
        FTE1.setWageForEmployee(12000);
        assertEquals(FTE1.getWageFromEmployee(), 12000);
    }
// TODO: Complete Attendence for Employee
//    @Test(timeout = 100)
//    public void testGetAttendenceFromEmployee(){
//        assertEquals(FTE1.getAttendenceFromEmployee(), );
//        assertEquals(FTE2.getAttendenceFromEmployee(), );
//    }

    @Test(timeout = 100)
    public void testGetLevelFromEmployee(){
        assertEquals(FTE1.getLevelFromEmployee(), 1);
        assertEquals(FTE2.getLevelFromEmployee(), 5);
        FTE1.setLevelForEmployee(2);
        assertEquals(FTE1.getLevelFromEmployee(), 2);
    }


    @Test(timeout = 100)
    public void testGetTotalVacationWithSalary(){
        assertEquals(FTE1.getTotalVacationWithSalary(), 0);
        assertEquals(FTE2.getTotalVacationWithSalary(), 0);
        FTE1.setTotalVacationWithSalaryForEmployee(15);
        assertEquals(FTE1.getTotalVacationWithSalary(), 15);
    }

    @Test(timeout = 100)
    public void testGetVacationUsed(){
        assertEquals(FTE1.getVacationUsed(), 0);
        assertEquals(FTE2.getVacationUsed(), 0);
        FTE1.setVacationUsedForEmployee(5);
        assertEquals(FTE1.getVacationUsed(), 5);
    }

    @Test(timeout = 100)
    public void testGetPosition(){
        assertEquals(FTE1.getPosition(), "Manager");
        assertEquals(FTE2.getPosition(), "");
        FTE1.setPositionForEmployee("");
        assertEquals(FTE1.getPosition(), "");
    }

    @Test(timeout = 100)
    public void testGetStatus(){
        assertEquals(FTE1.getStatus(), "pending");
        assertEquals(FTE2.getStatus(), "pending");
        FTE1.setStateForEmployee("In Progress");
        assertEquals(FTE1.getStatus(), "In Progress");
    }

}
