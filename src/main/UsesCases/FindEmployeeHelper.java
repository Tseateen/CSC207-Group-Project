package main.UsesCases;

import main.Entity.Employee;

public interface FindEmployeeHelper {
    public abstract Employee findEmployeeHelper(IEmployeeList employeeList);
}
