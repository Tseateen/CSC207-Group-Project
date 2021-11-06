package main.UsesCases;

import main.Entity.Group;
import main.Entity.Work;
import main.Entity.WorkFactory;

public class WorkManager {

    // TODO: return void? or return boolean?
    /**
     * Create a work.
     * @param workType the type of the work.
     * @param name the name (brief description) of the work.
     * @param id the id of the work.
     * @param createTime the time which the work is created.
     * @param level the level of the work (the authority level required to check this work).
     */
    public void createWork(String workType, String name, String id, String createTime, int level){
        WorkFactory WF = new WorkFactory();
        Work w = WF.createWork(workType, name, id, createTime, level);
    }

    /**
     * Delete a work.
     * @param workid the id of the work which is going to be deleted.
     * @return true iff the work is successfully deleted.
     */
    // TODO: implement this method
    public boolean deleteWork(String workid) {
        return false;
    }

    /**
     * Extend a work (add extra works to the current work).
     * @param workid the id of the work which is going to be extended.
     * @return true iff the work is successfully extended.
     */
    // TODO: implement this method
    public boolean extendWork(String workid) {
        return false;
    }

}
