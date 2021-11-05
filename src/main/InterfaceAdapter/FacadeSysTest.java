package main.InterfaceAdapter;

import main.UsesCases.*;

import java.util.Scanner;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class FacadeSysTest {
    private final FacadeUse facade_use;

    public FacadeSysTest() {
        facade_use = new FacadeUse();
    }

    // === System methods ===

    public boolean systemStart(String accountNumber, String password) {
        return facade_use.start(accountNumber, password);
    }

    public void systemEnd() {
        facade_use.end();
    }

    // === Personal UI Method ===
    public void personalInfo(){
        System.out.println(facade_use.UserInfo());
    }

    public void checkSalary() {
        System.out.println(facade_use.UserSalary());
    }

    public void setPersonalInfo() {
        Scanner keyIn = new Scanner(System.in);

        System.out.println("i) Change your address, type 1; " + "\n" +
                            "ii) Change your phone number, type 2; " + "\n" +
                            "iii) Change password, type 3; " + "\n" +
                            "iv) Back to main page, type other");
        String action = keyIn.nextLine();
        System.out.println(facade_use.AccountChange(action));
    }

    // === Work UI Method ===

    public void checkWorkInfo() {
        System.out.println(facade_use.SelfWork());
        Scanner keyIn = new Scanner(System.in);
        System.out.println("You only can check your work here.\n" +
                "If you want check your work detail, please type its id\n " +
                "if you want exit, type E");
        String action = keyIn.nextLine();
        boolean noExit = true;
        while (noExit) {
            switch (action) {
                case "E":
                    noExit = false;
                    break;
                default:
                    System.out.println(facade_use.WorkDetail(action));
            }
        }
    }


    public void createWork() {

    }

    public void distributeWork() {
    }
}
