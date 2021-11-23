package main.UsesCases;

import main.Entity.Group;
import main.Entity.Workable;

import java.util.ArrayList;

public class WorkFacade {

    // === Instance Variables ===

    private final WorkList workList;
    private final GroupList groupList;


    /**
     * Construct the WorkFacade.
     */
    public WorkFacade(WorkList workList, GroupList groupList) {
        this.workList = workList;
        this.groupList = groupList;
    }


    /**
     * Check if the Work is already existed with the Work ID.
     *
     * @param workID the ID of the work.
     *
     * @return true iff the work is already existed.
     */
    public boolean workExist(String workID) {
        for (Workable w: workList) {
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
    public ArrayList<String> workOfMine(String id) {
        return this.getWorkList("Mine", id);
    }


    /**
     * Get the leader of the Work who is working on the Work.
     *
     * @param id the ID of the work.
     *
     * @return a list of strings with the leader.
     */
    public ArrayList<String> workOfLed(String id) {
        return this.getWorkList("Led", id);
    }


    /**
     * Get all the Work that has a lower level than the employee's level.
     *
     * @param Level the level of the employee.
     *
     * @return a list of strings work that has a lower level than the targeted employee's level.
     */
    public ArrayList<String> workOfLowerLevel(String Level) {
        ArrayList<String> result = new ArrayList<>();
        for (Workable w: this.workList) {
            if (w.getLevel() > Integer.parseInt(Level)) {
                result.add(this.showWorkDetail(w.getID()).get(0) + " " + this.showWorkDetail(w.getID()).get(1) + " "
                + this.showWorkDetail(w.getID()).get(2) + "\n");
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
    private ArrayList<String> getWorkList(String type, String id) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> work_ids = new ArrayList<>();
        boolean inList = false;
        for (Group g: this.groupList) {
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
            result.add(this.showWorkDetail(s).get(0) + " " + this.showWorkDetail(s).get(1) + " "
                    + this.showWorkDetail(s).get(2) + "\n");
        }
        return result;
    }


    /**
     * Get all the Work information with the Work ID.
     *
     * @param workID the ID of the Work.
     *
     * @return a list of strings with the Work information, including the name, ID, state, level, description,
     * start time, end time of the Work.
     */
    public ArrayList<String> showWorkDetail(String workID) {
        ArrayList<String> result = new ArrayList<>();
        Workable w = this.workList.getWork(workID);
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
     * Set the leader of the Work and update this information in the GroupList.
     *
     * @param workID the ID of the Work.
     * @param leaderID the ID of the Employee.
     *
     */
    public void assignLeader(String workID, String leaderID) {
        for (Group g: this.groupList) {
            if (g.getWorkID().equals(workID)) {
                g.setLeaderId(leaderID);
                return;
            }
        }
        this.groupList.addGroup(leaderID, workID);
    }


    /**
     * Verify the leader of the Work.
     *
     * @param userID the ID of the Employee.
     * @param workID the ID of the Work.
     *
     * @return true iff the Employee is the Work's leader.
     */
    public boolean verifierLeader(String userID, String workID) {
        for (Group g: this.groupList) {
            if (g.getWorkID().equals(workID)){return g.getLeaderId().equals(userID);}
        }
        return false;
    }


    /**
     * Verify if the Employee is the member of the Work, i.e. he is working on this project.
     *
     * @param userID the ID of the Employee.
     * @param workID the ID of the Work.
     *
     * @return true iff the Employee is the Work's member.
     */
    public boolean isMember (String userID, String workID) {
        for (Group g: this.groupList) {
            if (g.getWorkID().equals(workID)){
                return (g.getLeaderId().equals(userID)||g.getMembers().contains(workID));
            }
        }
        return false;
    }


    /**
     * Assign the Work to the targeted Employee.
     *
     * @param userID the ID of the Employee.
     * @param workID the ID of the Work.
     *
     * @return true iff the Work has been successfully assigned to the Employee.
     */
    public boolean Distributor(String workID, String userID) {
        for (Group g: this.groupList) {
            if (g.getWorkID().equals(workID)){
                if (g.getMembers().contains(userID)) {return false;}
                g.addMember(userID);
                return true;
            }
        }
        return false;
    }


    /**
     * Create a new Work.
     *
     * @param info the information of the Work.
     *
     */
    public void workCreator( ArrayList<String> info) {
        this.workList.addWork(info.get(0), info.get(1), info.get(2), info.get(3), Integer.parseInt(info.get(4)));
    }


    /**
     * Get the authority level of the Work.
     *
     * @param workID the ID of the Work.
     *
     * @return a string with the authority level of the Work.
     */
    public String workLevel(String workID) {
        return String.valueOf(this.workList.getWork(workID).getLevel());
    }


    /**
     * Extend the deadline of the Work.
     *
     * @param workID the ID of the Work.
     * @param extendDate the Date the Work is extended.
     *
     */
    public void extendWork(String workID, String extendDate) {
        WorkManager workManager = new WorkManager();
        workManager.extendWork(this.workList.getWork(workID), extendDate);
    }


    /**
     * Change the Work information with different options.
     *
     * @param workID the ID of the Work to be changed.
     * @param opt the option with different parts of the Work information to be changed.
     * @param changeTo the new information.
     *
     * @return true iff the Work information has successfully been changed.
     *
     */
    public boolean changeWorkInfo(String workID, String opt, String changeTo) {
        Workable w = this.workList.getWork(workID);
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
     * Delete the people previously working on the Work.
     *
     * @param userID the ID of the Employee, either he is the leader or the member of the Work.
     */
    public void deleteEmployee(String userID) {
        GroupManager groupManager = new GroupManager();
        for (Group g : this.groupList) {
            if (g.getLeaderId().equals(userID)) {
                groupManager.resetGroup(g);
            }
            g.deleteMember(userID);
        }
    }
}
