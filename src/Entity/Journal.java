package Entity;

import java.time.LocalTime;
import java.util.HashMap;

public class Journal {
    private final HashMap<LocalTime, HashMap<Userable, String>> journals;

    public Journal(){
        journals = new HashMap<LocalTime, HashMap<Userable, String>>();
    }

    public void AddJournal(Userable user, String doc) {
        HashMap<Userable, String> m = new HashMap<Userable, String>();
        m.put(user, doc);
        journals.put(LocalTime.now(), m);
    }

    public HashMap<LocalTime, HashMap<Userable, String>>  getJournal() {
        return journals;
    }
}
