package main.UsesCases;
import main.Entity.*;

import java.util.HashMap;

public class PartTimeInfoManager {

    // === Instance Variables ===

    private final Userable user;
    private final PartTimeEmployee partTimeEmployee;

    /* === Representation Invariants ===
     * The ID of the Employee and User is unique.
     *
     */

    public PartTimeInfoManager(Userable user, PartTimeEmployee partTimeEmployee){
        this.user = user;
        this.partTimeEmployee = partTimeEmployee;
    }


    /**
     * This method will get the name from the user.
     * @return the name of the user.
     *
     */
    public String getNameFromUser(){
        return this.user.getName();
    }


    /**
     * This method will get the ID from the user.
     * @return the ID of the user.
     *
     */
    public String getIDFromUser(){
        return this.user.getID();
    }


    /**
     * This method will get the Username from the user.
     * @return the Username of the user.
     *
     */
    public String getUsernameFromUser(){
        return this.user.getUsername();
    }


    /**
     * This method will get the password from the user.
     * @return the password of the user.
     *
     */
    public String getPasswordFromUser(){
        return this.user.getPassword();
    }


    /**
     * This method will get the phone numbers from the user.
     * @return the phone of the user.
     *
     */
    public String getPhoneFromUser(){
        return this.user.getPhone();
    }


    /**
     * This method will get the address from the user.
     * @return the address of the user.
     *
     */
    public String getAddressFromUser(){
        return this.user.getAddress();
    }


    /**
     * This method will get the ID from the Employee.
     * @return the ID of the Employee.
     *
     */
    public String getIDFromEmployee(){
        return this.partTimeEmployee.getID();
    }


    /**
     * This method will get the department from the part-time Employee.
     * @return the department of the part-time Employee.
     *
     */
    public String getDepartmentFromEmployee(){
        return this.partTimeEmployee.getDepartment();
    }


    /**
     * This method will get the wage from the part-time Employee.
     * @return the wage of the part-time Employee.
     *
     */
    public int getWageFromEmployee(){
        return this.partTimeEmployee.getWage();
    }


    /**
     * This method will get the attendance from the part-time Employee.
     * @return the attendance of the part-time Employee.
     *
     */
    public int getAttendenceFromEmployee(){
        return this.partTimeEmployee.getAttendance();
    }


    /**
     * This method will get the level from the part-time Employee.
     * @return the level of the part-time Employee.
     *
     */
    public int getLevelFromEmployee(){
        return this.partTimeEmployee.getLevel();
    }


    /**
     * This method will get the Schedule from the part-time Employee.
     * @return the Schedule of the part-time Employee.
     *
     */
    public HashMap<String, String[]> getScheduleFromEmployee(){
        return this.partTimeEmployee.getSchedule();
    }


    /**
     * This method will set the name for the user.
     *
     */
    public void setNameForUser(String name){
        this.user.setName(name);
    }


    /**
     * This method will set the password for the user.
     *
     */
    public void setPasswordForUser(String password){
        this.user.setPassword(password);
    }


    /**
     * This method will set the phone for the user.
     *
     */
    public void setPhoneForUser(String phone){
        this.user.setPhone(phone);
    }


    /**
     * This method will set the address for the user.
     *
     */
    public void setAddressForUser(String address){
        this.user.setAddress(address);
    }

    /**
     * This method will set the attendance for the Employee.
     *
     */
    public void setAttendenceForEmployee(){
        this.partTimeEmployee.setAttendance();
    }

    /**
     * This method will set the wage for the Employee.
     *
     */
    public void setWageForEmployee(int wage){
        this.partTimeEmployee.setWage(wage);
    }

    /**
     * This method will set the department for the Employee.
     *
     */
    public void setDepartmentForEmployee(String department){
        this.partTimeEmployee.setDepartment(department);
    }

    /**
     * This method will set the level for the Employee.
     *
     */
    public void setLevelForEmployee(int level){
        this.partTimeEmployee.setLevel(level);
    }

    /**
     * This method will set the schedule for the Employee.
     *
     */
    public void setScheduleForEmployee(HashMap<String, String[]> schedule){
        this.partTimeEmployee.setSchedule(schedule);
    }
}
