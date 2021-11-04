package main.UsesCases;

import main.Entity.*;
public class AccountInfoManager {

    private Userable user;
    private FullTimeEmployee fullTimeEmployee;

    public AccountInfoManager(Userable user, FullTimeEmployee fullTimeEmployee){
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
}
