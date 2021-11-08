package main.UsesCases;
import main.Entity.*;
import java.util.*;

public class GroupManager {

    public void addMembers(Userable[] list, Group group){
        for(Userable userable: list){
            group.addMember(userable);
        }
    }

    public void resetMember(Group group) {
        for (Userable user: group.getMembers()) {
            group.deleteMember(user);
        }
    }

    public void changeLeader(Group group, Userable user) {
        group.setLeader(user);
    }

}
