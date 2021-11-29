package main.Entity;

import java.io.Serializable;

public interface Workable extends Serializable{

    String getName();

    String getID();

    int getLevel();

    String getCreate_time();

    String getEnd_time();

    String getDepartment();

    String getState();

    void setState(String state);

    void setStart_time(String time);

    String getStart_time();

    void setDescribe(String information);

    String getDescribe();

    void setRequirement(String requirement);

    String getRequirement();

    String getSign();

    void setSign(String sign);

    void setEnd_time(String time);
}


