package test.UsesCasesTest;

import main.Entity.*;
import main.UsesCases.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkFacade_test {
    WorkFacade workFacade;
    WorkList workList;
    GroupList groupList;
    Group g1, g2, g3;
    Workable w1, w2, w3, w4;
    Userable member1, member2, member3;

    @Before
    public void Setup(){

        w1 = new Work("First Work", "456", "IT", "None", 2);
        w2 = new Work("Second Work", "457", "HR", "None", 3);
        w3 = new Work("Third Work", "458", "IT", "None", 4);
        w4 = new Work("Fourth Work", "459", "IT", "None", 5);

        workList = new WorkList();

        workList.addWork(w1.getName(), w1.getID(), w1.getDepartment(), w1.getRequirement(), w1.getLevel());
        workList.addWork(w2.getName(), w2.getID(), w2.getDepartment(), w2.getRequirement(), w2.getLevel());
        workList.addWork(w3.getName(), w3.getID(), w3.getDepartment(), w3.getRequirement(), w3.getLevel());

        groupList = new GroupList();

        member1 = new User("lily001", "l99999", "Lily", "498765432",
                "234 Mississauga Road", "001");
        member2 = new User("luke223", "luke123", "Luke", "466666666",
                "567 Mississauga Road", "002");
        member3 = new User("cathy223", "cathy0001", "Cathy", "411111111",
                "111 Mississauga Road", "003");

        g1 = new Group(member1.getID(), w1.getID());
        g2 = new Group(member2.getID(), w2.getID());
        g3 = new Group(member3.getID(), w3.getID());

        g1.addMember(member2.getID());
        g2.addMember(member1.getID());

        groupList.addGroup(member1.getID(), g1.getWorkID());
        groupList.addGroup(member2.getID(), g2.getWorkID());
        groupList.addGroup(member3.getID(), g3.getWorkID());


        workFacade = new WorkFacade(workList, groupList);
    }


    @Test
    public void testWorkExist(){
        assertTrue(workFacade.workExist(w1.getID()));
        assertTrue(workFacade.workExist(w2.getID()));
        assertFalse(workFacade.workExist(w4.getID()));
    }

    @Test
    public void testWorkOfLed(){
        assertTrue(workFacade.workOfLed(member1.getID()).contains(workFacade.showWorkDetail(g1.getWorkID()).get(0) + " " +
                workFacade.showWorkDetail(g1.getWorkID()).get(1) + " "
                + workFacade.showWorkDetail(g1.getWorkID()).get(2) + "\n"));

        assertFalse(workFacade.workOfLed(member1.getID()).contains(workFacade.showWorkDetail(g2.getWorkID()).get(0) + " " +
                workFacade.showWorkDetail(g2.getWorkID()).get(1) + " "
                + workFacade.showWorkDetail(g2.getWorkID()).get(2) + "\n"));
    }

    @Test
    public void testWorkOfMine(){
        assertFalse(workFacade.workOfMine(member1.getID()).contains(workFacade.showWorkDetail(g1.getWorkID()).get(0) + " " +
                workFacade.showWorkDetail(g1.getWorkID()).get(1) + " "
                + workFacade.showWorkDetail(g1.getWorkID()).get(2) + "\n"));

        assertTrue(workFacade.workOfMine(member1.getID()).contains(workFacade.showWorkDetail(g2.getWorkID()).get(0) + " " +
                workFacade.showWorkDetail(g2.getWorkID()).get(1) + " "
                + workFacade.showWorkDetail(g2.getWorkID()).get(2) + "\n")); // Todo: This happens to be False. Please check to see what's wrong.
    }

    @Test
    public void testWorkOfLowerLevel(){
        assertTrue(workFacade.workOfLowerLevel("3").contains(workFacade.showWorkDetail(g3.getWorkID()).get(0) + " " +
                workFacade.showWorkDetail(g3.getWorkID()).get(1) + " "
                + workFacade.showWorkDetail(g3.getWorkID()).get(2) + "\n"));
        assertFalse(workFacade.workOfLowerLevel("3").contains(workFacade.showWorkDetail(g2.getWorkID()).get(0) + " " +
                workFacade.showWorkDetail(g2.getWorkID()).get(1) + " "
                + workFacade.showWorkDetail(g2.getWorkID()).get(2) + "\n"));
    }

    @Test
    public void testAssignLeader(){
        workFacade.assignLeader(w1.getID(), member2.getID());
        assertTrue(workFacade.workOfLed(member2.getID()).contains(workFacade.showWorkDetail(g1.getWorkID()).get(0) + " " +
                workFacade.showWorkDetail(g1.getWorkID()).get(1) + " "
                + workFacade.showWorkDetail(g1.getWorkID()).get(2) + "\n"));

        assertFalse(workFacade.workOfMine(member2.getID()).contains(workFacade.showWorkDetail(g1.getWorkID()).get(0) + " " +
                workFacade.showWorkDetail(g1.getWorkID()).get(1) + " "
                + workFacade.showWorkDetail(g1.getWorkID()).get(2) + "\n"));
        workFacade.assignLeader(w4.getID(), member1.getID());


        assertTrue(workFacade.workExist(w4.getID())); // Todo: This happens to be False. Please check to see what's wrong.
    }

    @Test
    public void testVerifierLeader(){
        assertTrue(workFacade.verifierLeader(member1.getID(), g1.getWorkID()));
        assertFalse(workFacade.verifierLeader(member1.getID(), g2.getWorkID()));
    }

    @Test
    public void testIsMember(){
        assertTrue(workFacade.isMember(member1.getID(), g1.getWorkID()));
        assertTrue(workFacade.isMember(member1.getID(), g2.getWorkID()));// Todo: This happens to be False. Please check to see what's wrong.
        assertFalse(workFacade.isMember(member1.getID(), g3.getWorkID()));
    }

    @Test
    public void testDistributor(){
        // Todo: This happens to be False.
        workFacade.Distributor(w1.getID(), member3.getID());
        assertTrue(workFacade.isMember(member3.getID(), g1.getWorkID()));
        assertFalse(workFacade.Distributor(w1.getID(), member2.getID()));
    }

    @Test
    public void testWorkCreator(){
        ArrayList<String> info = new ArrayList<>();
        info.add("Fifth Work");
        info.add("777");
        info.add("IT");
        info.add("None");
        info.add("7");
        workFacade.workCreator(info);
        assertTrue(workFacade.workExist("777"));
    }

    @Test
    public void testWorkLevel(){
        assertEquals(workFacade.workLevel(g1.getWorkID()), String.valueOf(w1.getLevel()));
    }

    @Test
    public void testExtendWork(){
        //Todo: Implement this test
    }


    @Test
    public void testChangeWorkInfo(){
        //Todo: This does not change anything. Please check what's wrong.
        workFacade.changeWorkInfo(w1.getID(), "DESCRIBE", "This is a changed describe");
        assertEquals(w1.getDescribe(),"This is a changed describe");
        workFacade.changeWorkInfo(w1.getID(), "REQ", "This is a changed requirement");
        assertEquals(w1.getRequirement(),"This is a changed requirement");
        workFacade.changeWorkInfo(w1.getID(), "STATE", "Working");
        assertEquals(w1.getState(),"Working");
        workFacade.changeWorkInfo(w1.getID(), "SIGN", "1");
        assertEquals(w1.getSign(),"1");
    }

    @Test
    public void testDeleteEmployee(){
        //Todo: This does not change anything. Please check what's wrong.
        workFacade.deleteEmployee(member1.getID());
        assertEquals(g1.getLeaderId(), "");
        assertFalse(g2.getMembers().contains(member1.getID()));
    }

}
