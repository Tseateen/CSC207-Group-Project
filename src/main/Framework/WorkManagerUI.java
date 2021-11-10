package main.Framework;

import main.Entity.Employee;
import main.Entity.Userable;
import main.InterfaceAdapter.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class WorkManagerUI {

    private final FacadeSys facadeSys;

    public WorkManagerUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    public void run() {
        Scanner keyIn = new Scanner(System.in);

        WorkInfoUI workInfoUI = new WorkInfoUI(this.facadeSys);

        boolean noExit = true;
        while (noExit) {
            System.out.println(
                    "i) Check your own work's information, type 1; " + "\n" +
                            "ii) Create a work, type 2; " + "\n" +
                            "iii) Create a leader, type 3; " + "\n" +
                            "iv) Distribute a work, type 4; " + "\n" +
                            "v) Assign KPI to members, type 5; "  + "\n" +
                            "vi) Create a user, type 6; "  + "\n" +
                            "vii) Delete a user, type 7; "  + "\n" +
                            "viii) Change a user's information, type 8; "  + "\n" +
                            "xx) Back to main page, type x");
            String action = keyIn.nextLine();
            switch (action) {
                case "1":
                    workInfoUI.run();
                    break;
                case "2":
                    facadeSys.createWork();
                    break;
                case "3":
                    System.out.println("Following are the work that you can do:");
                    // TODO: FindWork Method 太複雜
                    facadeSys.findWork();
                    System.out.println("Enter the workID you want to work on:");
                    String workID = keyIn.nextLine();
                    System.out.println("Following are the employees information you can assign as the leader");
                    facadeSys.findAllWorkers();
                    System.out.println("Enter the employee ID for the group leader (You can only choose " +
                            "between yourself and one of the employees shown above)");
                    String leaderID = keyIn.nextLine();
                    facadeSys.createLeader(workID, leaderID);
                    System.out.println("You have successfully chosen the leader");
                    break;
                case "4":
                    System.out.println("Following are the work IDs of the work which are lead by you: choose " +
                            "the work ID where you want to choose members");
                    facadeSys.findLeadWork();
                    String wid = keyIn.nextLine();
                    System.out.println("Following are the employees information you can assign as members");
                    facadeSys.findAllWorkers();
                    System.out.println("Enter the employee ID for the group members, split by a space");
                    String employeeid = keyIn.nextLine();
                    facadeSys.distributeWork(employeeid, wid);
                    System.out.println("You have successfully chosen the members");
                    break;
                case "5":
                    System.out.println("Following are the work IDs of the work which are lead by you: Enter the " +
                            "work ID where you want to give KPI to your members:");
                    facadeSys.findLeadWork(); // Should the work status be finished?
                    String woid = keyIn.nextLine();
                    for (Userable member: facadeSys.findWorkKpi(woid).getMembers()){
                        System.out.println("Enter the KPI for member" + member.getID());
                        String kpi = keyIn.nextLine();
                        String eid = member.getID();
                        facadeSys.giveKpi(woid, eid, kpi);
                    }
                    System.out.println("You have successfully assign KPI to every member");
                    break;
                case "6":
                    System.out.println("Please assign the username, password, name, phone, " +
                            "address, department, wage, position, level, status (Split by a space) ");
                    String info = keyIn.nextLine();
                    String[] user_info = info.split("");
                    if (!facadeSys.levelVerifier(user_info[8])) {
                        System.out.println("You cannot create this user.");
                        break;
                    };
                    facadeSys.UserCreator(info);
                    break;
                case "7":
                    System.out.println("Please enter the id of the user you want to delete");
                    String uid = keyIn.nextLine();
                    if (facadeSys.UserDelete(uid)){
                        System.out.println("The user has successfully deleted.");
                    }
                    else {
                        System.out.println("You cannot delete this employee, please try again.");
                    }
                    break;
                case "8":
                    System.out.println("Please enter the User ID that you want to change information to:");
                    String userid = keyIn.nextLine();
                    System.out.println("i) Change the user's Department, please type 1; " + "\n" +
                            "ii) Change the user's Wage, please type 2; " + "\n" +
                            "iii) Change the user's Level, please type 3; " + "\n" +
                            "iv) Change the user's Position (only apply to Full Time Employee), please type 4" + "\n" +
                            "v) Change the user's State (only apply to Full Time Employee), please type 5"  + "\n" +
                            "vi) Change the user's Total Vacation (only apply to Full Time Employee), please type 6"  + "\n" +
                            "vii) Change the user's Vacation Used (only apply to Full Time Employee), please type 7"  + "\n");
                    String option = keyIn.nextLine();
                    System.out.println("Please enter the new info");
                    String inf = keyIn.nextLine();
                    facadeSys.UserWorkInfoChange(userid, option, inf);
                    break;
                case "x":
                    noExit = false;
                    break;
                default:
                    System.out.println("Wrong action, please type again");
                    break;
            }
        }
    }
}
