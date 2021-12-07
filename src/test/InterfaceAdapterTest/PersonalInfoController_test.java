package test.InterfaceAdapterTest;

import main.InterfaceAdapter.*;
import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class PersonalInfoController_test {
    LoginList loginList;
    EmployeeList employeeList;
    PersonalInfoController personalInfoController;


    @Before
    public void Setup(){
        loginList = new LoginList();
        employeeList = new EmployeeList();
        personalInfoController = new PersonalInfoController(new PersonalManager());
        loginList.addUser("Luke", "Test123", "1234567890", "10 Test St");
        employeeList.addEmployee("HR", "100", "Manager","1", "F", "Luke");
        loginList.addUser("Kenny", "test1234", "1234567789", "12 Test St");
        employeeList.addEmployee("HR","100", "N","5", "P", "Kenny");


    }

    @Test
    public void testPersonalInfo(){
       ArrayList<String> personalInfo = new ArrayList<>();
        personalInfo.add("Name: " + "Luke" + "\n");
        personalInfo.add("ID: " + "Luke0" + "\n");
        personalInfo.add("Password: " + "Test123" + "\n");
        personalInfo.add("Phone Number: " + "1234567890" + "\n");
        personalInfo.add("Address: " +"10 Test St" + "\n");
        personalInfo.add("Department: " + "HR" + "\n");
        personalInfo.add("Position: " + "Manager" + "\n");
        personalInfo.add("State: " + "Working" + "\n");
        personalInfo.add("Total Vacation with Salary: " + "0" + "\n");
        personalInfo.add("Vacation Used: " + "0" + "\n");
        assertEquals(personalInfo, personalInfoController.personalInfo(loginList, employeeList, "Luke0"));
    }

    @Test
    public void testPersonalInfoPartTime(){
        ArrayList<String> personalInfo = new ArrayList<>();
        personalInfo.add("Name: " + "Kenny" + "\n");
        personalInfo.add("ID: " + "Kenny1" + "\n");
        personalInfo.add("Password: " + "test1234" + "\n");
        personalInfo.add("Phone Number: " + "1234567789" + "\n");
        personalInfo.add("Address: " +"12 Test St" + "\n");
        personalInfo.add("Department: " + "HR" + "\n");
        personalInfo.add("Work hours: " + "0");
        assertEquals(personalInfo,personalInfoController.personalInfo(loginList,employeeList,"Kenny1"));
    }
    @Test
    public void testSetPersonalInfoFullTime(){
        assertTrue(personalInfoController.setPersonalInfo("4", "1112223456", loginList, "Luke0"));
    }

    @Test
    public void testSetPersonalInfoInvalid(){
        assertFalse(personalInfoController.setPersonalInfo("10","1023123", loginList, "Luke0"));
    }

    @Test
    public void testSetEmployeeInfo(){
        assertTrue(personalInfoController.setEmployeeInfo("Kenny1", "2" , "7", employeeList) );
    }

    @Test
    public void testSetEmployeeInfoInvalid(){
        assertFalse(personalInfoController.setEmployeeInfo("Kenny1", "6" , "10", employeeList));
    }

    @Test
    public void testCheckUserLevelFullTime(){
        assertEquals("1", personalInfoController.checkUserLevel("Luke0", employeeList));
    }

    @Test
    public void testCheckUserLevelPartTime(){
        assertEquals("5", personalInfoController.checkUserLevel("Kenny1", employeeList));
    }
}
