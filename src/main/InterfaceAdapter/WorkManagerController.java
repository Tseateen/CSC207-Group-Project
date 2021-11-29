package main.InterfaceAdapter;

import main.UsesCases.*;

import java.util.ArrayList;

public class WorkManagerController {

    private final IWorkManager workManager;
    private final IGroupManager groupManager;

    public WorkManagerController(){
        this.workManager = new WorkManager();
        this.groupManager = new GroupManager();
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

    // === Usage: FacadeSys Worker Case (i),(ii) ====
    public boolean checkWorkExist(String workID, IWorkList workList) {
        return this.workManager.workExist(workID, workList);
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
        for (String i : this.workManager.workOfLed(userID, groupList, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public String showAllLowerWork(String userID, IWorkList workList) {
        StringBuilder result = new StringBuilder();
        for (String i : this.workManager.workOfLowerLevel(userID, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    // === Usage: FacadeSys Worker Case (iii) ====
    public void assignLeaderToWork(String workID, String
            leaderID, IGroupList groupList) {
        // We may need to use try in GM's assignLeader and return boolean
        this.groupManager.assignLeader(workID,
                leaderID, groupList);
    }
    // ==================================================

    public boolean distributeWork(String workID, String memberID, IGroupList groupList) {
        return this.groupManager.Distributor(workID, memberID, groupList);
    }

    public void createWork(ArrayList<String> info, IWorkList workList) {
        workList.addWork(info.get(0), info.get(1), info.get(2), info.get(3), Integer.parseInt(info.get(4)));
    }

    // === Usage: FacadeSys Worker Case (iii) ====
    public String checkWorkLevel(String workID, IWorkList workList) {
        return this.workManager.checkWorkLevel(workID,workList);
    }
    // ==================================================

    public void removeFromAll(String userID, IGroupList groupList) {
        this.groupManager.deleteEmployee(userID, groupList);
    }

    public boolean removeOneFromGroup(String userID, String workID, IGroupList groupList) {
        return this.groupManager.deleteMember(userID,workID,groupList);
    }

}
