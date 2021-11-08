package main.UsesCases;

import main.Entity.*;
import java.util.*;

public class AccountFacade {

    private final String username;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final PayManager managerPay;
    private final FullTimeInfoManager fullTimeInfoManager;
    private final PartTimeInfoManager partTimeInfoManager;
    private final Verifier managerVerifier;
    private static int idCounter = 0;

    public AccountFacade(LoginList loginList, EmployeeList employeeList, String username) {
        this.username = username;
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.managerPay = new PayManager();
        this.fullTimeInfoManager = new FullTimeInfoManager(findUserHelper(), (FullTimeEmployee) findFullTimeEmployeeHelper());
        this.partTimeInfoManager = new PartTimeInfoManager(findUserHelper(), (PartTimeEmployee) findPartTimeEmployeeHelper());
        this.managerVerifier = new Verifier(this.loginList);
    }

    // return all the info at the same time (馬哥的idea)
    public ArrayList<String> partTimeEmployeeInfo(){
        ArrayList<String> info = new ArrayList<>();
        info.add(this.partTimeInfoManager.getNameFromUser());
        info.add(this.partTimeInfoManager.getIDFromUser());
        info.add(this.partTimeInfoManager.getUsernameFromUser());
        info.add(this.partTimeInfoManager.getPasswordFromUser());
        info.add(this.partTimeInfoManager.getPhoneFromUser());
        info.add(this.partTimeInfoManager.getAddressFromUser());
        info.add(this.partTimeInfoManager.getDepartmentFromEmployee());
        return info;
    }



    // return one info at a time
    public String getPartTimeEmployeeInfo(String option) {
        switch (option) {
            case "1":
                return this.partTimeInfoManager.getNameFromUser();
            case "2":
                return this.partTimeInfoManager.getIDFromUser();
            case "3":
                return this.partTimeInfoManager.getUsernameFromUser();
            case "4":
                return this.partTimeInfoManager.getPasswordFromUser();
            case "5":
                return this.partTimeInfoManager.getPhoneFromUser();
            case "6":
                return this.partTimeInfoManager.getAddressFromUser();
            case "7":
                return this.partTimeInfoManager.getDepartmentFromEmployee();
            default :
                return "No such option, Please choose again!";
        }
    }

    public HashMap<String, String[]> getSchdulefromPartTimeEmployee(){
        return this.partTimeInfoManager.getScheduleFromEmployee();
    }


    public int getPartTimeEmployeInfoInt(String option){
        switch (option){
            case "9":
                return this.partTimeInfoManager.getWageFromEmployee();
            case "10":
                return this.partTimeInfoManager.getLevelFromEmployee();
            default:
                return 0;
        }
    }

    // return all the info at the same time (馬哥的idea)
    public ArrayList<String> FullTimeEmployeeInfo(){
        ArrayList<String> info = new ArrayList<>();
        info.add(this.fullTimeInfoManager.getNameFromUser());
        info.add(this.fullTimeInfoManager.getIDFromUser());
        info.add(this.fullTimeInfoManager.getUsernameFromUser());
        info.add(this.fullTimeInfoManager.getPasswordFromUser());
        info.add(this.fullTimeInfoManager.getPhoneFromUser());
        info.add(this.fullTimeInfoManager.getAddressFromUser());
        info.add(this.fullTimeInfoManager.getDepartmentFromEmployee());
        info.add(this.fullTimeInfoManager.getPosition());
        info.add(this.fullTimeInfoManager.getStatus());
        return info;
    }

    public int[] getFullTimeEmployeeInfoInt(){
        int[] intValue =  new int[2];
        intValue[0] = this.fullTimeInfoManager.getTotalVacationWithSalary();
        intValue[1] = this.fullTimeInfoManager.getVacationUsed();
        return intValue;
    }

    // return one info at a time
    public String getFullTimeEmployeeInfo(String option){
        switch (option) {
            case "1":
                return this.fullTimeInfoManager.getNameFromUser();
            case "2":
                return this.fullTimeInfoManager.getIDFromUser();
            case "3":
                return this.fullTimeInfoManager.getUsernameFromUser();
            case "4":
                return this.fullTimeInfoManager.getPasswordFromUser();
            case "5":
                return this.fullTimeInfoManager.getPhoneFromUser();
            case "6":
                return this.fullTimeInfoManager.getAddressFromUser();
            case "7":
                return this.fullTimeInfoManager.getDepartmentFromEmployee();
            case "8":
                return this.fullTimeInfoManager.getPosition();
            case "9":
                return this.fullTimeInfoManager.getStatus();
            default:
                return "No such option, Please choose again!";
        }
    }

