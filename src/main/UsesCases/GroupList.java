package main.UsesCases;

import main.Entity.Group;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class GroupList implements Iterable<Group>, IGroupList,Serializable{

    // === Instance Variables ===

    private final List<Group> GroupList;

    /* === Representation Invariants ===
     * The GroupList can store projects created with leaders.
     *
     */
    public GroupList(){
        this.GroupList = new ArrayList<Group>();
    }


    /**
     * This method will add a new group information into the GroupList.
     *
     * @param leader the leader of the group.
     * @param workID the workID of the group, which is unique and linked to the work.
     *
     */
    public void addGroup(String leader, String workID){
        Group group = new Group(leader, workID);
        GroupList.add(group);
    }


    /**
     * This method will delete the existing group information from the GroupList.
     *
     * @param id the id of the Group.
     * @return whether the Group has been successfully deleted.
     *
     */
    public boolean deleteUser(String id){
        int index = -1;

        for(int i = 0; i < this.getSize(); i ++){
            if(this.GroupList.get(i).getWorkID().equals(id)){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }else{
            this.GroupList.remove(this.GroupList.get(index));
            return true;
        }
    }

    /**
     * This method will find the Employee from the EmployeeList.
     *
     * @param workID the group's id that needs to be found.
     * @return the group found.
     */
    public Group getGroup(String workID) {
        for (Group g: this.GroupList) {
            if (g.getWorkID().equals(workID)) {
                return g;
            }
        }
        return null;
    }


    /**
     * This method will get the size of the GroupList, i.e. the total number of the existing groups from the GroupList.
     *
     * @return the total number of the existing groups.
     *
     */
    public int getSize(){
        return GroupList.size();
    }


    /**
     * This method will read the input for the interface and call the add method to add a new group.
     *
     * @param group the group information of the Group.
     *
     */
    public void readInput(Group group) {
        this.GroupList.add(group);
    }


    // === Iterator Design Pattern ===
    @Override
    public Iterator<Group> iterator() {
        return new GroupListIterator();
    }


    private class GroupListIterator implements Iterator<Group>{

        private int curr_index = 0;

        @Override
        public boolean hasNext() {
            return curr_index < GroupList.size();
        }

        @Override
        public Group next() {
            Group group;

            try {
                group = GroupList.get(curr_index);
            } catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
            curr_index ++;
            return group;
        }


        /**
         * This method will add a new group.
         *
         * @param group the information of the Group.
         *
         */
        public void add(Group group){
            GroupList.add(group);
        }
    }
}
