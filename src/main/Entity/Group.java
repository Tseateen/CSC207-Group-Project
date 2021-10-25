package main.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Group implements Iterable<Userable> {
    private List<Userable> groupMembers = new ArrayList<>();
    private Work project;


    public Group (Userable leader, Userable[] members, Work project){
        // Index 0 will always be the leader
        groupMembers.add(leader);
        for(Userable userable: members){
            groupMembers.add(userable);
        }
        this.project = project;
    }

    public void setLeader(Userable leader){
        if (groupMembers.contains(leader)){
            int leaderIndex = groupMembers.indexOf(leader);
            Userable previousLeader = groupMembers.get(0);
            groupMembers.set(0, leader);
            groupMembers.set(leaderIndex, previousLeader);
        }
        else{
            Userable previousLeader = groupMembers.get(0);
            groupMembers.set(0, leader);
            groupMembers.add(previousLeader);

        }
    }

    public Userable getLeader(){
        return groupMembers.get(0);
    }

    //leader does not change, all the members are replaced
    public void setMembers(Userable[] members) {
        List<Userable> newGroupMemebers = new ArrayList<>();
        newGroupMemebers.add(groupMembers.get(0));
        for(Userable member: members){
            newGroupMemebers.add(member);
        }
        groupMembers = newGroupMemebers;
    }

    public Userable[] getMembers() {
        Userable[] membersList = new Userable[groupMembers.size()-1];
        for(int i = 0; i < groupMembers.size()-1; i++){
            membersList[i] = groupMembers.get(i+1);
        }return membersList;
    }

    public boolean addMember(Userable member) {
        if (!groupMembers.contains(member)) {
            groupMembers.add(member);
            return true;
        }return false;
    }
    public boolean deleteMember(Userable member){
        if (!groupMembers.contains(member)){
            return false;
        }groupMembers.remove(member);
        return true;
    }

    public void setProject(Work project) {
        this.project = project;
    }

    public Work getProject() {
        return project;
    }


    @Override
    public Iterator<Userable> iterator() {
        return new GroupIterator();
    }
    private class GroupIterator implements Iterator<Userable> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < groupMembers.size();
        }

        @Override
        public Userable next() {
            Userable res;

            // List.get(i) throws an IndexOutBoundsException if
            // we call it with i >= groupMembers.size().
            // But Iterator's next() needs to throw a
            // NoSuchElementException if there are no more elements.
            try {
                res = groupMembers.get(current);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            current += 1;
            return res;
        }

        @Override
        public void remove() {
            Userable member = groupMembers.get(current);
            deleteMember(member);
        }

    }
}
