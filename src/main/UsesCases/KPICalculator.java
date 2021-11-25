package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.lang.*;


public record KPICalculator(GroupList groupList, WorkList workList) {


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

        String now = String.valueOf(System.currentTimeMillis());
        long milliSeconds = Long.parseLong(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        for (Group group : this.groupList) {
            if (group.getLeaderId().equals(userid)) {
                checkTimeHelper(workAsLeader, month, year, group);
            }

            if (group.getMembers().contains(userid)) {
                checkTimeHelper(workAsMember, month, year, group);
            }
        }
        return userWork;
    }

    // Check whether Work finished in this month
    private void checkTimeHelper(ArrayList<Workable> workAsLeader, int month, int year, Group group) {
        String groupID = group.getWorkID();
        for (Workable work : this.workList) {
            if (work.getState().equals("Finished") &&
                    Integer.valueOf(work.getEnd_time().substring(5, 7)).equals(month) &&
                    Integer.valueOf(work.getEnd_time().substring(0, 4)).equals(year) &&
                    work.getID().equals(groupID)) {
                workAsLeader.add(work);
            }
        }
    }
}
