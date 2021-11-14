package main.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Timestamp;


public class Journal {
    private final HashMap<String, String[]> journals; // <Timestamp,<id, info>>
    private final String id;

    public Journal(String id){
        this.id = id;
        journals = new HashMap<String, String[]>();
    }

    public void AddJournal(String usr_id, String doc) {
        String[] m = {usr_id, doc};
        Timestamp cur_time = new Timestamp(System.currentTimeMillis());
        journals.put(String.valueOf(cur_time.getTime()), m);
    }

    public String getId() {
        return this.id;
    }

    public HashMap<String, String[]>  getJournal() {
        return journals;
    }
}
