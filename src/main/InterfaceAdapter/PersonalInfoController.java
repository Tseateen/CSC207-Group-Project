package main.InterfaceAdapter;

import main.UsesCases.*;

import java.util.ArrayList;

public class PersonalInfoController {
    private final IPersonalManager personalManager;

    public PersonalInfoController(IPersonalManager personalManager){
        this.personalManager = personalManager;
    }

    // === Usage: FacadeSys Case (i) ====
    public ArrayList<String> personalInfo(ILoginList loginList, IEmployeeList employeeList, String userID){
        ArrayList<String> personalInfo = new ArrayList<>();
        ArrayList<String> info = this.personalManager.employeeInfo(loginList, employeeList, userID);
        personalInfo.add("Name: " + info.get(0) + "\n");
        personalInfo.add("ID: " + info.get(1) + "\n");
        personalInfo.add("Password: " + info.get(2) + "\n");
        personalInfo.add("Phone Number: " + info.get(3) + "\n");
        personalInfo.add("Address: " +info.get(4) + "\n");
        personalInfo.add("Department: " + info.get(5) + "\n");
        if (info.size() != 6) {
            personalInfo.add("Position: " + info.get(6) + "\n");
            personalInfo.add("State: " + info.get(7) + "\n");
            personalInfo.add("Total Vacation with Salary: " + info.get(8) + "\n");
            personalInfo.add("Vacation Used: " + info.get(9) + "\n");
        }
        return personalInfo;
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
    public String checkKPIBonus(IEmployeeList employeeList, String userID, IGroupList groupList, IWorkList workList){
        return String.valueOf(this.personalManager.checkKPIBonus(employeeList, userID, groupList, workList));
    }

    // === Usage: FacadeSys PersonalCase (vi) ====
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
    // ======================================================================


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

    // === Usage: FacadeSys Worker Case (iii) ====
    public String checkUserLevel(String userID, IEmployeeList employeeList){
        return this.personalManager.getUserLevel(userID, employeeList);
    }
    // ==================================================
}