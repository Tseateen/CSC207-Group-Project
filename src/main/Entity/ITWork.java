package main.Entity;

public class ITWork implements Work {
    private final String name;
    private final String id;
    private final String create_time;
    private String start_time;
    private String end_time;
    private final int level;
    private String state;
    private String describe;
    private String requirement;

    /* === Representation Invariants ===
     * create_time should always be before or be the same as the start_time
     * start_time should always be before or be the same as the end_time
     */

    /**
     * Construct an ITWork, giving them the given name, id
     * create_time, and level.
     *
     * @param name Name for this ITWork.
     * @param id ID for this ITWork.
     * @param create_time The time that this ITWork is being created.
     * @param level The level for this ITWork.
     *
     */
    public ITWork(String name, String id, String create_time, int level) {
        this.name = name;
        this.id = id;
        this.create_time = create_time;
        this.level = level;
        this.state = "Pending";
    }

    /**
     *
     * @return This method will return the name of this ITWork.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return This method will return the ID of this ITWork.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     *
     * @return This method will return the level of this ITWork.
     */
    @Override
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return This method will return the time that this ITWork is being created.
     */
    @Override
    public String getCreate_time() {
        return this.create_time;
    }

    /**
     *
     * @return This method will return the estimated end time of this ITWork.
     */
    @Override
    public String getEnd_time() {
        return this.end_time;
    }

    /**
     *
     * @return This method will return the state of this ITWork.
     */
    @Override
    public String getState() {
        return this.state;
    }

    /**
     *
     * @param state Given the new state of this ITWork.
     */
    @Override
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @param extend_time Given the extended time needed for this ITWork.
     */
    @Override
    public void Extend(String extend_time) {
        this.end_time += extend_time;
    }

    /**
     *
     * @param start_time Given the start time of this ITWork.
     */
    @Override
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    /**
     *
     * @return This method will return the start time of this ITWork.
     */
    @Override
    public String getStart_time() {
        return this.start_time;
    }

    /**
     *
     * @param information Given the information of this ITWork.
     */
    @Override
    public void setDescribe(String information) {
        this.describe = information;
    }

    /**
     *
     * @return This method will return the information of this ITWork.
     */
    @Override
    public String getDescribe() {
        return this.describe;
    }

    /**
     *
     * @param requirement Given the requirement of this ITWork.
     */
    @Override
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     *
     * @return This method will return the requirement of this ITWork.
     */
    @Override
    public String getRequirement() {
        return this.requirement;
    }
}
