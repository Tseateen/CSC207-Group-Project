package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class WorkFacade {
    WorkList workList;
    EmployeeList employeeList;
    GroupList groupList;
    WorkManager workManager;
    GroupManager groupManager;


    public WorkFacade(WorkList workList, EmployeeList employeeList, GroupList groupList, WorkManager workManager, GroupManager groupManager){
        this.workList = workList;
        this.employeeList = employeeList;
        this.groupList = groupList;
        this.workManager = workManager;
        this.groupManager = groupManager;
    }

    public List<Work> SelfWork(Userable user) {
        Employee employee = null;
        ArrayList<Work> ListOfWork = null;
        for (Employee e: this.employeeList){
            if (user.getID().equals(e.getID()));
            employee = e;
            break;
        }
        for (Workable w: this.workList){
            for (Group g: this.groupList){
                boolean s = false;
                for (Userable u: g.getMembers()){
                    if (u.equals(user)){
                        s = true;
                        break;
                    }
                }
                if (w.getID().equals(g.getWorkid()) && (g.getLeader().equals(employee) || s)){
                    ListOfWork.add((Work) w);
                }
            }
        }
        return ListOfWork;
    }


    public String WorkDetail(String workid) {
        Work work = null;
        Group group = null;
        for (Workable w: this.workList){
            if (workid.equals(w.getID())){
                work = (Work) w;
                break;
            }
        }
        for (Group g: this.groupList){
            if (work.getID().equals(g.getWorkid())){
                group = g;
                break;
            }
        }
        return "Work ID: " + work.getID() + "\n" +
                "Work Name: " + work.getName() + "\n" +
                "Work State " + work.getState() + "\n" +
                "Work Department " + work.getDepartment() + "\n" +
                "Work Description " + work.getDescribe() + "\n" +
                "Work Requirement: " + work.getRequirement() + "\n" +
                "Work Level: " + work.getLevel() + "\n" +
                "Work Leader: " + group.getLeader() + "\n" +
                "Work Members: " + group.getMembers() + "\n" +
                "Work Create Time: " + work.getCreate_time() + "\n" +
                "Work Start Time: " + work.getStart_time() + "\n" +
                "Work End Time: " + work.getEnd_time() + "\n";

    }


        //"name id department level"
    public String workCreate(String action) {
        String[] parts;
        parts = action.split(" ");
        this.workList.addWork(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
        return "Work Create success. WorkID is: " + parts[1];
    }


    public List<Work> AllWork(Userable user) {
        Employee employee = null;
        ArrayList<Work> ListOfWork = null;
        for (Employee e: this.employeeList){
            user.getID();
            employee = e;
            break;
            }
        String depart = employee.getDepartment();
        for (Workable w: this.workList){
            if (w.getLevel() > employee.getLevel()) {
                if (w.getDepartment().equals(employee.getDepartment())){
                    ListOfWork.add((Work) w);
                }
            }
        }
        return ListOfWork;
    }

    public ArrayList<String> AllWorkers(Userable user) {
        /**
         *  Id need to be shown, because user need to tap id to choose user, but what else infom we need?
         *  Ex. name, level...
         */
        Employee employee = null;
        ArrayList<String> ListOfEmployee = new ArrayList<>();
        for (Employee e : this.employeeList) {
            if (user.getID().equals(e.getID())) ;
            employee = e;
            break;
        }
        for (Employee e : this.employeeList) {
            if (employee.getLevel() < e.getLevel() && employee.getDepartment().equals(e.getDepartment())) {
                ListOfEmployee.add(e.getID() );
            }
        }
        return ListOfEmployee;
    }


}
