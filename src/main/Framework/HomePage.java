package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class HomePage {


    private final FacadeSys facadeSys;

    /**
     * Construct a HomePage
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public HomePage(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    /**
     * Run the HomePage
     */
    public void run() throws Exception {
        Scanner keyIn = new Scanner(System.in);
        PersonalManagerUI personal = new PersonalManagerUI(facadeSys);
        WorkManagerUI work = new WorkManagerUI(facadeSys);

        boolean noExit = true;

        while (noExit) {
            System.out.println(
                            "i) Look at your personal information, please type 1 " + "\n" +
                            "ii) Manager your work, please type 2 " + "\n" +
                            "iii) Close system, please type 3 ");
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
                    System.out.println("Please enter the correct action");
                    break;
            }
        }
    }
}

