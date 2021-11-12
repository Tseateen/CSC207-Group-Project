package main.InterfaceAdapter;

import main.Entity.*;
import main.UsesCases.*;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class FacadeSysNew {
    private final DataGateway fileGateway;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final Verifier verifier;
    private final WorkList workList;
    private final GroupList groupList;
    private final JournalList journalList;
    private final String username;
    // === AccountFacade ===
    private final AccountFacadeNew accountFacade;
    // === WorkFacade ===
    private final WorkFacadeNew workFacade;
    private final GroupManager groupManager;
    private final WorkManager workManager;

    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSysNew(String username) {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.verifier = new Verifier(this.loginList);
        this.fileGateway = new DataGateway(this.loginList, this.employeeList);
        this.accountFacade = new AccountFacadeNew(this.loginList, this.employeeList,username);
        this.workList = new WorkList();
        this.groupManager = new GroupManager();
        this.groupList = new GroupList();
        this.journalList = new JournalList();
        this.workManager = new WorkManager();
        this.username = username;

        this.workFacade = new WorkFacadeNew(this.workList, this.loginList, this.employeeList, this.groupList, this.workManager, this.groupManager);
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
        return accountFacade.getPersonalInfo(username)[];
    }


    public String checkSelfSalary() {
        return accountFacade.getPersonalInfo(username)[];
    }


    public boolean changePersonalInfo(String option, String changeTo) {
        return accountFacade.changeInfo(username, option, changeTo);
    }


    public String checkVacation() {
        return accountFacade.getPersonalInfo(username)[];
    }


    public String takeVacation(int days) {
        if (accountFacade.takeVacation(username, days)) {
            return accountFacade.getPersonalInfo(username)[];
        }
        return "";
    }

    //=========================
}
