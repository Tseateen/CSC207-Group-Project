package test.InterfaceAdapterTest;

import main.InterfaceAdapter.*;
import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import java.util.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class GroupManagerController_test {

    GroupList groupList;
    GroupManager groupManager;
    GroupManagerController groupManagerController;

    @Before
    public void Setup(){
        groupList = new GroupList();
        groupManager = new GroupManager();
        groupManagerController = new GroupManagerController(groupManager);
        groupManager.assignLeader("Work0", "Luke0", groupList);

        groupList.addGroup("Kenny1", "Work1");
        groupManager.Distributor("Work0", "Kyle3", groupList);
        groupManager.Distributor("Work0", "Cathy4", groupList);
        groupManager.Distributor("Work0", "YiDe5", groupList);
        groupManager.Distributor("Work0", "Lily6", groupList);
        groupManager.Distributor("Work0", "Andy7", groupList);
        groupManager.Distributor("Work0", "Benson8", groupList);
    }

    @Test
    public void testDistributeWork(){
        assertTrue(groupManagerController.distributeWork("Work0", "Pat2", groupList));
    }

    @Test
    public void testRemoveOneFromGroup(){
        assertTrue(groupManagerController.removeOneFromGroup("Benson8", "Work0", groupList));
    }

    @Test
    public void testRemoveOneFromGroupFalse(){
        assertFalse(groupManagerController.removeOneFromGroup("John", "Work0", groupList));
    }
    @Test
    public void testGroupExist(){
        assertTrue(groupManagerController.groupExist("Work0", groupList));
    }

    @Test
    public void testAllMember(){
        List<String> members = new ArrayList<>();
        members.add("Kyle3");
        members.add("Cathy4");
        members.add("YiDe5");
        members.add("Lily6");
        members.add("Andy7");
        members.add("Benson8");
        assertEquals(members, groupManagerController.allMember("Work0", groupList));
    }
}
