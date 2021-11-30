package main.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Group implements Iterable<String>, Serializable {
    private String leader_id;
    private final List<String> groupMembers = new ArrayList<>();
    private String workID;


    /**
     * Construct a Group from the given leader_id,
     * members, and project.
     *
     * @param workID The work ID of the work this group is working on.
     * @param leader_id The leader_id of this group.
     */
    public Group(String leader_id, String workID){
        this.leader_id = leader_id;
        this.workID = workID;
    }

    /**
     *
     * @param leader_id This method set a new leader for the group.
     */
    public void setLeaderId(String leader_id){
        this.leader_id = leader_id;
        this.groupMembers.remove(leader_id);
    }


    /**
     *
     * @return This method will return the leader of this group.
     */
    public String getLeaderID(){
        return this.leader_id;
    }


    /**
     *
     * @return This method will return the members of this group.
     */
    public List<String> getMembers() {
        return this.groupMembers;
    }

    public String getWorkID() {
        return this.workID;
    }

    /**
     * This method add a new member to the group.
     *
     * @param member The member who is going to be added to the group.
     * @return This method will return true iff the member is successfully added to the group.
     */
    public boolean addMember(String member) {
        if (!this.groupMembers.contains(member)) {
            this.groupMembers.add(member);
            return true;
        }return false;
    }

    /**
     * This method remove a member from the group.
     *
     * @param member The member who is going to be removed from the group.
     */
    public void deleteMember(String member){
        this.groupMembers.remove(member);
    }

    /**
     *
     * @param workID This method set a new project for the group to work on.
     */
    public void setProject(String workID) {
        this.workID = workID;
    }

    /**
     *
     * @return This method will return the project which this group is working on.
     */
    public String getProject() {
        return this.workID;
    }


    /**
     * Returns an iterator for this group.
     *
     * @return an iterator for this group.
     */
    @Override
    public Iterator<String> iterator() {
        return new GroupIterator();
    }

    /**
     * An Iterator for Group Userable.
     */
    private class GroupIterator implements Iterator<String> {

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
        public String next() {
            String res;

            // List.get(i) throws an IndexOutBoundsException if
            // we call it with i >= groupMembers.size().
            // But Iterators next() needs to throw a
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
            String member = groupMembers.get(current);
            deleteMember(member);
        }

    }
}
