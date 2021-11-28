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


    public void extendWork(String workID, String days, IWorkList workList){
        this.workManager.extendWork(workID,workList, days);
    }


    public void changeState(String workID, String newStatus, IWorkList workList){
        this.workManager.changeState(workID,workList, newStatus );
    }

    public boolean checkWorkExist(String workID, IWorkList workList) {
        return this.workManager.workExist(workID, workList);
    }

    public String showAllWorkNeedToDo(String userID, IGroupList groupList, IWorkList workList) {
        StringBuilder result = new StringBuilder("");
        for (String i : this.workManager.workOfMember(userID, groupList, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public String showWorkDetail(String workID, IWorkList workList) {
        StringBuilder result = new StringBuilder("");
        for (String i : this.workManager.showWorkDetail(workID, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public String showAllWorkLed(String userID, IGroupList groupList, IWorkList workList){
        StringBuilder result = new StringBuilder("");
        for (String i : this.workManager.workOfLed(userID, groupList, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public String showAllLowerWork(String userID, IWorkList workList) {
        StringBuilder result = new StringBuilder("");
        for (String i : this.workManager.workOfLowerLevel(userID, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public void assignLeaderToWork(String workID, String
            leaderID, IGroupList groupList) {
        // We may need to use try in GM's assignLeader and return boolen
        this.groupManager.assignLeader(workID,
                leaderID, groupList);
    }

    public boolean distributeWork(String workID, String memberID, IGroupList groupList) {
        return this.groupManager.Distributor(workID, memberID, groupList);
    }

    public void createWork(ArrayList<String> info, IWorkList workList) {
        workList.addWork(info.get(0), info.get(1), info.get(2), info.get(3), Integer.parseInt(info.get(4)));
    }

    public String workLevel(String workID, IWorkList workList) {
        return this.workManager.workLevel(workID,workList);
    }

}
