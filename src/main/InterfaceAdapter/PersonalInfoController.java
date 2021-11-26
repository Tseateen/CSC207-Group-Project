package main.InterfaceAdapter;

import main.Entity.Userable;
import main.UsesCases.*;

import java.util.ArrayList;

public class PersonalInfoController {
    private IPersonalManager personalManager;

    public PersonalInfoController(IPersonalManager personalManager){
        this.personalManager = personalManager;
    }


    public String personalInfo(){
        String personalInfo = "";
        ArrayList<String> info = this.personalManager.employeeInfo();
        personalInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                + "\n Department: " + info.get(6);
        if (info.size() == 7){
            return personalInfo;
        }else{
            String concatPeronalInfo = personalInfo.concat("\n Position: " + info.get(7) + "\n State: " +info.get(8)
                    +"\n Total Vacation with Salary: " + info.get(9) + "\n  Vacation Used: " + info.get(10));
            return concatPeronalInfo;
        }
    }


    public String checkTotalSalary(){
        return String.valueOf(this.personalManager.checkTotalSalary());
    }
    public String checkMinimumWage(){
        return String.valueOf(this.personalManager.checkMinimumWage());
    }
    public String checkVacationBonus(){
        return String.valueOf(this.personalManager.checkVacationBonus());
    }
    public String checkKPIBonus(){
        return String.valueOf(this.personalManager.checkKPIBonus());
    }

    public boolean setPersonalInfo(String option, String response){
        try{
            switch (option){
                case "1":
                    this.personalManager.setName(response);
                    break;
                case "2":
                    this.personalManager.setPassword(response);
                    break;
                case "3":
                    this.personalManager.setAddress(response);
                    break;
                case "4":
                    this.personalManager.setPhone(response);
                    break;
                default:
                    return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public boolean setEmployeeInfo(String userID, String option, String response){
        boolean correctAction = true;
        try{
            switch (option){
                case "1":
                    this.personalManager.setDepartment(userID, response);
                    break;
                case "2":
                    this.personalManager.setLevel(userID, response);
                    break;
                case "3":
                    this.personalManager.setWage(userID, response);
                    break;
                case "4":
                    if(this.personalManager.setPosition(userID, response)){
                        correctAction = false;
                    }
                    break;
                case "5":
                    if(this.personalManager.setEmployeeState(userID, response)){
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