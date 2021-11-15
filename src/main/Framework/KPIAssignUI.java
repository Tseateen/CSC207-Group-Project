/*
package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.Scanner;

public class KPIAssignUI {

    private final FacadeSys facadeSys;

    /**
     * Construct a KPIAssignUI
     * @param facadeSys A FacadeSys type object that is going to be used in the UI
     */
/*
    public KPIAssignUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    /**
     * Run the KPIAssignUI
     */
/*
    public void run(){
        Scanner keyIn = new Scanner(System.in);
        boolean noExist = true;
        while (noExist){
        System.out.println(
                "Following are the work IDs of the work which are lead by you: " );
        System.out.println(this.facadeSys.findLeadWorkList()); // Should the work status be finished?
        String WorkID = keyIn.nextLine();
        if (this.facadeSys.checkLeaderResult(WorkID)){
            System.out.println("You can now begin assign KPI to each member");
            for (String member : this.facadeSys.findWorkKpiMemberList(WorkID)){
                System.out.println("Enter the KPI for member " + member);
                String kpi = keyIn.nextLine();
                this.facadeSys.giveKpi(WorkID, member, kpi);
            }
            System.out.println("You have successfully assign KPI to every member");
            System.out.println("If you want to keep assigning KPI to other member in other work, please type C.\n" +
                    "Otherwise, type E");
            String action = keyIn.nextLine();
            // TODO: Can make better
            if (action.equalsIgnoreCase("E")){
                noExist = false;
            }
        }else{
            System.out.println("You are typing the wrong work ID!");
        }
        }
    }
}

 */



