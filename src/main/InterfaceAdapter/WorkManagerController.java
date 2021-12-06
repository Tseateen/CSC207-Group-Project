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
    /**
     * Get all the Work information with the Work ID.
     *
     * @param workID the ID of the Work.
     *
     * @return a list of strings with the Work information, including the name, ID, state, level, description,
     * start time, end time of the Work.
     */
    public String showWorkDetail(String workID, IWorkList workList) {
        StringBuilder result = new StringBuilder();
        for (String i : this.workManager.showWorkDetail(workID, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }


    // ==========================================
    /**
     * Extend a work (add extra works to the current work).
     *
     * @param workID the work's id which is going to be extended.
     * @param days the days to be extended of work.
     *
     */
    public void extendWork(String workID, String days, IWorkList workList){
        this.workManager.extendWork(workID,workList, days);
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
            result.append(i);
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

    // ==================================================
    public void changeState(String workID, String newStatus, IWorkList workList){
        this.workManager.changeState(workID, workList, newStatus);
    }

    public void updateStateAll(IWorkList workList) {
        this.workManager.autoChangeState(workList);
    }

}
