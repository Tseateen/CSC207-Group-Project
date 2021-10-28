package main.UsesCases;
import main.Entity.*;
import java.util.*;

public class GroupManager {
    private ArrayList<Group> GroupList;
    private int totalGroup = 0;

    /**
     * Construct the GroupManager.
     */
    public GroupManager(){
        this.GroupList = new ArrayList<>();
    }

    public int getTotalGroup() {
        return totalGroup;
    }

    public ArrayList<Group> getGroupList(){
        return this.GroupList;
    }

    public boolean createGroup(Userable leader, Userable[] members, int GroupId){
        // TODO: this method need modification
        totalGroup++;
        Group newGroup = new Group(leader, members, null, GroupId);
        GroupList.add(newGroup);
        return true;

    }public boolean deleteGroup(Group group){
        // TODO: implement this method
        return true;

    }public boolean mergeGroups(Group[] groups, Userable leader, Work project){
        //TODO: implement this method
        return true;
    }


}
