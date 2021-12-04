package main.Framework;
import java.util.*;
import main.InterfaceAdapter.FacadeSys;

public class ExtendWorkUI {
    private final FacadeSys facadeSys;

    public ExtendWorkUI(FacadeSys facadeSys){
        this.facadeSys = facadeSys;
    }

    public void run(){
        Scanner keyIn = new Scanner(System.in);
        System.out.println("Please type the work ID you want to extend:");
        String workID = keyIn.nextLine();
        System.out.println("Please type the how many days you want to extend:");
        String days = keyIn.nextLine();
        if(this.facadeSys.extendWork(days, workID)){
            System.out.println("Extend work success!");
        }
        else{
            System.out.println("The work does not exist or you are not valid to extend the work!");
        }
        }

}
