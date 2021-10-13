package Entity;

public class FullTimeEmployee extends Employee {

    // === Instance Variables ===

    // The total annual paid leave for this employee.
    private int totalVacationWithSalary;
    // the number of annual leave have been used by this employee.
    private int vacationUsed;
    // The position of this employee. For instance, sales, engineer, manger....
    private String position; //职位

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
     */
    public FullTimeEmployee(String department, String position, int wage, int level) {
        super(department, wage, level);
        this.position = position;
        this.totalVacationWithSalary = 0;
        this.vacationUsed = 0;
    }

    /**
     * This is the overload constructor. It also constructs a FullTimeEmployee
     * @param department Department for this employee.
     * @param wage Minimum wage for this employee.
     * @param level The authority level fore this employee.
     */
    public FullTimeEmployee(String department, int wage, int level) {
        super(department, wage, level);
        this.position = "";
        this.totalVacationWithSalary = 0;
        this.vacationUsed = 0;
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
     * @return After assign new position for this employee, this method will return the old position for code tracking
     * purpose.
     */
    public String setPosition(String position) {
        String op = new String(this.position);
        this.position = position;
        return op;
    }

    /**
     *
     * @return This method will return total annual paid leave for this employee.
     */
    public int getTotalVacationWithSalary(){
        return this.totalVacationWithSalary;
    }

    /**
     *
     * @param totalVacationWithSalary Given the new total annual paid leave for this employee
     */
    public void setTotalVacationWithSalary(int totalVacationWithSalary){
        this.totalVacationWithSalary = totalVacationWithSalary;
    }

    /**
     *
     * @return This method will return the total number of annual paid leave have been used.
     */
    public int getVacationUsed(){
        return this.vacationUsed;
    }

    /**
     * TODO: Is this setter implement correctly?
     * @param vacationUsed Given the new total number of annual paid leave have been used.
     */
    public void setVacationUsed(int vacationUsed){
        this.vacationUsed = vacationUsed;
    }

    /**
     *
     * @return the string representation of this FullTimeEmployee. For instance,
     * "Manager of IT with the authority level 0"
     */
    @Override
    public String toString() {
        return " " + this.getPosition() + " of " + this.getDepartment() +  " with authority level " + this.getLevel() ;
    }
}
