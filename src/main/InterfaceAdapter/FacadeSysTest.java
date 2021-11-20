package main.InterfaceAdapter;

import main.UsesCases.*;


import java.io.IOException;

import java.util.ArrayList;
import java.util.*;





public class FacadeSysTest {
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
    private final AccountFacadeTest accountFacade;
    // === WorkFacade ===
    private final WorkFacade workFacade;



    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSysTest(String username) {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.verifier = new Verifier(this.loginList);
        this.workList = new WorkList();
        this.groupList = new GroupList();
        this.fileGateway = new DataGateway(this.loginList, this.employeeList, this.groupList, this.workList);
        this.username = username;
        this.accountFacade = new AccountFacadeTest(this.loginList, this.employeeList);
        this.workFacade = new WorkFacade(this.workList, this.groupList);
    }


    // === System methods ===


    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        this.fileGateway.ReadInputFileToWorkList();
        this.fileGateway.ReadInputFileToGroupList();
        this.userID = this.accountFacade.getUserID(this.username);
        return this.verifier.verifyForLogin(username, password);
    }

    public void systemEnd() throws IOException, ClassNotFoundException {
        this.fileGateway.WriteOutputFile();
    }

    // === Personal UI Method ===
    public String personalInfo(){
        ArrayList<String> info = this.accountFacade.infoCheck(this.userID);
        return this.listToString(info);
    }

    public boolean setPersonalInfo(String option, String response){
        return this.accountFacade.setUserInfo(this.userID, option, response);
    }


    public String checkVacation() {
        ArrayList<String> info = this.accountFacade.vacationInfo(this.userID);
        return this.listToString(info);
    }
    // ==================================================

    // ================== Work UI Method ================
    public String showAllWorkNeedToDo() {
        ArrayList<String> info = this.workFacade.workOfMine(this.userID);
        return this.listToString(info);
    }
    public String showWorkDetail(String work_id) {
        ArrayList<String> info = this.workFacade.showWorkDetail(work_id);
        return this.listToString(info);
    }

    public ArrayList<String> showAllWorkLed(){
        return this.workFacade.workOfLed(this.userID);
    }


    public String showAllLowerWork() {
        ArrayList<String> info = this.workFacade.workOfLowerLevel(this.userID);
        return this.listToString(info);
    }


    public boolean assignLeaderToWork(String work_id, String leader_id) {
        if (this.workFacade.workExist(work_id) && this.levelVerifier(this.workFacade.workLevel(work_id))) {
            if (this.accountFacade.userExists(leader_id) && this.levelVerifier(this.accountFacade.userLevel(leader_id))){
                this.workFacade.assignLeader(work_id, leader_id);
                return true;
            }
        }
        return false;
    }


    public boolean distributeWork(String work_id, String userID) {
        if (this.workFacade.verifierLeader(this.userID, work_id) &&
                this.levelVerifier(this.accountFacade.userLevel(userID))) {
            return this.workFacade.Distributor(work_id, userID);
        }
        return false;
    }

    public boolean changeWorkInfo(String workID, String opt, String changeTo) {
        return this.workFacade.changeWorkInfo(workID,opt,changeTo);
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
    public boolean AccountVerifier(String username) {
        return Objects.isNull(this.accountFacade.getUserID(username)); // return true if username not exist
    }

    public boolean createUser(String username, String password, String name, String phone, String address,
                              String department, String wage, String position, String level, String status) {
        String[] userinfo = {username, password, name, phone, address, department, wage, position, level, status};
        return this.accountFacade.createNewAccount(userinfo);
    }
    // ==================================

    // Case 7: Delete a user
    public boolean deleteUser(String userid) {
        if (this.levelVerifier(this.accountFacade.userLevel(userid))) {
            if (this.accountFacade.deleteEmployee(userid)){
                this.workFacade.deleteEmployee(userid);
                return true;
            }
            return false;
        }
        return false;
    }
    // ==================================

    // Case 8: Check all lower level employees' salary-related information
    public String checkLowerEmployeeInfo(String id) {
        if (levelVerifier(this.accountFacade.userLevel(id))) {
            ArrayList<String> Info = this.accountFacade.infoCheck(id);
            return this.listToString(Info);
        }
        return "";
    }

    public String showAllLowerUser() {
        ArrayList<String> users = this.accountFacade.getLowerUsers(this.accountFacade.userLevel(this.userID));
        return this.listToString(users);
    }

    public boolean changeEmployeeInfo(String Option, String id, String changeTo) {
        return this.accountFacade.setEmployeeInfo(Option, id, changeTo);
    }

    //=====================================
    public boolean levelVerifier(String level) {
        try {
            if (level.length() != 1) {return false;}
            int num_level = Integer.parseInt(level);
            return num_level > Integer.parseInt(this.accountFacade.userLevel(this.userID));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String listToString(ArrayList<String> list) {
        StringBuilder result = new StringBuilder("");
        for (String i : list){
            result.append(i).append("\n");
        }
        return result.toString();
    }
}