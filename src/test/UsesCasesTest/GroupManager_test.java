package test.UsesCasesTest;

import main.Entity.*;
import main.UsesCases.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GroupManager_test {
    GroupManager GM;
    Group group;
    GroupList GL;
    UserAble leader, member1, member2, member3;
    Workable work;


    @Before
    public void Setup(){
        group = new Group("", "998");
        leader = new User("Andy", "Andy0", "a12345678",
                "412345678", "123 Mississauga Road");

        member1 = new User("Lily", "Lily1", "l99999999", "498765432",
                "234 Mississauga Road");
        member2 = new User("Luke", "Luke2", "luke1234", "466666666",
                "567 Mississauga Road");
        member3 = new User("Cathy", "Cathy3", "c8888888", "411111111",
                "111 Mississauga Road");
        work = new Work("First Work", "998", "IT", "None", 2,"2023456789013");

        GM = new GroupManager();
        GL = new GroupList();
        GL.addGroup(leader.getID(), work.getID());
        GM.assignLeader("998", leader.getID(), GL);

    }


    @Test
    public void testMembers(){

        for (Group group: GL){
            if (group.getWorkID().equals("998")){
                // Test addMembers
                List<String> members = new ArrayList<>();
                members.add(member1.getID());
                members.add(member2.getID());
                members.add(member3.getID());
                GM.addMembers(members, group);
                assertTrue(group.getMembers().contains(member1.getID()));
                assertTrue(group.getMembers().contains(member2.getID()));
                assertTrue(group.getMembers().contains(member3.getID()));
                assertEquals(3, group.getMembers().size());
            }
        }
    }

    @Test
    public void testChangeLeaders() {
        for (Group group : GL) {
            if (group.getWorkID().equals("998")) {
                GM.changeLeader(group, member1.getID());

                assertEquals(group.getLeaderID(), member1.getID());
                assertFalse(group.getMembers().contains(member1.getID()));
                assertFalse(group.getMembers().contains(leader.getID()));
            }
        }
    }

    @Test
    public void testResetGroup() {
        for (Group group : GL) {
            if (group.getWorkID().equals("998")) {
                GM.resetGroup(group);
                assertEquals("", group.getLeaderID());
                assertEquals(0, group.getMembers().size());
            }
        }
    }

    @Test
    public void testAssignLeader(){
        boolean test = false;
        GM.assignLeader("998", member1.getID(), GL);
        assertEquals(1, GL.getSize());
        for (Group group: GL){
            if (group.getLeaderID().equals(member1.getID())){
                test = true;
            }
        }
        assertTrue(test);

    }

    @Test
    public void testDeleteMember(){
        for (Group group: GL){
            if (group.getWorkID().equals("998")){
                List<String> members = new ArrayList<>();
                members.add(member1.getID());
                members.add(member2.getID());
                members.add(member3.getID());
                GM.addMembers(members, group);

            }
        }

        GM.deleteMember(member1.getID(), "998", GL);

        for (Group group: GL){
            if (group.getWorkID().equals("998")){
                assertFalse(group.getMembers().contains(member1.getID()));
                assertTrue(group.getMembers().contains(member2.getID()));
                assertTrue(group.getMembers().contains(member3.getID()));
                assertEquals(2, group.getMembers().size());
            }
        }

        GM.deleteMember(leader.getID(), "998", GL);
        //Todo: This test fails. (member3 is still in the group)
        for (Group group: GL){
            if (group.getWorkID().equals("998")){
                assertEquals(1, group.getMembers().size());
            }
        }
    }

    @Test
    public void testDistributor(){
        GM.Distributor("998", member1.getID(), GL);
        GM.Distributor("998", member2.getID(), GL);

        for (Group group: GL){
            if (group.getWorkID().equals("998")){
                assertTrue(group.getMembers().contains(member1.getID()));
                assertTrue(group.getMembers().contains(member2.getID()));
                assertFalse(group.getMembers().contains(member3.getID()));
                assertEquals(2, group.getMembers().size());
            }
        }
    }

    @Test
    public void testGroupExists(){
        //Todo: This method is really confusing....
        assertFalse(GM.groupExist("998", GL));
        assertTrue(GM.groupExist("999", GL));
    }

    @Test
    public void testAllMembers(){
        GM.Distributor("998", member1.getID(), GL);
        GM.Distributor("998", member2.getID(), GL);
        List<String> members = new ArrayList<>();
        members.add(member1.getID());
        members.add(member2.getID());
        assertEquals(members, GM.allMember("998", GL));

    }
}
