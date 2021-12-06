package test.UsesCasesTest;


import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PersonalManager_test {

    LoginList loginList;
    EmployeeList EL;
    PersonalManager PM;
    UserAble u1, u2;
    FullTimeEmployee e1;
    PartTimeEmployee e2;
    GroupList GL;
    WorkList WL;
    WorkManager WM;
    GroupManager GM;

    @Before
    public void Setup(){
        loginList = new LoginList();
        u1 = new User("Lily", "Lily0", "l99999999", "498765432",
                "234 Mississauga Road");
        u2 = new User("Cathy", "Cathy1", "33445566", "6478888888",
                "12 Apple Creek");
        loginList.addUser(u1.getName(), u1.getPassword(), u1.getPhone(), u1.getAddress());
        loginList.addUser(u2.getName(), u2.getPassword(), u2.getPhone(), u2.getAddress());
        EL = new EmployeeList();
        e1 = new FullTimeEmployee("Human Resource", "Manager", "6000", 4, u1.getID());
        e2 = new PartTimeEmployee("Technology", "25", 8, u2.getID());
        e1.setTotalVacationWithSalary("2");
        e1.setVacationUsed("1");
        EL.addEmployee(e1.getDepartment(),e1.getWage(),e1.getPosition(), Integer.toString(e1.getLevel()), "F", "Lily");
        EL.addEmployee(e2.getDepartment(),e2.getWage(),"N", Integer.toString(e2.getLevel()), "P", "Cathy");
        PM = new PersonalManager();
        PM.setTotalVacationWithSalary(u1.getID(),"2", EL);
        PM.setVacationUsed(u1.getID(),"1", EL);
        WL = new WorkList();
        GL = new GroupList();
        WM = new WorkManager();
        GM = new GroupManager();

        GL.addGroup(u1.getID(), "207");
        WL.addWork("Group Project", "207", "Human Resource", "Get it done", 9, "2022-01-01");
        GM.assignLeader("207", u1.getID(), GL);

        for (Workable work: WL){
            if (work.getID().equals("207")){
                work.setState("Finished");
            }
        }

        WM.changeWorkInfo("207", WL, "SIGN", "1");
    }

    @Test
    public void testEmployeeInfo(){
        ArrayList<String> info1 = new ArrayList<>(Arrays.asList(u2.getName(), u2.getID(), u2.getPassword(), u2.getPhone(),
                u2.getAddress(), e2.getDepartment()));
        assertEquals(info1, PM.employeeInfo(loginList, EL, u2.getID()));

        ArrayList<String> info2 = new ArrayList<>( Arrays.asList(u1.getName(), u1.getID(), u1.getPassword(), u1.getPhone(),
                u1.getAddress(), e1.getDepartment(), e1.getPosition(), e1.getState(), e1.getTotalVacationWithSalary(),
                e1.getVacationUsed()));
        assertEquals(info2, PM.employeeInfo(loginList, EL, u1.getID()));
    }


    @Test
    public void testCheckTotalSalary(){
        //Todo: Need to test about PartTimeEmployee.
        assertEquals("6652", PM.checkTotalSalary(EL, u1.getID(), GL, WL));
        assertEquals("0", PM.checkTotalSalary(EL, u2.getID(), GL, WL));
        // I think it needs modification, since this test cannot effectively test what the method did (KPI part)
    }

    @Test
    public void testCheckMinimumWage(){
        assertEquals("6000", PM.checkMinimumWage(EL, u1.getID()));
        assertEquals("25", PM.checkMinimumWage(EL, u2.getID()));
    }

    @Test
    public void testCheckVacationBonus(){
        assertEquals("200", PM.checkVacationBonus(EL, u1.getID()));
        assertEquals("0", PM.checkVacationBonus(EL, u2.getID()));
    }

    @Test
    public void testCheckKPIBonus(){
        //Todo: Need to test more.
        assertEquals("452", PM.checkKPIBonus(EL, u1.getID(), GL, WL));
        assertEquals("0", PM.checkKPIBonus(EL, u2.getID(), GL, WL));
        // same issue, require further modification
    }

    @Test
    public void testVacationInfo(){
        ArrayList<String> info1 = new ArrayList<>(Arrays.asList("1", "2"));
        assertEquals(info1, PM.vacationInfo(EL, u1.getID()));
        ArrayList<String> info2 = new ArrayList<>();
        assertEquals(info2, PM.vacationInfo(EL, u2.getID()));
    }

    @Test
    public void testGetWorkingHourFromPartTimeEmployee(){
        assertEquals("0", PM.getWorkingHourFromPartTimeEmployee(EL, u2.getID()));
        PM.setWorkingHour(u2.getID(), "6", EL);
        assertEquals("6", PM.getWorkingHourFromPartTimeEmployee(EL, u2.getID()));
    }

    @Test
    public void testSetName(){
        PM.setName("Luke", loginList, u1.getID());

        for (UserAble user: loginList){
            if (user.getID().equals(u1.getID())){
                assertEquals("Luke", user.getName());
                assertEquals("Lily0", user.getID());
            }
        }
    }

    @Test
    public void testSetPassword(){
        PM.setPassword("lily12345678987", loginList, u1.getID());

        for (UserAble user: loginList){
            if (user.getID().equals(u1.getID())){
                assertEquals("lily12345678987", user.getPassword());
            }
        }
    }

    @Test
    public void testSetAddress(){
        PM.setAddress("314 Markham Road", loginList, u1.getID());

        for (UserAble user: loginList){
            if (user.getID().equals(u1.getID())){
                assertEquals("314 Markham Road", user.getAddress());
            }
        }
    }

    @Test
    public void testSetPhone(){
        PM.setPhone("4141414141", loginList, u1.getID());

        for (UserAble user: loginList){
            if (user.getID().equals(u1.getID())){
                assertEquals("4141414141", user.getPhone());
            }
        }

    }

    @Test
    public void testGetUserLevel(){
        assertEquals("4",PM.getUserLevel(u1.getID(), EL));
        assertEquals("8",PM.getUserLevel(u2.getID(), EL));
    }
}
