package main.UsesCases;
import main.Entity.*;
import java.util.*;

public class GroupManager {


    /**
     * This method will add new members into a Group.
     *
     * @param list the list of the users.
     * @param group the Group information.
     */
    public void addMembers(List<Userable> list, Group group){
        for(Userable userable: list){
            group.addMember(userable);
        }
    }


    /**
     * This method will reset the Group members.
     *
     * @param group the Group information.
     */
    public void resetMember(Group group) {
        for (Userable user: group.getMembers()) {
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
    public void changeLeader(Group group, Userable user) {
        group.setLeader(user);
    }

}
