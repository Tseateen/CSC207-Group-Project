package main.InterfaceAdapter;

import main.UsesCases.*;
import java.util.ArrayList;
import java.util.List;


public class FacadeSys {

    // === Instance Variables ===

    // === UI Input Data ===
    private final String userID;
    // ==========================

    // === Controller ===
    private final EmployeeListController employeeListController;
    private final LoginListController loginListController;
    private final PersonalInfoController personalInfoController;
    private final VerifierController verifierController;
    private final WorkListController workListController;
    private final WorkManagerController workManagerController;
    private final GroupManagerController groupManagerController;
    // ==========================

    // === Data Related Class ===
    private final DataGateway fileGateway;
    private final ILoginList loginList;
    private final IEmployeeList employeeList;
    private final IWorkList workList;
    private final IGroupList groupList;

    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSys(String userID) {
        // UI Input
        this.userID = userID;

        // Login
        this.loginList = new LoginList();
        this.loginListController = new LoginListController(this.loginList);

        // Employee
        this.employeeList = new EmployeeList();
        this.employeeListController = new EmployeeListController(this.employeeList);

        // Work
        this.workList = new WorkList();
        this.workListController = new WorkListController(this.workList);

        // Group
        this.groupList = new GroupList();
        // Initial Use Case Controller
        this.personalInfoController = new PersonalInfoController(new PersonalManager());
        this.verifierController = new VerifierController(new Verifier());
        this.workManagerController = new WorkManagerController(new WorkManager(), new GroupManager());
        this.groupManagerController = new GroupManagerController(new GroupManager());
        // Initial DataGateway
        this.fileGateway = new DataGateway();
    }


