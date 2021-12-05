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
     *
     * @param userID the ID of the target user.
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

    /**
     * Log in method to the system.
     *
     * @param username the username from the input of the targeted user.
     * @param password the password from the input of the targeted user.
     *
     * @return true iff the user can successfully log in.
     *
     */
    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList(this.loginList);
        this.fileGateway.ReadInputFileToEmployeeList(this.employeeList);
        this.fileGateway.ReadInputFileToWorkList(this.workList);
        this.fileGateway.ReadInputFileToGroupList(this.groupList);
        return this.verifierController.verifyLogin(username, password, this.loginList);
    }


    /**
     * Method for ending the system.
     *
     */

    public void systemEnd(){
        this.fileGateway.WriteOutputFile(this.loginList, this.employeeList, this.workList, this.groupList);
    }

    // === Personal UI Method ===

    // === Case (i) Check your personal Info ====


    /**
     * Manage the personal information.
     *
     * @return the strings with personal information.
     *
     */
    public String personalInfo(){
        StringBuilder result = new StringBuilder();
        ArrayList<String> infos = this.personalInfoController.personalInfo(this.loginList,
                this.employeeList, this.userID);
        // 0: Name, 1:ID, 3:Phone Number , 4:Address, 5: Department 6: Work hours (PartTime)/ position (Full Time)
        result.append(infos.get(0)).append(infos.get(1)).append(infos.get(3)).append(infos.get(4))
                .append(infos.get(5)).append(infos.get(6));

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


    /**
     * Check the total salary for the user.
     *
     * @return the strings with total salary.
     *
     */
    public String checkTotalSalary(){
        return this.personalInfoController.checkTotalSalary(this.employeeList, this.userID, this.groupList, this.workList);
    }

    // ==========================================

    // === Case (iii) Check your minimum wage ====


    /**
     * Check the minimum wage for the user.
     *
     * @return the strings with minimum wage.
     *
     */
    public String checkMinimumWage(){
        return this.personalInfoController.checkMinimumWage(this.employeeList, this.userID);
    }

    // ==========================================

    // === Case (iv) Check your bonus salary from vacation ====


    /**
     * Check the vocation bonus for the user.
     *
     * @return the strings with vocation bonus.
     *
     */
    public String checkVacationBonus(){
        return this.personalInfoController.checkVacationBonus(this.employeeList, this.userID);
    }
    // ==========================================

    // === Case (v) Check your bonus salary from KPI ====


    /**
     * Check the KPI bonus for the user.
     *
     * @return the strings with KPI bonus.
     *
     */
    public String checkKPIBonus(){
        return this.personalInfoController.checkKPIBonus(this.employeeList, this.userID, this.groupList, this.workList);
    }
    // ==========================================

    // === Case (vi) Change personal Information ====


    /**
     * Setter method for the user's personal information.
     *
     * @return the strings with information if the personal information has been successfully set.
     *
     */
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


    /**
     * Show if there is work needed to do for the employee.
     *
     * @return the string of the work needed to do.
     *
     */
    public String showAllWorkNeedToDo() {
       return this.workManagerController.showAllWorkNeedToDo(this.userID, this.groupList, this.workList);
    }


    /**
     * Check the work detail for the employee.
     *
     * @param workID the ID of the Work.
     *
     * @return the strings with work detail.
     *
     */
    public String showWorkDetail(String workID) {
        return this.workManagerController.showWorkDetail(workID, this.workList);
    }


    /**
     * Check if the work existed for the employee.
     *
     * @param workID the ID of the Work.
     *
     * @return true iff the work existed.
     *
     */
    public boolean checkWorkExist(String workID) {
        return this.workListController.checkWorkExist(workID);
    }
    // ==================================================

    // === Case (ii) createWork ====


    /**
     * Create a new Work.
     *
     * @param info_list the information list.
     *
     * @return true iff the work has been created.
     *
     */
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


    /**
     * Assign a leader, i.e. Employee, to the Work.
     *
     * @param workID the ID of the Work.
     * @param leaderID the ID of the leader.
     *
     * @return true iff the leader has been assigned.
     *
     */
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


    /**
     * Show all the work leader of the Work.
     *
     * @return the string with all leaders.
     *
     */
    public String showAllWorkLead(){
        return this.workManagerController.showAllWorkLead(this.userID, this.groupList, this.workList);
    }


    /**
     * Show all the work that has a lower authority level of the targeted employee.
     *
     * @return the string with all the applicable Work.
     *
     */
    public String showAllLowerWork() {
        String level = this.personalInfoController.checkUserLevel(this.userID, this.employeeList);
        return this.workManagerController.showAllLowerWork(level, this.workList);
    }


    /**
     * Distribute the Work to the member.
     *
     * @param workID the ID of the Work.
     * @param memberID the member of the Work.
     *
     * @return true iff the Work has been successfully distributed.
     *
     */
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


    /**
     * Create the User for the system.
     *
     * @param name the name of the user.
     * @param password the password of the user.
     * @param phone the phone number of the user.
     * @param address the address of the user.
     * @param department the department of the user.
     * @param wage the wage of the user.
     * @param position the position of the user.
     * @param level the level of user.
     * @param status the status of the user.
     *
     * @return the list of strings with all the information related to the user.
     *
     */
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


    /**
     * Delete the user from the system.
     *
     * @param targetUserID the ID of the user to be deleted.
     *
     * @return true iff the user has been successfully deleted.
     *
     */
    public boolean deleteUser(String targetUserID) {
        boolean validLevelGiven = this.verifierController.verifyUserExistence(targetUserID, this.loginList ) &&
                this.verifierController.validToDelete(targetUserID, this.employeeList, this.userID);
        if (validLevelGiven) {
            this.employeeListController.deleteEmployee(targetUserID);
            this.loginListController.deleteUser(targetUserID);
            this.groupManagerController.removeEmployeeFromAllRelatedGroup(targetUserID, this.groupList);
        }
        return validLevelGiven;
    }
    // ==================================================

    // Case 7 Check all lower level employees' salary-related information


    /**
     * Check the salaries of the employees who have the lower level of authority from the targeted user.
     *
     * @param userID the ID of the targeted user.
     *
     * @return a list of strings with the information about salaries of the applicable users.
     *
     */
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


    /**
     * Show all users with the lower authority than the targeted user.
     *
     * @return a string with the users that have lower level.
     *
     */
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

    /**
     * Setter method for the employee's information.
     *
     * @param userID the ID of the user to be modified.
     * @param option the option of the user.
     * @param response the new information.
     *
     * @return the string message if the information has been successfully changed.
     *
     */
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

    /**
     * Extend the Work deadline.
     *
     * @param days the days to be extended.
     * @param workID the ID of the targeted Work.
     *
     * @return true iff the Work has been successfully extended.
     *
     */
    public boolean extendWork(String days, String workID) {
        if (this.verifierController.verifyLeader(this.userID, workID, this.groupList) && this.workList.checkWorkExist(workID)) {
            this.workManagerController.extendWork(workID, days, this.workList);
            return true;
        }
        return false;
    }
    //=====================================
}
