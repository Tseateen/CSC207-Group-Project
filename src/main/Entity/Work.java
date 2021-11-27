package main.Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.sql.Timestamp;

public class Work implements Workable, Serializable {
    private final String name;
    private final String id;
    private final String create_time;
    private String start_time;
    private String end_time;
    private final int level;
    private final String department;
    private String state; // Pending, InProgress, Finished, Expired
    private String describe;
    private String requirement;
    private String isGroup = "0";

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
    public Work(String name, String id, String department, String requirement, int level)  {
        this.name = name;
        this.id = id;
        this.level = level;
        this.state = "Pending";
        this.department = department;
        this.requirement = requirement;
        Timestamp now = new Timestamp(System.currentTimeMillis());

        this.create_time = String.valueOf(now.getTime());
        this.start_time = null;
        Calendar calendar=Calendar.getInstance();
        calendar.set(2099, Calendar.DECEMBER, 31,0,0,0);
        Timestamp end = new Timestamp(calendar.getTimeInMillis());
        this.end_time =String.valueOf(end.getTime());
    }

    /**
     *
     * @return This method will return the name of this Work.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return This method will return the ID of this Work.
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     *
     * @return This method will return the level of this Work.
     */
    @Override
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return This method will return the time that this Work is being created.
     */
    @Override
    public String getCreate_time() {
        return this.create_time;
    }

    // String Need to be the form of timestamp
    public void setEnd_time(String time) {
        this.end_time = time;
    }


    /**
     *
     * @return This method will return the estimated end time of this Work.
     */
    @Override
    public String getEnd_time() {
        return this.end_time;
    }


    /**
     *
     * @return This method will return the department responsible for the Work.
     */
    @Override
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
    @Override
    public void setState(String state) {
        this.state = state;
    }


    /**
     *
     * @param time Given the start time of this Work.
     */
    @Override
    // String Need to be the form of timestamp
    public void setStart_time(String time) {
        this.start_time = time;
    }

    /**
     *
     * @return This method will return the start time of this Work.
     */
    @Override
    public String getStart_time() {
        return this.start_time;
    }

    /**
     *
     * @param information Given the information of this Work.
     */
    @Override
    public void setDescribe(String information) {
        this.describe = information;
    }

    /**
     *
     * @return This method will return the information of this Work.
     */
    @Override
    public String getDescribe() {
        return this.describe;
    }

    /**
     *
     * @param requirement Given the requirement of this Work.
     */
    @Override
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     *
     * @return This method will return the requirement of this Work.
     */
    @Override
    public String getRequirement() {
        return this.requirement;
    }

    /**
     *
     * @return Return a sign to show there is a group for this work or not; 0 means non, 1 means yes
     */
    @Override
    public String getSign() {
        return this.isGroup;
    }

    /**
     *
     * @param sign Give a sign to mark this work has group or not.
     */
    @Override
    public void setSign(String sign) {
        this.isGroup = sign;
    }
}

