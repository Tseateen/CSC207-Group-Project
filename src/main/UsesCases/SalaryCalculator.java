package main.UsesCases;

import main.Entity.FullTimeEmployee;
import main.Entity.PartTimeEmployee;

import java.io.Serializable;

public class SalaryCalculator implements Serializable {

    // === Instance Variables ===
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
    public String calculatePartTimeSalary(PartTimeEmployee partTimeEmployee){
        Long hour = Long.parseLong(partTimeEmployee.getWorkingHour());
        Long wage = Long.parseLong(partTimeEmployee.getWage());
        return String.valueOf(hour * wage);
    }


    /**
     * Calculate the total salary to be paid for a full-time employee.
     *
     * @param fullTimeEmployee the full-time employee whose salary is going to be calculated.
     * @return An int that represents the total salary to be paid for this full-time employee.
     */
    public String calculateFullTimeSalary(FullTimeEmployee fullTimeEmployee, IGroupList groupList, IWorkList workList){
        return Long.parseLong(calculateBonusFromKPI(fullTimeEmployee, groupList, workList)) +
                calculateBonusFromVacation(fullTimeEmployee) +
                fullTimeEmployee.getWage();
    }


    /**
     * Calculate the bonus salary from the vacation of a full-time employee.
     *
     * @param fullTimeEmployee the full-time employee whose bonus salary is going to be calculated.
     * @return An int that represent the bonus salary from the vacation of this full-time employee.
     */
    public String calculateBonusFromVacation(FullTimeEmployee fullTimeEmployee){
        if (Long.parseLong(fullTimeEmployee.getVacationUsed()) > Long.parseLong(fullTimeEmployee.getTotalVacationWithSalary())){
                return String.valueOf(-(Long.parseLong(fullTimeEmployee.getVacationUsed()) -
                        Long.parseLong(fullTimeEmployee.getTotalVacationWithSalary())) *
                        (-50L * fullTimeEmployee.getLevel() + 550));
        }else{
            return String.valueOf(((Long.parseLong(fullTimeEmployee.getTotalVacationWithSalary()) -
                    Long.parseLong(fullTimeEmployee.getVacationUsed()))) *
                    (Long.parseLong(fullTimeEmployee.getWage()) / 30));
        }
    }


    /**
     * Calculate the bonus salary from the KPI of a full-time employee.
     *
     * @param fullTimeEmployee the full-time employee whose bonus salary is going to be calculated.
     * @return An int that represent the bonus salary from the KPI of this full-time employee.
     */
    public String calculateBonusFromKPI(FullTimeEmployee fullTimeEmployee, IGroupList groupList, IWorkList workList){
        return this.KPIcalculator.calculateKPI(fullTimeEmployee.getID(), groupList, workList);
    }
}
