package main.InterfaceAdapter;

import main.UsesCases.*;


import java.io.IOException;

import java.util.ArrayList;
import java.util.*;





public class FacadeSys {

    // === Instance Variables ===
    private String employeeType;
    // === DataFile ===
    private final DataGateway fileGateway;
    private final ILoginList loginList;
    private final LoginListController loginListController;
    private final IEmployeeList employeeList;
    private final EmployeeListController employeeListController;
    private final IWorkList workList;
    private final IGroupList groupList;
    private final String userID;
    private final PersonalInfoController personalInfoController;
    private final VerifierController verifierController;
    private final WorkManagerController workManagerController;



    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSys(String userID) {
        this.loginList = new LoginList();
        this.loginListController = new LoginListController(this.loginList);
        this.employeeList = new EmployeeList();
        this.employeeListController = new EmployeeListController(this.employeeList);
        this.workList = new WorkList();
        this.groupList = new GroupList();
        this.fileGateway = new DataGateway(this.loginList, this.employeeList, this.groupList, this.workList);
        this.userID = userID;
        this.workManagerController = new WorkManagerController();
        this.personalInfoController = new PersonalInfoController();
        this.verifierController = new VerifierController();
    }


    // === System methods ===


    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        this.fileGateway.ReadInputFileToWorkList();
        this.fileGateway.ReadInputFileToGroupList();
        return this.verifierController.verifyLogin(username, password, this.loginList);
    }

    public void systemEnd() throws IOException, ClassNotFoundException {
        this.fileGateway.WriteOutputFile();
    }

    // === Personal UI Method ===
    public String personalInfo(){
        StringBuilder result = new StringBuilder("");
        ArrayList<String> infos = this.personalInfoController.personalInfo(this.loginList,
                this.employeeList, this.userID);
        result.append(infos.get(0)).append(infos.get(1)).append(infos.get(3)).append(infos.get(4))
                .append(infos.get(5));
        if (this.verifierController.verifyFullTime(this.userID, this.employeeList)) {
            result.append(infos.get(6));
        }
        return result.toString();
    }

    public String checkTotalSalary(){
        return this.personalInfoController.checkTotalSalary(this.employeeList, this.userID, this.groupList, this.workList);
    }
    public String checkMinimumWage(){
        return this.personalInfoController.checkMinimumWage(this.employeeList, this.userID);
    }
    public String checkVacationBonus(){
        return this.personalInfoController.checkVacationBonus(this.employeeList, this.userID);
    }
    public String checkKPIBonus(){
        return this.personalInfoController.checkKPIBonus(this.employeeList, this.userID, this.groupList, this.workList);
    }

    public String setPersonalInfo(String option, String response){
        if (this.personalInfoController.setPersonalInfo(option, response, this.loginList, this.userID)){
            return "Set personal information success!";
        }
        else{
            return "Invalid option or response!";
        }
    }

    public String setEmployeeInfo(String userID, String option, String response){
        if (this.personalInfoController.setEmployeeInfo(userID, option, response, this.employeeList)){
            return "Set employee information success";
        }
        else{
            return "Invalid option or response, or the employee does not exist!";
        }
    }

    public String checkVacation() {
        if (!this.verifierController.verifyFullTime(this.userID, this.employeeList)) {
            return "Not full time worker";
        }
        ArrayList<String> info = this.personalInfoController.personalInfo(this.loginList,
                this.employeeList, this.userID);
        return info.get(8) + info.get(9);
    }
    // ==================================================

    // ================== Work UI Method ================
    public String showAllWorkNeedToDo() {
       return this.workManagerController.showAllWorkNeedToDo(this.userID, this.groupList, this.workList);
    }
    public String showWorkDetail(String workID) {
        return this.workManagerController.showWorkDetail(workID, this.workList);
    }

    public String showAllWorkLed(){
        return this.workManagerController.showAllWorkLed(this.userID, this.groupList, this.workList);
    }


    public String showAllLowerWork() {
        return this.workManagerController.showAllLowerWork(this.userID, this.workList);
    }


    public boolean assignLeaderToWork(String workID, String leaderID) {
        String workLevel = this.workManagerController.workLevel(workID,workList);
        String leaderLevel = this.personalInfoController.userLevel(leaderID, this.employeeList);
        if (this.workManagerController.checkWorkExist(workID, this.workList) &&
                this.verifierController.verifyLevel(workLevel, this.userID, this.employeeList))
        {
            if (this.verifierController.verifyUserExistence(leaderID, this.loginList) && this.verifierController.verifyLevel(leaderLevel,this.userID,this.employeeList)){
                this.workManagerController.assignLeaderToWork(workID, leaderID, this.groupList);
                return true;
            }
        }
        return false;
    }


    public boolean distributeWork(String workID, String memberID) {
        String memberLevel = this.personalInfoController.userLevel(memberID, this.employeeList);
        if (this.verifierController.verifyLeader(this.userID, workID, this.groupList) &&
                this.verifierController.verifyLevel(memberLevel, this.userID, this.employeeList)) {
            return this.workManagerController.distributeWork(workID,memberID,this.groupList);
        }
        return false;
    }


    public boolean createWork(ArrayList<String> info_list) {
        if (this.verifierController.verifyLevel(info_list.get(4), this.userID, this.employeeList)) {
            this.workManagerController.createWork(info_list, this.workList);
            return true;
        }else{
            return false;
        }
    }

    public boolean extendWork(String days, String workID) {
        if (this.verifierController.verifyLeader(this.userID, workID, this.groupList) ) {
            this.workManagerController.extendWork(workID, days, this.workList);
            return true;
        }
        return false;
    }

