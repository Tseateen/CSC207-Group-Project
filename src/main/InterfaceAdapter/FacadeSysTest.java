package main.InterfaceAdapter;

import main.Entity.Userable;
import main.UsesCases.*;
import main.UsesCases.AccountFacade;

import java.util.*;

import java.util.Scanner;


public class FacadeSysTest<T> {
    private final DataGateway fileGateway;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    // === AccountFacade ===
    private final AccountFacade accountFacade;
    // === WorkFacade ===
    private final WorkFacade workFacade;

    public FacadeSysTest() {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.fileGateway = new DataGateway(this.loginList, this.employeeList);
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList);
        this.workFacade = new WorkFacade();
    }

    // === System methods ===

    public boolean systemStart(String accountNumber, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        return this.accountFacade.VerifyForThisLogin(accountNumber, password);
    }

    public void systemEnd() {
        this.fileGateway.WriteOutputFile();
    }

    // === Personal UI Method ===
    public void partTimePersonalInfo(){
        ArrayList<String> info = accountFacade.partTimeEmployeeInfo();
        String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                + "\n Department: " + info.get(6);
        System.out.println(presentInfo);
    }

    public void fullTimePersonalInfo(){
        ArrayList<String> info = accountFacade.FullTimeEmployeeInfo();
        String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                + "\n Department: " + info.get(6) + "\n Position: " + info.get(7) + "\n State: " +info.get(8);
        System.out.println(presentInfo);
    }
    public void checkPartTimeSalary() {
        String presentWage = String.valueOf(accountFacade.partTimeSalary());
        System.out.println(presentWage);
    }

    public void checkFullTimeSalary(){
        String presentWage = String.valueOf(accountFacade.fullTimeSalary());
        System.out.println(presentWage);
    }
    public void setPartTimePersonalInfo() {
        Scanner keyIn = new Scanner(System.in);
        System.out.println("i) Change your name, please type 1; " + "\n" +
                "ii) Change your password, please type 2; " + "\n" +
                "iii) Change your phone number, please type 3; " + "\n" +
                "iv) Change your address, please type 4" + "\n" +
                "v) Change you attendence, please type 5");
        String option = keyIn.nextLine();
        System.out.println("Please type the value you want to change:");
        String response = keyIn.nextLine();
        accountFacade.setPartTimeBasicInfo(option, response);
        System.out.println("Set personal info success!");
    }

    public void setFullTimePersonalInfo(){
        Scanner keyIn = new Scanner(System.in);
        System.out.println("i) Change your name, please type 1; " + "\n" +
                "ii) Change your password, please type 2; " + "\n" +
                "iii) Change your phone number, please type 3; " + "\n" +
                "iv) Change your address, please type 4" + "\n" +
                "v) Change you attendence, please type 5");
        String option = keyIn.nextLine();
        System.out.println("Please type the value you want to change:");
        String response = keyIn.nextLine();
        accountFacade.setFullTimeBasicInfo(option, response);
        System.out.println("Set personal info success!");
    }

    public void vacationRequire() {
        // Todo: Used to get vacation
    }

    // ==================== Work UI Method ==============

    public void checkWorkInfo() {
        System.out.println(workFacade.SelfWork(accountFacade.user()));
        System.out.println("You only can check your work here.\n" +
                "If you want check your work detail, please type its id\n ");
        VisitStep("check");
        /** new
         * Todo: If this user is any work's leader, show it out differently.
         */


    }


    public void createWork() {
        System.out.println("Please entry work's level which you want to create.");
        VisitStep("create");
    }


    private void Creator(String level) {
        if (!levelVerifier(level)) {
            System.out.println("Work level verifier fail, please try again.");
            return;
        }
        Scanner keyIn = new Scanner(System.in);
        System.out.println();// Todo: 写一句话说输入文件所需的格式 ("workType name id createTime level")，用空格隔开。

        String action = keyIn.nextLine();
        System.out.println(workFacade.workCreate(action));
    }


    private boolean levelVerifier(String level) {
        try {
            if (level.length() != 1) {return false;}
            int a = Integer.parseInt(level);
            return a > accountFacade.user_level();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void CreateLeader() {
        /**
         * Todo: Give a leader to a non-leader lower level work.
         * 1. Show all non-leader works which is in same department and lower level
         * 2. Let them choose the work based on work id
         * 3.Ask him did he need to know about employees information who can be invoked be him
         *   This part may be implemented by calling AllWorker below.
         * 4. Let them choose group leader
         */
    }


    public void distributeWork(Userable user) {
        System.out.println(workFacade.AllWork(user)); // All work: show works that level lower than them
        /** Todo: How to distribute works?
         * 1. Show all works that this user lead
         * 2. Let them choose the work based on work id (they only can choose work that they lead)
         * 3. Ask him did he need to know about employees information who can be invoked be him
         *    This part may be implemented by calling AllWorker below.
         * 4. Let them choose group members
         */

    }


    public void KPIgiver() {
        /**
         * Todo: This part is used by leader to give kpi to their subordinates
         * However, Im not really sure about who can give this kpi, user's leader or group's leader?
         * When you implement this method pls think about it.
         */
    }


    public void WorkUpdate() {
        /** Todo: This one is designed for employee to report their work progress
         *  we need to record their message to Journal. And if the work finished,
         *  only leader!!! can to change work's statues to 'Finished'. There may be some more steps needed.
         *  also, if the user is this work's leader, they can choose to extend due date
         */
    }

    private void VisitStep(String method) {
        Scanner keyIn = new Scanner(System.in);
        System.out.println("Type E to exit.");
        boolean noExit = true;
        while (noExit) {
            String action = keyIn.nextLine();
            if ("E".equals(action)) {
                noExit = false;
            } else {
                if (method.equals("check")) {System.out.println(workFacade.WorkDetail(action));}
                if (method.equals("create")) {Creator(action);}
            }
        }
    }

    // Here are some method used to show other user information, may used in hr workers or work distribute

    public void AllWorkers() {
        /** Todo: Show all workers whose authority level are lower than this user and department are same
         *  with this user.
         *  Id need to be shown, because user need to tap id to choose user, but what else infom we need?
         *  Ex. name, level...
         */
    }

    public void UserCreator(String level) {
        // Todo: this part is similar with workCreator, so new user's level shouldn't higher than creator
        // Todo: or we may need to default new user's level as 9.
        // Todo: to verify the type of user input and make sure the type match the parameters of CreateNewAccount
        // This part may use Creator
        if (!levelVerifier(level)) {
            System.out.println("Authority level verifier fail, please try again.");
            return;
        }
        System.out.println("Please assigned the username to this new account");
        Scanner keyIn = new Scanner(System.in);
        String username = keyIn.nextLine();
        System.out.println();
        System.out.println("Please assigned the password to this new account");
        String password = keyIn.nextLine();
        System.out.println();
        System.out.println("Please enter the name of this new user");
        String name = keyIn.nextLine();
        System.out.println();
        System.out.println("Please enter the phone number of this new user");
        String phone = keyIn.nextLine();
        System.out.println();
        System.out.println("Please enter the address of this user");
        String address = keyIn.nextLine();
        System.out.println();
        System.out.println("Please enter the department this user belongs to");
        String department = keyIn.nextLine();
        System.out.println();
        System.out.println("Please enter the initial monthly wage of this user");
        int wage = Integer.parseInt(keyIn.nextLine());
        System.out.println();
        System.out.println("Please enter the work position of this user");
        String position = keyIn.nextLine();
        System.out.println();
        System.out.println("Please assigned this user an authority level");
        int userlevel = Integer.parseInt(keyIn.nextLine());
        System.out.println();
        System.out.println("Please enter the status of this user (Full-time: F, Part-time: P)");
        String status = keyIn.nextLine();
        System.out.println();

        AccountFacade.CreateNewAccount(username, password, name, phone, address, department, wage, position, userlevel, status);

    }

    public void UserDelete() {
        /**
         * Todo: Used to delete a user, can't delete user who has higher level.
         * We may need to use memento for delete User
         */
    }

    public void UserWorkInfoChange() {
        // Todo: Used to change a worker's level, department, position, salary, vacation; can't ...
    }

    public void SalaryCheck() {
        /**
         * Todo: Show all(maybe some) other department same level workers' and lower level hr workers'
         * salary, work days, vacation used, reward, and kpi. Hr worker need to comfirm all those info
         */
    }
}

