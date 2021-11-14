package main.UsesCases;

import main.Entity.*;
import java.util.*;

public class AccountFacade {

    private final String username;
    private final Userable user;
    private final Employee employee;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final PayManager managerPay;
    private final Verifier managerVerifier;
    private static int idCounter = 1;
    private final String employeeType;

    public AccountFacade(LoginList loginList, EmployeeList employeeList, String username) {
        this.username = username;
        this.loginList = loginList;
        this.employeeList = employeeList;

        this.managerPay = new PayManager();
        this.managerVerifier = new Verifier(this.loginList);
       this.employeeType = "";
        this.user = this.findUserHelper();
        this.employee = findEmployeeHelper();
    }

    /**
     * Getter method for the type of the employee
     *
     * @return a String that contains the type of the employee
     */
    public String getEmployeeType() {
        System.out.println(this.findEmployeeTypeHelper());
        return this.findEmployeeTypeHelper();
    }


    // return all the info at the same time (馬哥的idea)
    public ArrayList<String> partTimeEmployeeInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(this.user.getName());
        info.add(this.user.getID());
        info.add(this.user.getUsername());
        info.add(this.user.getPassword());
        info.add(this.user.getPhone());
        info.add(this.user.getAddress());
        info.add(this.employee.getDepartment());
        return info;
    }

    /**
     * Get the basic infomation of an employee such as name, ID, username, password, phone number, address, and department.
     *
     * @return The ArrayList that contains the basic informaiton of an employee
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
     * Get the schedule of a part time employee
     *
     * @return A HashMap that contains the schedule of a part time employee
     */
    public HashMap<String, String[]> getSchedulefromPartTimeEmployee() {
        PartTimeEmployee employee = (PartTimeEmployee) this.employee;
        return employee.getSchedule();
    }

    /**
     * Get the integer information of a full time employee such as total vacation with salary and vacation used
     *
     * @return An int array that contains the integer information of a full time employee
     */
    public int[] getFullTimeEmployeeInfoInt() {
        FullTimeEmployee employee = (FullTimeEmployee) findEmployeeHelper();
        int[] intValue = new int[2];
        intValue[0] = employee.getTotalVacationWithSalary();
        intValue[1] = employee.getVacationUsed();
        return intValue;
    }

    /**
     * Set the basic information of a part time employee includes name, password, phone number, address, and attendance
     *
     * @param option   The option that the client want to set
     * @param response The value that the client want to set
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
     * Set the advanced information of a part time employee includes department, wage, and authority  level
     *
     * @param option   The option that the client want to set
     * @param response The value that the client want to set
     */
    public void setPartTimeAdvancedInfo(String option, String response) {
        PartTimeEmployee employee = (PartTimeEmployee) this.employee;
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
     * Set teh schedule for a part time employee
     *
     * @param schedule The new/updated schedule for a part time employee
     */
    public void setSchedule(HashMap<String, String[]> schedule) {
        PartTimeEmployee employee = (PartTimeEmployee) this.employee;
        employee.setSchedule(schedule);
    }

    /**
     * Set the basic information of a full time employee includes name, password, phone number, address, and attendance
     *
     * @param option   The option that the client want to set
     * @param response The value that the client want to set
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
     * Set the advanced information of a full time employee includes department, wage, authority  level, position, total vacation, and vacation used
     *
     * @param option   The option that the client want to set
     * @param response The value that the client want to set
     */
    public void setFullTimeAdvancedInfo(String option, String response) {
        FullTimeEmployee employee = (FullTimeEmployee) this.employee;
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
     * Get the authority level of an employee
     *
     * @return An int represent the authority level of an employee
     */
    public int user_Level() {
        Employee employee = this.findEmployeeHelper();
        String employeeType = this.findEmployeeTypeHelper();
        if (employeeType.equals("PartTimeEmployee")) {
            PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employee;
            return partTimeEmployee.getLevel();
        } else {
            FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) employee;
            return fullTimeEmployee.getLevel();
        }
    }

    /**
     * Verify if the authority level of an employee is lower than the specific authority level
     *
     * @param level The specific authority level that the client want to check
     * @return boolean represent if the specific authority level is higher than the employee's authority level
     */
    public boolean ValidToCreateThisLevel(String level) {
        if (level.length() != 1) {
            return false;
        }
        int LevelWantToCreate = Integer.parseInt(level);
        return LevelWantToCreate > this.user_Level();
    }

    /**
     * Create a new account for an employee include the user information and employee information
     *
     * @param userinfo The String array that contains the user information and employee information
     * @return boolean that indicates if the new account is been created
     */
    public boolean CreateNewAccount(String[] userinfo) {
        try {
            String id = String.valueOf(idCounter);
            this.loginList.addUser(userinfo[0], userinfo[1], userinfo[2], userinfo[3], userinfo[4], id);
            this.employeeList.addEmployee(userinfo[5], Integer.parseInt(userinfo[6]), userinfo[7], Integer.parseInt(userinfo[8]), userinfo[9], id);
            idCounter += 1;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to create account due to uncompleted information");
            return false;
        }
        return true;
    }

    public String DeleteAccount(String userid) {
        this.loginList.deleteUser(userid);
        this.employeeList.deleteEmployee(userid);
        return userid;
    }

    /**
     * Check the salary of an employee
     *
     * @return An int that represent the salary/wage of an employee
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
     * A helper function that find the correct user based on the username
     *
     * @return a Userable that represent the target user
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
     * A helper function that find the correct employee based on the user by comparing the ID
     *
     * @return a Employee that represent the target employee
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
     * check if the employee is part time employee or full time employee
     *
     * @return The String that represent either part time employee or full time employee
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
     * check the employee type by ID
     *
     * @param id the target id
     * @return The String that represent either part time employee or full time employee
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

    public List<Employee> lowerLevelEmployee(String id) {
        /**
         * return a list of employee who is lower level than the employee with given id
         */
        List<Employee> validemployees = new ArrayList<>();
        for (Employee employee : this.employeeList) {
            if (ValidToCreateThisLevel(String.valueOf(employee.getLevel()))) {
                validemployees.add(employee);
            }
        }
        return validemployees;
    }

    public boolean userExists(String userid) {
        for (Employee employee : this.employeeList) {
            if (employee.getID().equals(userid)) {
                return true;
            }
        }
        return false;
    }

    public String getLevel(String userid) {
        for (Employee employee : this.employeeList){
            if (employee.getID().equals(userid)){
                return String.valueOf(employee.getLevel());
            }
        }
        return null;
    }

    public int getTotalVacationByID(String id){
        for (Employee employee : this.employeeList) {
            if (employee.getID().equals(id)) {
                if (employee instanceof FullTimeEmployee) {
                    return ((FullTimeEmployee) employee).getTotalVacationWithSalary();
                }
            }
            // TODO: throw new Exception("Part time employee does not have vacation");
        }
        return 0;
    }

    public int getVacationUsedByID(String id){
        for (Employee employee : this.employeeList) {
            if (employee.getID().equals(id)) {
                if (employee instanceof FullTimeEmployee) {
                    return ((FullTimeEmployee) employee).getVacationUsed();
                }
            }
            // TODO: throw new Exception("Part time employee does not have vacation");
        }
        return 0;
    }

    // Case 8: FacadeSys.LowerEmployeeCheck
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
                    employeeCheckList.add(Objects.toString(getTotalVacationByID(id)));
                    break;
                case "4":
                    employeeCheckList.add(Objects.toString(getVacationUsedByID(id)));
                    break;
            }

        }
        return employeeCheckList;
    }
    // ==================================================
}
