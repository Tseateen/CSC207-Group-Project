package main.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Group implements Iterable<Userable> {
    private final Userable leader;
    private List<Userable> groupMembers = new ArrayList<>();
    private String workid;


    /**
     * Construct a Group from the given leader,
     * members, and project.
     *
     * @param workid The work ID of the work this group is working on.
     * @param leader The leader of this group.
     */
    public Group(Userable leader, String workid){
        this.leader = leader;
        this.workid = workid;
    }

    /**
     *
     * @param leader This method set a new leader for the group.
     */
    public void setLeader(Userable leader){
        if (this.groupMembers.contains(leader)){
            int leaderIndex = this.groupMembers.indexOf(leader);
            Userable previousLeader = this.groupMembers.get(0);
            this.groupMembers.set(0, leader);
            this.groupMembers.set(leaderIndex, previousLeader);
        }
        else{
            Userable previousLeader = this.groupMembers.get(0);
            this.groupMembers.set(0, leader);
            this.groupMembers.add(previousLeader);

        }
    }


    /**
     *
     * @return This method will return the leader of this group.
     */
    public Userable getLeader(){
        return this.leader;
    }


    /**
     *
     * @param members This method set a new group of members for the group.
     */
    //leader does not change, all the members are replaced
    public void setMembers(Userable[] members) {
        List<Userable> newGroupMemebers = new ArrayList<>();
        newGroupMemebers.add(this.groupMembers.get(0));
        for(Userable member: members){
            newGroupMemebers.add(member);
        }
        this.groupMembers = newGroupMemebers;
    }

    /**
     *
     * @return This method will return the members of this group.
     */
    public Userable[] getMembers() {
        Userable[] membersList = new Userable[this.groupMembers.size()-1];
        for(int i = 0; i < this.groupMembers.size()-1; i++){
            membersList[i] = this.groupMembers.get(i+1);
        }return membersList;
    }

    public String getWorkid() {
        return this.workid;
    }

    /**
     * This method add a new member to the group.
     *
     * @param member The member who is going to be added to the group.
     * @return This method will return true iff the member is successfully added to the group.
     */
    public boolean addMember(Userable member) {
        if (!this.groupMembers.contains(member)) {
            this.groupMembers.add(member);
            return true;
        }return false;
    }

    /**
     * This method remove a member from the group.
     *
     * @param member The member who is going to be removed from the group.
     * @return This method will return true iff the member is successfully removed from the group.
     */
    public boolean deleteMember(Userable member){
        if (!this.groupMembers.contains(member)){
            return false;
        }this.groupMembers.remove(member);
        return true;
    }

    /**
     *
     * @param workid This method set a new project for the group to work on.
     */
    public void setProject(String workid) {
        this.this.workid = workid;
    }

    /**
     *
     * @return This method will return the project which this group is working on.
     */
    public String getProject() {
        return this.workid;
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
