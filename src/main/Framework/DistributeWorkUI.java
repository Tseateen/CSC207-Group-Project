package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class DistributeWorkUI {

    // === Instance Variables ===
    private final FacadeSys facadeSys;


    /**
     * Construct a DistributeWorkUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public DistributeWorkUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    /**
     * Run the DistributeWorkUI
     */
    public void run() {
        Scanner keyIn = new Scanner(System.in);
        System.out.println("Following are the work IDs of the work which are lead by you: " +
                "choose the work ID where you want to choose members");
        System.out.println(this.facadeSys.showAllWorkLead());
        String workID = keyIn.nextLine();
        System.out.println("Following are the employees information you can assign as members");
        System.out.println("Name  ID  Level  Department");
        System.out.println(this.facadeSys.showAllLowerUser());
        System.out.println("Enter the employee ID for the group members");
        String employeeID = keyIn.nextLine();
        if (this.facadeSys.distributeWork(workID, employeeID)){
            System.out.println("Distribute work successfully");
        }else{
            System.out.println("The work ID/member does not exist or he level of the leader is lower than the level of the work!");
            System.out.println();
        }
    }
}
