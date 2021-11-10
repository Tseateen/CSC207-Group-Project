package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class WorkInfoUI {

    private final FacadeSys facadeSys;

    public WorkInfoUI(FacadeSys facadeSys){
        this.facadeSys = facadeSys;
    }

    public void run(){
        Scanner keyIn = new Scanner(System.in);
        System.out.println("=== Following is your work ===");
        boolean showDetail = false;
        facadeSys.checkWorkInfo(showDetail);
        System.out.println(
                "You only can check your work here! \n" +
                "If you want to check more detail on your particular work, please type Y. \n" +
                        "Otherwise, please type N \n.");
        String action = keyIn.nextLine();
        if(action.equalsIgnoreCase("Y")){
            showDetail = true;
            facadeSys.checkWorkInfo(showDetail);
        }
    }
}
