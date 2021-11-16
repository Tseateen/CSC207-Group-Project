package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.List;
import java.util.Scanner;

public class PrepareForWorkUI {

    private final FacadeSys facadeSys;

    /**
     * Construct a PrepareForWorkUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public PrepareForWorkUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    /**
     * Run the PrepareForWorkUI
     */
    public void run(){
            Scanner keyIn = new Scanner(System.in);
            System.out.println("Following are the work that you can do:");
            // Use presenter later
            System.out.println(this.facadeSys.showAllLowerWork());
            System.out.println("Enter the workID you want to work on:");
            String workID = keyIn.nextLine();
            System.out.println("Following are the employees information you can assign as the leader");
            System.out.println(this.facadeSys.showAllLowerUser());
            System.out.println("Enter the employee ID for the group leader (You can only choose " +
                    "between yourself and one of the employees shown above)");
            String leaderID = keyIn.nextLine();
            if (this.facadeSys.assignLeaderToWork(workID, leaderID)) {
                System.out.println("Assign leader successfully");
            } else {
                System.out.println("The work ID or employee does not exist, or you level is lower than the level of work!");
            }
    }
}
