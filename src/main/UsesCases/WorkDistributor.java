package main.UsesCases;

import main.Entity.*;
import java.util.*;
public class WorkDistributor implements WorkManager{

    private HashMap<Group, Work> workMap;

    public WorkDistributor(){
        this.workMap = new HashMap<Group, Work>();
    }

    // TODO: implement this method
    @Override
    public void createWork(String workType, String name, String id, String createTime, int level){
        WorkFactory WF = new WorkFactory();
        Work w = WF.createWork(workType, name, id, createTime, level);
    }

    // TODO: implement this method
    @Override
    public void deleteWork() {

    }
    // TODO: implement this method
    @Override
    public void extendWork(Work project) {

    }

    // TODO: Do we really need this?
    public void assignWork(Work project, Group group){
        group.setProject(project);
    }


}
