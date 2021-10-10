package Entity;

public class FullTimeEmployee extends Employee {

    private int totalVacationWithSalary;
    private int vacationUsed;

    public FullTimeEmployee(String department, String position, int wage, int level) {
        super(department, position, wage, level);
        this.totalVacationWithSalary = 0;
        this.vacationUsed = 0;
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
}
