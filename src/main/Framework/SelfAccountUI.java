package main.Framework;

import main.InterfaceAdapter.*;
import java.util.Scanner;

public class SelfAccountUI {
    private String AccountNumber;

    public SelfAccountUI(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public void run() {
        AccountSystem AccountImfor = new AccountSystem(AccountNumber);
        System.out.println("Account Number: " + AccountImfor.AccountNumber());
        System.out.println("Department: " + AccountImfor.Departmnet());
        System.out.println("Uid: " + AccountImfor.getId());
        Scanner keyIn = new Scanner(System.in);
        boolean noExit = true;
        while(noExit) {
            String action = keyIn.nextLine();

            switch (action.toUpperCase()) {
                case "IMF":
                    Detail(AccountImfor);
                    break;
                case "SAL":
                    Salary(AccountImfor);
                    break;
                case "CHG":
                    Changer(AccountImfor);
                    break;
                case "E":
                    noExit = false;
                    break;
                default:
                    System.out.println("Please typing as required;");
                    break;
            }

        }
    }

    private void Detail(AccountSystem AccountImfor) {
        // Todo: print some base imfor
    }

    private void Salary(AccountSystem AccountImfor) {
        String salary_unit = "Month";
        if (AccountImfor.typ() == "E") {salary_unit = "Hour";}
        System.out.println("Salary:" + AccountImfor.Sal() + " per " + salary_unit);
    }

    private void Changer(AccountSystem AccountImfor) {
        // Todo: implement some things to changer account imfor
    }
}
