package main.InterfaceAdapter;

import main.UsesCases.IGroupList;

public class GroupListController {

    private final IGroupList groupList;

    public GroupListController(IGroupList groupList) {
    this.groupList = groupList;
    }
}
