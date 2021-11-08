package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class WorkFacade {
    WorkList worklist;
    EmployeeList employeelist;
    GroupList grouplist;
    WorkManager workmanager;


    public WorkFacade(WorkList worklist, EmployeeList employeelist, GroupList grouplist, WorkManager workmanager){
        this.worklist = worklist;
        this.employeelist = employeelist;
        this.grouplist = grouplist;
        this.workmanager = workmanager;
    }

    public List<Work> SelfWork(Userable user) {
        Employee employee = null;
        ArrayList<Work> ListOfWork = null;
        for (Employee e: this.employeelist){
            if (user.getID().equals(e.getID()));
            employee = e;
            break;
        }
        for (Work w: this.worklist){
            for (Group g: this.grouplist){
                boolean s = false;
                for (Userable u: g.getMembers()){
                    if (u.equals(user)){
                        s = true;
                        break;
                    }
                }
                if (w.getID().equals(g.getWorkid()) && (g.getLeader().equals(employee) || s)){
                    ListOfWork.add(w);
                }
            }
        }
        return ListOfWork;
    }


    public String WorkDetail(String workid) {
        Work work = null;
        Group group = null;
        for (Work w: this.worklist){
            if (workid.equals(w.getID())){
                work = w;
                break;
            }
        }
        for (Group g: this.grouplist){
            if (work.getID().equals(g.getWorkid())){
                group = g;
                break;
            }
        }
        return "Work ID: " + work.getID() + "\n" +
                "Work Name: " + work.getName() + "\n" +
                "Work State " + work.getState() + "\n" +
                "Work Description " + work.getDescribe() + "\n" +
                "Work Requirement: " + work.getRequirement() + "\n" +
                "Work Level: " + work.getLevel() + "\n" +
                "Work Leader: " + group.getLeader() + "\n" +
                "Work Members: " + group.getMembers() + "\n" +
                "Work Create Time: " + work.getCreate_time() + "\n" +
                "Work Start Time: " + work.getStart_time() + "\n" +
                "Work End Time: " + work.getEnd_time() + "\n";

    }


        //"workType name id createTime level"
    public String workCreate(String action) {
        String[] parts = new String[5];
        parts = action.split(" ");
        this.workmanager.createWork(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
        return "Work Create success. WorkID is: " + parts[2];
    }


    public List<Work> AllWork(Userable user) {
        Employee employee = null;
        ArrayList<Work> ListOfWork = null;
        for (Employee e: this.employeelist){
            user.getID();
            employee = e;
            break;
            }
        String depart = employee.getDepartment();
        for (Work w: this.worklist){
            if (w.getLevel() > employee.getLevel()) {
                if ((w instanceof ITWork && employee.getDepartment().equals("IT")) ||
                        w instanceof SaleWork && employee.getDepartment().equals("Sale")){
                    ListOfWork.add(w);
                }
            }
        }
        return ListOfWork;
    }
}
