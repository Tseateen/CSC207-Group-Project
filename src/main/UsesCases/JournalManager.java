package main.UsesCases;

import main.Entity.*;

import java.util.*;

public class JournalManager {
    // Todo: need to add docstring
    private final Journal journal;

    public JournalManager (Journal journal) {
        this.journal = journal;
    }


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


    public ArrayList<String> CheckOnesJournal(String id) {
        // check a specific user's journal
        return Checker(id);
    }


    public ArrayList<String> CheckAll() {
        // check all info in journal
        return Checker("");
    }

    public Set<String> AllMembers() {
        // check all member who worked on this work before
        Set<String> Id_set = new HashSet<String>();
        for (String i: this.journal.getJournal().keySet()) {
            Id_set.add(this.journal.getJournal().get(i)[0]);
        }
        return Id_set;
    }
}
