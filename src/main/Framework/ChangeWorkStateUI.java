package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class ChangeWorkStateUI {
    // === Instance Variables ===
    private final FacadeSys facadeSys;


    /**
     * Construct a ChangeWorkStateUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public ChangeWorkStateUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    /**
     * Run the CreateWorkUI
     */
    public void run() {
        Scanner keyIn = new Scanner(System.in);
        boolean noExit = true;
        while (noExit){
            System.out.println("I) Auto update state for all works, please enter 1" + "\n" +
                    "II) Change State for a work you led with auto state check, please enter 2" + "\n" +
                    "III) Back to main page, please enter E " + "\n");
            String action = keyIn.nextLine();
            switch (action){
                case "1":
                    this.facadeSys.updateAllWorkState();
                    break;
                case "2":
                    System.out.println("Please enter work id that you want to change\n");
                    String ID = keyIn.nextLine();
                    System.out.println("Please enter state you want to change to");
                    String state = keyIn.nextLine();
                    if (this.facadeSys.changeWorkState(ID, state)) {
                        System.out.println("Change state succeed\n");
                    } else {
                        System.out.println("Work may not exist, or you may be not the leader of this work.\n");
                    }
                    break;
                case "E":
                case "e":
                    noExit = false;
                    break;
                default:
                    System.out.println("Wrong action, please type again");
                    break;
            }
        }
    }
}
