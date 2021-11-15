package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class WorkInfoUI {

    private final FacadeSys facadeSys;

    /**
     * Construct a WorkIndoUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public WorkInfoUI(FacadeSys facadeSys){
        this.facadeSys = facadeSys;
    }

    /**
     * Run the WorkInfoUI
     */
    public void run(){
        Scanner keyIn = new Scanner(System.in);
        System.out.println("=== Following is your work ===");
        System.out.println(this.facadeSys.showAllWorkNeedToDo());
        System.out.println(
                "You only can check your work here! \n" +
                "If you want to check more detail on your particular work, please type Y. \n" +
                        "Otherwise, please type N \n.");
        String action = keyIn.nextLine();
        if(action.equalsIgnoreCase("Y")){
            boolean noExist = true;
            while (noExist){
                System.out.println("Please give the ID of the work that you want to check !");
                String ID = keyIn.nextLine();
                if (this.facadeSys.checkWorkExist(ID)){
                    this.facadeSys.showWorkDetail(ID);
                }else{
                    System.out.println("This work does not exist");
                }
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
