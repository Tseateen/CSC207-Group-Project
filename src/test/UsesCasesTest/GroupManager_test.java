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
        leader = new User("Andy", "Andy0", "a12345678",
                "412345678", "123 Mississauga Road");

        group.setLeaderId("001");

        member1 = new User("Lily", "Lily1", "l99999999", "498765432",
                "234 Mississauga Road");
        member2 = new User("Luke", "Luke2", "luke1234", "466666666",
                "567 Mississauga Road");
        member3 = new User("Cathy", "Cath3", "c8888888", "411111111",
                "111 Mississauga Road");
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
        assertEquals(group.getMembers().size(), 3);


        // Test resetMember
        // Todo: The following line will raise Error.
        groupmanager.resetMember(group);
        assertEquals(group.getMembers().size(), 0);

    }

    @Test
    public void testChangeLeaders(){
        groupmanager.changeLeader(group, member1.getID());
        assertEquals(group.getLeaderID(),  member1.getID());
        assertFalse(group.getMembers().contains(member1.getID()));
    }

    @Test
    public void testResetGroup(){
        groupmanager.resetGroup(group);
        assertEquals(group.getLeaderID(),  "");
        assertEquals(group.getMembers().size(), 0);
    }

}
