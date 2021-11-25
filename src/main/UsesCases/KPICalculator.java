package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.*;

public class KPICalculator {
    private final GroupList groupList;
    private final WorkList workList;

    public KPICalculator(GroupList groupList, WorkList workList) {
        this.groupList = groupList;
        this.workList = workList;
    }


    public int calculateKPI(String userid) {
        int KPI = 0;

        for (Workable work : findUserWorkHelper(userid).get("Leader")) {
            KPI += Math.round(1500 * Math.log10(11 - work.getLevel()));
        }

        for (Workable work : findUserWorkHelper(userid).get("Member")) {
            KPI += Math.round(1000 * Math.log10(11 - work.getLevel()));
        }

        return KPI;
    }


    //=== Helper Method ===

    private HashMap<String, ArrayList<Workable>> findUserWorkHelper(String userid) {
        HashMap<String, ArrayList<Workable>> userWork = new HashMap<>();
        ArrayList<Workable> workAsLeader = new ArrayList<>();
        ArrayList<Workable> workAsMember = new ArrayList<>();
        userWork.put("Leader", workAsLeader);
        userWork.put("Member", workAsMember);

        for (Group group : this.groupList) {
            if (group.getLeaderId().equals(userid)) {
                String groupID = group.getWorkID();
                for (Workable work : this.workList) {
                    if (work.getState().equals("Finished") && work.getID().equals(groupID)) {
                        workAsLeader.add(work);
                    }
                }
            }
            if (group.getLeaderId().equals(userid)) {
                String groupID = group.getWorkID();
                for (Workable work : this.workList) {
                    if (work.getState().equals("Finished") && work.getID().equals(groupID)) {
                        workAsMember.add(work);
                    }
                }
            }
        }
        return userWork;
    }

}
