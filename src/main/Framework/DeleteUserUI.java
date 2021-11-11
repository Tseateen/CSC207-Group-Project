package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DeleteUserUI {

    private final FacadeSys facadeSys;

    public DeleteUserUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }


    public void run() {
        Scanner keyIn = new Scanner(System.in);
        boolean noExist = true;
        while (noExist){
            System.out.println("Please enter the ID of the user you want to delete");
            String userId = keyIn.nextLine();

            boolean SuccessCreatNewUser= this.facadeSys.deleteUser(userId);
            if (SuccessCreatNewUser){
                System.out.println(
                        "If you want to delete another user, please type C. \n" +
                                " Otherwise, please type E to exist");
                String action = keyIn.nextLine();
                if(action.equalsIgnoreCase("E")){
                    noExist = false;
                }
            }
            else {
                System.out.println("You cannot delete this level of the user! Please type again.");
            }
        }
    }
}
