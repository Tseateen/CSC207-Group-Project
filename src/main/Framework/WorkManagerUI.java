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
        CreateUserUI createUserUI = new CreateUserUI(this.facadeSys);
        DeleteUserUI deleteUserUI = new DeleteUserUI(this.facadeSys);
        CheckSalaryUI checkSalaryUI = new CheckSalaryUI(this.facadeSys);

        boolean noExit = true;
        while (noExit) {
            System.out.println(
                    "i) Check your own work's information, enter 1; " + "\n" +
                            "ii) Create a work, enter 2; " + "\n" +
                            "iii) Start a work with assigning leader, enter 3; " + "\n" +
                            "iv) Distribute a work, enter 4; " + "\n" +
                            "v) Create a user, enter 5; "  + "\n" +
                            "vi) Delete a user, enter 6; "  + "\n" +
                            "vii) Check all lower level employees' salary-related information, enter 7" + "\n" +
                            "viii) Back to main page, enter E " + "\n");
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
                    prepareForWork.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "4":
                    distributeWork.run();
                    break;
                case "5":
                    createUserUI.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "6":
                    deleteUserUI.run();
                    System.out.println("Successfully back to main WorkUI");
                    break;
                case "7":
                    checkSalaryUI.run();
                    System.out.println("Successfully back to main WorkUI");
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
