package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class Main {

    /**
     * Start the HR System
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the HR System!");
        Scanner keyIn = new Scanner(System.in);
        boolean correctInfo = false;
        while(!correctInfo) {
        System.out.println("Please enter your account username/ID:");
        String username = keyIn.nextLine();
        System.out.println("Please enter your account password:");
        String password = keyIn.nextLine();
        FacadeSys facadeSys = new FacadeSys(username);
        boolean result = facadeSys.systemStart(username,password);
        InstructionUI instructionUI = new InstructionUI();
        if (result) {
            instructionUI.run();
            HomePage homePage = new HomePage(facadeSys);
            homePage.run();
            correctInfo = true;
        } else {
            System.out.println("Account username doesn't exist or password does not match. Please enter again! \n");
        }
        }
    }
}
