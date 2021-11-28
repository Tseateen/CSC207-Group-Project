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
    private final IVerifier verifier;
    private final IWorkList workList;
    private final IGroupList groupList;
    private final String username;
    private String userID;
    private final PersonalInfoController personalInfoController;
    private final VerifierController verifierController;
    // === AccountFacade ===
    private final AccountFacade accountFacade;
    // === WorkFacade ===
    private final WorkFacade workFacade;
    private final IWorkManager workManager;
    private final IGroupManager groupManager;
    private final WorkManagerController workManagerController;
    private final IPersonalManager personalManager;



    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSys(String username) {
        this.loginList = new LoginList();
        this.loginListController = new LoginListController(this.loginList);
        this.employeeList = new EmployeeList();
        this.employeeListController = new EmployeeListController(this.employeeList);
        this.workList = new WorkList();
        this.groupList = new GroupList();
        this.fileGateway = new DataGateway(this.loginList, this.employeeList, this.groupList, this.workList);
        this.username = username;
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList,this.username);
        this.workFacade = new WorkFacade(this.workList, this.groupList);
        this.workManager = new WorkManager();
        this.groupManager = new GroupManager();
        this.workManagerController = new WorkManagerController(this.workManager, this.groupManager);
        this.personalManager = new PersonalManager(this.loginList, this.employeeList, this.userID, this.groupList, this.workList);
        this.personalInfoController = new PersonalInfoController(this.personalManager);
        this.verifier = new Verifier(this.username, this.loginList, this.employeeList);
        this.verifierController = new VerifierController(new Verifier(this.username, this.loginList, this.employeeList));
    }


    // === System methods ===


    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        this.fileGateway.ReadInputFileToWorkList();
        this.fileGateway.ReadInputFileToGroupList();
        this.employeeType = this.accountFacade.getEmployeeType();
        this.userID = this.accountFacade.getUserID();
        return this.verifierController.verifyLogin(username, password, this.loginList);
    }

    public void systemEnd() throws IOException, ClassNotFoundException {
        this.fileGateway.WriteOutputFile();
    }

    // === Personal UI Method ===
    public String personalInfo(){
        return this.personalInfoController.personalInfo(this.loginList, this.employeeList, this.userID);
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
        return this.personalInfoController.checkKPIBonus(this.employeeList, this.userID);
    }

    public String setPersonalInfo(String option, String response){
        if (this.personalInfoController.setPersonalInfo(option, response, this.loginList, this.username)){
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

    public void checkVacation() {
        int[] infoInt = this.accountFacade.getFullTimeEmployeeInfoInt();
        String vacationInfo = "Total Vacation with Salary: " + infoInt[0] + "\n  Vacation Used: " + infoInt[1];
        System.out.println(vacationInfo);
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

    public void extendWork(String days, String workID) {
        this.workManagerController.extendWork(workID, days, this.workList);
    }

//    public boolean checkLeaderOf(String work_id) {
//        return this.workFacade.verifierLeader(this.userID, work_id);
//    }

    public boolean checkWorkExist(String workID) {
        return this.workManagerController.checkWorkExist(workID, this.workList);
    }//D


    // Here are some method used to show other user information, may used in hr workers or work distribute

    // Case 6: Create a user
    public boolean createUser(String name, String password,  String phone, String address,
                              String department, String wage, String position, String level, String status) {
        boolean validLevelGiven = this.verifierController.validToCreate(level, this.employeeList, this.userID);
        if (validLevelGiven){
            this.loginListController.addUser(name, password,phone, address);
            this.employeeListController.addEmployee(department,Integer.parseInt(wage),position,Integer.parseInt(level),status, name);
        }
        return validLevelGiven;
    }
    // ==================================

    // Case 7: Delete a user
    public boolean deleteUser(String targetUserID) {
        boolean validLevelGiven = this.verifierController.verifyUserExistence(targetUserID, this.loginList ) &&
                this.verifierController.validToDelete(targetUserID, this.employeeList, this.userID);
        if (validLevelGiven) {
            this.accountFacade.DeleteAccount(userID);
        }
        return validLevelGiven;
    }
    // ==================================

    // Case 8: Check all lower level employees' salary-related information
    public List<String> checkLowerEmployeeSalary(String id, String option) {
        return this.accountFacade.lowerEmployeeCheck(id, option);
    }

    public String showAllLowerUser() {
        StringBuilder result = new StringBuilder("");
        for (String i : this.accountFacade.getLowerUsers(accountFacade.user_Level(this.userID))) {
            result.append(i).append("\n");
        }
        return result.toString();
    }

    //=====================================
    public boolean levelVerifier(String level) {
        try {
            if (level.length() != 1) {return false;}
            int num_level = Integer.parseInt(level);
            return num_level > Integer.parseInt(this.accountFacade.user_Level(this.userID));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}