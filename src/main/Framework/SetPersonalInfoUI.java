package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class SetPersonalInfoUI {

    private final FacadeSys facadeSys;

    /**
     * Construct a SetPersonalInfoUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public SetPersonalInfoUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    /**
     * Run the SetPersonalInfoUI
     */
    public void run() {
        Scanner keyIn = new Scanner(System.in);
        boolean noExit = true;

        while (noExit) {
            System.out.println(
                    "i) Change your name, please type 1; " + "\n" +
                            "ii) Change your password, please type 2; " + "\n" +
                            "iii) Change your phone number, please type 3; " + "\n" +
                            "iv) Change your address, please type 4" + "\n" +
                            "v) Change you attendance, please type 5");
            String option = keyIn.nextLine();
            System.out.println("Please type the value you want to change:");
            String response = keyIn.nextLine();
            facadeSys.setPersonalInfo(option,response);
            System.out.println("Set personal info success!");
            System.out.println("If you want to exist setting on other information, type E. " +
                    "Otherwise, type any other button to continuous setting.");
            String action = keyIn.nextLine();
            if(action.equalsIgnoreCase("E")){
                noExit = false;
            }
        }
    }
}
