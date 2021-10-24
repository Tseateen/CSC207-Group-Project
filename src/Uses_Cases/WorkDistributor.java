package Uses_Cases;

import Entity.*;
import java.util.*;
public class WorkDistributor {

    private Map<Work, Group> workMap;

    public void createGroup(Userable leader, Userable[] members, String project){
        Group newGroup = new Group(leader, members, project);
    }
}
