package main.UsesCases;
import main.Entity.*;

import java.util.HashMap;

public class PartTimeInfoManager {
    private Userable user;
    private PartTimeEmployee partTimeEmployee;

    public PartTimeInfoManager(Userable user, PartTimeEmployee partTimeEmployee){
        this.user = user;
        this.partTimeEmployee = partTimeEmployee;
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
        return this.partTimeEmployee.getID();
    }
    public String getDepartmentFromEmployee(){
        return this.partTimeEmployee.getDepartment();
    }
    public int getWageFromEmployee(){
        return this.partTimeEmployee.getWage();
    }
    public int getAttendenceFromEmployee(){
        return this.partTimeEmployee.getAttendance();
    }
    public int getLevelFromEmployee(){
        return this.partTimeEmployee.getLevel();
    }
    public HashMap<String, String[]> getScheduleFromEmployee(){
        return this.partTimeEmployee.getSchedule();
    }
    public void setNameForUser(String name){
        this.user.setName(name);
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
        this.partTimeEmployee.setAttendance();
    }
    public void setWageForEmployee(int wage){
        this.partTimeEmployee.setWage(wage);
    }
    public void setDepartmentForEmployee(String department){
        this.partTimeEmployee.setDepartment(department);
    }
    public void setLevelForEmployee(int level){
        this.partTimeEmployee.setLevel(level);
    }
    public void setScheduleForEmployee(HashMap<String, String[]> schedule){
        this.partTimeEmployee.setSchedule(schedule);
    }
}
