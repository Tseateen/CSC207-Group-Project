package main.InterfaceAdapter;

import main.UsesCases.*;

import java.util.Scanner;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class FacadeSysTest<T> {
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
        System.out.println("You only can check your work here.\n" +
                "If you want check your work detail, please type its id\n ");
        VisitStep("check");
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
        System.out.println(facade_use.workCreat(action));
    }


    private boolean levelVerifier(String level) {
        try {
            if (level.length() != 1) {return false}
            int a = Integer.parseInt(level);
            return a > facade_use.user_level();
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void distributeWork() {
        System.out.println(facade_use.AllWork());
        // Todo:如何去分配工作？
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
                if (method.equals("check")) {System.out.println(facade_use.WorkDetail(action));}
                if (method.equals("creat")) {Creator(action);}
            }
        }
    }
}
