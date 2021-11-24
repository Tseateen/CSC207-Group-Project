package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonalManager implements FindDataHelper {
    private final String username;
    private final LoginList loginList;
    private final EmployeeList employeeList;

    /**
     * Construct the PersonalManager, managing the information from other UsesCases.
     */
    public PersonalManager(LoginList loginList, EmployeeList employeeList, String username) {
        this.username = username;
        this.loginList = loginList;
        this.employeeList = employeeList;

    }

    /**
     * Get the basic information of an employee such as name, ID, username, password, phone number, address,
     * and department.
     *
     * @return The ArrayList that contains the basic information of an employee.
     */
    public ArrayList<String> employeeInfo() {
        ArrayList<String> info = new ArrayList<>();
        Userable user = this.findUserHelper();
        String employeeType = this.findEmployeeTypeHelper();
        info.add(user.getName());
        info.add(user.getID());
        info.add(user.getUsername());
        info.add(user.getPassword());
        info.add(user.getPhone());
        info.add(user.getAddress());
        if (employeeType.equals("PartTimeEmployee")) {
            PartTimeEmployee partTimeEmployee = (PartTimeEmployee) this.findEmployeeHelper();
            info.add(partTimeEmployee.getDepartment());
        } else {
            FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) this.findEmployeeHelper();
            info.add(fullTimeEmployee.getDepartment());
            info.add(fullTimeEmployee.getPosition());
            info.add(fullTimeEmployee.getState());
        }
        return info;
    }

    /**
     * Get the integer information of a full-time employee such as total vacation with salary and vacation used.
     *
     * @return An int array that contains the integer information of a full-time employee.
     */
    public int[] getFullTimeEmployeeInfoInt() {
        FullTimeEmployee employee = (FullTimeEmployee) findEmployeeHelper();
        int[] intValue = new int[2];
        intValue[0] = employee.getTotalVacationWithSalary();
        intValue[1] = employee.getVacationUsed();
        return intValue;
    }

    /**
     * Getter method for the schedule of a part-time employee.
     *
     * @return A HashMap that contains the schedule of a part-time employee.
     */
    public HashMap<String, String[]> getSchedulefromPartTimeEmployee() {
        PartTimeEmployee employee = (PartTimeEmployee) this.findEmployeeHelper();
        return employee.getSchedule();
    }


    /**
     * Check the salary of an employee.
     *
     * @return An int that represent the salary/wage of an employee.
     */
    public int checkSalary() {
        if (this.findEmployeeTypeHelper().equals("PartTimeEmployee")) {
            PartTimeEmployee employee = (PartTimeEmployee) this.findEmployeeHelper();
            return employee.getWage();
        } else {
            FullTimeEmployee employee = (FullTimeEmployee) this.findEmployeeHelper();
            return employee.getWage();
        }
    }

    public ArrayList<String> vacationInfo() {
        ArrayList<String> vacationList = new ArrayList<>();
        Employee employee = this.findEmployeeHelper();
        if (employee instanceof PartTimeEmployee) {
            return vacationList;
        }
        vacationList.add(String.valueOf(((FullTimeEmployee)employee).getVacationUsed()));
        vacationList.add(String.valueOf(((FullTimeEmployee)employee).getTotalVacationWithSalary()));
        return vacationList;
    }

    public String getuserLevel(String userID) {
        return String.valueOf(this.employeeList.getEmployee(userID).getLevel());
    }

    public void setName(String response){
        this.findUserHelper().setName(response);
    }

    public void setPassword(String response){
        this.findUserHelper().setPassword(response);
    }

    public void setPhone(String response){
        this.findUserHelper().setPhone(response);
    }

    public void setAddress(String response){
        this.findUserHelper().setAddress(response);
    }

    public void setDepartment(String userID, String department){
        Employee employee = this.employeeList.getEmployee(userID);
        employee.setDepartment(department);
    }

    public void setLevel(String userID, String level){
        Employee employee = this.employeeList.getEmployee(userID);
        employee.setLevel(Integer.parseInt(level));
    }

    public void setWage(String userID, String wage){
        Employee employee = this.employeeList.getEmployee(userID);
        employee.setWage(Integer.parseInt(wage));
    }





    /**
     * A helper function that find the correct user based on the username.
     *
     * @return a Userable that represent the target user.
     */
    @Override
    public Userable findUserHelper() {
        Userable correctUser = new User();
        for (Userable user : this.loginList) {
            if (user.getUsername().equals(this.username)) {
                return user;
            }
        }
        return correctUser;
    }


    /**
     * A helper function that find the correct employee based on the user by comparing the ID.
     *
     * @return an Employee that represent the target employee.
     */
    @Override
    public Employee findEmployeeHelper() {
        Userable user = findUserHelper();
        for (Employee employee : this.employeeList) {
            if (employee.getID().equals(user.getID())) {
                return employee;
            }
        }
        return null;
    }


    /**
     * check if the employee is a part-time employee or full time employee.
     *
     * @return The String that represent either a part-time employee or a full time employee.
     */
    @Override
    public String findEmployeeTypeHelper(){
        String employeeType = "";
        Userable user = findUserHelper();
        for (Employee employee: this.employeeList){
            if (employee.getID().equals(user.getID())){
                if(employee instanceof PartTimeEmployee){
                    employeeType = "PartTimeEmployee";
                }else{
                    employeeType = "FullTimeEmployee";
                }
            }
        }
        return employeeType;
    }


}
