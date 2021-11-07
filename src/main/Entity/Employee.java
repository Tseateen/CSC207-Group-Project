package main.Entity;

import java.io.Serializable;

public abstract class Employee implements Serializable {

    // === Instance Variables ===

    private final String id;

    // The department of this employee
    private String department;
    // The minimum wage of this employee
    private int wage;
    // The attendance of this employee
    private int attendance;
    // The authority level of this employee
    private int level;

    /* === Representation Invariants ===
     * The authority level for any employee should always equal or greater than 0. i.e. level = 0,1,2,3,...
     * The minimums wage of any employee should always equal or greater than 0.
     * The number of attendance for any employee should always equal or greater than 0.
     */

    /**
     * Construct an Employee, giving them the given department,
     * wage, and level.
     *
     * @param id
     * @param department Department for this employee.
     * @param wage Minimum wage for this employee.
     * @param level The authority level fore this employee.
     */
    public Employee(String id, String department, int wage, int level){
        this.id = id;
        this.department = department;
        this.wage = wage;
        this.level = level;
        this.attendance = 0;
    }

    // === Regular methods ===

    /**
     *
     * @return the number of attendance for this employee.
     */
    public int getAttendance(){
        return this.attendance;
    }

    /**
     * This method will help employee to claim attendance.
     */
    public void setAttendance(){
        this.attendance ++;
    }

    /**
     *
     * @return the number of wage for this employee.
     */
    public int getWage(){
        return this.wage;
    }

    /**
     *
     * @param wage This method can help admin setting the new minimum wage of this employee.
     */
    public void setWage(int wage){
        this.wage = wage;
    }

    /**
     *
     * @return This method will return the string which refers to the department of this employee.
     */
    public String getDepartment(){
        return this.department;
    }

    /**
     *
     * @param department This method will help admin setting the new department for this employee.
     */
    public void setDepartment(String department){
        this.department = department;
    }

    /**
     *
     * @return This method will return the authority level of this employee.
     */
    public int getLevel(){
        return this.level;
    }

    /**
     *
     * @param level This method can let admin to assign the new authority level for this employee.
     */
    public void setLevel(int level){
        this.level = level;
    }

    public String getID(){
        return this.id;
    }

}
