package test.InterfaceAdapterTest;

import main.InterfaceAdapter.*;
import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LoginListController_test {

    LoginList loginList;
    EmployeeList employeeList;
    LoginListController loginListController;

    @Before
    public void Setup(){
        loginList = new LoginList();
        employeeList = new EmployeeList();
        loginListController = new LoginListController(loginList);
    }

    @Test
    public void testAddUser(){
        assertEquals("Luke0",loginListController.addUser("Luke", "Test123", "1234567890", "10 Test St"));
    }
}
