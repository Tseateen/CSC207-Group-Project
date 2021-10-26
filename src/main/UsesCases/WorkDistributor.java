package main.UsesCases;

import main.Entity.*;
import java.util.*;
public class WorkDistributor {

    private Map<Group, Work> workMap;

    public void createWork(String workType, String name, String id, String createTime, int level){
        WorkFactory WF = new WorkFactory();
        Work w = WF.createWork(workType, name, id, createTime, level);
    }


}
