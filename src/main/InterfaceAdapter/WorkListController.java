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
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(info.get(6));
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
    public String FindWorkLevel(String workID){
            return this.workList.FindWorkLevel(workID);

    }
    // ==================================================

    // === Usage: FacadeSys Worker Case (iii) ====
    public boolean checkWorkExist(String workID){
        return this.workList.checkWorkExist(workID);
    }
    // ==================================================
}
