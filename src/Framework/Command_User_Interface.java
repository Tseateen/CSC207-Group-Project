package Framework;

import java.util.Scanner;

public class Command_User_Interface {

    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);

        while(true) {
            System.out.println("Please type your account number: ");
            int accountNumber = keyIn.nextInt();
            System.out.println("Please enter your password: ");
            int passwordNumber = keyIn.nextInt();
            System.out.println("If you want to manager employee, then please type M. " + "\n" +
                    "If you want to look up personal info, then please type L.");
            keyIn.nextLine();
            String action = keyIn.nextLine();

            if (action.equals("M")) {
                System.out.println("Success. Loading Manager interface");
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
