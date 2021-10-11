package Framework;

import Interface_Adapter.Admin_System;
import Interface_Adapter.Employee_System;

import java.util.Scanner;

public class Command_User_Interface {

    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);
        Admin_System adminSystem = new Admin_System();
        Employee_System employeeSystem = new Employee_System();
        while(true) {
            System.out.println("Please type your account: ");
            String accountNumber = keyIn.nextLine();
            System.out.println("Please enter your password: ");
            String passwordNumber = keyIn.nextLine();
            System.out.println("If you want to manage employee, please type M. " + "\n" +
                    "If you want to look up personal info, please type L.");

            String action = keyIn.nextLine();

            if (action.equals("M")) {
                System.out.println("Success. Loading Manager interface");
                if(!adminSystem.verifyAccountExistence(accountNumber, passwordNumber)){
                    System.out.println("Wrong account number or wrong password! Please type again !");
                    continue;
                }
                if (!adminSystem.verifyAccIsAdmin(accountNumber, passwordNumber)){
                    System.out.println("You have no authority! Please check again your action !");
                    continue;
                }
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
                adminSystem.createUser(accountNumber, passwordNumber, name, phone,
                        address,status,department,position,wage,level);
                System.out.println("Success to build the new employee");
            } else if (action.equals("L")) {
                System.out.println("Success, Loading Employee interface");
            } else {
                System.out.println("Wrong Action please type again !" + "\n"
                        + "If you want to exit this system, then please type E" + "\n"
                        + "Or, you can press Enter to continuous.");
                String command = keyIn.nextLine();
                if (command.equals("E")) {
                    break;
                }
            }
        }
        System.out.println("Exit successfully");
    }
}
