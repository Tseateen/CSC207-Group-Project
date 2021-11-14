package main.Framework;

import main.InterfaceAdapter.*;

import java.util.List;
import java.util.Scanner;

public class WorkManagerUI {

    private final FacadeSys facadeSys;

    public WorkManagerUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    public void run(){
        Scanner keyIn = new Scanner(System.in);

        WorkInfoUI workInfoUI = new WorkInfoUI(this.facadeSys);
        CreateWorkUI createWorkUI = new CreateWorkUI(this.facadeSys);
        PrepareForWorkUI prepareForWork = new PrepareForWorkUI(this.facadeSys);
        DistributeWorkUI distributeWork = new DistributeWorkUI(this.facadeSys);
        KPIAssignUI kpiAssignUI = new KPIAssignUI(this.facadeSys);
        CreateUserUI createUserUI = new CreateUserUI(this.facadeSys);
        DeleteUserUI deleteUserUI = new DeleteUserUI(this.facadeSys);
        CheckSalaryUI checkSalaryUI = new CheckSalaryUI(this.facadeSys);

        boolean noExit = true;
        while (noExit) {
            System.out.println(
                    "i) Check your own work's information, type 1; " + "\n" +
                            "ii) Create a work, type 2; " + "\n" +
                            "iii) Start a work with assigning leader, type 3; " + "\n" +
                            "iv) Distribute a work, type 4; " + "\n" +
                            "v) Assign KPI to members, type 5; "  + "\n" +
                            "vi) Create a user, type 6; "  + "\n" +
                            "vii) Delete a user, type 7; "  + "\n" +
                            "viii) Check all lower level employees' salary-related information, type 8" + "\n" +
                            "ix) Change a user's information, type 9; "  + "\n" +
                            "xx) Back to main page, type E");
            String action = keyIn.nextLine();
            switch (action) {
                case "1":
                    workInfoUI.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "2":
                    createWorkUI.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "3":
                    // TODO: 為什麼 create 完 work 才assign leader?會不會有Work 沒有Leader?
                    // TODO: 剩一點點未完成
                    prepareForWork.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "4":
                    distributeWork.run();
                    break;
                case "5":
                    kpiAssignUI.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "6":
                    createUserUI.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "7":
                    deleteUserUI.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "8":
                    checkSalaryUI.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "9":
                    System.out.println("Please enter the User ID that you want to change information to:");
                    String userid = keyIn.nextLine();
                    System.out.println("i) Change the user's Department, please type 1; " + "\n" +
                            "ii) Change the user's Wage, please type 2; " + "\n" +
                            "iii) Change the user's Level, please type 3; " + "\n" +
                            "iv) Change the user's Position (only apply to Full Time Employee), please type 4" + "\n" +
                            "v) Change the user's State (only apply to Full Time Employee), please type 5"  + "\n" +
                            "vi) Change the user's Total Vacation (only apply to Full Time Employee), please type 6"  + "\n" +
                            "vii) Change the user's Vacation Used (only apply to Full Time Employee), please type 7"  + "\n");
                    String option = keyIn.nextLine();
                    System.out.println("Please enter the new info");
                    String inf = keyIn.nextLine();
                    //facadeSys.UserWorkInfoChange(userid, option, inf);
                    break;
                case "E":
                    noExit = false;
                    break;
                default:
                    System.out.println("Wrong action, please type again");
                    break;
            }
        }
    }
}
