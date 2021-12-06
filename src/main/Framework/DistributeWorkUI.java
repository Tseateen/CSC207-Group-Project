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
        boolean noExit = true;
        while (noExit) {
            System.out.println("Following are the work IDs of the work which are lead by you: " +
                    "choose the work ID where you want to manage\n Exit enter E");
            System.out.println(this.facadeSys.showAllWorkLead());
            String workID = keyIn.nextLine();
            if (workID.equals("e") || workID.equals("E")){
                System.out.println("Success exit\n");
                noExit = false;
            } else if (this.facadeSys.selfLeaderCheck(workID)) {
                System.out.println("I) Add member to your group or distribute a work, please enter 1; " + "\n" +
                        "II) Remove a member from your group, please enter 2; " + "\n" +
                        "III) Show all member in your group, please enter 3;\n" +
                        "Exit enter E" + "\n");
                String action = keyIn.nextLine();
                switch (action) {
                    case "1":
                        System.out.println("Following are the employees information you can assign as members");
                        System.out.println("Name  ID  Level  Department");
                        System.out.println(this.facadeSys.showAllLowerUser());
                        System.out.println("Enter the employee ID for the group members");
                        String employeeID = keyIn.nextLine();
                        if (this.facadeSys.distributeWork(workID, employeeID)) {
                            System.out.println("Distribute work successfully");
                        } else {
                            System.out.println("The work ID/member does not exist or he level of the leader is lower than the level of the work!");
                            System.out.println();
                        }
                        break;
                    case "2":
                        System.out.println("Following are the employees information in your group");
                        System.out.println("Name  ID  Level  Department");
                        System.out.println(this.facadeSys.allGroupMember(workID));
                        System.out.println("Enter the employee ID for the group members\n" +
                                "If you remove the leader, this group will be delete");
                        String employeeID2 = keyIn.nextLine();
                        if(this.facadeSys.removeMember(workID, employeeID2)){
                            System.out.println("User was removed from group");
                        } else {
                            System.out.println("User was not in group.");
                        }
                        break;
                    case "3":
                        System.out.println(this.facadeSys.allGroupMember(workID));
                        break;
                    case "E":
                    case "e":
                        noExit = false;
                        break;
                    default:
                        System.out.println("Wrong action, please type again");
                        break;
                }
            } else {
                System.out.println("It's not your work. Or wrong action, please try again.");
            }
        }
    }
}
