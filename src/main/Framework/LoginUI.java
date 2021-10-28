package main.Framework;

import java.util.*;
import main.InterfaceAdapter.*;
public class LoginUI {


    public static boolean runLogin(){
        Scanner keyIn = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();
        // === Instance Variables ===
        // The user's account username
        String username;
        // The user's account password
        String password;
        // The result of log in
        boolean result;
        System.out.println("Please type your account username:");
        username = keyIn.nextLine();
        System.out.println("Please type your account password:");
        password = keyIn.nextLine();
        result = loginSystem.runLogin(username, password);
        return result;
    }
}
