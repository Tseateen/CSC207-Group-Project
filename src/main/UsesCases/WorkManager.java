package main.UsesCases;

import com.sun.jdi.IntegerValue;
import main.Entity.Group;
import main.Entity.Work;
import main.Entity.Workable;

import java.sql.Timestamp;
import java.util.ArrayList;

public class WorkManager implements IWorkManager{


    /**
     * Extend a work (add extra works to the current work).
     * @param work the work which is going to be extended.
     * @param extend_date how much date its gonna extended.
     */
    public void extendWork(Workable work, String extend_date) {
        String due = work.getEnd_time();
        due = String.valueOf(Integer.parseInt(due) + Integer.parseInt(extend_date));
        work.setEnd_time(due);
        autoChangeState(work);
    }

    /**
     * Change a work's state
     * @param work the work which is going to be changed.
     * @param new_statue the state that the given work is going to be changed to.
     */
    public void changeState(Workable work, String new_statue) {
        work.setState(new_statue);
        autoChangeState(work);
    }

    /**
     * Change a work's state automatically as the time goes.
     * @param work the work which is going to be changed.
     */
    public void autoChangeState(Workable work) {
        String statue = work.getState();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long due = Integer.parseInt(work.getEnd_time());
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
     * @param opt the option with different parts of the Work information to be changed.
     * @param changeTo the new information.
     * @param workList work list we have to find work
     *
     * @return true iff the Work information has successfully been changed.
     *
     */
    public boolean changeWorkInfo(String workID, IWorkList workList , String opt, String changeTo) {
        Workable w = ((WorkList)workList).getWork(workID);
        switch (opt) {
            case "DESCRIBE":
                w.setDescribe(changeTo);
                return true;
            case "REQ":
                w.setRequirement(changeTo);
                return true;
            case "STATE":
                w.setState(changeTo);
                return true;
            case "SIGN":
                w.setSign(changeTo);
                return true;
            default:
                return false;
        }
    }

    /**
     * Get the authority level of the Work.
     *
     * @param workID the ID of the Work.
     * @param workList the list of works
     * @return a string with the authority level of the Work.
     */
    public String workLevel(String workID, IWorkList workList) {
        return String.valueOf(workList.getWork(workID).getLevel());
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
        result.add(w.getStart_time());
        result.add(w.getEnd_time());
        // Here can add group info
        return result;
    }

    /**
     * Check if the Work is already existed with the Work ID.
     *
     * @param workID the ID of the work.
     *
     * @return true iff the work is already existed.
     */
    public boolean workExist(String workID, IWorkList workList) {
        for (Workable w: (WorkList)workList) {
            if (w.getID().equals(workID)) {return true;}
        }
        return false;
    }


    /**
     * Get the members of the Work who are working on the Work.
     *
     * @param id the ID of the work.
     *
     * @return a list of strings with the members.
     */
    public ArrayList<String> workOfMine(String id,IGroupList groupList, IWorkList workList) {
        return this.getWorkList("Mine", id, groupList, workList);
    }


    /**
     * Get the leader of the Work who is working on the Work.
     *
     * @param id the ID of the work.
     *
     * @return a list of strings with the leader.
     */
    public ArrayList<String> workOfLed(String id,IGroupList groupList, IWorkList workList) {
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
                inList = g.getLeaderId().equals(id);
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