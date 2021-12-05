package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.ArrayList;
import java.util.Scanner;


public class CreateWorkUI {

    // === Instance Variables ===
    private final FacadeSys facadeSys;


    /**
     * Construct a CreateWorkUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public CreateWorkUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    /**
     * Run the CreateWorkUI
     */
    public void run() {
        Scanner keyIn = new Scanner(System.in);
        boolean noExist = true;
        boolean invalidCreate = false;
        while (noExist){
        String[] WorkInfoArray = {"name", "ID", "Description", "Department", "level"};
            ArrayList<String> work_info = new ArrayList<>();
        do{
            work_info.clear();
            for(String each_work_info: WorkInfoArray){
                    System.out.println("Please type the " + each_work_info + " of new work");
                String info = keyIn.nextLine();
                work_info.add(info);
        }
            if (!this.facadeSys.createWork(work_info)){
                    System.out.println("You can not create a work that has a higher level than you! Please reassign the work");
                    invalidCreate = true;
            }
            }while(invalidCreate);
                System.out.println(
                        "If you want to create another work, please type anything expect for E. \n" +
                        " Otherwise, please type E to exist");
                String action = keyIn.nextLine().toUpperCase();
                if(action.equalsIgnoreCase("E")){
                    noExist = false;
                }

        }
    }
}

