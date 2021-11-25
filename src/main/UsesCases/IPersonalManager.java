package main.UsesCases;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPersonalManager {


    public abstract ArrayList<String> employeeInfo();
    public abstract HashMap<String, String[]> getSchedulefromPartTimeEmployee();
    public abstract int checkSalary();
    public abstract ArrayList<String> vacationInfo();
    public abstract String getUserLevel(String userID);
    public abstract void setName(String name);
    public abstract void setPassword(String password);
    public abstract void setPhone(String phone);
    public abstract void setAddress(String address);
    public abstract void setDepartment(String userID, String Department);
    public abstract void setLevel(String userID, String level);
    public abstract void setWage(String userID, String wage);
    public abstract boolean setPosition(String userID, String position);
    public abstract boolean setEmployeeState(String userID, String state);
}

