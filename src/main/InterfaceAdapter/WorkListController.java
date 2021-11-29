package main.InterfaceAdapter;

import main.UsesCases.IWorkList;
import main.UsesCases.WorkList;

public class WorkListController {

    private IWorkList workList;

    public WorkListController(IWorkList workList) {
        this.workList = workList;
    }
}
