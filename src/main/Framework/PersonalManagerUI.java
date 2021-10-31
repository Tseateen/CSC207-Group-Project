package main.Framework;

import main.InterfaceAdapter.*;
import java.util.Scanner;

public class PersonalManagerUI {
    private final String username;
    private final HomePage homePage;
    private final FacadeSys facadeSys;

    public PersonalManagerUI(String username, HomePage homePage, FacadeSys facadeSys) {
        this.username = username;
        this.homePage = homePage;
        this.facadeSys = facadeSys;
    }

    public void run() {
        Scanner keyIn = new Scanner(System.in);
        boolean noExit = true;
        while (noExit) {
            System.out.println(noExit);
            System.out.println(
                            "i) Check your personal Info, type 1 " + "\n" +
                            "ii) Check personal Salary, type 2 " + "\n" +
                            "iii) Change personal Information, 3 " + "\n" +
                            "iv) Back to main page, type 4" + "\n");
            String action = keyIn.nextLine();
            switch (action) {
                    case "1":
                        facadeSys.personalInfo();
                        break;
                    case "2":
                        facadeSys.checkSalary();
                        break;
                    case "3":
                        facadeSys.setPersonalInfo();
                        break;
                    case "4":
                        noExit = false;
                        System.out.println(noExit);
                        break;
                    default:
                        System.out.println("Wrong action, please type again.");
                        break;

            }

        }
    }
}


/*
    private void Salary(AccountSystem AccountImfor) {
        String salary_unit = "Month";
        if (AccountImfor.typ() == "E") {salary_unit = "Hour";}
        System.out.println("Salary:" + AccountImfor.Sal() + " per " + salary_unit);
    }

    private void Detail(AccountSystem AccountImfor) {
        // Todo: print some base imfor
    }



    private void Changer(AccountSystem AccountImfor) {
        // Todo: implement some things to changer account imfor
    }

}
 */
