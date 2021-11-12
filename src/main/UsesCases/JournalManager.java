package main.UsesCases;

import main.Entity.*;

import java.util.*;

public class JournalManager {

    // === Instance Variables ===
    private final Journal journal;


    public JournalManager (Journal journal) {
        this.journal = journal;
    }


    /**
     * This method will check the work in journals by the work ID.
     *
     * @param req the ID of the work.
     * @return the Journal docs of the information of the project.
     *
     */
    private ArrayList<String> Checker(String req) {
        // req means id, if there is an id be input,means check single, designed for code smell
        ArrayList<String> JournalDocs = new ArrayList<String>();
        for (String i: journal.getJournal().keySet()) {
            if (req.length() == 0 || journal.getJournal().get(i)[0].equals(req)){
                JournalDocs.add(i +"---"+ journal.getJournal().get(i)[0] +"---"+ journal.getJournal().get(i)[1]);
            }
        }
        return JournalDocs;
    }


    /**
     * This method will check the journal of a specific user by its id.
     *
     * @param id the ID of the work.
     * @return the Journal docs of the information related to the specific user.
     *
     */
    public ArrayList<String> CheckOnesJournal(String id) {
        // check a specific user's journal
        return Checker(id);
    }


    /**
     * This method will check the journal of all the users by its id.
     *
     * @return the Journal docs of the information for all users.
     *
     */
    public ArrayList<String> CheckAll() {
        // check all info in journal
        return Checker("");
    }


    /**
     * This method will set the journal of for all members and check all members if they have worked on the work before.
     *
     * @return the ID of the journal.
     *
     */
    public Set<String> AllMembers() {
        // check all member who worked on this work before
        Set<String> Id_set = new HashSet<String>();
        for (String i: this.journal.getJournal().keySet()) {
            Id_set.add(this.journal.getJournal().get(i)[0]);
        }
        return Id_set;
    }
}
