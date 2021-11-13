package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CreateWorkUI {

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
        while (noExist){
        String[] WorkInfoArray = {"name", "ID", "Department", "level"};
        String name , ID, Department, level;
        Map<Integer, String> work_info = new HashMap<>();
        int counter = 0;
        for(String each_work_info: WorkInfoArray){
            System.out.println("Please type the " + each_work_info + " of new work");
            String info = keyIn.nextLine();
            work_info.put(counter, info);
            counter ++;
        }
        boolean SuccessCreatNewWork = this.facadeSys.createWork(
                work_info.get(0),
                work_info.get(1),
                work_info.get(2),
                work_info.get(3));
            if (SuccessCreatNewWork){
                System.out.println(
                        "If you want to create another work, please type C. \n" +
                        " Otherwise, please type E to exist");
                String action = keyIn.nextLine();
                if(action.equalsIgnoreCase("E")){
                    noExist = false;
                }
            }else {
                System.out.println("You cannot create this level of the work! Please type again.");
            }
        }
    }
}

