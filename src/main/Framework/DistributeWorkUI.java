package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.List;
import java.util.Scanner;

public class DistributeWorkUI {
    private final FacadeSys facadeSys;

    public DistributeWorkUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    public void run() {
        Scanner keyIn = new Scanner(System.in);
        System.out.println("Following are the work IDs of the work which are lead by you: " +
                "choose the work ID where you want to choose members");
        System.out.println(this.facadeSys.showAllWorkLed);
        String workID = keyIn.nextLine();
        System.out.println("Following are the employees information you can assign as members");
        System.out.println(this.facadeSys.showAllLowerUser);
        System.out.println("Enter the employee ID for the group members");
        String employeeID = keyIn.nextLine();
        if (this.facadeSys.distributeWork(employeeID, workID)){
            System.out.println("Distribute work successfully");
        }else{
            System.out.println("The level of the leader is lower than the level of the work!");
        }
    }
}
