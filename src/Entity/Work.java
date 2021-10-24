package Entity;

public abstract class Work {
    private String name;
    private String id;
    private String start_time;
    private String end_time;
    private Journal journal;
    private int level;
    private String state;
    private String describe;
    private String requirement;

    public Work(String name, String id, String start_time, String end_time,
                String describe, String requirement, int level) {
        this.name = name;
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.state = "Unstarted";
        this.journal = Journal();
        this.describe = describe;
        this.requirement = requirement;
        this.level = level;
    }

    public void setDescribe(String information) {
        this.describe = information;
    }

    public String getDescribe() {
        return describe;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getRequirement() {
        return requirement;
}
