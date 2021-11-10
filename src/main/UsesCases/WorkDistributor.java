package main.UsesCases;

import main.Entity.*;
import java.util.*;
public class WorkDistributor{

    /**
     * Verify if the work has started (a group is working on it)
     * @param work the work that is going to be verified.
     * @return true iff the work has started, false iff the work is still pending to be started.
     */
    private boolean verifyWork(Work work){
        if (work.getState().equals("Pending"))
            return false;
        else return true;
    }

    //Todo: implement this method

    /**
     * Assign the work to a group (the group is constructed by the GroupManager).
     * @param gm the GroupManager that is used to create the group.
     * @param leader the leader of the group.
     * @param members the members of the group.
     * @param w the work that is going to be assigned to the group.
     * @return the Group object which represent the group that is assigned to the given work.
     */
    public Group assignWork(GroupManager gm, Userable leader, Userable[] members, Work w){
        if (verifyWork(w))
            return null;
        w.setState("In Progress");
        gm.createGroup(leader, w.getID());
        return new Group(leader, w.getID());
    }

}
