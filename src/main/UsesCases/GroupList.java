package main.UsesCases;

import main.Entity.Group;
import main.Entity.Userable;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class GroupList implements Iterable<Group> {

    private final List<Group> GroupList;

    public GroupList(){
        this.GroupList = new ArrayList<Group>();
    }

    public void addGroup(Userable leader, String workID){
        Group group = new Group(leader, workID);
        GroupList.add(group);
    }

    public boolean deleteUser(String id){
        int index = -1;

        for(int i = 0; i < this.getSize(); i ++){
            if(this.GroupList.get(i).getWorkid().equals(id)){
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

    public int getSize(){
        return GroupList.size();
    }

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

        public void add(Group group){
            GroupList.add(group);
        }
    }
}
