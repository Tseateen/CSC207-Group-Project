package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CreateUserUI {

    private final FacadeSys facadeSys;

    /**
     * Construct a CreateUserUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
    public CreateUserUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    /**
     * Run the CheckSalaryUI
     */
    public void run() {
        Scanner keyIn = new Scanner(System.in);
        boolean noExist = true;
        while (noExist){
            String[] WorkInfoArray = {"name",  "password", "phone", "address", "department", "wage",
                    "N for Part-Time Employee! Please enter the position", "level", "F for Full Time Employee! P for Part-Time Employee! Please enter the status"};
            Map<Integer, String> user_info = new HashMap<>();
            int counter = 0;
            for(String each_work_info: WorkInfoArray){
                System.out.println("Please enter " + each_work_info + " of new user");
                String info = keyIn.nextLine();
                user_info.put(counter, info);
                counter ++;
            }
            List<String> createResult = this.facadeSys.createUser(
                    user_info.get(0),
                    user_info.get(1),
                    user_info.get(2),
                    user_info.get(3),
                    user_info.get(4),
                    user_info.get(5),
                    user_info.get(6),
                    user_info.get(7),
                    user_info.get(8));

            boolean SuccessCreatNewUser= Boolean.parseBoolean(createResult.get(0));
            if (SuccessCreatNewUser){
                System.out.println(
                                "Please remember the following information" + "\n"
                                + " Username For the new user is : " + createResult.get(1) + "\n"
                                + " Password For the new user is : " + createResult.get(2) + "\n"
                                + "If you want to create another user, please type C. \n"
                                + " Otherwise, please type E to exist");
                String action = keyIn.nextLine();
                if(action.equalsIgnoreCase("E")){
                    noExist = false;
                }
            }
            else {
                System.out.println("You cannot create this level of the user! Please type again.");
            }
        }
    }
}
