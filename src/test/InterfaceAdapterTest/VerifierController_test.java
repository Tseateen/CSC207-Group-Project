package test.InterfaceAdapterTest;

import main.InterfaceAdapter.*;
import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class VerifierController_test {
    LoginList loginList;
    EmployeeList employeeList;
    GroupList groupList;
    VerifierController verifierController;

    @Before
    public void Setup(){
        loginList = new LoginList();
        employeeList = new EmployeeList();
        groupList = new GroupList();
        verifierController = new VerifierController(new Verifier());
        loginList.addUser("Luke", "Test123", "1234567890", "10 Test St");
        employeeList.addEmployee("HR", "100", "Manager","1", "F", "Luke");
        loginList.addUser("Kenny", "test1234", "1234567789", "12 Test St");
        employeeList.addEmployee("HR","100", "N","5", "P", "Kenny");
        groupList.addGroup("Luke0", "Work0");
    }


    @Test
    public void testValidToCreate(){
        assertTrue(verifierController.validToCreate("3", employeeList, "Luke0"));
    }

    @Test
    public void testValidToCreateWithAlphabet(){
        assertFalse(verifierController.validToCreate("Hi", employeeList, "Luke0"));
    }

    @Test
    public void testVerifyUserExistence(){
        assertTrue(verifierController.verifyUserExistence("Luke0", loginList));
    }

    @Test
    public void testVerifyUserExistenceNone(){
        assertFalse(verifierController.verifyUserExistence("Hello0", loginList));
    }

    @Test
    public void testValidToDelete(){
        assertTrue(verifierController.validToDelete("Kenny1", employeeList, "Luke0"));
    }

    @Test
    public void testValidToDeleteUserNotExist(){
        assertFalse(verifierController.validToDelete("Hello1", employeeList, "Luke0"));
    }

    @Test
    public void testVerifyFullTime(){
        assertTrue(verifierController.verifyFullTime("Luke0", employeeList));
    }

    @Test
    public void testVerifyFullTimeFalse(){
        assertFalse(verifierController.verifyFullTime("Kenny1", employeeList));
    }

    @Test
    public void testVerifyLogin(){
        assertTrue(verifierController.verifyLogin("Luke0", "Test123", loginList));
    }

    @Test
    public void testVerifyLoginFalse(){
        assertFalse(verifierController.verifyLogin("Luke0", "test", loginList));
    }

    @Test
    public void testVerifyLeader(){
        assertTrue(verifierController.verifyLeader("Luke0", "Work0", groupList));
    }
    @Test
    public void testVerifyLevel(){
        assertTrue(verifierController.verifyLevel("9", "Luke0", employeeList));
    }

    @Test
    public void testVerifyLevelFalse(){
        assertFalse(verifierController.verifyLevel("0", "Luke0", employeeList));
    }
}
