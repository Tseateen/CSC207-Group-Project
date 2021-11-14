package test.UsesCasesTest;

import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GroupList_test {
    GroupList GL;
    Userable user1;
    Userable[] memberList;

    @Before
    public void Setup() {
        GL = new GroupList();
        Userable user1 = new User("111", "123456", "Lily", "4203456789",
                "10 King St.", "8888");

    }


    @Test(timeout = 100)
    public void testAddGroup() {
        GL.addGroup(user1, "2222");
        for (Group eachGroup : GL) {
            if (eachGroup.getWorkID().equals("2222")) {
                List<Userable> membersList = eachGroup.getMembers();
                assertEquals(eachGroup.getLeader(), membersList.get(0));
            }
        }
    }


    @Test(timeout = 100)
    public void testGetSize(){
        assertEquals(GL.getSize(), 1);
    }


    @Test(timeout = 100)
    public void testDeleteUser() {
        GL.deleteUser("2222");
        for (Group e : GL) {
            assertNotEquals(e.getWorkID(), "2222");
        }
    }

}

