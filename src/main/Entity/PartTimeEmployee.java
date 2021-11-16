package main.Entity;

import java.io.Serializable;
import java.util.HashMap;
public class PartTimeEmployee extends Employee implements Serializable {
    // === Instance Variables ===

    // The schedule of this PartTime employee.
    private HashMap<String, String[]> schedule;

    /**
     * Construct a PartTimeEmployee, giving them the given department, wage, and level.
     * @param department Department for this employee.
     * @param wage Minimum wage for this employee.
     * @param level The authority level fore this employee.
     * @param id the id of employee
     */
    public PartTimeEmployee(String department, int wage, int level, String id) {
        super(id, department, wage, level);
        this.schedule = new HashMap<String, String[]>();
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
    public PartTimeEmployee(String department, int wage, int level,String id, HashMap<String, String[]> schedule) {
        super(id, department, wage, level);
        this.schedule = schedule;
    }

    public PartTimeEmployee(){
        super();
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