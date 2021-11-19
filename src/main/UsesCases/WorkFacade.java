package main.UsesCases;

import main.Entity.Group;
import main.Entity.Workable;

import java.util.ArrayList;

public class WorkFacade {

    private final WorkList workList;
    private final GroupList groupList;

    public WorkFacade(WorkList workList, GroupList groupList) {
        this.workList = workList;
        this.groupList = groupList;
    }

    public boolean workExist(String work_id) {
        for (Workable w: workList) {
            if (w.getID().equals(work_id)) {return true;}
        }
        return false;
    }

    public ArrayList<String> workOfMine(String id) {
        return this.getWorkList("Mine", id);
    }

    public ArrayList<String> workOfLed(String id) {
        return this.getWorkList("Led", id);
    }

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

    public ArrayList<String> showWorkDetail(String work_id) {
        ArrayList<String> result = new ArrayList<>();
        Workable w = this.workList.getWork(work_id);
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

    public void assignLeader(String work_id, String leader_id) {
        for (Group g: this.groupList) {
            if (g.getWorkID().equals(work_id)) {
                g.setLeaderId(leader_id);
                return;
            }
        }
        this.groupList.addGroup(leader_id, work_id);
    }

    public boolean verifierLeader(String user_id, String work_id) {
        for (Group g: this.groupList) {
            if (g.getWorkID().equals(work_id)){return g.getLeaderId().equals(user_id);}
        }
        return false;
    }

    public boolean isMember (String user_id, String work_id) {
        for (Group g: this.groupList) {
            if (g.getWorkID().equals(work_id)){
                return (g.getLeaderId().equals(user_id)||g.getMembers().contains(work_id));
            }
        }
        return false;
    }



    public boolean Distributor(String work_id, String user_id) {
        for (Group g: this.groupList) {
            if (g.getWorkID().equals(work_id)){
                if (g.getMembers().contains(user_id)) {return false;}
                g.addMember(user_id);
                return true;
            }
        }
        return false;
    }

    public void workCreator( ArrayList<String> info) {
        this.workList.addWork(info.get(0), info.get(1), info.get(2), info.get(3), Integer.parseInt(info.get(4)));
    }

    public String workLevel(String work_id) {
        return String.valueOf(this.workList.getWork(work_id).getLevel());
    }

    public void extendWork(String work_id, String extend_date) {
        WorkManager workManager = new WorkManager();
        workManager.extendWork(this.workList.getWork(work_id), extend_date);
    }

    public boolean changeWorkInfo(String work_id, String opt, String changeTo) {
        Workable w = this.workList.getWork(work_id);
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
