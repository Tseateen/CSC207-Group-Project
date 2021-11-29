package main.Entity;

import java.io.Serializable;
public class PartTimeEmployee extends Employee implements Serializable {
    // === Instance Variables ===

    // The working hour of this PartTime employee.
    private String workingHour;

    /**
     * Construct a PartTimeEmployee, giving them the given department, wage, and level.
     * @param department Department for this employee.
     * @param wage Minimum wage hourly for this employee.
     * @param level The authority level fore this employee.
     * @param id the id of employee
     */
    public PartTimeEmployee(String department, String wage, int level, String id) {
        super(id, department, wage, level);
        this.workingHour = "0";
    }

    public PartTimeEmployee(){
        super();
    }
    // === Regular methods ===

    /**
     *
     * @return This method returns the working hour of a part-time employee.
     */

    public String getWorkingHour(){
        return this.workingHour;
    }

    /**
     *
     *
     * @param hours The hours that a part-time employee worked.
     */
    public void addWorkingHour(String hours){
        Long cur_hour = Long.parseLong(this.workingHour) + Long.parseLong(hours);
        this.workingHour = String.valueOf(cur_hour);
    }

    /**
     * Resets the working hours of the part-time employee.
     */
    public void resetWorkingHour() {this.workingHour = "0";}

}