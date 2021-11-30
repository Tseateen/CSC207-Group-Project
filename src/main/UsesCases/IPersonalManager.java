package main.UsesCases;

import java.io.Serializable;
import java.util.ArrayList;

public interface IPersonalManager extends Serializable {

    ArrayList<String> employeeInfo(ILoginList loginList, IEmployeeList employeeList, String userID);
    String getWorkingHourFromPartTimeEmployee(IEmployeeList employeeList, String userID);
    String checkTotalSalary(IEmployeeList employeeList, String userID, IGroupList groupList, IWorkList workList);
    String checkMinimumWage(IEmployeeList employeeList, String userID);
    String checkVacationBonus(IEmployeeList employeeList, String userID);
    ArrayList<String> vacationInfo(IEmployeeList employeeList, String userID);
    String getUserLevel(String userID,IEmployeeList employeeList);
    void setName(String name, ILoginList loginList, String userID);
    void setPassword(String password, ILoginList loginList, String userID);
    void setPhone(String phone, ILoginList loginList, String userID);
    void setAddress(String address, ILoginList loginList, String userID);
    String checkKPIBonus(IEmployeeList employeeList, String userID, IGroupList groupList, IWorkList workList);
    void setDepartment(String userID, String Department, IEmployeeList employeeList);
    void setLevel(String userID, String level,IEmployeeList employeeList);
    void setWage(String userID, String wage, IEmployeeList employeeList);
    boolean setPosition(String userID, String position, IEmployeeList employeeList);
    boolean setEmployeeState(String userID, String state, IEmployeeList employeeList);
}

