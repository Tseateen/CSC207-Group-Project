package main.UsesCases;

import main.Entity.Group;

import java.io.Serializable;
import java.util.List;

public interface IGroupManager extends Serializable {
    void addMembers(List<String> list, Group group);
    boolean deleteMember(String userID, String workID, IGroupList groupList);
    void deleteEmployee(String userID, IGroupList groupList);
    void resetMember(Group group);
    void changeLeader(Group group, String user);
    void resetGroup(Group group);
    boolean Distributor(String workID, String userID, IGroupList groupList);
    void assignLeader(String workID, String leaderID, IGroupList groupList);
    boolean groupExist(String workID, IGroupList groupList);
    List<String> allMember(String workID, IGroupList groupList);
}