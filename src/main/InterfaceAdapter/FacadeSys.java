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
    private final WorkList workList;
    private final GroupList groupList;
    private final String username;
    private String userID;
    private final PersonalInfoController personalInfoController;
    private final VerifierController verifierController;
    // === AccountFacade ===
    private final AccountFacade accountFacade;
    // === WorkFacade ===
    private final WorkFacade workFacade;
    private final IWorkManager workManager;
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
        this.workManagerController = new WorkManagerController(this.workList,workManager);
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
        return this.personalInfoController.checkTotalSalary(this.employeeList, this.userID);
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
        StringBuilder result = new StringBuilder("");
        for (String i : this.workFacade.workOfMine(this.userID)){
            result.append(i).append("\n");
        }
        return result.toString();
    }
    public String showWorkDetail(String work_id) {
        StringBuilder result = new StringBuilder("");
        for (String i : this.workFacade.showWorkDetail(work_id)){
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public ArrayList<String> showAllWorkLed(){
        return this.workFacade.workOfLed(this.userID);
    }


    public String showAllLowerWork() {
        StringBuilder result = new StringBuilder("");
        for (String i : this.workFacade.workOfLowerLevel(this.userID)){
            result.append(i).append("\n");
        }
        return result.toString();
    }


    public boolean assignLeaderToWork(String work_id, String leader_id) {
        if (this.workFacade.workExist(work_id) && this.levelVerifier(this.workFacade.workLevel(work_id))) {
            if (this.accountFacade.userExists(leader_id) && this.levelVerifier(this.accountFacade.user_Level(leader_id))){
                this.workFacade.assignLeader(work_id, leader_id);
                return true;
            }
        }
        return false;
    }


    public boolean distributeWork(String work_id, String userID) {
        if (this.workFacade.verifierLeader(this.userID, work_id) &&
                this.levelVerifier(this.accountFacade.user_Level(userID))) {
            return this.workFacade.Distributor(work_id, userID);
        }
        return false;
    }


    public void createWork(ArrayList<String> info_list) {
        this.workFacade.workCreator(info_list);
    }



    public void extendWork(String days, String work_id) {
        this.workManagerController.extendWork(work_id, days);
    }

//    public boolean checkLeaderOf(String work_id) {
//        return this.workFacade.verifierLeader(this.userID, work_id);
//    }

    public boolean checkWorkExist(String work_id) {
        return this.workFacade.workExist(work_id);
    }


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