package main.UsesCases;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface IPersonalManager {

    public abstract ArrayList<String> employeeInfo(ILoginList loginList, IEmployeeList employeeList, String userID);
    public abstract String getWorkingHourFromPartTimeEmployee(IEmployeeList employeeList, String userID);
    public abstract String checkTotalSalary(IEmployeeList employeeList, String userID, IGroupList groupList, IWorkList workList);
    public abstract String checkMinimumWage(IEmployeeList employeeList, String userID);
    public abstract String checkVacationBonus(IEmployeeList employeeList, String userID);
    public abstract String checkKPIBonus(IEmployeeList employeeList, String userID);
    public abstract ArrayList<String> vacationInfo(IEmployeeList employeeList, String userID);
    public abstract String getUserLevel(String userID,IEmployeeList employeeList);
    public abstract void setName(String name, ILoginList loginList, String userID);
    public abstract void setPassword(String password, ILoginList loginList, String userID);
    public abstract void setPhone(String phone, ILoginList loginList, String userID);
    public abstract void setAddress(String address, ILoginList loginList, String userID);
    public abstract void setDepartment(String userID, String Department, IEmployeeList employeeList);
    public abstract void setLevel(String userID, String level,IEmployeeList employeeList);
    public abstract void setWage(String userID, String wage, IEmployeeList employeeList);
    public abstract boolean setPosition(String userID, String position, IEmployeeList employeeList);
    public abstract boolean setEmployeeState(String userID, String state, IEmployeeList employeeList);
}

