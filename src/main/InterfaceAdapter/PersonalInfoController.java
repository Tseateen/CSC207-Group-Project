package main.InterfaceAdapter;

import main.Entity.Userable;
import main.UsesCases.*;

import java.util.ArrayList;

public class PersonalInfoController {
    private final IPersonalManager personalManager;

    public PersonalInfoController(IPersonalManager personalManager){
        this.personalManager = personalManager;
    }


    public String personalInfo(ILoginList loginList, IEmployeeList employeeList, String userID){
        String personalInfo = "";
        ArrayList<String> info = this.personalManager.employeeInfo(loginList, employeeList, userID);
        personalInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                + "\n Department: " + info.get(6);
        if (info.size() == 7){
            return personalInfo;
        }else{
            return personalInfo.concat("\n Position: " + info.get(7) + "\n State: " +info.get(8)
                    +"\n Total Vacation with Salary: " + info.get(9) + "\n  Vacation Used: " + info.get(10));
        }
    }


    public String checkTotalSalary(IEmployeeList employeeList, String userID, IGroupList groupList, IWorkList workList){
        return String.valueOf(this.personalManager.checkTotalSalary(employeeList, userID, groupList, workList));
    }
    public String checkMinimumWage(IEmployeeList employeeList, String userID){
        return String.valueOf(this.personalManager.checkMinimumWage(employeeList, userID));
    }
    public String checkVacationBonus(IEmployeeList employeeList, String userID){
        return String.valueOf(this.personalManager.checkVacationBonus(employeeList, userID));
    }
    public String checkKPIBonus(IEmployeeList employeeList, String userID){
        return String.valueOf(this.personalManager.checkKPIBonus(employeeList, userID));
    }

    public boolean setPersonalInfo(String option, String response, ILoginList loginList, String userID){
        try{
            switch (option){
                case "1":
                    this.personalManager.setName(response, loginList, userID);
                    break;
                case "2":
                    this.personalManager.setPassword(response, loginList, userID);
                    break;
                case "3":
                    this.personalManager.setAddress(response, loginList, userID);
                    break;
                case "4":
                    this.personalManager.setPhone(response, loginList, userID);
                    break;
                default:
                    return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public boolean setEmployeeInfo(String userID, String option, String response, IEmployeeList employeeList){
        boolean correctAction = true;
        try{
            switch (option){
                case "1":
                    this.personalManager.setDepartment(userID, response, employeeList );
                    break;
                case "2":
                    this.personalManager.setLevel(userID, response, employeeList);
                    break;
                case "3":
                    this.personalManager.setWage(userID, response, employeeList);
                    break;
                case "4":
                    if(this.personalManager.setPosition(userID, response, employeeList)){
                        correctAction = false;
                    }
                    break;
                case "5":
                    if(this.personalManager.setEmployeeState(userID, response, employeeList)){
                        correctAction = false;
                    }
                    break;
            }
            return correctAction;
        } catch (Exception e){
            return false;
        }
    }
}