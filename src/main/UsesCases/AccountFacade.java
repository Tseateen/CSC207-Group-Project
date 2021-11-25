package main.UsesCases;

import main.Entity.*;
import java.util.*;

public class AccountFacade {

    // === Instance Variables ===
    private final String username;
    private final LoginList loginList;
    private final EmployeeList employeeList;


    /**
     * Construct the AccountFacade, managing the information from other UsesCases.
     */
    public AccountFacade(LoginList loginList, EmployeeList employeeList, String username) {
        this.username = username;
        this.loginList = loginList;
        this.employeeList = employeeList;

    }


    /**
     * Getter method for the user using findUserHelper method and the ID of the user.
     *
     * @return the user found.
     */
    public String getUserID() {
        return this.findUserHelper().getID();
    }


    /**
     * Getter method for the type of the employee.
     *
     * @return a String that contains the type of the employee.
     */
    public String getEmployeeType() {
        return this.findEmployeeTypeHelper();
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
     * Getter method for the working hours of a part-time employee.
     *
     * @return A int that represents the working hours of a part-time employee.
     */
    public int getWorkingHourFromPartTimeEmployee() {
        PartTimeEmployee employee = (PartTimeEmployee) this.findEmployeeHelper();
        return employee.getWorkingHour();
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
     * Set the basic information of a part time employee includes name, password, phone number, address, and attendance.
     *
     * @param option   The option that the client want to set.
     * @param response The value that the client want to set.
     */
    public void setPartTimeBasicInfo(String option, String response) {
        PartTimeEmployee employee = (PartTimeEmployee) this.findEmployeeHelper();
        switch (option) {
            case "1":
                this.findUserHelper().setName(response);
                break;
            case "2":
                this.findUserHelper().setPassword(response);
                break;
            case "3":
                this.findUserHelper().setPhone(response);
                break;
            case "4":
                this.findUserHelper().setAddress(response);
                break;
            case "5":
                employee.setAttendance();
                break;
        }
    }


    /**
     * Set the advanced information of a part-time employee includes department, wage, and authority level.
     *
     * @param option   The option that the client want to set.
     * @param response The value that the client want to set.
     */
    public void setPartTimeAdvancedInfo(String option, String response) {
        PartTimeEmployee employee = (PartTimeEmployee) this.findEmployeeHelper();
        switch (option) {
            case "1":
                employee.setDepartment(response);
            case "2":
                int intWage;
                try {
                    intWage = Integer.parseInt(response);
                } catch (NumberFormatException e) {
                    intWage = 0;
                }
                employee.setWage(intWage);
            case "3":
                int intLevel;
                try {
                    intLevel = Integer.parseInt(response);
                } catch (NumberFormatException e) {
                    intLevel = 0;
                }
                employee.setLevel(intLevel);
        }
    }


    /**
     * Add the hours that a part-time employee worked to their total working hours.
     *
     * @param hoursWorked The hours that a part-time employee worked.
     */
    public void addWorkingHours(int hoursWorked) {
        PartTimeEmployee employee = (PartTimeEmployee) this.findEmployeeHelper();
        employee.addWorkingHour(hoursWorked);;
    }


    /**
     * Set the basic information of a full-time employee includes name, password, phone number, address, and attendance.
     *
     * @param option   The option that the client want to set.
     * @param response The value that the client want to set.
     */
    public void setFullTimeBasicInfo(String option, String response) {
        Userable user = findUserHelper();
        FullTimeEmployee employee = (FullTimeEmployee) this.findEmployeeHelper();
        switch (option) {
            case "1":
                user.setName(response);
                break;
            case "2":
                user.setPassword(response);
                break;
            case "3":
                user.setPhone(response);
                break;
            case "4":
                user.setAddress(response);
                break;
            case "5":
                employee.setAttendance();
                break;

        }
    }


    /**
     * Set the advanced information of a full time employee includes department, wage, authority  level, position,
     * total vacation, and vacation used.
     *
     * @param option   The option that the client want to set.
     * @param response The value that the client want to set.
     */
    public void setFullTimeAdvancedInfo(String option, String response) {
        FullTimeEmployee employee = (FullTimeEmployee) this.findEmployeeHelper();
        switch (option) {
            case "1":
                employee.setDepartment(response);
            case "2":
                int intWage;
                try {
                    intWage = Integer.parseInt(response);
                } catch (NumberFormatException e) {
                    intWage = 0;
                }
                employee.setWage(intWage);
            case "3":
                int intLevel;
                try {
                    intLevel = Integer.parseInt(response);
                } catch (NumberFormatException e) {
                    intLevel = 0;
                }
                employee.setLevel(intLevel);
            case "4":
                employee.setPosition(response);
            case "5":
                employee.setState(response);
            case "6":
                int intTotalVacation;
                try {
                    intTotalVacation = Integer.parseInt(response);
                } catch (NumberFormatException e) {
                    intTotalVacation = 0;
                }
                employee.setTotalVacationWithSalary(intTotalVacation);
            case "7":
                int intVacationUsed;
                try {
                    intVacationUsed = Integer.parseInt(response);
                } catch (NumberFormatException e) {
                    intVacationUsed = 0;
                }
                employee.setVacationUsed(intVacationUsed);
        }
    }


    /**
     * Get the authority level of an employee.
     *
     * @return An int represent the authority level of an employee.
     */
    public String user_Level(String user_id) {
        Employee e = this.employeeList.getEmployee(user_id);
        return String.valueOf(e.getLevel());
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


    /**
     * A helper function that find the correct user based on the username.
     *
     * @return a Userable that represent the target user.
     */
    private Userable findUserHelper() {
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


    /**
     * check the employee type by his ID.
     *
     * @param id the target id.
     * @return The String that represent either part time employee or full time employee.
     */
    public String employeeTypeByID(String id) {
        String typeEmployee = "";
        for (Employee employee : this.employeeList) {
            if (employee.getID().equals(id)) {
                if (employee instanceof PartTimeEmployee) {
                    typeEmployee = "PartTimeEmployee";
                } else {
                    typeEmployee = "FullTimeEmployee";
                }
            }
        }
        return typeEmployee;
    }


    // ================ WorkAreaRequiredFunction =====================

    /**
     * Verify if the authority level of an employee is lower than the specific authority level.
     *
     * @param level The specific authority level that the client want to check.
     * @return boolean represent if the specific authority level is higher than the employee's authority level.
     */
    public boolean ValidToCreateThisLevel(String level) {
        if (level.length() != 1) {
            return false;
        }
        int LevelWantToCreate = Integer.parseInt(level);
        return LevelWantToCreate > Integer.parseInt(this.user_Level(this.getUserID()));
    }


    // Case 6: FacadeSys.CreateEmployeeMethod
    /**
     * Create a new account for an employee include the user information and employee information.
     *
     * @param userinfo The String array that contains the user information and employee information.
     */
    public void CreateNewAccount(String[] userinfo) {
        try {
            this.loginList.addUser(userinfo[0], userinfo[1], userinfo[2], userinfo[3], userinfo[4]);
            this.employeeList.addEmployee(userinfo[5], Integer.parseInt(userinfo[6]), userinfo[7], Integer.parseInt(userinfo[8]), userinfo[9]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to create account due to uncompleted information");
        }
    }
    // ==================================================

    // Case 7: FacadeSys.DeleteEmployeeMethod

    /**
     * Check if there is a user already existed by the userid.
     *
     * @param userid the ID of the targeted user.
     * @return true if the user already existed.
     */
    public boolean userExists(String userid) {
        for (Employee employee : this.employeeList) {
            if (employee.getID().equals(userid)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Get the user from the EmployeeList by his ID.
     *
     * @param userid the ID of the targeted employee.
     * @return the employee's level in strings.
     */
    public String getLevel(String userid) {
        for (Employee employee : this.employeeList){
            if (employee.getID().equals(userid)){
                return String.valueOf(employee.getLevel());
            }
        }
        return null;
    }


    /**
     * Delete the user from the EmployeeList by his ID.
     *
     * @param userid the ID of the targeted employee.
     *
     */
    public void DeleteAccount(String userid) {
        this.loginList.deleteUser(userid);
        this.employeeList.deleteEmployee(userid);
    }
    // ==================================================

    // Case 8: FacadeSys.LowerEmployeeCheck

    /**
     * Check the employees who have the lower level from the EmployeeList by his ID.
     *
     * @param id the ID of the targeted employee.
     * @param Option the different requests for the information.
     *
     * @return a list of strings that has the lower level employees than the target employees.
     */
    public List<String> lowerEmployeeCheck(String id, String Option) {
        List<Employee> valid_employees;
        valid_employees = lowerLevelEmployee(id);
        List<String> employeeCheckList = new ArrayList<>();
        for (Employee employee : valid_employees) {
            employeeCheckList.add(employee.getID());
            switch (Option) {
                case "1":
                    employeeCheckList.add(Objects.toString(employee.getWage()));
                    break;
                case "2":
                    employeeCheckList.add(Objects.toString(employee.getAttendance()));
                    break;
                case "3":
                    employeeCheckList.add(Objects.toString(getVacationTotalAndUsed(id).get(1)));
                    break;
                case "4":
                    employeeCheckList.add(Objects.toString(getVacationTotalAndUsed(id).get(0)));
                    break;
            }

        }
        return employeeCheckList;
    }


    /**
     * Check a list of employees that has a lower level than the target employee.
     *
     * @param id the ID of the targeted employee.
     * @return a list of employee who is lower level than the employee with given id.
     *
     */
    public List<Employee> lowerLevelEmployee(String id) {

        List<Employee> validemployees = new ArrayList<>();
        for (Employee employee : this.employeeList) {
            if (ValidToCreateThisLevel(String.valueOf(employee.getLevel()))) {
                validemployees.add(employee);
            }
        }
        return validemployees;
    }


    /**
     * Get a list of employees with the information of the total and used vocations
     * that has a lower level than the target employee.
     *
     * @param id the string of ID of the target employee.
     *
     * @return a list of employee who is lower level than the employee with given id.
     *
     */
    public ArrayList<String> getVacationTotalAndUsed(String id){
        ArrayList<String> vac = new ArrayList<String>();
        Employee e = this.employeeList.getEmployee(id);
        if (e instanceof PartTimeEmployee) {
            return vac;
        }
        vac.add(String.valueOf(((FullTimeEmployee)e).getVacationUsed()));
        vac.add(String.valueOf(((FullTimeEmployee)e).getTotalVacationWithSalary()));
        return vac;
    }
    // ==================================================


    /**
     * Get a list of employees that has a lower level than the target employee.
     *
     * @param level the string of level of the targeted employee.
     *
     * @return a list of employee who is lower level from the EmployeeList than the targeted employee with given id.
     *
     */
    public ArrayList<String> getLowerUsers(String level) {
        ArrayList<String> users = new ArrayList<String>();
        for (Employee e: this.employeeList) {
            if (e.getLevel() > Integer.parseInt(level)) {
                users.add(this.loginList.getUser(e.getID()).getName() + " " + e.getID()
                        + " " + e.getLevel() + " " + e.getDepartment() + "\n");
            }
        }
        return users;
    }
}