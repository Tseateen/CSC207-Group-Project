package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class WorkFacade {
    private final WorkList workList;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final GroupList groupList;
    private final WorkManager workManager;
    private final GroupManager groupManager;


    public WorkFacade(WorkList workList, LoginList loginList, EmployeeList employeeList, GroupList groupList, WorkManager workManager, GroupManager groupManager){
        this.workList = workList;
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.groupList = groupList;
        this.workManager = workManager;
        this.groupManager = groupManager;
    }

    public List<Work> SelfWork(String username) {
        Userable user = this.loginList.getUser(username);
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
                for (Userable each_user: g.getMembers()){
                    if (each_user.equals(user)){
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
    public String workCreate(String name, String ID, String Department, String level) {
        this.workList.addWork(name, ID, Department, Integer.parseInt(level));
        return "Work Create success. WorkID is: " + ID;
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

    public ArrayList<String> AllWorkers(AccountFacade accountFacade) {
        /**
         *  Id need to be shown, because user need to tap id to choose user, but what else infom we need?
         *  Ex. name, level...
         */
        Employee CurrentEmployee = accountFacade.findEmployeeHelper();
        ArrayList<String> ListOfEmployeeInfo = new ArrayList<>();

        for (Employee eachEmployee : this.employeeList) {
            // TODO: 小於等於 還就小於
            if ((CurrentEmployee.getLevel() < eachEmployee.getLevel()) &&
                    CurrentEmployee.getDepartment().equals(eachEmployee.getDepartment())) {
                ListOfEmployeeInfo.add(eachEmployee.getID());
            }
        }
        return ListOfEmployeeInfo;
    }

    public List<String> findLeadWork(String username) {
        List<String> groupids = new ArrayList<String>();

        Userable self = null;
        for (Userable u: this.loginList){
            if (u.getUsername().equals(username)){
                self = u;
                break;
            }
        }

        for (Group group: this.groupList){
            if (group.getLeader().equals(self)){
                groupids.add(group.getWorkid());
            }
        }

        List<String> workid = new ArrayList<String>();

        for (String gid: groupids){
            for (Workable w: this.workList){
                if (w.getID().equals(gid)){
                    workid.add(w.getID());
                    break;
                }
            }
        }
        return workid;
    }

    public boolean checkLeader (String workID, String username) throws NullPointerException{
        Group group = null;
        Work work = null;
        for (Group g : this.groupList) {
            if (g.getWorkid().equals(workID)) {
                group = g;
                break;
            }
        }
        Userable self = new User();
        for (Userable u : this.loginList) {
            if (u.getUsername().equals(username)) {
                self = u;
                break;
            }
        }
        return (group.getLeader().equals(self));
    }
    public List<String> findWorkKpiMember(String workID) {
        /**
         * * Todo: Write Exception & Modify this method.
         * Todo: still don't know the relationship between work and kpi?
         * (Update: Group leader to assign KPI)
         */
        Group group = null;
        Work work = null;
        for (Workable w : this.workList) {
            if (w.getID().equals(workID)) {
                work = (Work) w;
                break;
            }
        }
        for (Group g : this.groupList) {
            if (g.getWorkid().equals(workID)) {
                group = g;
                break;
            }
        }
        List<String> memberList = new ArrayList<String>();
        try {
            for (Userable member : group.getMembers()){
                memberList.add(member.getID());
            }
            return memberList;
        }catch(NullPointerException e){
            return null;
        }
    }

    public void setKpi(String workID, String employeeID, String kpi){
        Work work = null;
        for (Workable w: this.workList){
            if (w.getID().equals(workID)){
                work = (Work) w;
                break;
            }
        }
        for (Employee e: this.employeeList){
            if (e.getID().equals(employeeID)){
                e.setKpi(work, Integer.valueOf(kpi));
                break;
            }
        }
    }

    public List<String> AssignableWorkList(AccountFacade accountFacade){
        Employee employee = accountFacade.findEmployeeHelper();
        List<Workable> ListOfWork = new ArrayList<>();
        List<String> ListOfWorkID = new ArrayList<>();
        for (Workable work : this.workList) {
            boolean leader = false;

            if (work.getSign().equals("0") &&
                    (employee.getLevel() < work.getLevel()) &&
                    employee.getDepartment().equals(work.getDepartment())) {
                ListOfWork.add(work);
            }
        }
        for (Workable work : ListOfWork) {
            ListOfWorkID.add(work.getID() + ": " + work.getName());
        }
        return ListOfWorkID;
    }

    public boolean CreateNewGroup(String workID, String LeaderID){

        for(Userable user: this.loginList){
            if(user.getID().equals(LeaderID)){
                this.groupList.addGroup(user,workID);
                return true;
            }
        }
        return false;
    }
}
