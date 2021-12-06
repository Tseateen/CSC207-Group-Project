package main.InterfaceAdapter;

import main.UsesCases.*;

import java.util.ArrayList;

public class PersonalInfoController {


    // === Instance Variables ===
    private final IPersonalManager personalManager;


    /**
     * Construct the PersonalInfoController.
     */
    public PersonalInfoController(IPersonalManager personalManager){
        this.personalManager = personalManager;
    }

    // === Usage: FacadeSys Case (i) ====

    /**
     * Set up the personal information to the system.
     *
     * @param userID the ID of the targeted User.
     *
     * @return an ArrayList of strings with the information about the user.
     */
    public ArrayList<String> personalInfo(ILoginList loginList, IEmployeeList employeeList, String userID){
        ArrayList<String> personalInfo = new ArrayList<>();
        ArrayList<String> info = this.personalManager.employeeInfo(loginList, employeeList, userID);
        ArrayList<String> vacationInfo = this.personalManager.vacationInfo(employeeList, userID);
        personalInfo.add("Name: " + info.get(0) + "\n");
        personalInfo.add("ID: " + info.get(1) + "\n");
        personalInfo.add("Password: " + info.get(2) + "\n");
        personalInfo.add("Phone Number: " + info.get(3) + "\n");
        personalInfo.add("Address: " +info.get(4) + "\n");
        personalInfo.add("Department: " + info.get(5) + "\n");
        if (info.size() == 6) {
           personalInfo.add("Work horus: " + this.personalManager.getWorkingHourFromPartTimeEmployee(employeeList,userID));
        }
        else{
            personalInfo.add("Position: " + info.get(6) + "\n");
            personalInfo.add("State: " + info.get(7) + "\n");
            personalInfo.add("Total Vacation with Salary: " + vacationInfo.get(0) + "\n");
            personalInfo.add("Vacation Used: " + vacationInfo.get(1) + "\n");
        }
        return personalInfo;
    }


    /**
     * Check the total salary of an employee (including bonus).
     *
     * @param userID the ID of the targeted user.
     *
     * @return A string that represent the salary/wage of an employee.
     */
    public String checkTotalSalary(IEmployeeList employeeList, String userID, IGroupList groupList, IWorkList workList){
        return String.valueOf(this.personalManager.checkTotalSalary(employeeList, userID, groupList, workList));
    }


    /**
     * Check the minimum wage of an employee.
     *
     * @param userID the ID of the targeted user.
     *
     * @return A string that represent the minimum wage of an employee.
     */
    public String checkMinimumWage(IEmployeeList employeeList, String userID){
        return String.valueOf(this.personalManager.checkMinimumWage(employeeList, userID));
    }


    /**
     * Check the vocation bonus of an employee.
     *
     * @param userID the ID of the targeted user.
     *
     * @return A string that represent the vocation bonus of an employee.
     */
    public String checkVacationBonus(IEmployeeList employeeList, String userID){
        return String.valueOf(this.personalManager.checkVacationBonus(employeeList, userID));
    }


    /**
     * Check the KPI bonus of an employee.
     *
     * @param userID the ID of the targeted user.
     *
     * @return A string that represent the KPI bonus of an employee.
     */
    public String checkKPIBonus(IEmployeeList employeeList, String userID, IGroupList groupList, IWorkList workList){
        return String.valueOf(this.personalManager.checkKPIBonus(employeeList, userID, groupList, workList));
    }


    // === Usage: FacadeSys PersonalCase (vi) ====

    /**
     * Setter method to set the personal information of a user.
     *
     * @param option the part the information to be set of the targeted user.
     * @param userID the ID of the user.
     * @param response the information will be changed to.
     *
     * @return true iff the personal information has been successfully set.
     */
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

    /**
     * Setter method to set the personal information of an employee.
     *
     * @param option the part the information to be set of the targeted user.
     * @param userID the ID of the Employee.
     * @param response the information will be changed to.
     *
     * @return true iff the personal information has been successfully set.
     */
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


    /**
     * Checker method to check the user leve of an employee.
     *
     * @param userID the ID of the targeted user.
     *
     * @return the string with the user's authority level.
     */
    public String checkUserLevel(String userID, IEmployeeList employeeList){
        return this.personalManager.getUserLevel(userID, employeeList);
    }
    // ==================================================
}