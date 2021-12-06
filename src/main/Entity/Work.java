package main.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Work implements Workable, Serializable {

    // === Instance Variables ===

    // the name of the Work.
    private final String name;
    // the ID of the Work.
    private final String id;
    // the time of the Work has been created.
    private final String createTime;
    // the time of the Work has been ended.
    private String endTime;
    // the authority level of the Work.
    private final int level;
    // the department of the Work responsible for.
    private final String department;
    // the state of the Work: Pending, InProgress, Finished, Expired.
    private String state;
    // the description of the Work.
    private String describe;
    // the requirement of the Work.
    private String requirement;
    // whether Work has been assigned to a Group.
    private String isGroup = "0";
    // whether Work was calculated to KPI before.
    private String isKPI = "0";

    /* === Representation Invariants ===
     * create_time should always be before or be the same as the start_time
     * start_time should always be before or be the same as the end_time
     */

    /**
     * Construct a Work, giving them the given name, id
     * create_time, and level.
     *
     * @param name Name for this Work.
     * @param id ID for this Work.
     * @param level The level for this Work.
     * @param department The department responsible for this Work.
     *
     */
    public Work(String name, String id, String department, String requirement, int level, String endTime)  {
        this.name = name;
        this.id = id;
        this.level = level;
        this.state = "Pending";
        this.department = department;
        this.requirement = requirement;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.createTime = String.valueOf(now.getTime());
        this.endTime = endTime;
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
    public String getCreateTime() {
        return this.createTime;
    }

    // String Need to be the form of timestamp
    public void setEndTime(String time) {
        this.endTime = time;
    }


    /**
     *
     * @return This method will return the estimated end time of this Work.
     */
    @Override
    public String getEndTime() {
        return this.endTime;
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
     * @param sign Give a sign to mark this work has grouped or not.
     */
    @Override
    public void setSign(String sign) {
        this.isGroup = sign;
    }

    /**
     *
     * @return Return a sign to show whether this work was calculated to KPI before; 0 means no, 1 means yes
     */
    @Override
    public String getIsKPI() {
        return this.isKPI;
    }

    /**
     *
     * @param sign Give a sign to mark this work was calculated to KPI before.
     */
    @Override
    public void setIsKPI(String sign) {
        this.isKPI = sign;
    }


}

