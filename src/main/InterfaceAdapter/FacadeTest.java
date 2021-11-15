package main.InterfaceAdapter;


import main.Entity.*;
import main.UsesCases.*;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class FacadeTest {
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
    private final WorkFacadeNew workFacade;



    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeTest(String username) {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.verifier = new Verifier(this.loginList);
        this.workList = new WorkList();
        this.groupList = new GroupList();
        this.journalList = new JournalList();
        this.fileGateway = new DataGateway(this.loginList, this.employeeList, this.groupList, this.workList);
        this.username = username;
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList,this.username);

        this.workFacade = new WorkFacadeNew(this.workList, this.loginList, this.employeeList, this.groupList, this.journalList);
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

    // ===========================

    public String checkSelfInfo() {
        return this.accountFacade.getPersonalInfo(this.username)[];
    }


    public String checkSelfSalary() {
        return this.accountFacade.getPersonalInfo(this.username)[];
    }


    public boolean changePersonalInfo(String option, String changeTo) {
        return this.accountFacade.changeInfo(this.username, option, changeTo);
    }


    public String checkVacation() {
        return this.accountFacade.getPersonalInfo(this.username)[];
    }


    public String takeVacation(int days) {
        if (this.accountFacade.takeVacation(this.username, days)) {
            return this.accountFacade.getPersonalInfo(this.username)[];
        }
        return "";
    }

    //=========================

    public String showAllWorkNeedToDo() {
        String result = "";
        for (String i : this.workFacade.workOfMine(this.username)){
            result = result + i + "\n";
        }
        return result;
    }
    public String showWorkDetail() {

    }

    public String showAllWorkLed(){
        String result = "";
        for (String i : this.workFacade.workOfLed(this.username)){
            result = result + i + "\n";
        }
        return result;
    }


    public String showAllLowerWork() {
        String result = "";
        for (String i : this.workFacade.workOfLowerLevel(this.username)){
            result = result + i + "\n";
        }
        return result;
    }

    public String showDetail(String work_id) {
        if (levelVerifier(this.workFacade.workLevel(work_id))||this.workFacade.isMember(work_id, this.username)) {
            String result = "";
            for (String i : this.workFacade.showWorkDetail(work_id)){
                result = result + i + "\n";
            }
            return result;
        }
        return "Can't check";
    }


    public boolean assignLeaderToWork(String work_id, String leader_id) {
        if (this.workFacade.workExist(work_id) && this.levelVerifier(this.workFacade.workLevel(work_id))) {
            if (this.accountFacade.userExist(leader_id) && this.levelVerifier(this.accountFacade.userLevel(leader_id))){
                this.workFacade.assignLeader(work_id, leader_id, username);
                return true;
            }
        }
        return false;
    }


    public boolean distributeWork(String work_id, String user_id) {
        if (this.workFacade.verifierLeader(username, work_id) &&
                this.levelVerifier(this.accountFacade.userLevel(user_id))) {
            return this.workFacade.Distributer(username, work_id, user_id);
        }
        return false;
    }


    public void createWork(ArrayList<String> info_list) {
        this.workFacade.workCreator(this.username, info_list);
    }


    public boolean updateWork(String option, String work_id, String result) {
        return this.workFacade.Updater(this.username, option, work_id, result);
    }


    public void extendWork(String days, String work_id) {
        this.workFacade.extendWork( this.username,work_id, days);
    }

    public boolean checkLeaderOf(String work_id) {
        return this.workFacade.verifierLeader(this.username, work_id);
    }

    public boolean checkWorkExist(String work_id) {
        return this.workFacade.workExist(work_id);
    }

    //=============================

    public String[] showAllLowerUser() {
        return this.accountFacade.showLowerUsers(this.username);
    }


    public String showSpecificUser(String user_id) {
        if (this.accountFacade.userExist(user_id) && this.levelVerifier(this.accountFacade.userLevel(user_id))) {
            return this.accountFacade.getPersonalInfo(user_id);
        }
        return "";
    }


    public boolean createUser(String[] user_info) {
        return this.accountFacade.creator(this.username, user_info);
    }


    public boolean deleteUser(String work_id){
        return this.accountFacade.deleter(this.username, work_id);
    }


    //===============================

    public boolean levelVerifier(String level) {
        try {
            if (level.length() != 1) {return false;}
            int num_level = Integer.parseInt(level);
            return num_level > this.accountFacade.userLevel(this.username);
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
