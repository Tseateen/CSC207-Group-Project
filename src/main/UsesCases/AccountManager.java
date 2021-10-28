package main.UsesCases;
import main.Entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class AccountManager {

    // === Instance Variables ===

    // The map of user and employee. i.e. key = Userable Object, item = Employee Object
    // TODO: should it be final?
    private static Map<Userable, Employee> employeeMap;
    // total_number is a counter for assigning unique identity.
    private static int total_number = 0;
    // TODO: should we make this as static variable ?
    // totalEmployee can record the current total number of employee.
    private static int totalEmployee = 0;
    // TODO: should we make this as static variable ?
    // totalPart_time can record the current total number of part-time employee.
    private static int totalPart_time = 0;
    // TODO: should we make this as static variable ?
    // totalFull_time can record the current total number of full-time employee.
    private static int totalFull_time = 0;

    /* === Representation Invariants ===
     * the number for both totalPart_time and totalFull_time should always greater or equal to 0, but the sum of both
     * number should always equal to the number of totalEmployee.
     * i.e. 0 <= totalPart_time and 0 <= totalFull_time and totalPart_time + totalFull_time = totalEmployee.
     *
     * the total_number should always greater or equal to totalEmployee. i.e. totalEmployee <= total_number.
     */


    /* Construct the AccountManager.
     * In this constructor, it will give a first admin for initial purpose.
     * TODO: we may use DB and GATEWAY to assign the first admin.
     */
    public AccountManager(){
        employeeMap = new HashMap<Userable, Employee>();
        createEmployee("1", "0", "Admin", "", "", "F",
                "HR", "Admin", 0, 0);
    }

    /**
     * Overload constructor for accounts other than the initial admin account (which is the first constructor).
     *
     */
    public AccountManager(HashMap<Userable, Employee> list) {
        employeeMap = list;
        totalEmployee = employeeMap.size();
        totalFull_time = CountFullTime(employeeMap);
        totalPart_time = totalEmployee - totalFull_time;
        total_number = employeeMap.size();
    }

    // === Regular methods ===

    private int CountFullTime(Map<Userable, Employee> list) {
        int num = 0;
        for (Userable i : list.keySet()) {
            if (i instanceof FullTimeEmployee) { num++;}
        }
        return num;
    }

    /**
     *  Create a new user and put into employee list.
     * @param accountNumber Given account number for this new user.
     * @param password Given password for this new user.
     * @param name Given name for this new user.
     * @param phone Given phone number for this new user.
     * @param address Given address for this new user.
     * @param status Given the user status. i.e "F" represents full-time employee. "P" represents part-time employee.
     * @param department Given the department of this user.
     * @param position Given the position of this user.
     * @param wage Given the minimum wage of this user.
     * @param level Given the authority level of this user.
     * @return Userable object iff creates user successful. Otherwise, return null.
     */

    public Object createEmployee(String accountNumber, String password, String name, String phone, String address, String status,
                               String department, String position, int wage, int level){
        total_number++;
        Userable newUser = new User(accountNumber, password, name, phone, address, String.valueOf(total_number));
        Employee newEmployee;
        if(status.equals("P")) {
            newEmployee = new PartTimeEmployee(department, wage, level);
            totalPart_time++;
            totalEmployee++;
            employeeMap.put(newUser,newEmployee);
            return newUser;
        }
        else if (status.equals("F")) {
            newEmployee = new FullTimeEmployee(department, position, wage, level);
            totalFull_time++;
            totalEmployee++;
            employeeMap.put(newUser,newEmployee);
            return newUser;
        }
        return null;
    }

    /**
     *
     * @param id an unique identity for a Employee.
     * @return Employee object iff delete employee successfully. Otherwise, return null
     */

    public Object deleteEmployee(String id) {
        for (Userable i : employeeMap.keySet()) {
            if (Objects.equals(((User) i).getID(), id)) {
                Employee j = employeeMap.remove(i);
                if (j instanceof PartTimeEmployee) {
                    totalPart_time--;
                } else {
                    totalFull_time--;
                }
                totalEmployee--;
                return i;
            }
        }
        return null;
    }

    /**
     *
     * @return a number that represents the total number of employee.
     */
    public int getTotalEmployee () {return totalEmployee;}

    /**
     *
     * @return a number that represents the total number of Full-Time employee.
     */
    public int getTotalFull_time() {
        return totalFull_time;
    }

    /**
     *
     * @return a number that represents the total number of Part-Time employee.
     */
    public int getTotalPart_time() {
        return totalPart_time;
    }

    /**
     *
     * @return a map that key = Userable object and its items = Employee object.
     */
    public Map<Userable, Employee> getEmployeeMap(){
        return employeeMap;
    }

    /**
     *
     * @return string of representation for future code testing. i.e. This is an account manager.
     */
    @Override
    public String toString(){
        return "This is an account manager.";
    }
}
