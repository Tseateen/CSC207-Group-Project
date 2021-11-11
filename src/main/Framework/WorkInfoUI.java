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
        facadeSys.checkWorkInfo();
        System.out.println(
                "You only can check your work here! \n" +
                "If you want to check more detail on your particular work, please type Y. \n" +
                        "Otherwise, please type N \n.");
        String action = keyIn.nextLine();
        // TODO: check 可以變更好
        if(action.equalsIgnoreCase("Y")){
            boolean noExist = true;
            while (noExist){
                System.out.println("Please give the ID of the work that you want to check !");
                String ID = keyIn.nextLine();
                facadeSys.checkWorkInfo(ID);
                System.out.println("If you want to check another work in detail, please type C.\n " +
                        "Otherwise, please type E to exist");
                action = keyIn.nextLine();
                if(action.equalsIgnoreCase("E")){
                    noExist = false;
                }
            }
        }
    }
}
