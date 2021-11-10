package main.Framework;

import main.Entity.Employee;
import main.Entity.Userable;
import main.InterfaceAdapter.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class WorkManagerUI {

    private final String username;
    private final HomePage homePage;
    private final FacadeSys facadeSys;

    public WorkManagerUI(String username, HomePage homePage, FacadeSys facadeSys) {
        this.username = username;
        this.homePage = homePage;
        this.facadeSys = facadeSys;
    }

    public void run() {
        /*
        HashMap<String, String> selfWork = WorkSystem.selfWorkCheck(account);
        // Todo: need to be implement in WorkSystem return a hash<work id, work name>
        for (String i : selfWork.keySet()) {
            System.out.println(i + " " + selfWork.get(i));
        }
         */
        Scanner keyIn = new Scanner(System.in);
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
                        System.out.println(action);
                        facadeSys.checkWorkInfo();
                        break;
                    case "2":
                        facadeSys.createWork();
                        break;
                    case "3":
                        System.out.println("Following are the work that you can do:");
                        facadeSys.findWork();
                        System.out.println("Enter the workid you want to work on:");
                        String workid = keyIn.nextLine();
                        System.out.println("Following are the employees information you can assign as the leader");
                        facadeSys.findAllWorkers();
                        System.out.println("Enter the employee ID for the group leader (You can only choose " +
                                "between yourself and one of the employees shown above)");
                        String leaderid = keyIn.nextLine();
                        facadeSys.createLeader(workid, leaderid);
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
                        System.out.println("Please assigned the username to this new account");
                        String username = keyIn.nextLine();
                        System.out.println("Please assigned the password to this new account");
                        String password = keyIn.nextLine();
                        System.out.println("Please enter the name of this new user");
                        String name = keyIn.nextLine();
                        System.out.println("Please enter the phone number of this new user");
                        String phone = keyIn.nextLine();
                        System.out.println("Please enter the address of this user");
                        String address = keyIn.nextLine();
                        System.out.println("Please enter the department this user belongs to");
                        String department = keyIn.nextLine();
                        System.out.println("Please enter the initial monthly wage of this user");
                        int wage = Integer.parseInt(keyIn.nextLine());
                        System.out.println("Please enter the work position of this user");
                        String position = keyIn.nextLine();
                        System.out.println("Please assigned this user an authority level");
                        int userlevel = Integer.parseInt(keyIn.nextLine());
                        System.out.println("Please enter the status of this user (Full-time: F, Part-time: P)");
                        String status = keyIn.nextLine();
                        facadeSys.UserCreator(username, password, name, phone, address, department, wage, position, userlevel, status);
                        break;
                    case "7":
                        System.out.println("Please enter the id of the user you want to delete");
                        String uid = keyIn.nextLine();
                        facadeSys.UserDelete(uid);
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
                        String info = keyIn.nextLine();
                        facadeSys.UserWorkInfoChange(userid, option, info);
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

    /*

    private void workCheck(Set i) {
        boolean noExit = true;
        Scanner keyIn = new Scanner(System.in);
        while (noExit) {
            System.out.println("Tap id to check your work, E to Exit");
            String action = keyIn.nextLine();
            action = action.toUpperCase();
            if (action == "E") {
                noExit = false;
            }else if (i.contains(action)){
                System.out.println(WorkSystem.checkWork(action));
                // Todo: need to be implemented in WorkSystem, used to return a work infor, 或许可以加一些其他功能，用id当索引
            }else {
                System.out.println("Id may wrong, or its not your work. Please tap again");
            }
        }
    }

    private void workCreate() {
        // Todo: 这里是用于创建新的工作的，需要去实现一下，向用户索取需要的信息，然后通过WorkSystem去call use case
    }

    private void workDistribute() {
        // Todo：这里要先把用户可见的工作呈现出来，用户可以看见他所在部门里权限低于他权限的工作，可以通过WorkSystem去实现
        // Todo: 工作的呈现形式建议使用<id , name> + state的形式，并且可用workCheck()去看工作信息
        // Todo：然后，这个的核心来了，用户可以对还未开始的工作进行分配！！！
    }
}
/
     */
