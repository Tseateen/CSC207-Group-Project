package test.EntityTest;

import main.Entity.Group;
import main.Entity.User;

import main.Entity.UserAble;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Group_test {
    UserAble p1;
    UserAble p2;
    UserAble p3;
    Group G1;


    @Before
    public void setUp() {
        p1 = new User("Andy", "Andy1", "a123456", "412345678",
                "Mississauga Road");
        p2 = new User("Luke", "Luke2", "l123456", "401234567",
                "223 Allen Road");
        p3 = new User("Kyle", "Kyle3", "k123456", "498765432",
                "300 Yonge Street");

        G1 = new Group(p1.getID(), "w123");
        G1.addMember(p2.getID());
        G1.addMember(p3.getID());
    }


    @Test(timeout = 100)
    public void testLeader() {
        assertEquals(G1.getLeaderID(), p1.getID());
        String a = p2.getID();
        G1.setLeaderId(a);
        assertEquals(G1.getLeaderID(), p2.getID());
        UserAble p4 = new User("Lily", "Lily4", "li23456", "411111111",
                "123 Bloor Street");
        G1.setLeaderId(p4.getID());
        assertEquals(G1.getLeaderID(), p4.getID());
    }

    @Test(timeout = 100)
    public void testMembers1() {
        assertTrue(G1.getMembers().contains(p2.getID()));
        UserAble p4 = new User("Lily", "Lily4", "li23456", "411111111",
                "123 Bloor Street");
        G1.addMember(p4.getID());
        assertTrue(G1.getMembers().contains(p4.getID()));
    }

    @Test(timeout = 100)
    public void testMembers2() {
        UserAble p4 = new User("Lily", "Lily4", "li23456", "411111111",
                "123 Bloor Street");
        G1.addMember(p4.getID());
        assertTrue(G1.getMembers().contains(p4.getID()));
        G1.deleteMember(p3.getID());
        assertFalse(G1.getMembers().contains(p3.getID()));
        G1.deleteMember(p3.getID());
        assertTrue(G1.getMembers().contains(p2.getID()));
    }

    @Test(timeout = 100)
    public void testProject() {
        assertEquals(G1.getProject(), "w123");
        G1.setProject("w124");
        assertEquals(G1.getProject(), "w124");
    }
}
