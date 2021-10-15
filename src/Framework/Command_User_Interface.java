package Framework;

import Interface_Adapter.Admin_System;

import java.util.Scanner;

public class Command_User_Interface {

    /* Currently, we assume there exists an Admin, which account number is 1 and password 0.
     * Only Admin can use action "M" for creating new employee. Other people will be refused for option "M".
     */
    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);
        Admin_System adminSystem = new Admin_System();
        while(true) {
            System.out.println("Please type your account: ");
            String accountNumber = keyIn.nextLine();
            System.out.println("Please enter your password: ");
            String passwordNumber = keyIn.nextLine();
            System.out.println("If you want to manage employee, please type M. " + "\n" +
                    "If you want to look up personal info, please type L.");

            String action = keyIn.nextLine();

            // TODO: Use Try catch for the following code.
            if (action.equals("M")) {
                if(!adminSystem.verifyForLogin(accountNumber, passwordNumber)){
                    System.out.println("Wrong account number or wrong password! Please type again !");
                    continue;
                }
                if (!adminSystem.verifyAccIsAdmin(accountNumber, passwordNumber)){
                    System.out.println("You have no authority! Please check again your action !");
                    continue;
                }
                System.out.println("Success. Loading Manager interface");
                System.out.println("Please give the new account number for employee !");
                String newAccountNumber = keyIn.nextLine();
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
                adminSystem.createUser(newAccountNumber, newPassword, name, phone,
                        address,status,department,position,wage,level);
                System.out.println("Success to build the new employee");
                break;
            } else if (action.equals("L")) {
                if(!adminSystem.verifyForLogin(accountNumber, passwordNumber)){
                    System.out.println("Wrong account number or wrong password! Please type again !");
                    continue;
                }
                System.out.println("Success, Loading Employee interface");
                break;
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
