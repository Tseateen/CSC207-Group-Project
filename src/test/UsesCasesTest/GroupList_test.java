package test.UsesCasesTest;


import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GroupList_test {
    GroupList GL;
    Userable user1;
    ArrayList<String> memberList = new ArrayList<>();

    @Before
    public void Setup() {
        GL = new GroupList();
        user1 = new User("111", "123456", "Lily", "4203456789",
                "10 King St.", "8888");
        memberList.add(user1.getID());
    }


    @Test(timeout = 100)
    public void testAddGroup() {
        GL.addGroup(user1.getID(), "2222");
        for (Group eachGroup : GL) {
            if (eachGroup.getWorkID().equals("2222")) {
                eachGroup.addMember("111");
            }
        }
        for (Group eachGroup : GL) {
            if (eachGroup.getWorkID().equals("2222")) {
                List<String> membersList = eachGroup.getMembers();
                assertEquals("111", membersList.get(0));
            }
        }

    }


    @Test(timeout = 100)
    public void testGetSize(){
        assertEquals(GL.getSize(), 0);
    }


    @Test(timeout = 100)
    public void testDeleteUser() {
        GL.deleteUser("2222");
        for (Group e : GL) {
            assertNotEquals(e.getWorkID(), "2222");
        }
    }

}

