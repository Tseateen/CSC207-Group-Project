package Entity;

public class Sale_Work implements Work{
    private final String name;
    private final String id;
    private final String create_time;
    private String start_time;
    private String end_time;
    private final int level;
    private String state;
    private String describe;
    private String requirement;

    public Sale_Work(String name, String id, String create_time, int level) {
        this.name = name;
        this.id = id;
        this.create_time = create_time;
        this.level = level;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getCreate_time() {
        return this.create_time;
    }

    @Override
    public String getEnd_time() {
        return this.end_time;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void Extend(String end_time) {
        this.end_time += end_time;
    }

    @Override
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    @Override
    public String getStart_time() {
        return this.start_time;
    }

    @Override
    public void setDescribe(String information) {
        this.describe = information;
    }

    @Override
    public String getDescribe() {
        return this.describe;
    }

    @Override
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public String getRequirement() {
        return this.requirement;
    }
}
