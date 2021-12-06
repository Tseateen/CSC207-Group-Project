package main.UsesCases;

import main.Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.*;


public class KPICalculator implements Serializable {


    /**
     * This method will calculate KPI for the employee.
     *
     * @param userid the user's ID of the targeted employee.
     * @param groupList the list of Group.
     * @param workList the list of Work.
     *
     * @return the string of KPI of the targeted employee.
     *
     */
    public String calculateKPI(String userid, IGroupList groupList, IWorkList workList) {
        int KPI = 0;

        for (Workable work : findUserWorkHelper(userid,groupList,workList).get("Leader")) {
            KPI += Math.round(1500 * Math.log10(11 - work.getLevel()));
        }

        for (Workable work : findUserWorkHelper(userid,groupList,workList).get("Member")) {
            KPI += Math.round(1000 * Math.log10(11 - work.getLevel()));
        }

        return String.valueOf(KPI);
    }


    //=== Helper Method ===

    private HashMap<String, ArrayList<Workable>> findUserWorkHelper(String userid, IGroupList groupList, IWorkList workList) {
        HashMap<String, ArrayList<Workable>> userWork = new HashMap<>();
        ArrayList<Workable> workAsLeader = new ArrayList<>();
        ArrayList<Workable> workAsMember = new ArrayList<>();
        userWork.put("Leader", workAsLeader);
        userWork.put("Member", workAsMember);

        for (Group eachGroup : (GroupList)groupList) {
            if (eachGroup.getLeaderID().equals(userid)) {
                String groupID = eachGroup.getWorkID();
                for (Workable work : (WorkList)workList) {
                    if (checkStatusHelper(work, groupID)){
                        workAsLeader.add(work);
                        work.setIsKPI("1");
                    }
                }
            }

            if (eachGroup.getMembers().contains(userid)) {
                String groupID = eachGroup.getWorkID();
                for (Workable work : (WorkList)workList) {
                    if (checkStatusHelper(work, groupID) ) {
                        workAsMember.add(work);
                        work.setIsKPI("1");

                    }
                }
            }
        }
        return userWork;
    }

    private boolean checkStatusHelper(Workable work, String groupID){
        return (work.getState().equals("Finished") &&
                work.getID().equals(groupID)) &&
                work.getIsKPI().equals("0");
    }

}
