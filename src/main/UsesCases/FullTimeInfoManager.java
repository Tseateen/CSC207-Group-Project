package main.UsesCases;

import main.Entity.*;

import java.util.HashMap;

public class FullTimeInfoManager implements AccountInfoInterface {

    private Userable user;
    private FullTimeEmployee fullTimeEmployee;

    public FullTimeInfoManager(Userable user, FullTimeEmployee fullTimeEmployee){
        this.user= user;
        this.fullTimeEmployee = fullTimeEmployee;
    }

    public String getNameFromUser(){
        return this.user.getName();
    }
    public String getIDFromUser(){
        return this.user.getID();
    }
    public String getUsernameFromUser(){
        return this.user.getUsername();
    }
    public String getPasswordFromUser(){
        return this.user.getPassword();
    }
    public String getPhoneFromUser(){
        return this.user.getPhone();
    }
    public String getAddressFromUser(){
        return this.user.getAddress();
    }
    public String getIDFromEmployee(){
        return this.fullTimeEmployee.getId();
    }
    public String getDepartmentFromEmployee(){
        return this.fullTimeEmployee.getDepartment();
    }
    public int getWageFromEmployee(){
        return this.fullTimeEmployee.getWage();
    }
    public int getAttendenceFromEmployee(){
        return this.fullTimeEmployee.getAttendance();
    }
    public int getLevelFromEmployee(){
        return this.fullTimeEmployee.getLevel();
    }
    public int getTotalVacationWithSalary(){
        return this.fullTimeEmployee.getTotalVacationWithSalary();
    }
    public int getVacationUsed(){
        return this.fullTimeEmployee.getVacationUsed();
    }
    public String getPosition(){
        return this.fullTimeEmployee.getPosition();
    }
    public String getStatus(){
        return this.fullTimeEmployee.getState();
    }

    public void setNameForUser(String name){
        this.user.setName(name);
    }

    public void setUsernameForUser(String username){
        this.user.setUsername(username);
    }

    public void setPasswordForUser(String password){
        this.user.setPassword(password);
    }

    public void setPhoneForUser(String phone){
        this.user.setPhone(phone);
    }


    public void setAddressForUser(String address){
        this.user.setAddress(address);
    }

    public void setAttendenceForEmployee(){
        this.fullTimeEmployee.setAttendance();
    }
    public void setWageForEmployee(int wage){
        this.fullTimeEmployee.setWage(wage);
    }
    public void setDepartmentForEmployee(String department){
        this.fullTimeEmployee.setDepartment(department);
    }
    public void setLevelForEmployee(int level){
        this.fullTimeEmployee.setLevel(level);
    }
    public void setPositionForEmployee(String position){
        this.fullTimeEmployee.setPosition(position);
    }
    public void setStateForEmployee(String status){
        this.fullTimeEmployee.setState(status);
    }
    public void setTotalVacationWithSalaryForEmployee(int totalVacation){
        this.fullTimeEmployee.setTotalVacationWithSalary(totalVacation);
    }
    public void setVacationUsedForEmployee(int VacationUsed){
        this.fullTimeEmployee.setVacationUsed(VacationUsed);
    }
}
