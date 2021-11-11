package main.InterfaceAdapter;

import main.Entity.*;
import main.UsesCases.*;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;





public class FacadeSys {

    // === Instance Variables ===

    // === DataFile ===
    private final DataGateway fileGateway;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final Verifier verifier;
    private final WorkList workList;
    private final GroupList groupList;
    private final JournalList journalList;
    private final String username;
    // === AccountFacade ===
    private final AccountFacade accountFacade;
    // === WorkFacade ===
    private final WorkFacade workFacade;
    private final GroupManager groupManager;
    private final String employeeType;
    private final WorkManager workManager;

    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSys(String username) {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.verifier = new Verifier(this.loginList);
        this.fileGateway = new DataGateway(this.loginList, this.employeeList);
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList,username);
        this.workList = new WorkList();
        this.groupManager = new GroupManager();
        this.groupList = new GroupList();
        this.journalList = new JournalList();
        this.workManager = new WorkManager();
        this.username = username;

        this.workFacade = new WorkFacade(this.workList, this.loginList, this.employeeList, this.groupList, this.workManager, this.groupManager);
        this.employeeType = this.accountFacade.employeeType();
    }

    // === System methods ===

    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        return this.verifier.verifyForLogin(username, password);
    }

    public void systemEnd() throws IOException, ClassNotFoundException {
        this.fileGateway.WriteOutputFile();
    }

    // === Personal UI Method ===
    public void personalInfo(){
        if (this.employeeType.equals("PartTimeEmployee")){
            ArrayList<String> info = this.accountFacade.employeeInfo();
            String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                    + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                    + "\n Department: " + info.get(6);
            System.out.println(presentInfo);
        }else{
            ArrayList<String> info = this.accountFacade.employeeInfo();
            int[] infoInt = this.accountFacade.getFullTimeEmployeeInfoInt();
            String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                    + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                    + "\n Department: " + info.get(6) + "\n Position: " + info.get(7) + "\n State: " +info.get(8)
                    +"\n Total Vacation with Salary: " + infoInt[0] + "\n  Vacation Used: " + infoInt[1];
            System.out.println(presentInfo);
        }
    }

    public void checkSalary(){
        if (this.employeeType.equals("PartTimeEmployee")){
            String presentWage = String.valueOf(this.accountFacade.checkSalary());
            System.out.println(presentWage);
        }else{
            String presentWage = String.valueOf(this.accountFacade.checkSalary());
            System.out.println(presentWage);
        }
    }

    public void setPersonalInfo(String option, String response){
        if (this.employeeType.equals("PartTimeEmployee")){
            this.accountFacade.setPartTimeBasicInfo(option, response);
        }else{
            this.accountFacade.setFullTimeBasicInfo(option, response);
        }
    }


    public void checkVacation() {
        int[] infoInt = this.accountFacade.getFullTimeEmployeeInfoInt();
        String vacationInfo = "Total Vacation with Salary: " + infoInt[0] + "\n  Vacation Used: " + infoInt[1];
        System.out.println(vacationInfo);
    }
    // ==================================================

    // ================== Work UI Method ================

    // Case 1: Check Person Work Information.
    public void checkWorkInfo() {
        // TODO : Presenter
        System.out.println(this.workFacade.SelfWork(username));
    }

    // Overload
    public void checkWorkInfo(String ID) {
        // TODO : Presenter
        System.out.println(this.workFacade.WorkDetail(ID));
    }
    // ==================================

    // Case 2: Creating new Work
    public boolean createWork(String name, String ID, String Department, String level) {
        boolean validLevelGiven = this.accountFacade.ValidToCreateThisLevel(level);
        if (validLevelGiven){
            // TODO: Presenter : Print WorkID
            String WorkID = this.workFacade.workCreate(name, ID, Department, level);
        }
        return validLevelGiven;
    }
    // ==================================

    // Case 3: Start a work with assigning leader
    public List<String> findCurrentUserWork() {
        return this.workFacade.AssignableWorkList(this.accountFacade);
    }

    public ArrayList<String> findAllWorkers(){
        return this.workFacade.AllWorkers(this.accountFacade);
    }


    public boolean AssignALeaderToWork(String WorkID, String LeaderID) {
        return this.workFacade.CreateNewGroup(WorkID,LeaderID);
    }
    // ==================================

    public String findLeadWorkList() {
        String presentWorkList = "";
        for (String workID : this.workFacade.findLeadWork(this.username)){
            presentWorkList = workID + "\n";
        }
        return presentWorkList;
    }


    public void distributeWork(String employeeid, String wid) {
        /**
         * 1. Show all works that this user lead
         * 2. Let them choose the work based on work id (they only can choose work that they lead)
         * 3. Ask him did he need to know about employees information who can be invoked be him
         *    This part may be implemented by calling AllWorker below.
         * 4. Let them choose group members
         */

        List<Userable> members = new ArrayList<>();
        String[] parts;
        parts = employeeid.split(" ");
        try {
            for (String eid : parts) {
                for (Userable u : this.loginList) {
                    if (!((findAllWorkers().contains(u.getID())))) {
                        System.out.println("You can only choose the employees shown above");
                        return;
                    }
                    if (u.getID().equals(eid)) {
                        members.add(u);
                        break;
                    }
                }
            }
            for (Group g : this.groupList) {
                if (g.getWorkid().equals(wid)) {
                    this.groupManager.addMembers(members, g);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Error occurred in FacadeSys.distributeWork");
        }
    }
    // ==================================

    // Case 5: Assign KPI
    public boolean checkLeaderResult(String workID){
        return this.workFacade.checkLeader(workID,this.username);
    }

    public List<String> findWorkKpiMemberList(String WorkID) {
        return this.workFacade.findWorkKpiMember(WorkID);
    }

    public void giveKpi(String workID, String employeeID, String kpi){
        this.workFacade.setKpi(workID, employeeID, kpi);
    }
    // ==================================

    public void WorkUpdate() {
        /** Todo: This one is designed for employee to report their work progress
         *  we need to record their message to Journal. And if the work finished,
         *  only leader!!! can to change work's statues to 'Finished'. There may be some more steps needed.
         *  also, if the user is this work's leader, they can choose to extend due date
         */
    }


    // Here are some method used to show other user information, may used in hr workers or work distribute


    public boolean createUser(String username, String password, String name, String phone, String address,
                              String department, String wage, String position, String level, String status) {
        boolean validLevelGiven = this.accountFacade.ValidToCreateThisLevel(level);
        if (validLevelGiven){
            String[] userinfo = {username, password, name, phone, address, department, wage, position, level, status};
            this.accountFacade.CreateNewAccount(userinfo);
        }
        return validLevelGiven;
    }


    public boolean deleteUser(String userid) {
        /**
         * We may need to use memento for delete User
         *  This part we assume we already checked level in UI (Todo)
         * Todo: Implement userExist in AccountFacade which used to check user exist or not by their id
         * Todo: Implement getLevel in AccountFacade which used to get user level by their id
         */
        boolean validLevelGiven = this.accountFacade.userExists(userid) &&
                this.accountFacade.ValidToCreateThisLevel(this.accountFacade.getLevel(userid));
        if (validLevelGiven) {
            this.accountFacade.DeleteAccount(userid);
        }
        return validLevelGiven;
    }


    public void UserWorkInfoChange(String userid, String option, String info) {

        Employee employee = null;
        Userable user = null;
        try {
            for (Employee e : this.employeeList) {
                if (e.getID().equals(userid)) {
                    employee = e;
                    break;
                }

                for (Userable u : this.loginList) {
                    if (u.getID().equals(userid)) {
                        user = u;
                        break;
                    }

                    if (!(levelVerifier(Integer.toString(e.getLevel())))) {// Need to be in UI?
                        System.out.println("You cannot change info to this employee, please try again.");
                        return;
                    }

                    if (Objects.equals(accountFacade.employeeTypeByID(user.getID()), "FullTimeEmployee")) {
                        accountFacade.setFullTimeAdvancedInfo(option, info);
                        System.out.println("The information is successfully updated");
                    }

                    if (Objects.equals(accountFacade.employeeTypeByID(user.getID()), "PartTimeEmployee")) {
                        if ((!Objects.equals(option, "1")) && (!Objects.equals(option, "2")) && (!Objects.equals(option, "3"))) {
                            System.out.println("Part Time Employee does not have these infos");
                        }
                        return;
                    }
                    accountFacade.setPartTimeAdvancedInfo(option, info);
                    System.out.println("The information is successfully updated");
                }
            }
        }catch (Exception e){
            System.out.println("Error occurred in FacadeSys.UserWorkInfoChange");
        }
    }

    public String[] SingleSalaryCheck(String id) {
        /**
         * shows all info of a user except password.
         */
        if (!accountFacade.userExist(id)) {
            return null;
        }
        if (accountFacade.getDepartment(id).equals("HR") && !levelVerifier(String.valueOf(accountFacade.getLevel(id)))) {
            return null;
        }
        if (!accountFacade.getDepartment(id).equals("HR") && !levelVerifier(String.valueOf(accountFacade.getLevel(id) - 1))) {
            return null;
        }
        return accountFacade.getAllInfo(id);
    }


    public void SalaryCheck(String id, String option) {
        // Todo: reward and kpi is not implemented yet
        // Todo: need further modification
        /**
         * Todo: Show all(maybe some) other department same level workers' and lower level hr workers'
         * salary, work days, vacation used, reward, and kpi. Hr worker need to confirm all those info
         */
        // 1: Salary, 2: attendance, 3: total vacation with salary, 4: used vacation
        List<Employee> validemployees = new ArrayList<>();
        validemployees = accountFacade.lowerLevelEmployee(id);
        switch (option) {
            case "1": {
                for (Employee e : validemployees) {
                    System.out.println(e.getID() + e.getWage());
                }
            } case "2": {
                for(Employee e: validemployees){
                    System.out.println(e.getID() + e.getAttendance());
                }
            } case "3": {
                for(Employee e: validemployees){
                    if(Objects.equals(accountFacade.employeeTypeByID(e.getID()),"PartTimeEmployee")){
                        System.out.println("This is a Part time employee, who does not have vacation");
                    }
                    System.out.println(e.getID() + accountFacade.getFullTimeEmployeeInfoInt()[0]);
                }
            } case "4":{
                for(Employee e: validemployees) {
                    if (Objects.equals(accountFacade.employeeTypeByID(e.getID()), "PartTimeEmployee")) {
                        System.out.println("This is a Part time employee, who does not have vacation");
                    }
                    System.out.println(e.getID() + accountFacade.getFullTimeEmployeeInfoInt()[1]);
                }
            }
        }
    }
    // ==================================================
}
