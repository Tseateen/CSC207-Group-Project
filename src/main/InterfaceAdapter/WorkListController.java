package main.InterfaceAdapter;

import main.UsesCases.IWorkList;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WorkListController {

    // === Instance Variables ===
    private final IWorkList workList;


    /**
     * Construct the WorkListController.
     */
    public WorkListController(IWorkList workList) {
        this.workList = workList;
    }


    // === Usage: FacadeSys Worker Case (ii) ====
    public boolean createWork(ArrayList<String> info) {
        if (info.size() < 6) {
            Calendar calendar=Calendar.getInstance();
            calendar.set(2099, Calendar.DECEMBER, 31,0,0,0);
            Timestamp end = new Timestamp(calendar.getTimeInMillis());
            String endTime =String.valueOf(end.getTime());
            this.workList.addWork(info.get(0), info.get(1), info.get(2), info.get(3), Integer.parseInt(info.get(4)), endTime);
            return true;
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(info.get(5));
            Timestamp ts = new Timestamp(date.getTime());
            String endTime =String.valueOf(ts.getTime());
            this.workList.addWork(info.get(0), info.get(1), info.get(2), info.get(3), Integer.parseInt(info.get(4)), endTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    // ==================================================


    // === Usage: FacadeSys Worker Case (iii) ====
    /**
     * Get the authority level of the Work.
     *
     * @param workID the ID of the Work.
     *
     * @return a string with the authority level of the Work.
     */
    public String FindWorkLevel(String workID){
            return this.workList.FindWorkLevel(workID);

    }
    // ==================================================


    // === Usage: FacadeSys Worker Case (iii) ====
    /**
     * Verify work exist or not.
     *
     * @param workID the work's id which is going to be extended.
     *
     * @return work exist or not.
     */
    public boolean checkWorkExist(String workID){
        return this.workList.checkWorkExist(workID);
    }
    // ==================================================
}
