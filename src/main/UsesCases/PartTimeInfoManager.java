package main.UsesCases;
import main.Entity.*;
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
        return this.partTimeEmployee.getId();
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

}
