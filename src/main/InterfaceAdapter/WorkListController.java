package main.InterfaceAdapter;

import main.UsesCases.IWorkList;

import java.util.ArrayList;

public class WorkListController {

    // === Instance Variables ===
    private final IWorkList workList;


    /**
     * Construct the WorkListController.
     */
    public WorkListController(IWorkList workList) {
        this.workList = workList;
    }

    // === Usage: FacadeSys Worker Case (ii) ====
    public void createWork(ArrayList<String> info) {
        this.workList.addWork(info.get(0), info.get(1), info.get(2), info.get(3), Integer.parseInt(info.get(4)));
    }
    // ==================================================

    // === Usage: FacadeSys Worker Case (iii) ====
    public String FindWorkLevel(String workID){
        return this.workList.FindWorkLevel(workID);
    }
    // ==================================================

    // === Usage: FacadeSys Worker Case (iii) ====
    public boolean checkWorkExist(String workID){
        return this.workList.checkWorkExist(workID);
    }
    // ==================================================
}
