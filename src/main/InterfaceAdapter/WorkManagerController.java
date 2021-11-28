package main.InterfaceAdapter;

import main.UsesCases.*;

import java.util.ArrayList;

public class WorkManagerController {

    private final IWorkList workList;
    private final IWorkManager workManager;
    private final IGroupList groupList;
    private final IGroupManager groupManager;

    public WorkManagerController(IWorkList workList,IWorkManager workManager,
                                 IGroupList groupList, IGroupManager groupManager){
        this.workList = workList;
        this.workManager = workManager;
        this.groupList = groupList;
        this.groupManager = groupManager;
    }


    public void extendWork(String workID, String days){
        this.workManager.extendWork(workID,this.workList, days);
    }


    public void changeState(String workID, String newStatus){
        this.workManager.changeState(workID,this.workList, newStatus );
    }

    public boolean checkWorkExist(String work_id) {
        return this.workManager.workExist(work_id, this.workList);
    }

    public String showAllWorkNeedToDo(String userID) {
        StringBuilder result = new StringBuilder("");
        for (String i : this.workManager.workOfMember(userID, this.groupList, this.workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public String showWorkDetail(String work_id) {
        StringBuilder result = new StringBuilder("");
        for (String i : this.workManager.showWorkDetail(work_id, this.workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public String showAllWorkLed(String userID){
        StringBuilder result = new StringBuilder("");
        for (String i : this.workManager.workOfLed(userID, this.groupList, this.workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public String showAllLowerWork(String userID) {
        StringBuilder result = new StringBuilder("");
        for (String i : this.workManager.workOfLowerLevel(userID, workList)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public void assignLeaderToWork(String work_id, String leader_id) {
        // We may need to use try in GM's assignLeader and return boolen
        this.groupManager.assignLeader(work_id, leader_id, this.groupList);
    }

    public boolean distributeWork(String work_id, String memberID) {
        return this.groupManager.Distributor(work_id, memberID, this.groupList);
    }

    public void createWork(ArrayList<String> info) {
        this.workList.addWork(info.get(0), info.get(1), info.get(2), info.get(3), Integer.parseInt(info.get(4)));
    }


}
