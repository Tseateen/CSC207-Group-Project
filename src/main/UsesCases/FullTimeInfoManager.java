package main.UsesCases;

import main.Entity.*;

public class FullTimeInfoManager{

    // === Instance Variables ===

    private final Userable user;
    private final FullTimeEmployee fullTimeEmployee;

    /* === Representation Invariants ===
     * The ID of the Employee and User is unique.
     * The higher level of employees can change the information of the lower level employees.
     *
     */

    public FullTimeInfoManager(Userable user, FullTimeEmployee fullTimeEmployee){
        this.user= user;
        this.fullTimeEmployee = fullTimeEmployee;
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
     * This method will get the Password from the user.
     * @return the Password of the user.
     *
     */
    public String getPasswordFromUser(){
        return this.user.getPassword();
    }


    /**
     * This method will get the phone numbers from the user.
     * @return the phone numbers of the user.
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
        return this.fullTimeEmployee.getID();
    }


    /**
     * This method will get the department from the Employee.
     * @return the department of the Employee.
     *
     */
    public String getDepartmentFromEmployee(){
        return this.fullTimeEmployee.getDepartment();
    }


    /**
     * This method will get the wage from the Employee.
     * @return the wage of the Employee.
     *
     */
    public int getWageFromEmployee(){
        return this.fullTimeEmployee.getWage();
    }


    /**
     * This method will get the attendance information from the Employee.
     * @return the attendance information of the Employee.
     *
     */
    public int getAttendanceFromEmployee(){
        return this.fullTimeEmployee.getAttendance();
    }


    /**
     * This method will get the level from the Employee.
     * @return the level of the Employee.
     *
     */
    public int getLevelFromEmployee(){
        return this.fullTimeEmployee.getLevel();
    }


    /**
     * This method will get the total vocation with salary from the full-time Employee.
     * @return the total vocation with salary of the full-time Employee.
     *
     */
    public int getTotalVacationWithSalary(){
        return this.fullTimeEmployee.getTotalVacationWithSalary();
    }


    /**
     * This method will get the vocation used from the full-time Employee.
     * @return the vocation used of the full-time Employee.
     *
     */
    public int getVacationUsed(){
        return this.fullTimeEmployee.getVacationUsed();
    }


    /**
     * This method will get the position from the full-time Employee, like "manager" or none.
     * @return the position of the full-time Employee.
     *
     */
    public String getPosition(){
        return this.fullTimeEmployee.getPosition();
    }


    /**
     * This method will get the status from the full-time Employee.
     * @return the status of the full-time Employee.
     *
     */
    public String getStatus(){
        return this.fullTimeEmployee.getState();
    }


    /**
     * This method will set the official name for the user.
     *
     */
    public void setNameForUser(String name){
        this.user.setName(name);
    }


    /**
     * This method will set the Username for the user.
     *
     */
    public void setUsernameForUser(String username){
        this.user.setUsername(username);
    }


    /**
     * This method will set the password for the user.
     *
     */
    public void setPasswordForUser(String password){
        this.user.setPassword(password);
    }


    /**
     * This method will set the phone numbers for the user.
     *
     */
    public void setPhoneForUser(String phone){
        this.user.setPhone(phone);
    }


    /**
     * This method will set the address for the User.
     *
     */
    public void setAddressForUser(String address){
        this.user.setAddress(address);
    }


    /**
     * This method will set the attendance for the full-time Employee.
     *
     */
    public void setAttendanceForEmployee(){
        this.fullTimeEmployee.setAttendance();
    }


    /**
     * This method will set the wage for the full-time Employee.
     *
     */
    public void setWageForEmployee(int wage){
        this.fullTimeEmployee.setWage(wage);
    }


    /**
     * This method will set the department for the full-time Employee.
     *
     */
    public void setDepartmentForEmployee(String department){
        this.fullTimeEmployee.setDepartment(department);
    }


    /**
     * This method will set the level for the full-time Employee.
     *
     */
    public void setLevelForEmployee(int level){
        this.fullTimeEmployee.setLevel(level);
    }


    /**
     * This method will set the position for the full-time Employee.
     *
     */
    public void setPositionForEmployee(String position){
        this.fullTimeEmployee.setPosition(position);
    }


    /**
     * This method will set the state for the full-time Employee.
     *
     */
    public void setStateForEmployee(String status){
        this.fullTimeEmployee.setState(status);
    }


    /**
     * This method will set the total vocation with salary for the full-time Employee.
     *
     */
    public void setTotalVacationWithSalaryForEmployee(int totalVacation){
        this.fullTimeEmployee.setTotalVacationWithSalary(totalVacation);
    }


    /**
     * This method will set the vocation used for the full-time Employee.
     *
     */
    public void setVacationUsedForEmployee(int VacationUsed){
        this.fullTimeEmployee.setVacationUsed(VacationUsed);
    }
}
