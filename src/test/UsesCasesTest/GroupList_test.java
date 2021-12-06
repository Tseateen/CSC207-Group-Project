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
    UserAble user1;
    ArrayList<String> memberList = new ArrayList<>();

    @Before
    public void Setup() {
        GL = new GroupList();
        user1 = new User("Andy", "Andy1", "a1234567", "412345678",
                "123 Mississauga Road");

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

}