    public int getFullTimeEmployeeInfoInt(String option){
        switch (option){
            case "10":
                return this.fullTimeInfoManager.getTotalVacationWithSalary();
            case "11":
                return this.fullTimeInfoManager.getVacationUsed();
            default:
                return 0;
        }
    }

    public void setPartTimeBasicInfo(String option, String response){
        switch (option){
            case "1":
                this.partTimeInfoManager.setNameForUser(response);
            case "2":
                this.partTimeInfoManager.setPasswordForUser(response);
            case "3":
                this.partTimeInfoManager.setPhoneForUser(response);
            case "4":
                this.partTimeInfoManager.setAddressForUser(response);
            case "5":
                this.partTimeInfoManager.setAttendenceForEmployee();
        }
    }

    public void setPartTimeAdvancedInfo(String option, String response){
        switch (option){
            case "1":
                this.partTimeInfoManager.setDepartmentForEmployee(response);
            case "2":
                int intWage;
                try {
                    intWage = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intWage = 0;
                }
                this.partTimeInfoManager.setWageForEmployee(intWage);
            case "3":
                int intLevel;
                try {
                    intLevel = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intLevel = 0;
                }
                this.partTimeInfoManager.setLevelForEmployee(intLevel);
        }
    }

    public void setSchedule(HashMap<String, String[]> schedule){
        this.partTimeInfoManager.setScheduleForEmployee(schedule);
    }
    public void setFullTimeBasicInfo(String option, String response){
        switch (option){
            case "1":
                this.fullTimeInfoManager.setNameForUser(response);
            case "2":
                this.fullTimeInfoManager.setPasswordForUser(response);
            case "3":
                this.fullTimeInfoManager.setPhoneForUser(response);
            case "4":
                this.fullTimeInfoManager.setAddressForUser(response);
            case "5":
                this.fullTimeInfoManager.setAttendenceForEmployee();

        }
    }

    public void setFullTimeAdvancedInfo(String option, String response){
        switch (option){
            case "1":
                this.fullTimeInfoManager.setDepartmentForEmployee(response);
            case "2":
                int intWage;
                try {
                    intWage = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intWage = 0;
                }
                this.fullTimeInfoManager.setWageForEmployee(intWage);
            case "3":
                int intLevel;
                try {
                    intLevel = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intLevel = 0;
                }
                this.fullTimeInfoManager.setLevelForEmployee(intLevel);
            case "4":
                this.fullTimeInfoManager.setPositionForEmployee(response);
            case "5":
                this.fullTimeInfoManager.setStateForEmployee(response);
            case "6":
                int intTotalVacation;
                try {
                    intTotalVacation = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intTotalVacation = 0;
                }
                this.fullTimeInfoManager.setTotalVacationWithSalaryForEmployee(intTotalVacation);
            case "7":
                int intVacationUsed;
                try {
                    intVacationUsed = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intVacationUsed = 0;
                }
                this.fullTimeInfoManager.setVacationUsedForEmployee(intVacationUsed);
        }
    }



    public void CreateNewAccount(String username, String password, String name, String phone,
                                        String address, String department, int wage, String position, int level, String status) {
        String id = String.valueOf(idCounter);
        this.loginList.addUser(username, password, name, phone, address, id);
        this.employeeList.addEmployee(department, wage, position, level, status, id);
        idCounter += 1;
        }

    public int PartTimeSalary(String account) {
        return this.partTimeInfoManager.getWageFromEmployee();
    }

    public int FullTimeSalary(){
        return this.fullTimeInfoManager.getWageFromEmployee();
    }

    public Userable findUserHelper() {
        Userable correctUser = new User();
        for (Userable user : this.loginList) {
            if (user.getUsername().equals(this.username)) {
                correctUser = user;
            }
        }
        return correctUser;
    }

    public Employee findPartTimeEmployeeHelper(){
        Userable correctUser = findUserHelper();
        Employee correctEmployee = new PartTimeEmployee();
        for(Employee partTimeEmployee: this.employeeList){
            if (partTimeEmployee.getID().equals(correctUser.getID())){
                correctEmployee = partTimeEmployee;
            }
        }
        return correctEmployee;
    }

    public Employee findFullTimeEmployeeHelper(){
        Userable correctUser = findUserHelper();
        Employee correctEmployee = new FullTimeEmployee();
        for(Employee fullTimeEmployee: this.employeeList){
            if (fullTimeEmployee.getID().equals(correctUser.getID())){
                correctEmployee = fullTimeEmployee;
            }
        }
        return correctEmployee;
    }
}
