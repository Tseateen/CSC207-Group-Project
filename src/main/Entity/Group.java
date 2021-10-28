package main.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Group implements Iterable<Userable> {
    private List<Userable> groupMembers = new ArrayList<>();
    private Work project;


    /**
     * Construct a Group from the given leader,
     * members, and project.
     *
     * @param leader The leader of this group.
     * @param members Members of this group.
     * @param project The project this group is working on.
     */
    public Group (Userable leader, Userable[] members, Work project){
        // Index 0 will always be the leader
        groupMembers.add(leader);
        for(Userable userable: members){
            groupMembers.add(userable);
        }
        this.project = project;
    }

    /**
     *
     * @param leader This method set a new leader for the group.
     */
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

    /**
     *
     * @return This method will return the leader of this group.
     */
    public Userable getLeader(){
        return groupMembers.get(0);
    }


    /**
     *
     * @param members This method set a new group of members for the group.
     */
    //leader does not change, all the members are replaced
    public void setMembers(Userable[] members) {
        List<Userable> newGroupMemebers = new ArrayList<>();
        newGroupMemebers.add(groupMembers.get(0));
        for(Userable member: members){
            newGroupMemebers.add(member);
        }
        groupMembers = newGroupMemebers;
    }

    /**
     *
     * @return This method will return the members of this group.
     */
    public Userable[] getMembers() {
        Userable[] membersList = new Userable[groupMembers.size()-1];
        for(int i = 0; i < groupMembers.size()-1; i++){
            membersList[i] = groupMembers.get(i+1);
        }return membersList;
    }

    /**
     *
     * @param member The member who is going to be added to the group.
     * @return This method will return true iff the member is successfully added to the group.
     */
    public boolean addMember(Userable member) {
        if (!groupMembers.contains(member)) {
            groupMembers.add(member);
            return true;
        }return false;
    }

    /**
     *
     * @param member The member who is going to be removed from the group.
     * @return This method will return true iff the member is successfully removed from the group.
     */
    public boolean deleteMember(Userable member){
        if (!groupMembers.contains(member)){
            return false;
        }groupMembers.remove(member);
        return true;
    }

    /**
     *
     * @param project This method set a new project for the group to work on.
     */
    public void setProject(Work project) {
        this.project = project;
    }

    /**
     *
     * @return This method will return the project which this group is working on.
     */
    public Work getProject() {
        return project;
    }


    /**
     * Returns an iterator for this group.
     *
     * @return an iterator for this group.
     */
    @Override
    public Iterator<Userable> iterator() {
        return new GroupIterator();
    }

    /**
     * An Iterator for Group Userables.
     */
    private class GroupIterator implements Iterator<Userable> {

        /**
         * The index of the next Userable to return.
         */
        private int current = 0;

        /**
         * Returns whether there is another Userable to return.
         *
         * @return True iff there is another Userable to return.
         */
        @Override
        public boolean hasNext() {
            return current < groupMembers.size();
        }

        /**
         * Returns the next Userable.
         *
         * @return the next Userable.
         */
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

        /**
         * Removes the Userable just returned.
         */
        @Override
        public void remove() {
            Userable member = groupMembers.get(current);
            deleteMember(member);
        }

    }
}
