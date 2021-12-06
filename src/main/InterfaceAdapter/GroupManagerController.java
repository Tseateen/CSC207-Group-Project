package main.InterfaceAdapter;

import main.UsesCases.IGroupList;
import main.UsesCases.IGroupManager;
import main.UsesCases.IWorkList;

import java.util.List;

public class GroupManagerController {


    // === Instance Variables ===
    private final IGroupManager groupManager;


    /**
     * Construct the GroupManagerController.
     */
    public GroupManagerController(IGroupManager groupManager) {
        this.groupManager = groupManager;
    }


    // === Usage: FacadeSys Work Case (vi) ===

    /**
     * One-step method to remove the employee from all related group.
     *
     * @param userID the ID of the Employee.
     * @param groupList the list of Group information.
     *
     */
    public void removeEmployeeFromAllRelatedGroup(String userID, IGroupList groupList) {
        this.groupManager.deleteEmployee(userID, groupList);
    }
    // ==================================================

    // === Usage: FacadeSys Work Case (iV) ===

    /**
     * Distribute the Work to the targeted Employee.
     *
     * @param workID the ID of the Employee.
     * @param memberID the ID of the members.
     * @param groupList the list of groups
     *
     * @return true iff the Work has been successfully distributed to the Employee.
     */
    public boolean distributeWork(String workID, String memberID, IGroupList groupList) {
        return this.groupManager.Distributor(workID, memberID, groupList);
    }
    // ==================================================
    // ==================================================

    public boolean removeOneFromGroup(String userID, String workID, IGroupList groupList) {
        return this.groupManager.deleteMember(userID,workID,groupList);
    }

    public boolean groupExist(String workID, IGroupList groupList) {
        return this.groupManager.groupExist(workID,groupList);
    }

    public List<String> allMember(String workID, IGroupList groupList) {
        return this.groupManager.allMember(workID, groupList);
    }

}
