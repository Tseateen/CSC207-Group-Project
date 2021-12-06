package main.InterfaceAdapter;

import main.UsesCases.IEmployeeList;

public class EmployeeListController {

    // === Instance Variables ===
    private final IEmployeeList employeeList;


    /**
     * Construct the EmployeeListController.
     */
    public  EmployeeListController(IEmployeeList employeeList){
        this.employeeList = employeeList;
    }


    /**
     * Add Employee to the list.
     *
     * @param department the department of the Employee.
     * @param wage the wage of the Employee.
     * @param position the position of the Employee.
     * @param level the authority level of the Employee.
     * @param status the status of the Employee.
     * @param name the name of the Employee.
     *
     */
    public void addEmployee(String department, String wage, String position,  String level, String status, String name){
        this.employeeList.addEmployee(department, wage, position, level, status, name);
    }


    //=== Usage: FacadeSys Worker Case (vi) ====

    /**
     * Delete Employee from the list.
     *
     * @param ID the ID of the targeted Employee.
     *
     */
    public void deleteEmployee(String ID){
        this.employeeList.deleteEmployee(ID);
    }
    // ==================================
}
