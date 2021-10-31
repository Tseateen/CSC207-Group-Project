package main.Framework;

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
                            "ii) Creat a work, type 2; " + "\n" +
                            "iii) Distribute a work, type 3; " + "\n" +
                            "iv) Back to main page, type 4");
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
                        facadeSys.distributeWork();
                        break;
                    case "4":
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

    private void workCreat() {
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
