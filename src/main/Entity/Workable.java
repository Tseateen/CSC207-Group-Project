package main.Entity;

import java.io.Serializable;

public interface Workable extends Serializable{
    /**
     * An interface of User class, for reducing dependency purpose.
     *
     */

    String getName();

    String getID();

    int getLevel();

    String getCreateTime();

    String getEndTime();

    String getDepartment();

    String getState();

    void setState(String state);

    void setDescribe(String information);

    String getDescribe();

    void setRequirement(String requirement);

    String getRequirement();

    String getSign();

    void setSign(String sign);

    void setEndTime(String time);

    void setIsKPI(String IsKPI);

    String getIsKPI();
}


