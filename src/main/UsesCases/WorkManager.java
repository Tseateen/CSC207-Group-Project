package main.UsesCases;

import com.sun.jdi.IntegerValue;
import main.Entity.Group;
import main.Entity.Work;
import main.Entity.Workable;

import java.sql.Timestamp;

public class WorkManager implements IWorkManager{


    /**
     * Extend a work (add extra works to the current work).
     * @param work the work which is going to be extended.
     * @param extend_date how much date its gonna extended.
     */
    public void extendWork(Workable work, String extend_date) {
        String due = work.getEnd_time();
        due = String.valueOf(Integer.parseInt(due) + Integer.parseInt(extend_date));
        work.setEnd_time(due);
        autoChangeState(work);
    }

    /**
     * Change a work's state
     * @param work the work which is going to be changed.
     * @param new_statue the state that the given work is going to be changed to.
     */
    public void changeState(Workable work, String new_statue) {
        work.setState(new_statue);
        autoChangeState(work);
    }

    /**
     * Change a work's state automatically as the time goes.
     * @param work the work which is going to be changed.
     */
    public void autoChangeState(Workable work) {
        String statue = work.getState();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long due = Integer.parseInt(work.getEnd_time());
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