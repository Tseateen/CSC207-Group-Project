package main.UsesCases;

import main.Entity.Group;
import main.Entity.Work;
import main.Entity.WorkFactory;

public class WorkManager {
    public void createWork(String workType, String name, String id, String createTime, int level){
        WorkFactory WF = new WorkFactory();
        Work w = WF.createWork(workType, name, id, createTime, level);
    }

    // TODO: implement this method
    public void deleteWork(String workid) {

    }

    // TODO: implement this method
    public void extendWork(String workid) {

    }

}
