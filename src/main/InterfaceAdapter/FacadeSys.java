package main.InterfaceAdapter;

import main.Entity.*;
import main.UsesCases.*;


import java.io.IOException;

import java.util.ArrayList;
import java.util.*;





public class FacadeSys {

    // === Instance Variables ===
    private String employeeType;
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



    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSys(String username) {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.verifier = new Verifier(this.loginList);
        this.workList = new WorkList();
        this.groupList = new GroupList();
        this.journalList = new JournalList();
        this.fileGateway = new DataGateway(this.loginList, this.employeeList, this.groupList, this.workList);
        this.username = username;
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList,this.username);

        this.workFacade = new WorkFacade(this.workList, this.loginList, this.employeeList, this.groupList);
    }


    // === System methods ===


    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        this.employeeType = this.accountFacade.getEmployeeType();
        return this.verifier.verifyForLogin(username, password);
    }

    public void systemEnd() throws IOException, ClassNotFoundException {
        this.fileGateway.WriteOutputFile();
    }

    // === Personal UI Method ===
    public String personalInfo(){
        System.out.println(this.employeeType);
        String presentInfo = "";
        if (this.employeeType.equals("PartTimeEmployee")){
            ArrayList<String> info = this.accountFacade.employeeInfo();
            presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                    + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                    + "\n Department: " + info.get(6);
            return presentInfo;
        }else{
            ArrayList<String> info = this.accountFacade.employeeInfo();
            int[] infoInt = this.accountFacade.getFullTimeEmployeeInfoInt();
            presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                    + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                    + "\n Department: " + info.get(6) + "\n Position: " + info.get(7) + "\n State: " +info.get(8)
                    +"\n Total Vacation with Salary: " + infoInt[0] + "\n  Vacation Used: " + infoInt[1];
            return presentInfo;
        }
    }

    public String checkSalary(){
        if (this.employeeType.equals("PartTimeEmployee")){
            return String.valueOf(this.accountFacade.checkSalary());
        }else{
            return String.valueOf(this.accountFacade.checkSalary());
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

    // Case 4: Distribute a work
    public List<String> findLeadWorkList() {
        return this.workFacade.findLeadWork(this.username);
    }


    public void distributeWork(String employeeID, String workID) {

        List<Userable> members = new ArrayList<>();
        String[] parts;
        parts = employeeID.split(" ");
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
                if (g.getWorkID().equals(workID)) {
                    //this.groupManager.addMembers(members, g);
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
        /* Todo: This one is designed for employee to report their work progress
         *  we need to record their message to Journal. And if the work finished,
         *  only leader!!! can to change work's statues to 'Finished'. There may be some more steps needed.
         *  also, if the user is this work's leader, they can choose to extend due date
         */
    }


    // Here are some method used to show other user information, may used in hr workers or work distribute

    // Case 6: Create a user
    public boolean createUser(String username, String password, String name, String phone, String address,
                              String department, String wage, String position, String level, String status) {
        boolean validLevelGiven = this.accountFacade.ValidToCreateThisLevel(level);
        if (validLevelGiven){
            String[] userinfo = {username, password, name, phone, address, department, wage, position, level, status};
            this.accountFacade.CreateNewAccount(userinfo);
        }
        return validLevelGiven;
    }
    // ==================================

    // Case 7: Delete a user
    public boolean deleteUser(String userid) {
        boolean validLevelGiven = this.accountFacade.userExists(userid) &&
                this.accountFacade.ValidToCreateThisLevel(this.accountFacade.getLevel(userid));
        if (validLevelGiven) {
            this.accountFacade.DeleteAccount(userid);
        }
        return validLevelGiven;
    }
    // ==================================

    // Case 8: Check all lower level employees' salary-related information
    public List<String> checkLowerEmployeeSalary(String id, String option) {
        return this.accountFacade.lowerEmployeeCheck(id, option);
    }
}
// ==================================================

//    public void UserWorkInfoChange(String userid, String option, String info) {
//
//        Employee employee = null;
//        Userable user = null;
//        try {
//            for (Employee e : this.employeeList) {
//                if (e.getID().equals(userid)) {
//                    employee = e;
//                    break;
//                }
//
//                for (Userable u : this.loginList) {
//                    if (u.getID().equals(userid)) {
//                        user = u;
//                        break;
//                    }
//
//                    if (!(levelVerifier(Integer.toString(e.getLevel())))) {// Need to be in UI?
//                        System.out.println("You cannot change info to this employee, please try again.");
//                        return;
//                    }
//
//                    if (Objects.equals(accountFacade.employeeTypeByID(user.getID()), "FullTimeEmployee")) {
//                        accountFacade.setFullTimeAdvancedInfo(option, info);
//                        System.out.println("The information is successfully updated");
//                    }
//
//                    if (Objects.equals(accountFacade.employeeTypeByID(user.getID()), "PartTimeEmployee")) {
//                        if ((!Objects.equals(option, "1")) && (!Objects.equals(option, "2")) && (!Objects.equals(option, "3"))) {
//                            System.out.println("Part Time Employee does not have these infos");
//                        }
//                        return;
//                    }
//                    accountFacade.setPartTimeAdvancedInfo(option, info);
//                    System.out.println("The information is successfully updated");
//                }
//            }
//        }catch (Exception e){
//            System.out.println("Error occurred in FacadeSys.UserWorkInfoChange");
//        }
//    }