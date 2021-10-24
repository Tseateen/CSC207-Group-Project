package UsesCases;

import Entity.*;
import java.util.*;
public class WorkDistributor {

    private Map<Group, Work> workMap;

    public void createGroup(Userable leader, Userable[] members, Work project){
        Group newGroup = new Group(leader, members, project);
    }


}
