package test.EntityTest;

import main.Entity.Group;
import main.Entity.User;

import main.Entity.Userable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Group_test {
    Userable p1;
    Userable p2;
    Userable p3;
    Group G1;


    @Before
    public void setUp() {
        Userable p1 = new User("andy1234", "a12345", "Andy", "412345678",
                "Mississauga Road", "001");
        Userable p2 = new User("luke002", "lu2345", "Luke", "401234567",
                "223 Allen Road", "002");
        Userable p3 = new User("kyle002", "k23456", "Kyle", "498765432",
                "300 Yonge Street", "003");
        Userable[] m1 = {p2, p3};
        // Group constructor
        G1 = new Group(p1.getID(), "w123");
    }


    @Test(timeout = 100)
    public void testLeader() {
        assertEquals(G1.getLeaderId(), p1);
        G1.setLeaderId(p2.getID());
        assertEquals(G1.getLeaderId(), p2);
        Userable p4 = new User("lily123", "li23456", "Lily", "411111111",
                "123 Bloor Street", "004");
        G1.setLeaderId(p4.getID());
        assertEquals(G1.getLeaderId(), p4.getID());
    }

    @Test(timeout = 100)
    public void testMembers1() {
        assertTrue(G1.getMembers().contains(p2.getID()));
        Userable p4 = new User("lily123", "li23456", "Lily", "411111111",
                "123 Bloor Street", "004");
        G1.setLeaderId(p4.getID());
        assertTrue(G1.getMembers().contains(p1.getID()));
    }

    @Test(timeout = 100)
    public void testMembers2() {
        Userable p4 = new User("lily123", "li23456", "Lily", "411111111",
                "123 Bloor Street", "004");
        G1.addMember(p4.getID());
        assertTrue(G1.getMembers().contains(p4.getID()));
        G1.deleteMember(p3.getID());
        assertFalse(G1.getMembers().contains(p3.getID()));
        G1.deleteMember(p3.getID());
        assertTrue(!G1.getMembers().contains(p2.getID())&&!G1.getMembers().contains(p4.getID()));
    }

    @Test(timeout = 100)
    public void testProject() {
        assertEquals(G1.getProject(), "w123");
        G1.setProject("w124");
        assertEquals(G1.getProject(), "w124");
    }
}
