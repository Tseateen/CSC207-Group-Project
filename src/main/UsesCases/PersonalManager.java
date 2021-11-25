package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonalManager implements FindDataHelper, IPersonalManager {
    private final String username;
    private final ILoginList loginList;
    private final IEmployeeList employeeList;

    /**
     * Construct the PersonalManager, managing the information from other UsesCases.
     */
    public PersonalManager(ILoginList loginList, IEmployeeList employeeList, String username) {
        this.username = username;
        this.loginList = loginList;
        this.employeeList = employeeList;

    }


    //====== Methods regarding retrieve personal information =======
    /**
     * Get the basic information of an employee such as name, ID, username, password, phone number, address,
     * and department.
     *
     * @return The ArrayList that contains the basic information of an employee.
     */
    @Override
    public ArrayList<String> employeeInfo() {
        ArrayList<String> info = new ArrayList<>();
        Userable user = this.findUserHelper();
        String employeeType = this.findEmployeeTypeHelper(user.getID());
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
            info.add(String.valueOf(fullTimeEmployee.getTotalVacationWithSalary()));
            info.add(String.valueOf(fullTimeEmployee.getVacationUsed()));
        }
        return info;
    }

    /**
     * Getter method for the schedule of a part-time employee.
     *
     * @return A HashMap that contains the schedule of a part-time employee.
     */
    @Override
    public HashMap<String, String[]> getSchedulefromPartTimeEmployee() {
        PartTimeEmployee employee = (PartTimeEmployee) this.findEmployeeHelper();
        return employee.getSchedule();
    }

    //==== retrieve employee's wage information ===
    /**
     * Check the salary of an employee.
     *
     * @return An int that represent the salary/wage of an employee.
     */
    @Override
    public int checkSalary() {
        if (this.findEmployeeTypeHelper(this.findUserHelper().getID()).equals("PartTimeEmployee")) {
            PartTimeEmployee employee = (PartTimeEmployee) this.findEmployeeHelper();
            return employee.getWage();
        } else {
            FullTimeEmployee employee = (FullTimeEmployee) this.findEmployeeHelper();
            return employee.getWage();
        }
    }

    // ==== retrieve fill time employee's vacation information =====
    @Override
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

    // ==== retrieve user's level ====
    @Override
    public String getUserLevel(String userID) {
        return String.valueOf(this.employeeList.getEmployee(userID).getLevel());
    }


    // ==== setter methods for changing user/employee's information ====
    @Override
    public void setName(String response){
        this.findUserHelper().setName(response);
    }

    @Override
    public void setPassword(String response){
        this.findUserHelper().setPassword(response);
    }

    @Override
    public void setPhone(String response){
        this.findUserHelper().setPhone(response);
    }

    @Override
    public void setAddress(String response){
        this.findUserHelper().setAddress(response);
    }

    @Override
    public void setDepartment(String userID, String department){
        Employee employee = this.employeeList.getEmployee(userID);
        employee.setDepartment(department);
    }

    @Override
    public void setLevel(String userID, String level){
        Employee employee = this.employeeList.getEmployee(userID);
        employee.setLevel(Integer.parseInt(level));
    }

    @Override
    public void setWage(String userID, String wage){
        Employee employee = this.employeeList.getEmployee(userID);
        employee.setWage(Integer.parseInt(wage));
    }

    @Override
    public boolean setPosition(String userID, String position){
        try{
            FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) this.employeeList.getEmployee(userID);
            fullTimeEmployee.setPosition(position);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean setEmployeeState(String userID, String state){
        try{
            FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) this.employeeList.getEmployee(userID);
            fullTimeEmployee.setState(state);
            return true;
        } catch (Exception e){
            return false;
        }
    }




    // ==== helper functions ====
    /**
     * A helper function that find the correct user based on the username.
     *
     * @return a Userable that represent the target user.
     */
    @Override
    public Userable findUserHelper() {
        Userable correctUser = new User();
        for (Userable user : (LoginList) this.loginList) {
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
        for (Employee employee : (EmployeeList) this.employeeList) {
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
    public String findEmployeeTypeHelper(String userID){
        String employeeType = "";
        for (Employee employee: (EmployeeList) this.employeeList){
            if (employee.getID().equals(userID)){
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
