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
        // TODO: Use presenter later
        List<String> ListOfWorkID = this.facadeSys.findCurrentUserWork();
        for(String each_work_info: ListOfWorkID){
            System.out.println(each_work_info + '\n');
        }
        System.out.println("Enter the workID you want to work on:");
        String workID = keyIn.nextLine();
        System.out.println("Following are the employees information you can assign as the leader");
        List<String> ListOfEmployeeID = this.facadeSys.findAllWorkers();
        for(String each_ID : ListOfEmployeeID){
            System.out.println("ID: " + each_ID + "\n");
        }
        System.out.println("Enter the employee ID for the group leader (You can only choose " +
                "between yourself and one of the employees shown above)");
        String leaderID = keyIn.nextLine();
        //TODO: 尚未做完！ 需要繼續判斷SuccessAssignAGroupOfWork
        boolean SuccessAssignAGroupOfWork = this.facadeSys.AssignALeaderToWork(workID, leaderID);

        System.out.println("You have successfully chosen the leader to the work " +
                "and created a group for the chosen work");
    }
}
