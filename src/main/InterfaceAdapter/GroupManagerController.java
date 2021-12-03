package main.InterfaceAdapter;

import main.UsesCases.IGroupList;
import main.UsesCases.IGroupManager;

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
    public void removeEmployeeFromAllRelatedGroup(String userID, IGroupList groupList) {
        this.groupManager.deleteEmployee(userID, groupList);
    }
    // ==================================================

    // === Usage: FacadeSys Work Case (iV) ===
    public boolean distributeWork(String workID, String memberID, IGroupList groupList) {
        return this.groupManager.Distributor(workID, memberID, groupList);
    }
    // ==================================================
}
