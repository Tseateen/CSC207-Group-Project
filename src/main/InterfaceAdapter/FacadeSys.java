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
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final Verifier verifier;
    private final WorkList workList;
    private final GroupList groupList;
    private final String username;
    private String userID;
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
        this.fileGateway = new DataGateway(this.loginList, this.employeeList, this.groupList, this.workList);
        this.username = username;
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList,this.username);
        this.workFacade = new WorkFacade(this.workList, this.groupList);
    }


    // === System methods ===


    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        this.fileGateway.ReadInputFileToWorkList();
        this.fileGateway.ReadInputFileToGroupList();
        this.employeeType = this.accountFacade.getEmployeeType();
        this.userID = this.accountFacade.getUserID();
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
        this.workFacade.extendWork( work_id, days);
    }

//    public boolean checkLeaderOf(String work_id) {
//        return this.workFacade.verifierLeader(this.userID, work_id);
//    }

    public boolean checkWorkExist(String work_id) {
        return this.workFacade.workExist(work_id);
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