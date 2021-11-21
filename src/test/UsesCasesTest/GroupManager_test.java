package test.UsesCasesTest;

import main.Entity.*;
import main.UsesCases.GroupManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GroupManager_test {
    GroupManager groupmanager;
    Group group;
    Userable leader, member1, member2, member3;
    Workable work;


    @Before
    public void Setup(){
        group = new Group("001", "998");
        leader = new User("andy1234", "a12345", "Andy", "412345678",
                "123 Mississauga Road", "001");

        group.setLeaderId("001");

        member1 = new User("lily001", "l99999", "Lily", "498765432",
                "234 Mississauga Road", "002");
        member2 = new User("luke223", "luke123", "Luke", "466666666",
                "567 Mississauga Road", "003");
        member3 = new User("cathy223", "cathy0001", "Cathy", "411111111",
                "111 Mississauga Road", "004");
        work = new Work("First Work", "998", "IT", "None", 2);

        groupmanager = new GroupManager();
    }


    @Test
    public void testMembers(){
        // Test addMembers
        List<String> members = List.of(new String[]{member1.getID(), member2.getID()});
        groupmanager.addMembers(members, group);
        assertTrue(group.getMembers().contains(member1.getID()));
        assertTrue(group.getMembers().contains(member2.getID()));

        List<String> new_members = List.of(new String[]{member3.getID()});
        groupmanager.addMembers(new_members, group);
        assertTrue(group.getMembers().contains(member1.getID()));
        assertTrue(group.getMembers().contains(member2.getID()));
        assertTrue(group.getMembers().contains(member3.getID()));

        // Test resetMember
        groupmanager.resetMember(group);
        // Todo: The following will raise Error.
        assertEquals(group.getMembers().size(), 0);

    }

    @Test
    public void testChangeLeaders(){
        groupmanager.changeLeader(group, member1.getID());
        assertEquals(group.getLeaderId(),  member1.getID());
        assertFalse(group.getMembers().contains(member1.getID()));
    }

    @Test
    public void testResetGroup(){
        groupmanager.resetGroup(group);
        assertEquals(group.getLeaderId(),  "");
        assertEquals(group.getMembers().size(), 0);
    }

}
