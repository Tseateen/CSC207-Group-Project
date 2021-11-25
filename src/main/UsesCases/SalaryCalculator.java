package main.UsesCases;

import main.Entity.FullTimeEmployee;
import main.Entity.PartTimeEmployee;

public class SalaryCalculator {
    private final KPICalculator KPIcalculator;

    /**
     * Construct a SalaryCalculator.
     */
    public SalaryCalculator(KPICalculator kpiCalculator) {
        this.KPIcalculator = kpiCalculator;
    }

    /**
     * Calculate the total salary to be paid for a part-time employee.
     *
     * @param partTimeEmployee the part-time employee whose salary is going to be calculated.
     * @return An int that represents the total salary to be paid for this part-time employee.
     */
    public int calculatePartTimeSalary(PartTimeEmployee partTimeEmployee){
        return partTimeEmployee.getWorkingHour() * partTimeEmployee.getWage();
    }

    /**
     * Calculate the total salary to be paid for a full-time employee.
     *
     * @param fullTimeEmployee the full-time employee whose salary is going to be calculated.
     * @return An int that represents the total salary to be paid for this full-time employee.
     */
    public int calculateFullTimeSalary(FullTimeEmployee fullTimeEmployee){
        return calculateBonusFromKPI(fullTimeEmployee) + calculateBonusFromVacation(fullTimeEmployee) +
                fullTimeEmployee.getWage();
    }

    /**
     * Calculate the bonus salary from the vacation of a full-time employee.
     *
     * @param fullTimeEmployee the full-time employee whose bonus salary is going to be calculated.
     * @return An int that represent the bonus salary from the vacation of this full-time employee.
     */
    public int calculateBonusFromVacation(FullTimeEmployee fullTimeEmployee){
        if (fullTimeEmployee.getVacationUsed() > fullTimeEmployee.getTotalVacationWithSalary()){
                return (-(fullTimeEmployee.getVacationUsed() - fullTimeEmployee.getTotalVacationWithSalary()) *
                        (-50 * fullTimeEmployee.getLevel() + 550));
        }else{
            return ((fullTimeEmployee.getTotalVacationWithSalary() - fullTimeEmployee.getVacationUsed())) *
                    (fullTimeEmployee.getWage() / 30);
        }
    }

    /**
     * Calculate the bonus salary from the KPI of a full-time employee.
     *
     * @param fullTimeEmployee the full-time employee whose bonus salary is going to be calculated.
     * @return An int that represent the bonus salary from the KPI of this full-time employee.
     */
    public int calculateBonusFromKPI(FullTimeEmployee fullTimeEmployee){
        return this.KPIcalculator.calculateKPI(fullTimeEmployee.getID());
    }
}
