package main.Entity;

import java.util.Date;
import java.util.Calendar;

public class Work{
    private final String name;
    private final String id;
    private final Date create_time;
    private Date start_time;
    private Date end_time;
    private final int level;
    private final String department;
    private String state;
    private String describe;
    private String requirement;

    /* === Representation Invariants ===
     * create_time should always be before or be the same as the start_time
     * start_time should always be before or be the same as the end_time
     */

    /**
     * Construct an Work, giving them the given name, id
     * create_time, and level.
     *
     * @param name Name for this Work.
     * @param id ID for this Work.
     * @param level The level for this Work.
     * @param department The department responsible for this Work.
     *
     */
    public Work(String name, String id, String department, int level)  {
        this.name = name;
        this.id = id;
        this.level = level;
        this.state = "Pending";
        this.department = department;
        Calendar current=Calendar.getInstance();
        this.create_time = current.getTime();
        this.start_time = null;
        Calendar calendar=Calendar.getInstance();
        calendar.set(3000, Calendar.DECEMBER, 31,0,0,0);
        this.end_time =calendar.getTime();
    }

    /**
     *
     * @return This method will return the name of this Work.
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return This method will return the ID of this Work.
     */
    public String getID() {
        return this.id;
    }

    /**
     *
     * @return This method will return the level of this Work.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return This method will return the time that this Work is being created.
     */

    public Date getCreate_time() {
        return this.create_time;
    }

    // String Need to be the form of "YYYY/MM/DD HH:mm:ss"
    public void setEnd_time(String time) {
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(5, 7)) -1;
        int date = Integer.parseInt(time.substring(8, 10));
        int hour = Integer.parseInt(time.substring(11, 13));
        int minute = Integer.parseInt(time.substring(14, 16));
        int second = Integer.parseInt(time.substring(17, 19));
        Calendar end_time = Calendar.getInstance();
        end_time.set(year, month, date,hour, minute, second);
        this.end_time = end_time.getTime();
    }


    /**
     *
     * @return This method will return the estimated end time of this Work.
     */

    public Date getEnd_time() {
        return this.end_time;
    }


    /**
     *
     * @return This method will return the department responsible for the Work.
     */
    public String getDepartment() {
        return this.department;
    }


    /**
     *
     * @return This method will return the state of this Work.
     */
    public String getState() {
        return this.state;
    }

    /**
     *
     * @param state Given the new state of this Work.
     */

    public void setState(String state) {
        this.state = state;
    }

    // Todo: Implement this method.
    /**
     *
     * @param day Given the extended day needed for this Work.
     */

    public void Extend(int day) {

    }

    /**
     *
     * @param time Given the start time of this Work.
     */
    // String Need to be the form of "YYYY/MM/DD HH:mm:ss"
    public void setStart_time(String time) {
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(5, 7)) -1;
        int date = Integer.parseInt(time.substring(8, 10));
        int hour = Integer.parseInt(time.substring(11, 13));
        int minute = Integer.parseInt(time.substring(14, 16));
        int second = Integer.parseInt(time.substring(17, 19));
        Calendar start_time = Calendar.getInstance();
        start_time.set(year, month, date, hour, minute, second);
        this.start_time = start_time.getTime();
    }

    /**
     *
     * @return This method will return the start time of this Work.
     */
    public Date getStart_time() {
        return this.start_time;
    }

    /**
     *
     * @param information Given the information of this Work.
     */
    public void setDescribe(String information) {
        this.describe = information;
    }

    /**
     *
     * @return This method will return the information of this Work.
     */
    public String getDescribe() {
        return this.describe;
    }

    /**
     *
     * @param requirement Given the requirement of this Work.
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     *
     * @return This method will return the requirement of this Work.
     */
    public String getRequirement() {
        return this.requirement;
    }
}

