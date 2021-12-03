package main.Entity;

import java.io.Serializable;

public class FullTimeEmployee extends Employee implements Serializable {

    // === Instance Variables ===

    // The total annual paid leave for this employee.
    private String totalVacationWithSalary;
    // the number of annual leave have been used by this employee.
    private String vacationUsed;
    // The position of this employee. For instance, sales, engineer, manger....
    private String position;
    // The state of employee. For instance, working, vacation...
    private String state = "Working";

    /* === Representation Invariants ===
     * totalVacationWithSalary should always equal or greater than 0.
     * vacationUsed should always equal or greater than 0, but it should also smaller or equal to
     * totalVacationWithSalary. i.e. 0 <= vacationUsed <= totalVacationWithSalary
     */


    /**
     * Construct a FullTImeEmployee, giving them the given department,
     * position, wage and level.
     * @param department Department for this employee.
     * @param position The position of this employee.
     * @param wage Minimum wage for this employee.
     * @param level The authority level fore this employee.
     * @param id FullTime employeeID
     */
    public FullTimeEmployee(String department, String position, String wage, int level, String id) {
        super(id, department, wage, level);
        this.position = position;
        this.totalVacationWithSalary = "0";
        this.vacationUsed = "0";
    }

    public FullTimeEmployee(){
        super();
    }

    /**
     * This is the overload constructor. It also constructs a FullTimeEmployee
     * @param department Department for this employee.
     * @param wage Minimum wage for this employee.
     * @param level The authority level fore this employee.
     */
    public FullTimeEmployee(String department, String wage, int level, String id) {
        super(id, department, wage, level);
        this.position = "";
        this.totalVacationWithSalary = "0";
        this.vacationUsed = "0";
    }

    // === Regular methods ===

    /**
     *
     * @return This method will return the current position of this employee.
     */
    public String getPosition() {
        return this.position;
    }

    /**
     *
     * @param position Given the new position of this employee
     * purpose.
     */
    public void setPosition(String position) {
        String op = this.position;
        this.position = position;
    }

    /**
     *
     * @return This method will return total annual paid leave for this employee.
     */
    public String getTotalVacationWithSalary(){
        return this.totalVacationWithSalary;
    }

    /**
     *
     * @param totalVacationWithSalary Given the new total annual paid leave for this employee
     */
    public void setTotalVacationWithSalary(String totalVacationWithSalary){
        this.totalVacationWithSalary = totalVacationWithSalary;
    }

    /**
     *
     * @return This method will return the total number of annual paid leave have been used.
     */
    public String getVacationUsed(){
        return this.vacationUsed;
    }

    /**
     *
     * @param vacationUsed Given the new total number of annual paid leave have been used.
     */
    public void setVacationUsed(String vacationUsed){
        this.vacationUsed = vacationUsed;
    }

    public void setState(String i) {
        this.state = i;
    }

    public String getState() {
        return state;
    }

}