//    public boolean checkLeaderOf(String work_id) {
//        return this.workFacade.verifierLeader(this.userID, work_id);
//    }

    public boolean checkWorkExist(String workID) {
        return this.workManagerController.checkWorkExist(workID, this.workList);
    }


    // Here are some method used to show other user information, may used in hr workers or work distribute

    // Case 6: Create a user
    public boolean createUser(String name, String password,  String phone, String address,
                              String department, String wage, String position, String level, String status) {
        boolean validLevelGiven = this.verifierController.validToCreate(level, this.employeeList, this.userID);
        if (validLevelGiven){
            this.loginListController.addUser(name, password,phone, address);
            this.employeeListController.addEmployee(department,wage,position,level,status, name);
        }
        return validLevelGiven;
    }
    // ==================================

    // Case 7: Delete a user
    public boolean deleteUser(String targetUserID) {
        boolean validLevelGiven = this.verifierController.verifyUserExistence(targetUserID, this.loginList ) &&
                this.verifierController.validToDelete(targetUserID, this.employeeList, this.userID);
        if (validLevelGiven) {
            this.employeeListController.deleteEmployee(userID);
            this.loginListController.deleteUser(userID);
            this.workManagerController.removeFromAll(userID, this.groupList);
        }
        return validLevelGiven;
    }
    // ==================================

    // Case 8: Check all lower level employees' salary-related information
    public List<String> checkLowerEmployeeSalary(String id, String option) {
        return this.accountFacade.lowerEmployeeCheck(id, option); // Todo
    }

    public String showAllLowerUser() {
        UserManager u = new UserManager();
        StringBuilder result = new StringBuilder("");
        for (String i : u.getLowerUsers(this.personalInfoController.userLevel(this.userID,this.employeeList),
                this.loginList,this.employeeList)) {
            result.append(i).append("\n");
        }
        return result.toString();
    }

    //=====================================
    public boolean levelVerifier(String otherID) {
        try {
            this.verifierController.verifyLevel(this.personalInfoController.userLevel(this.userID,this.employeeList),
                    otherID,this.employeeList);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}