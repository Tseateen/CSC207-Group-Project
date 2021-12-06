

package main.UsesCases;

import main.Entity.Group;
import main.Entity.Workable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WorkManager implements IWorkManager, Serializable {


    /**
     * Extend a work (add extra works to the current work).
     * @param workID the work's id which is going to be extended.
     * @param workList the list of work
     * @param extendDate how much date it's gonna extended.
     */
    public void extendWork(String workID, IWorkList workList, String extendDate) {
        Workable work = workList.getWork(workID);
        Date date = new Date(Long.parseLong(work.getEndTime()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, Integer.parseInt(extendDate));
        Timestamp ts = new Timestamp(cal.getTimeInMillis());
        String due = String.valueOf(ts.getTime());
        work.setEndTime(due);
        this.autoChange(work);
    }


    /**
     * Change a work's state
     * @param workID the work's id which is going to be extended.
     * @param workList the list of work
     * @param newStatue the state that the given work is going to be changed to.
     */
    public void changeState(String workID, IWorkList workList, String newStatue) {
        Workable work = workList.getWork(workID);
        work.setState(newStatue);
        autoChange(work);
    }


    /**
     * Change a work's state automatically as the time goes.
     * @param workList the list of work
     */
    public void autoChangeState(IWorkList workList) {
        for (Workable w: (WorkList)workList) {
            this.autoChange(w);
        }
    }

    private void autoChange(Workable work) {
        String statue = work.getState();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long due = Long.parseLong(work.getEndTime());
        if (statue.equals("Finished")) {
            return;
        }
        if (now.getTime() > due) {
            work.setState("Expired");
            return;
        }
        if (work.getSign().equals("1")) {
            work.setState("InProgress");
            return;
        }
        work.setState("Pending");

    }
    /**
     * Change the Work information with different options.
     *
     * @param workID the ID of the Work to be changed.
     * @param workList work list we have to find work
     *
     * @param opt the option with different parts of the Work information to be changed.
     * @param changeTo the new information.
     *
     */
    public void changeWorkInfo(String workID, IWorkList workList , String opt, String changeTo) {
        Workable w = workList.getWork(workID);
        switch (opt) {
            case "DESCRIBE":
                w.setDescribe(changeTo);
                return;
            case "REQ":
                w.setRequirement(changeTo);
                return;
            case "STATE":
                w.setState(changeTo);
                return;
            case "SIGN":
                w.setSign(changeTo);
                return;
            default:
        }
    }

    /**
     * Get all the Work information with the Work ID.
     *
     * @param workID the ID of the Work.
     *
     * @return a list of strings with the Work information, including the name, ID, state, level, description,
     * start time, end time of the Work.
     */
    public ArrayList<String> showWorkDetail(String workID, IWorkList workList) {
        ArrayList<String> result = new ArrayList<>();
        Workable w = workList.getWork(workID);
        result.add(w.getName());
        result.add(w.getID());
        result.add(w.getState());
        result.add(String.valueOf(w.getLevel()));
        result.add(w.getDescribe());
        result.add(w.getRequirement());
        String s = w.getCreateTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(s));
        result.add(sf.format(date));
        String s2 = w.getEndTime();
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = new Date(Long.parseLong(s2));
        result.add(sf2.format(date2));
        // Here can add group info
        return result;
    }

    /**
     * Get the members of the Work who are working on the Work.
     *
     * @param id the ID of the work.
     *
     * @return a list of strings with the work's id.
     */
    public ArrayList<String> workOfMember(String id,IGroupList groupList, IWorkList workList) {
        return this.getWorkList("Mine", id, groupList, workList);
    }


    /**
     * Get the leader's Works.
     *
     * @param id the ID of the leader.
     *
     * @return a list of strings with the work's id.
     */
    public ArrayList<String> TheWorkLeadByThisUser(String id, IGroupList groupList, IWorkList workList) {
        return this.getWorkList("Led", id, groupList, workList);
    }


    /**
     * Get all the Work that has a lower level than the employee's level.
     *
     * @param Level the level of the employee.
     *
     * @return a list of strings work that has a lower level than the targeted employee's level.
     */
    public ArrayList<String> workOfLowerLevel(String Level, IWorkList workList) {
        ArrayList<String> result = new ArrayList<>();
        for (Workable w: (WorkList)workList) {
            if (w.getLevel() > Integer.parseInt(Level)) {
                result.add(this.showWorkDetail(w.getID(), workList).get(0) + " "
                        + this.showWorkDetail(w.getID(), workList).get(1) + " "
                        + this.showWorkDetail(w.getID(), workList).get(2) + "\n");
            }
        }
        return result;
    }

    /**
     * Get the Work information with the requirements given and the ID of the Work.
     *
     * @param type the different requirements when requiring the Work information.
     * @param id the ID of the targeted Work.
     *
     * @return a list of Work information based on the requirements.
     */
    private ArrayList<String> getWorkList(String type, String id, IGroupList groupList, IWorkList workList) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> work_ids = new ArrayList<>();
        boolean inList = false;
        for (Group g: (GroupList)groupList) {
            if (type.equals("Mine")) {
                inList = g.getMembers().contains(id);
            } else if (type.equals("Led")) {
                inList = g.getLeaderID().equals(id);
            }
            if (inList) {
                work_ids.add(g.getWorkID());
            }
        }
        for (String s: work_ids) {
            result.add(this.showWorkDetail(s, workList).get(0) + " " + this.showWorkDetail(s, workList).get(1) + " "
                    + this.showWorkDetail(s, workList).get(2) + "\n");
        }
        return result;
    }

}
