package main.InterfaceAdapter;

import main.UsesCases.*;

import java.util.Scanner;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


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
    public void personalInfo(){
        System.out.println(accountFacade.UserInfo());
    }

    public void checkSalary() {
        System.out.println(accountFacade.UserSalary());
    }

    public void setPersonalInfo() {
        Scanner keyIn = new Scanner(System.in);

        System.out.println("i) Change your address, type 1; " + "\n" +
                "ii) Change your phone number, type 2; " + "\n" +
                "iii) Change password, type 3; " + "\n" +
                "iv) Back to main page, type other");
        String action = keyIn.nextLine();
        System.out.println(accountFacade.AccountChange(action));
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
        System.out.println("Please entry work's level which you want to creat.");
        VisitStep("creat");
    }


    private void Creator(String level) {
        if (!levelVerifier(level)) {
            System.out.println("Work level verifier fail, please try again.");
            return;
        }
        Scanner keyIn = new Scanner(System.in);
        System.out.println();// Todo: 写一句话说输入文件所需的格式，用空格隔开。
        String action = keyIn.nextLine();
        System.out.println(workFacade.workCreat(action));
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

    public void CreatLeader() {
        /**
         * Todo: Give a leader to a non-leader lower level work.
         * 1. Show all non-leader works which is in same department and lower level
         * 2. Let them choose the work based on work id
         * 3.Ask him did he need to know about employees information who can be invoked be him
         *   This part may be implemented by calling AllWorker below.
         * 4. Let them choose group leader
         */
    }


    public void distributeWork() {
        System.out.println(workFacade.AllWork()); // All work: show works that level lower than them
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
                if (method.equals("creat")) {Creator(action);}
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

    public void UserCreator() {
        // Todo: this part is similar with workCreator, so new user's level shouldn't higher than creator
        // Todo: or we may need to default new user's level as 9.
        // This part may use Creator
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

