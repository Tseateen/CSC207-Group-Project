package main.InterfaceAdapter;

import main.UsesCases.IEmployeeList;

public class EmployeeListController {

    private final IEmployeeList employeeList;

    public  EmployeeListController(IEmployeeList employeeList){
        this.employeeList = employeeList;
    }

    public void addEmployee(String department, int wage, String position,  int level, String status){
        this.employeeList.addEmployee(department, wage, position, level, status);
    }

    public void deleteEmployee(String id){
        this.employeeList.deleteEmployee(id);
    }
}
