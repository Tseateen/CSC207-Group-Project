package Entity;

public class FullTimeEmployee extends Employee {
    /**
     * 一个全职员工，储存他工作相关信息
     *
     * @param position normal worker or manager目前就这两个，后续可能更改
     */

    private int totalVacationWithSalary;
    private int vacationUsed;
    private String position; //职位

    public FullTimeEmployee(String department, String position, int wage, int level) {
        super(department, wage, level);
        this.position = position;
        this.totalVacationWithSalary = 0;
        this.vacationUsed = 0;
    }

    public FullTimeEmployee(String department, int wage, int level) {
        super(department, wage, level);
        this.position = "";
        this.totalVacationWithSalary = 0;
        this.vacationUsed = 0;
    }


    /**
     * set position for this full time employee and return their old position
     */
    public String setPosition(String position) {
        String op = new String(this.position);
        this.position = position;
        return op;
    }

    public String getPosition() {
        return this.position;
    }

    public int getTotalVacationWithSalary(){
        return this.totalVacationWithSalary;
    }

    public void setTotalVacationWithSalary(int totalVacationWithSalary){
        this.totalVacationWithSalary = totalVacationWithSalary;
    }

    public int getVacationUsed(){
        return this.vacationUsed;
    }

    public void setVacationUsed(int vacationUsed){
        this.vacationUsed = vacationUsed;
    }

    public String toString() {
        return this.getPosition() + " of " + this.getDepartment() +  ", " + this.getLevel() + " level.";
    }
}
