package main.Entity;

import java.time.LocalTime;
import java.util.HashMap;

public class Journal {
    private final HashMap<String, HashMap<Userable, String>> journals;
    private final String id;

    public Journal(String id){
        this.id = id;
        journals = new HashMap<String, HashMap<Userable, String>>();
    }

    public void AddJournal(Userable user, String doc) {
        HashMap<Userable, String> m = new HashMap<Userable, String>();
        m.put(user, doc);
        journals.put(LocalTime.now(), m);
    }

    public String getId() {
        return this.id;
    }

    public HashMap<LocalTime, HashMap<Userable, String>>  getJournal() {
        return journals;
    }
}
