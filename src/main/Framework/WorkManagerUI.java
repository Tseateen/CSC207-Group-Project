package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class WorkManagerUI {

    // === Instance Variables ===
    private final FacadeSys facadeSys;


    /**
     * Construct a WorkManagerUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public WorkManagerUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    /**
     * Run the WorkManagerUI
     */
    public void run(){
        Scanner keyIn = new Scanner(System.in);

        WorkInfoUI workInfoUI = new WorkInfoUI(this.facadeSys);
        CreateWorkUI createWorkUI = new CreateWorkUI(this.facadeSys);
        PrepareForWorkUI prepareForWork = new PrepareForWorkUI(this.facadeSys);
        DistributeWorkUI distributeWork = new DistributeWorkUI(this.facadeSys);
        CreateUserUI createUserUI = new CreateUserUI(this.facadeSys);
        DeleteUserUI deleteUserUI = new DeleteUserUI(this.facadeSys);
        SetEmployeeInfoUI setEmployeeInfoUI = new SetEmployeeInfoUI(this.facadeSys);
        CheckSalaryUI checkSalaryUI = new CheckSalaryUI(this.facadeSys);
        ExtendWorkUI extendWorkUI = new ExtendWorkUI(this.facadeSys);

        boolean noExit = true;
        while (noExit) {
            System.out.println(
                            "i) Check your own work's information, please enter 1; " + "\n" +
                            "ii) Create a work, please enter 2; " + "\n" +
                            "iii) Start a work with assigning leader, please enter 3; " + "\n" +
                            "iv) Distribute a work, please enter 4; " + "\n" +
                            "v) Create a user, please enter 5; "  + "\n" +
                            "vi) Delete a user, please enter 6; "  + "\n" +
                            "vii) Check all lower level employees' salary-related information, please enter 7" + "\n" +
                            "viii) Change employee's information, please enter 8" + "\n" +
                            "X) Extend a Work, please enter 9" + "\n" +
                            "Xi) Back to main page, please enter E " + "\n");
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
                case "8":
                    setEmployeeInfoUI.run();
                    System.out.println("Successfully back to main WorkUI");;
                    break;
                case "9":
                    extendWorkUI.run();
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
