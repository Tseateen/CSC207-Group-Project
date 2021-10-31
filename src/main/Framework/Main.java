package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the HR System!");
        Scanner keyIn = new Scanner(System.in);
        FacadeSys facadeSys = new FacadeSys();
        System.out.println("Please type your account username:");
        String username = keyIn.nextLine();
        System.out.println("Please type your account password:");
        String password = keyIn.nextLine();
        boolean result = facadeSys.systemStart(username,password);
        if (result){
            HomePage homePage = new HomePage(username, facadeSys);
            homePage.run();
        }else{
            System.out.println("Account username doesn't exist or password does not match. Please type again!");
        }
    }
}
