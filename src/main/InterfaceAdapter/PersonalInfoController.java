package main.InterfaceAdapter;

import main.Entity.Userable;
import main.UsesCases.IEmployeeList;
import main.UsesCases.ILoginList;
import main.UsesCases.IPersonalManager;
import main.UsesCases.PersonalManager;

import java.util.ArrayList;

public class PersonalInfoController {
    private IPersonalManager personalManager;

    public PersonalInfoController(ILoginList loginList, IEmployeeList employeeList, String username){
        this.personalManager = new PersonalManager(loginList, employeeList, username);
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


    public String checkSalary(){
        return String.valueOf(this.personalManager.checkSalary());
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
                case "2":
                    this.personalManager.setLevel(userID, response);
                case "3":
                    this.personalManager.setWage(userID, response);
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
