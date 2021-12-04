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
        u1 = new User("Lily", "Lily0", "l99999999", "498765432",
                "234 Mississauga Road");
        u2 = new User("Cathy", "Cathy1", "33445566", "6478888888",
                "12 Apple Creek");
        LL.addUser(u1.getName(), u1.getPassword(), u1.getPhone(), u1.getAddress());
        LL.addUser(u2.getName(), u2.getPassword(), u2.getPhone(), u2.getAddress());
        EL = new EmployeeList();
        e1 = new FullTimeEmployee("Human Resource", "Manager", "6000", 4, u1.getID());
        e2 = new PartTimeEmployee("Technology", "25", 8, u2.getID());
        EL.addEmployee(e1.getDepartment(),e1.getWage(),e1.getPosition(), Integer.toString(e1.getLevel()), "F", "Lily");
        EL.addEmployee(e2.getDepartment(),e2.getWage(),"N", Integer.toString(e2.getLevel()), "P", "Cathy");
        PM = new PersonalManager();
        GL = new GroupList();

        // Todo: The following commented code will raise error
        //GL.addGroup(u1.getID(), "207");
        //WL.addWork("Group Project", "207", "Human Resource", "Get it done", 9);

        e1.setTotalVacationWithSalary("2");
        e1.setVacationUsed("1");
    }

    @Test
    public void testEmployeeInfo(){
        ArrayList<String> info1 = new ArrayList<>(Arrays.asList(u2.getName(), u2.getID(), u2.getPassword(), u2.getPhone(),
                u2.getAddress(), e2.getDepartment()));
        assertEquals(info1, PM.employeeInfo(LL, EL, u2.getID()));

        // Todo: TotalVacationWithSalary and VacationUsed did not change
        ArrayList<String> info2 = new ArrayList<>( Arrays.asList(u1.getName(), u1.getID(), u1.getPassword(), u1.getPhone(),
                u1.getAddress(), e1.getDepartment(), e1.getPosition(), e1.getState(), e1.getTotalVacationWithSalary(),
                e1.getVacationUsed()));
        assertEquals(info2, PM.employeeInfo(LL, EL, u1.getID()));
    }


    @Test
    public void testCheckTotalSalary(){
        assertEquals("0", PM.checkTotalSalary(EL, u1.getID(), GL, WL));
        assertEquals("6200", PM.checkTotalSalary(EL, u2.getID(), GL, WL));
        // I think it needs modification, since this test cannot effectively test what the method did (KPI part)
    }

    @Test
    public void testCheckMinimumWage(){
        assertEquals("6000", PM.checkMinimumWage(EL, u1.getID()));
        assertEquals("25", PM.checkMinimumWage(EL, u2.getID()));
    }

    @Test
    public void testCheckVacationBonus(){
        // Todo: TotalVacationWithSalary and VacationUsed did not change
        assertEquals("200", PM.checkVacationBonus(EL, "Lily0"));
        assertEquals("0", PM.checkVacationBonus(EL, "Cathy1"));
    }

    @Test
    public void testCheckKPIBonus(){
        assertEquals("0", PM.checkKPIBonus(EL, "Lily0", GL, WL));
        assertEquals("0", PM.checkKPIBonus(EL, "Cathy1", GL, WL));
        // same issue, require further modification
    }

    @Test
    public void testVacationInfo(){
        // Todo: TotalVacationWithSalary and VacationUsed did not change
        ArrayList<String> info1 = new ArrayList<>(Arrays.asList("1", "2"));
        assertEquals(info1, PM.vacationInfo(EL, "Lily0"));
        ArrayList<String> info2 = new ArrayList<>();
        assertEquals(info2, PM.vacationInfo(EL, "Cathy1"));
    }
}
