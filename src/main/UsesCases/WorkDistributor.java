package main.UsesCases;

import main.Entity.*;
import java.util.*;
public class WorkDistributor{

    //private HashMap<Group, Work> workMap;
    private Work w;

    public WorkDistributor(){
        this.w = w;
    }

    private boolean verifyWork(Work w){
        if (w.getState().equals("Pending"))
        return false;
        else return true;
    }

    //Todo: implement this method

    public Group assignWork(GroupManager gm, Userable leader, Userable[] members, Work w){
        if (verifyWork(w))
            return null;
        w.setState("In Progress");
        gm.createGroup(leader, members, w.getId());
        return new Group(leader, members, w.getId());
    }



}
