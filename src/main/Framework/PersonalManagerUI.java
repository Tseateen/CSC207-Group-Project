package main.Framework;

import main.InterfaceAdapter.*;
import java.util.Scanner;

public class PersonalManagerUI {

    // === Instance Variables ===
    private final FacadeSys facadeSys;


    /**
     * Construct a PersonalManagerUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public PersonalManagerUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    /**
     * Run the PersonalManagerUI
     */
    public void run() {
        Scanner keyIn = new Scanner(System.in);

        SetPersonalInfoUI setPersonInfo = new SetPersonalInfoUI(this.facadeSys);
        boolean noExit = true;

        while (noExit) {
            System.out.println(
                            "i) Check your personal Info, type 1 " + "\n" +
                            "ii) Check your total Salary (including bonus), type 2 " + "\n" +
                            "iii) Check your minimum wage, type 3" + "\n" +
                            "iv) Check your bonus salary from vacation, type 4" + "\n" +
                            "v) Check your bonus salary from KPI, type 5" + "\n" +
                            "vi) Change personal Information, type 6 " + "\n" +
                            "vii) Back to main page, type 7" + "\n");
            String action = keyIn.nextLine();
            switch (action) {
                case "1":
                    System.out.println(this.facadeSys.personalInfo());
                    break;
                case "2":
                    System.out.println(this.facadeSys.checkTotalSalary());
                    break;
                case "3":
                    System.out.println(this.facadeSys.checkMinimumWage());
                    break;
                case "4":
                    System.out.println(this.facadeSys.checkVacationBonus());
                    break;
                case "5":
                    System.out.println(this.facadeSys.checkKPIBonus());
                    break;
                case "6":
                    setPersonInfo.run();
                    break;
                case "7":
                    noExit = false;
                    break;
                default:
                    System.out.println("Wrong action, please type again.");
                    break;

            }

        }
    }

}
