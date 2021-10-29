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

    /**
     *
     * @return This method will return the total number of groups.
     */
    public int getTotalGroup() {
        return totalGroup;
    }

    /**
     *
     * @return This method will return list of all groups.
     */
    public ArrayList<Group> getGroupList(){
        return this.GroupList;
    }

    /**
     * This method create a new group and added to GroupList.
     *
     * @param leader the leader of the group that is going to be created.
     * @param members the members of the group that is going to be created.
     * @param WorkId The id of the work that is going to be created.
     * @return true iff the group is created successfully.
     */
    public boolean createGroup(Userable leader, Userable[] members, String WorkId){
        // TODO: this method need modification
        totalGroup++;
        Group newGroup = new Group(leader, members, WorkId);
        GroupList.add(newGroup);
        return true;

    }

    /**
     * This method delete a group from the GroupList
     *
     * @param group the group that is going to be removed from the GroupList
     * @return true iff the group is deleted successfully.
     */
    public boolean deleteGroup(Group group){
        // TODO: implement this method
        return true;

    }

    /**
     * This method merge a list of groups into one group, and set the new leader and new project
     *
     * @param groups that are going to be merged into one group.
     * @param leader the leader that is going to be set as the new group leader.
     * @param project the project that is going to be set as the new project that the merged
     *                group will work on.
     * @return true iff the group is merged successfully.
     */
    public boolean mergeGroups(Group[] groups, Userable leader, Work project){
        //TODO: implement this method
        return true;
    }


}
