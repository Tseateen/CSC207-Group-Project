package main.Entity;

import com.sun.source.doctree.SinceTree;

import java.io.Serializable;

public interface Workable {

    public abstract String getName();

    public abstract String getID();

    public abstract int getLevel();

    public abstract String getCreate_time();

    public abstract String getEnd_time();

    public abstract String getDepartment();

    public abstract String getState();

    public abstract void setState(String state);

    public abstract void setStart_time(String time);

    public abstract String getStart_time();

    public abstract void setDescribe(String information);

    public abstract String getDescribe();

    public abstract void setRequirement(String requirement);

    public abstract String getRequirement();

    public abstract String getSign();

    public abstract void setSign(String sign);
}


