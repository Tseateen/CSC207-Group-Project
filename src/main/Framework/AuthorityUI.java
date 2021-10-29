package main.Framework;

import java.lang.reflect.Array;
import java.util.*;
import main.InterfaceAdapter.*;

public class AuthorityUI {
    private String[] authority;

    public AuthorityUI (String[] authority) {
        this.authority = authority;
    }

    public void run() {
        Scanner keyIn = new Scanner(System.in);
        SelfAccountUI self = new SelfAccountUI(this.authority[0]);
        WorkAuthorityUI work = new WorkAuthorityUI(this.authority);
        boolean noExit = true;
        while(noExit) {
            String action = keyIn.nextLine();

            switch (action.toUpperCase()) {
                case "WORK":
                    work.run();
                    break;
                case "SELF":
                    self.run();
                    break;
                case "E":
                    noExit = false;
                    break;
                default:
                    System.out.println("Please typing as required;");
                    break;
            }

        }
    }

}
