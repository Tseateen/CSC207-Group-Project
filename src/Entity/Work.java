package Entity;

public abstract class Work {
    private final String name;
    private final String id;
    private final String create_time;
    private String start_time;
    private String end_time;
    private final int level;
    private String state;
    private String describe;
    private String requirement;


    public Work(String name, String id, String create_time, String end_time, int level,
                String describe, String requirement){
        this.name = name;
        this.id = id;
        this.create_time= create_time;
        this.end_time = end_time;
        this.level = level;
        this.state = "Undo";
        this.describe = describe;
        this.requirement = requirement;
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    public int getLevel() {
        return level;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void Extend(String end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
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
}
