package Entity;

public interface Work {

    public abstract String getName();

    public abstract String getId();

    public abstract int getLevel();

    public abstract String getCreate_time();

    public abstract String getEnd_time();

    public abstract String getState();

    public abstract void setState(String state);

    public abstract void Extend(String end_time);

    public abstract void setStart_time(String start_time);

    public abstract String getStart_time();

    public abstract void setDescribe(String information);

    public abstract String getDescribe();

    public abstract void setRequirement(String requirement);

    public abstract String getRequirement();
}
