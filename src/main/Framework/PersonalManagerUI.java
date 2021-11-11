package main.Framework;

import main.InterfaceAdapter.*;
import java.util.Scanner;

public class PersonalManagerUI {

    private final FacadeSys facadeSys;

    public PersonalManagerUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    public void run() {
        Scanner keyIn = new Scanner(System.in);

        setPersonalInfoUI changePersonInfo = new setPersonalInfoUI(this.facadeSys);
        boolean noExit = true;

        while (noExit) {
            System.out.println(
                    "i) Check your personal Info, type 1 " + "\n" +
                            "ii) Check personal Salary, type 2 " + "\n" +
                            "iii) Change personal Information, type 3 " + "\n" +
                            "iv) Back to main page, type 4" + "\n");
            String action = keyIn.nextLine();
            switch (action) {
                case "1":
                    this.facadeSys.personalInfo();
                    break;
                case "2":
                    this.facadeSys.checkSalary();
                    break;
                case "3":
                    changePersonInfo.run();
                    break;
                case "4":
                    noExit = false;
                    break;
                default:
                    System.out.println("Wrong action, please type again.");
                    break;

            }

        }
    }

}
