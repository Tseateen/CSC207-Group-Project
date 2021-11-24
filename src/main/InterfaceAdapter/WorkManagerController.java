package main.InterfaceAdapter;

import main.UsesCases.IWorkManager;
import main.UsesCases.WorkList;

public class WorkManagerController {

    private final WorkList workList;
    private final IWorkManager workManager;

    public WorkManagerController(WorkList workList,IWorkManager workManager){
        this.workList = workList;
        this.workManager = workManager;
    }


    public void extendWork(String workID, String days){
        this.workManager.extendWork(this.workList.getWork(workID), days);
    };


    public void changeState(String workID, String newStatus){
        this.workManager.changeState(this.workList.getWork(workID), newStatus );
    };

}
