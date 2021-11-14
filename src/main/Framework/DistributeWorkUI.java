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
        List<String> workIDList = this.facadeSys.findLeadWorkList();
        for(String workID: workIDList){
            System.out.println(workID + "\n");
        }
        String workID = keyIn.nextLine();

        System.out.println("Following are the employees information you can assign as members");
        List<String> allSuitableWorkerList = this.facadeSys.findAllWorkers();
        for(String worker: allSuitableWorkerList){
            System.out.println(worker + "\n");
        }

        System.out.println("Enter the employee ID for the group members");
        String employeeID = keyIn.nextLine();
        this.facadeSys.distributeWork(employeeID, workID);
        System.out.println("You have successfully chosen the members");
    }
}
