package main.UsesCases;

import com.sun.jdi.IntegerValue;
import main.Entity.Group;
import main.Entity.Work;
import main.Entity.WorkFactory;
import java.sql.Timestamp;

public class WorkManager {


    /**
     * Extend a work (add extra works to the current work).
     * @param work the work which is going to be extended.
     * @param extend_date how much date its gonna extended.
     */
    public void extendWork(Work work, String extend_date) {
        String due = work.getEnd_time();
        due = String.valueOf(Integer.parseInt(due) + Integer.parseInt(extend_date));
        work.Extend(due);
    }

    public void autoChangeStatue(Work work, String new_statue) {
        work.setState(new_statue);
    }

    // Todo: How to auto change statue when a work expire? maybe add a static method in Work??

}
