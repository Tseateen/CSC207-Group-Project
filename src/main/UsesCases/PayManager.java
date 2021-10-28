package main.UsesCases;

import main.Entity.Employee;
import main.Entity.Userable;

import java.util.HashMap;
import java.util.Map;

public class PayManager {

    private Map<Userable, Employee> employeeMap;

    public PayManager() {
        this.employeeMap = new HashMap<Userable, Employee>();
    }

    /**
     * calculate monthly minimum wage for all employees (consider exceeded totalVacationWithSalary)
     */
    private Map<Employee, Integer> calculateWage(){

    }

    public void payWage(){

    }

    /**
     * calculate monthly Work Bonus
     */
    public void calculateBonusFromWork(){

    }

    private int payBonusFromWork(){

    }

    /**
     * calculate yearly Bonus (consider KPI & totalVacationWithSalary not being used)
     */
    public void calculateAnnaulBonus(){

    }

    private int payAnnaulBonus(){

    }

}
