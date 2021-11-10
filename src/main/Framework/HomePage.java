package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.io.IOException;
import java.util.*;

public class HomePage {

    private final FacadeSys facadeSys;

    public HomePage(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    public void run() throws IOException,ClassNotFoundException {
        Scanner keyIn = new Scanner(System.in);
        PersonalManagerUI personal = new PersonalManagerUI(facadeSys);
        WorkManagerUI work = new WorkManagerUI(facadeSys);

        boolean noExit = true;

        while (noExit) {
            System.out.println(
                    "i) Look at your personal information, type 1 " + "\n" +
                            "ii) Manager your work, type 2 " + "\n" +
                            "iii) Close system, type 3 ");
            String action = keyIn.nextLine();
            switch (action) {
                case "1":
                    personal.run();
                    break;
                case "2":
                    work.run();
                    break;
                case "3":
                    noExit = false;
                    facadeSys.systemEnd();
                    System.out.println("Successfully exit");
                    break;
                default:
                    System.out.println("Please give the correct action");
                    break;
            }
        }
    }
}

