package main.Framework;

import java.util.*;
import main.InterfaceAdapter.*;
public class QuestionUI {



    public static void runQuestion() {
        Scanner keyIn = new Scanner(System.in);
        AdminSystem adminSystem = new AdminSystem();
        boolean noExit = true;
        while(noExit) {
            System.out.println("If you want to manage employee, please type M. " + "\n" + "If you want to look up personal info, please type L. " + "\n" + "If you want to exit the system, please type E.");
            String action = keyIn.nextLine();

            switch (action.toUpperCase()) {
                case "M":
                    System.out.println("Please give the new account number for employee !");
                    String newusername = keyIn.nextLine();
                    while (adminSystem.verifyAccountExist(newusername)) {
                        System.out.println("Account number exist, please give a new account number !");
                        newusername = keyIn.nextLine();
                    }
                    System.out.println("Please give the new password for employee !");
                    String newPassword = keyIn.nextLine();
                    System.out.println("Please give the new name of employee !");
                    String name = keyIn.nextLine();
                    System.out.println("Please give the phone number of employee !");
                    String phone = keyIn.nextLine();
                    System.out.println("Please give the new address of employee !");
                    String address = keyIn.nextLine();
                    System.out.println("Please type \"P\"(i.e represent Parttime), or type \"F\"(i.e represent Fulltime) ");
                    String status = keyIn.nextLine();
                    System.out.println("Please give the department of employee !");
                    String department = keyIn.nextLine();
                    System.out.println("Please give the position of employee !");
                    String position = keyIn.nextLine();
                    System.out.println("Please give the initial wage of employee !");
                    int wage = keyIn.nextInt();
                    System.out.println("Please give the Authority Level of employee !");
                    int level = keyIn.nextInt();
                    adminSystem.createUser(newusername, newPassword, name, phone,
                            address, status, department, position, wage, level);
                    System.out.println("Success to build the new employee");
                    keyIn.nextLine();
                    break;
                case "L":
                    System.out.println("Success, Loading Employee interface");
                    System.out.println();
                    break;
                case "E":
                    System.out.println("Exit successfully");
                    noExit = false;
                    break;
                default:
                    System.out.println("This option does not exist! Please type again!");
                    System.out.println();
            }

        }
    }
}

