package main.UsesCases;
import main.Entity.*;


import java.util.*;

public class GroupManager implements IGroupManager{


    /**
     * This method will add new members into a Group.
     *
     * @param list the list of the users.
     * @param group the Group information.
     */
    public void addMembers(List<String> list, Group group){
        for(String id: list){
            group.addMember(id);
        }
    }

    /**
     * Delete the worker from all works. The group will be reset if they are the leader.
     *
     * @param userID the ID of the Employee, either he is the leader or the member of the Work.
     * @param groupList the list of groups
     */
    public void deleteEmployee(String userID, IGroupList groupList) {
        for (Group g : (GroupList)groupList) {
            if (g.getLeaderId().equals(userID)) {
                this.resetGroup(g);
            }
            g.deleteMember(userID);
        }
    }

    /**
     * Delete user from specific work
     *
     * @param userID the id of worker
     * @param groupList the list of group
     * @param workID the id of work
     *
     * @return delete or not
     */
    public boolean deleteMember(String userID, String workID, IGroupList groupList) {
        Group g = groupList.getGroup(workID);
        if (Objects.isNull(g)) { return false;}
        if (g.getLeaderId().equals(userID)) {
            this.resetGroup(g);
            return true;
        }
        if (g.getMembers().contains(userID)) {
            g.deleteMember(userID);
            return true;
        }
        return false;
    }


    /**
     * This method will reset the Group members.
     *
     * @param group the Group information.
     */
    public void resetMember(Group group) {
        for (String user: group.getMembers()) {
            group.deleteMember(user);
        }
    }


    /**
     * This method will change the Group leader.
     *
     * @param group the Group information.
     * @param user the user information of the leader.
     *
     */
    public void changeLeader(Group group, String user) {
        group.setLeaderId(user);
    }

    /**
     * This method will reset the whole Group.
     *
     * @param group the Group information.
     */
    public void resetGroup(Group group) {
        this.resetMember(group);
        this.changeLeader(group, "");
    }

    /**
     * Assign the Work to the targeted Employee.
     *
     * @param userID the ID of the Employee.
     * @param workID the ID of the Work.
     * @param groupList the list of groups
     *
     * @return true iff the Work has been successfully assigned to the Employee.
     */
    public boolean Distributor(String workID, String userID, IGroupList groupList) {
        for (Group g: (GroupList) groupList) {
            if (g.getWorkID().equals(workID)){
                if (g.getMembers().contains(userID)) {return false;}
                g.addMember(userID);
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if the Employee is the member of the Work, i.e. he is working on this project.
     *
     * @param userID the ID of the Employee.
     * @param workID the ID of the Work.
     * @param groupList the list of groups
     *
     * @return true iff the Employee is the Work's member.
     */
    public boolean isMember (String userID, String workID, IGroupList groupList) {
        for (Group g: (GroupList)groupList) {
            if (g.getWorkID().equals(workID)){
                return (g.getLeaderId().equals(userID)||g.getMembers().contains(workID));
            }
        }
        return false;
    }

    /**
     * Verify the leader of the Work.
     *
     * @param userID the ID of the Employee.
     * @param workID the ID of the Work.
     * @param groupList the list of groups
     *
     * @return true iff the Employee is the Work's leader.
     */
    public boolean verifierLeader(String userID, String workID, IGroupList groupList) {
        for (Group g: (GroupList)groupList) {
            if (g.getWorkID().equals(workID)){return g.getLeaderId().equals(userID);}
        }
        return false;
    }

    /**
     * Set the leader of the Work and update this information in the GroupList.
     *
     * @param workID the ID of the Work.
     * @param leaderID the ID of the Employee.
     *
     */
    public void assignLeader(String workID, String leaderID, IGroupList groupList) {
        for (Group g: (GroupList)groupList) {
            if (g.getWorkID().equals(workID)) {
                g.setLeaderId(leaderID);
                return;
            }
        }
        groupList.addGroup(leaderID, workID);
    }

    /**
     *
     */

}
