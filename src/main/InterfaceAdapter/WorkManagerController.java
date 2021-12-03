package main.InterfaceAdapter;

import main.UsesCases.*;

public class WorkManagerController {

    // === Instance Variables ===
    private final IWorkManager workManager;
    private final IGroupManager groupManager;


    /**
     * Construct the WorkManagerController.
     */
    public WorkManagerController(IWorkManager workManager, IGroupManager groupManager){
        this.workManager = workManager;
        this.groupManager = groupManager;
    }


    // === Usage: FacadeSys Worker Case (i) ====
    public String showAllWorkNeedToDo(String userID, IGroupList groupList, IWorkList workList) {
        StringBuilder result = new StringBuilder();
        for (String i : this.workManager.workOfMember(userID, groupList, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }
    // ==========================================

    // === Usage: FacadeSys Worker Case (i) ====
    public String showWorkDetail(String workID, IWorkList workList) {
        StringBuilder result = new StringBuilder();
        for (String i : this.workManager.showWorkDetail(workID, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }
    // ==========================================

    public void extendWork(String workID, String days, IWorkList workList){
        this.workManager.extendWork(workID,workList, days);
    }


    public void changeState(String workID, String newStatus, IWorkList workList){
        this.workManager.changeState(workID,workList, newStatus );
    }

    public String showAllWorkLead(String userID, IGroupList groupList, IWorkList workList){
        StringBuilder result = new StringBuilder();
        for (String i : this.workManager.TheWorkLeadByThisUser(userID, groupList, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public String showAllLowerWork(String level, IWorkList workList) {
        StringBuilder result = new StringBuilder();
        for (String i : this.workManager.workOfLowerLevel(level, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    // === Usage: FacadeSys Worker Case (iii) ====
    public void assignLeaderToWork(String workID, String
            leaderID, IGroupList groupList) {
            this.groupManager.assignLeader(workID,
                    leaderID, groupList);

    }
    // ==================================================

    public boolean removeOneFromGroup(String userID, String workID, IGroupList groupList) {
        return this.groupManager.deleteMember(userID,workID,groupList);
    }

}
