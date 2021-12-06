package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class SetEmployeeInfoUI {

    // === Instance Variables ===
    private final FacadeSys facadeSys;


    /**
     * Construct a SetPersonalInfoUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public SetEmployeeInfoUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    /**
     * Run the SetPersonalInfoUI
     */
    public void run() {
        Scanner keyIn = new Scanner(System.in);
        boolean noExit = true;

        while (noExit) {
            System.out.println("Please type the employee's ID you want to change:");
            String employeeID = keyIn.nextLine();
            System.out.println(
                    "i) Change employee's department, please type 1; " + "\n" +
                            "ii) Change employee's level, please type 2; " + "\n" +
                            "iii) Change employee's wage, please type 3; " + "\n" +
                            "iv) Change employee's position, please type 4");
            String option = keyIn.nextLine();
            if (option.equals("1") || option.equals("2") || option.equals("3")
                    || option.equals("4")) {
                System.out.println("Please type the value you want to change:");
                String response = keyIn.nextLine();

                System.out.println(this.facadeSys.setEmployeeInfo(employeeID,option, response));

                System.out.println("If you want to exist setting on other information, type E. " +
                        "Otherwise, type any other button to continuous setting.");
                String action = keyIn.nextLine().toUpperCase();
                if (action.equalsIgnoreCase("E")) {
                    noExit = false;
                }
            }else {
                System.out.println("Option does not exist. Please type again!\n");
            }
        }
    }
}
