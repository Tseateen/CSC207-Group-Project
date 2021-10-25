package Entity;

import java.util.HashMap;
public class PartTimeEmployee extends Employee{
    // === Instance Variables ===

    // TODO: Should we implement this with key=week(i.e. Monday), value= time segment by string.
    // The schedule of this PartTime employee.
    private HashMap<String, String[]> schedule;

    /**
     * Construct a PartTimeEmployee, giving them the given department, wage, and level.
     * @param department Department for this employee.
     * @param wage Minimum wage for this employee.
     * @param level The authority level fore this employee.
     */
    public PartTimeEmployee(String department, int wage, int level) {
        super(department, wage, level);
    }

    /**
     * This is an Overload Constructor for PartTimeEmployee. It also contruct PartTImeEmployee
     * giving them the given department, wage, level and schedule.
     *
     * @param department Department for this employee.
     * @param wage Minimum wage for this employee.
     * @param level The authority level fore this employee.
     * @param schedule The schedule of this PartTime employee.
     */
    public PartTimeEmployee(String department, int wage, int level, HashMap<String, String[]> schedule) {
        super(department, wage, level);
        this.schedule = schedule;
    }

    // === Regular methods ===

    /**
     *
     * @return This method returns the schedule of a part-time employee.
     */

    public HashMap<String, String[]> getSchedule(){
        return this.schedule;
    }

    /**
     *
     *
     * @param schedule The schedule that is going to modify a part-time employee's schedule.
     */
    public void setSchedule(HashMap<String, String[]> schedule){
        this.schedule = schedule;
    }

}
