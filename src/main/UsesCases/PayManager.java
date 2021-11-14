package main.UsesCases;

import main.Entity.Employee;

import main.Entity.Work;

import java.util.HashMap;
import java.util.Map;

public class PayManager {

    private Map<String, Employee> employeeMap;


    /**
     * Construct the PayManager.
     */
    public PayManager() {
        // TODO: the employeeMap is read from txt file.
        this.employeeMap = new HashMap<String, Employee>();
    }

    /**
     * Calculate monthly minimum wage for all employees (consider exceeded totalVacationWithSalary)
     * @param employeeMap the HashMap that contains all the employees.
     * @return the HashMap that contains employees and their calculated wage respectively.
     */
    private Map<Employee, Integer> calculateWage(Map<String, Employee> employeeMap ){
        //TODO implement this method
        return null;
    }

    /**
     * Pay the employee by the calculated minimum wage.
     * @param wageMap the Hashmap that contains employees and their calculated minimum wage respectively.
     * @return True iff the minimum wage is paid successfully.
     */
    public boolean payWage(Map<Employee, Integer> wageMap){

        //TODO implement this method
        return true;
    }

    /**
     * calculate monthly Work Bonus for the employee
     * @param employeeMap the HashMap that contains all the employees.
     * @param project the project that the employee had completed
     * @return the Hashmap that contains employees and their calculated work bonus respectively.
     */
    public Map<Employee, Integer> calculateWorkBonus(Map<String, Employee> employeeMap, Work project){
        //TODO implement this method
        return null;
    }

    /**
     * Pay the calculated work bonus to the employee.
     * @param workBonusMap the Hashmap that contains employees and their calculated work bonus respectively.
     * @return True iff the work bonus is paid successfully.
     */
    private boolean payWorkBonus(Map<Employee, Integer> workBonusMap){
        //TODO implement this method
        return true;
    }

    /**
     * calculate the annual bonus for the employee
     * @param employeeMap the HashMap that contains all the employees.
     * @return the Hashmap that contains employees and their calculated annual bonus respectively.
     */
    public Map<Employee, Integer> calculateAnnualBonus(Map<String, Employee> employeeMap){
        //TODO implement this method
        return null;
    }

    /**
     * Pay the calculated annual bonus to the employee.
     * @param annualBonusMap the Hashmap that contains employees and their calculated annual bonus respectively.
     * @return True iff the annual bonus is paid successfully.
     */
    private boolean payAnnualBonus(Map<Employee, Integer> annualBonusMap){
        //TODO implement this method
        return true;
    }

    public Map<String, Employee> getEmployeeMap() {
        return employeeMap;
    }
}