    // === System methods ===


    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList(this.loginList);
        this.fileGateway.ReadInputFileToEmployeeList(this.employeeList);
        this.fileGateway.ReadInputFileToWorkList(this.workList);
        this.fileGateway.ReadInputFileToGroupList(this.groupList);
        return this.verifierController.verifyLogin(username, password, this.loginList);
    }

    public void systemEnd(){
        this.fileGateway.WriteOutputFile(this.loginList, this.employeeList, this.workList, this.groupList);
    }

    // === Personal UI Method ===

    // === Case (i) Check your personal Info ====

    public String personalInfo(){
        StringBuilder result = new StringBuilder();
        ArrayList<String> infos = this.personalInfoController.personalInfo(this.loginList,
                this.employeeList, this.userID);
        // 0: Name, 1:ID, 3:Phone Number , 4:Address, 5: Department 6: Work hours (PartTime)/ position (Full Time)
        result.append(infos.get(0)).append(infos.get(1)).append(infos.get(3)).append(infos.get(4))
                .append(infos.get(5)).append(infos.get(6));;

        if (this.verifierController.verifyFullTime(this.userID, this.employeeList)) {
        // 7: Status, 8: Total vacation with salary, 9: Vacation used
            result.append(infos.get(7));
            result.append(infos.get(8));
            result.append(infos.get(9));
        }
        return result.toString();
    }

    // ==========================================

    // === Case (ii) Check your total Salary (including bonus) ====

    public String checkTotalSalary(){
        return this.personalInfoController.checkTotalSalary(this.employeeList, this.userID, this.groupList, this.workList);
    }

    // ==========================================

    // === Case (iii) Check your minimum wage ====

    public String checkMinimumWage(){
        return this.personalInfoController.checkMinimumWage(this.employeeList, this.userID);
    }

    // ==========================================

    // === Case (iv) Check your bonus salary from vacation ====
    public String checkVacationBonus(){
        return this.personalInfoController.checkVacationBonus(this.employeeList, this.userID);
    }
    // ==========================================

    // === Case (v) Check your bonus salary from KPI ====
    public String checkKPIBonus(){
        return this.personalInfoController.checkKPIBonus(this.employeeList, this.userID, this.groupList, this.workList);
    }
    // ==========================================

    // === Case (vi) Change personal Information ====
    public String setPersonalInfo(String option, String response){
        // option {1: Name, 2: Password, 3: Address, 4. Phone number}
        if (this.personalInfoController.setPersonalInfo(option, response, this.loginList, this.userID)){
            return "Set personal information success!";
        }
        else{
            return "Invalid option or response!";
        }
    }
    // ==================================================

    // ================== Work UI Method ================

    // === Case (i) Check your own work's information ====
    public String showAllWorkNeedToDo() {
       return this.workManagerController.showAllWorkNeedToDo(this.userID, this.groupList, this.workList);
    }

    public String showWorkDetail(String workID) {
        return this.workManagerController.showWorkDetail(workID, this.workList);
    }

    public boolean checkWorkExist(String workID) {
        return this.workListController.checkWorkExist(workID);
    }
    // ==================================================

    // === Case (ii) createWork ====
    public boolean createWork(ArrayList<String> info_list) {
        if (this.verifierController.verifyLevel(info_list.get(4), this.userID, this.employeeList)) {
            this.workListController.createWork(info_list);
            return true;
        }else{
            return false;
        }
    }
    // ==================================================

    // === Case (iii) Start a work with assigning leader ====
    public boolean assignLeaderToWork(String workID, String leaderID) {
        if (this.workListController.checkWorkExist(workID) && this.verifierController.verifyUserExistence(leaderID, this.loginList))
        {
            String workLevel = this.workListController.FindWorkLevel(workID);
            String leaderLevel = this.personalInfoController.checkUserLevel(leaderID, this.employeeList);
            if (this.verifierController.verifyLevel(workLevel, this.userID, this.employeeList)&& this.verifierController.verifyLevel(leaderLevel,this.userID,this.employeeList)){
                this.workManagerController.assignLeaderToWork(workID, leaderID, this.groupList);
                return true;
            }
        }
        return false;
    }
    // ==================================================

    // === Case (iv) Distribute a work ===
    public String showAllWorkLead(){
        return this.workManagerController.showAllWorkLead(this.userID, this.groupList, this.workList);
    }

    public String showAllLowerWork() {
        String level = this.personalInfoController.checkUserLevel(this.userID, this.employeeList);
        return this.workManagerController.showAllLowerWork(level, this.workList);
    }


    public boolean distributeWork(String workID, String memberID) {
        if (this.workListController.checkWorkExist(workID) && this.verifierController.verifyUserExistence(memberID, this.loginList)) {
            String memberLevel = this.personalInfoController.checkUserLevel(memberID, this.employeeList);
            if (this.verifierController.verifyLeader(this.userID, workID, this.groupList) &&
                    this.verifierController.verifyLevel(memberLevel, this.userID, this.employeeList)) {
                return this.groupManagerController.distributeWork(workID, memberID, this.groupList);
            }
        }
        return false;
    }
    // ==================================================

    // Here are some method used to show other user information, may be used in hr workers or work distribute



    // === Case (v) Create a user ===
    public List<String> createUser(String name, String password, String phone, String address,
                                            String department, String wage, String position, String level, String status) {
        List<String> result = new ArrayList<>();
        boolean validLevelGiven = this.verifierController.validToCreate(level, this.employeeList, this.userID);
        result.add(String.valueOf(validLevelGiven));
        if (validLevelGiven){
            // Give ID
            result.add(this.loginListController.addUser(name, password,phone, address));
            result.add(password);
            this.employeeListController.addEmployee(department,wage,position,level,status, name);}
        return result;
    }
    // ==================================================

    // === Case (vi) Delete a user ===
    public boolean deleteUser(String targetUserID) {
        boolean validLevelGiven = this.verifierController.verifyUserExistence(targetUserID, this.loginList ) &&
                this.verifierController.validToDelete(targetUserID, this.employeeList, this.userID);
        if (validLevelGiven) {
            this.employeeListController.deleteEmployee(userID);
            this.loginListController.deleteUser(userID);
            this.groupManagerController.removeEmployeeFromAllRelatedGroup(userID, this.groupList);
        }
        return validLevelGiven;
    }
    // ==================================================

    // Case 7 Check all lower level employees' salary-related information
    public List<String> checkLowerLevelEmployeeSalary(String userID) {
        List<String> result = new ArrayList<>();
        List<String> personalInfo = personalInfoController.personalInfo(this.loginList, this.employeeList, userID);
        // Size < 6 means that the user id is for part-time employee
        if (personalInfo.size() <= 6){
            result.add(personalInfoController.checkTotalSalary(this.employeeList, userID, groupList, workList));
        return result;
        } else {
            result.add(personalInfoController.checkTotalSalary(this.employeeList, userID, groupList, workList));
            // 8 : Total Vacation with Salary:
            result.add(personalInfo.get(8));
            // 9 :Vacation Used
            result.add(personalInfo.get(9));
            return result;
        }
    }

    public String showAllLowerUser() {
        UserManager u = new UserManager();
        StringBuilder result = new StringBuilder();
        for (String i : u.getLowerUsers(this.personalInfoController.checkUserLevel(this.userID,this.employeeList),
                this.loginList,this.employeeList)) {
            result.append(i).append("\n");
        }
        return result.toString();
    }

    //=====================================

    // === Case (viii) Change employee's information ===
    public String setEmployeeInfo(String userID, String option, String response){
        // option {1: Department, 2: Level, 3: setWage, 4. Position}
        if (this.personalInfoController.setEmployeeInfo(userID, option, response, this.employeeList)){
            return "Set employee information success";
        }
        else{
            return "Invalid option or response, or the employee does not exist!";
        }
    }
    //=====================================

    // === Case (X) Extend a Work
    // Before following method call, we will firstly call the method "showAllWorkLead".
    public boolean extendWork(String days, String workID) {
        if (this.verifierController.verifyLeader(this.userID, workID, this.groupList)) {
            this.workManagerController.extendWork(workID, days, this.workList);
            return true;
        }
        return false;
    }
    //=====================================
}
