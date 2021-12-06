package test.UsesCasesTest;


import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class WorkManager_test {
    LoginList LL;
    EmployeeList EL;
    UserAble u1, u2;
    Group group;
    FullTimeEmployee e1, e2;
    GroupList GL;
    WorkList WL;
    GroupManager GM;
    WorkManager WM;

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
        e2 = new FullTimeEmployee("Human Resource", "Manager", "4500", 8, u2.getID());
        EL.addEmployee(e1.getDepartment(),e1.getWage(),e1.getPosition(), Integer.toString(e1.getLevel()), "F", "Lily");
        EL.addEmployee(e2.getDepartment(),e2.getWage(),e2.getPosition(), Integer.toString(e2.getLevel()), "F", "Cathy");

        group = new Group(u1.getID(), "207");
        WL = new WorkList();
        GL = new GroupList();
        WM = new WorkManager();
        GM = new GroupManager();
        GL.addGroup(u1.getID(), "207");
        List<String> members = new ArrayList<>();
        members.add(u2.getID());
        for (Group group: GL){
            if (group.getWorkID().equals("207")){
                GM.addMembers(members, group);
            }
        }
        WL.addWork("Group Project", "207", "Human Resource", "Get it done", 8, "1523456789013");
        //endTime: 2018-04-11
    }

    @Test
    public void testWorkOfMember(){

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Group Project 207 Pending\n");
        assertEquals(expected, WM.workOfMember(u2.getID(), GL, WL));
    }

    @Test
    public void testShowWorkDetail(){
        ArrayList<String> expected = new ArrayList<>();

        expected.add("Group Project");
        expected.add("207");
        expected.add("Pending");
        expected.add("8");
        expected.add(null);
        expected.add("Get it done");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String s = String.valueOf(now.getTime());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(s));
        expected.add(sf.format(date));

        expected.add("2018-04-11");

        assertEquals(expected, WM.showWorkDetail("207", WL));

    }

    @Test
    public void testExtendWork(){

        WM.extendWork("207", WL,  "3");
        String s2 = "";
        for (Workable work: WL){
            if (work.getID().equals("207")){
                s2 = work.getEndTime();
            }
        }
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = new Date(Long.parseLong(s2));
        String s = sf2.format(date2);

        assertEquals("2018-04-14", s);

    }

    @Test
    public void testChangeState(){

        WM.changeState("207", WL, "Finished");

        for (Workable work: WL){
            if (work.getID().equals("207")){
                assertEquals("Finished", work.getState());
            }
        }
    }

    @Test
    public void testTheWorkLeadByThisUser(){
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Group Project 207 Pending\n");
        assertEquals(expected, WM.TheWorkLeadByThisUser(u1.getID(), GL, WL));
    }

    @Test
    public void testWorkOfLowerLevel(){
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Group Project 207 Pending\n");
        assertEquals(expected, WM.workOfLowerLevel("7", WL));
        ArrayList<String> expected2 = new ArrayList<>();
        assertEquals(expected2, WM.workOfLowerLevel("9", WL));

    }

    @Test
    public void testAutoChangeState(){
        WM.autoChangeState(WL);
        for (Workable work: WL){
            if (work.getID().equals("207")){
                assertEquals("Expired", work.getState());
                work.setEndTime("2023456789013");
                WM.autoChangeState(WL);
                assertEquals("Pending", work.getState());
                work.setSign("1");
                WM.autoChangeState(WL);
                assertEquals("InProgress", work.getState());
                work.setState("Finished");
                WM.autoChangeState(WL);
                assertEquals("Finished", work.getState());

            }
        }
    }
}
