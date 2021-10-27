package main.Framework;

import java.util.*;
import main.InterfaceAdapter.*;
public class CommandLineUI {

    Scanner keyIn = new Scanner(System.in);
    public void runLogin(LoginSystem loginSystem){
        // === Instance Variables ===
        // The user's account username
        String username;
        // The user's account password
        String password;
        // The result of log in
        String result;
        System.out.println("Please type your account username:");
        username = keyIn.nextLine();
        System.out.println("Please type your account password:");
        password = keyIn.nextLine();
        result = loginSystem.runLogin(username, password);
        System.out.println(result);
    }
}
