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
        due = String.valueOf(Integer.valueOf(due) + Integer.valueOf(extend_date));
        work.setEnd_time(due);
    }

    public void changeState(Work work, String new_statue) {
        work.setState(new_statue);
    }

    public void autoChangeState(Work work) {
        String statue = work.getState();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long due = Integer.valueOf(work.getEnd_time());
        if (statue.equals("Finished")) {
            return;
        }
        if (now.getTime() > due) {
            work.setState("Expired");
            return;
        }
        if (work.getSign().equals("1")) {
            work.setState("InProgress");
            return;
        }
        work.setState("Pending");

    }

}
