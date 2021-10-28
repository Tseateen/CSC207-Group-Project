package Uses_Cases;

import Entity.*;
import java.util.*;

public class GroupManager {
    private LinkedList<Group> GroupMap;
    private int totalGroup = 0;

    public GroupManager(){
        this.GroupMap = new LinkedList<Group>();
    }

    public int getTotalGroup() {
        return totalGroup;
    }

    public void createGroup(Userable leader, Userable[] members){
        totalGroup++;
        Group newGroup = new Group(leader, members, null);
        GroupMap.add(newGroup);

    }



}
