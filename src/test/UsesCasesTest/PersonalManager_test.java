package test.UsesCasesTest;


import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PersonalManager_test {

    LoginList LL;
    EmployeeList EL;
    PersonalManager PM;
    Userable u1, u2;
    FullTimeEmployee e1;
    PartTimeEmployee e2;
    GroupList GL;
    WorkList WL;

    @Before
    public void Setup(){
        LL = new LoginList();
        u1 = new User("Lily", "002", "l99999999", "498765432",
                "234 Mississauga Road");
        u2 = new User("Cathy", "003", "33445566", "6478888888",
                "12 Apple Creek");
        LL.addUser(u1.getName(), u1.getPassword(), u1.getPhone(), u1.getAddress());
        LL.addUser(u2.getName(), u2.getPassword(), u2.getPhone(), u2.getAddress());
        EL = new EmployeeList();
        e1 = new FullTimeEmployee("Human Resource", "Manager", "6000", 4, "002");
        e2 = new PartTimeEmployee("Technology", "25", 8, "003");
        EL.addEmployee(e1.getDepartment(),e1.getWage(),e1.getPosition(), e1.getLevel()+"", "F", "Lily");
        EL.addEmployee(e2.getDepartment(),e2.getWage(),"N", e2.getLevel()+"", "P", "Cathy");
        PM = new PersonalManager();
        GL = new GroupList();
        GL.addGroup("Lily", "207");
        WL.addWork("Group Project", "207", "Human Resource", "Get it done", 9);
        e1.setTotalVacationWithSalary("2");
        e1.setVacationUsed("1");

    }

    @Test
    public void testEmployeeInfo(){
        ArrayList<String> info1 = new ArrayList<>(Arrays.asList("Cathy", "003", "33445566", "6478888888",
                "12 Apple Creek", "Technology"));
        assertEquals(info1, PM.employeeInfo(LL, EL, "003"));
        ArrayList<String> info2 = new ArrayList<>( Arrays.asList("Lily", "002", "l99999999", "498765432",
                "234 Mississauga Road", "Human Resource", "Human Resource", "Manager", "Working", "0", "0"));
        assertEquals(info2, PM.employeeInfo(LL, EL, "002"));
    }


    @Test
    public void testCheckTotalSalary(){
        assertEquals("0", PM.checkTotalSalary(EL,"003", GL, WL));
        assertEquals("6200", PM.checkTotalSalary(EL,"002", GL, WL));
        // I think it needs modification, since this test cannot effectively test what the method did (KPI part)
    }

    @Test
    public void testCheckMinimumWage(){
        assertEquals("5000", PM.checkMinimumWage(EL, "002"));
        assertEquals("25", PM.checkMinimumWage(EL, "003"));
    }

    @Test
    public void testCheckVacationBonus(){
        assertEquals("200", PM.checkVacationBonus(EL, "002"));
        assertEquals("0", PM.checkVacationBonus(EL, "003"));
    }

    @Test
    public void testCheckKPIBonus(){
        assertEquals("0", PM.checkKPIBonus(EL, "002", GL, WL));
        assertEquals("0", PM.checkKPIBonus(EL, "003", GL, WL));
        // same issue, require further modification
    }

    @Test
    public void testVacationInfo(){
        ArrayList<String> info1 = new ArrayList<>(Arrays.asList("1", "2"));
        assertEquals(info1, PM.vacationInfo(EL, "002"));
        ArrayList<String> info2 = new ArrayList<>();
        assertEquals(info2, PM.vacationInfo(EL, "003"));
    }
}
