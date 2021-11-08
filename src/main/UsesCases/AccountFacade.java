package main.UsesCases;

import main.Entity.*;
import java.util.HashMap;

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
                return this.partTimeInfoManager.getIDFromEmployee();
            case "8":
                return this.partTimeInfoManager.getDepartmentFromEmployee();
            default :
                return "No such option, Please choose again!";
        }
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

    public HashMap<String, String[]> getSchdulefromPartTimeEmployee(){
        return this.partTimeInfoManager.getScheduleFromEmployee();
    }

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
                return this.fullTimeInfoManager.getIDFromEmployee();
            case "8":
                return this.fullTimeInfoManager.getDepartmentFromEmployee();
            case "9":
                return this.fullTimeInfoManager.getPosition();
            case "10":
                return this.fullTimeInfoManager.getStatus();
            default:
                return "No such option, Please choose again!";
        }
    }

    public int getFullTimeEmployeeInfoInt(String option){
        switch (option){
            case "11":
                return this.fullTimeInfoManager.getTotalVacationWithSalary();
            case "12":
                return this.fullTimeInfoManager.getVacationUsed();
            default:
                return 0;
        }
    }

    public void setPartTimeInfoManager(String option, String response){
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
                this.partTimeInfoManager.setDepartmentForEmployee(response);
            case "6":
                this.partTimeInfoManager.setAttendenceForEmployee();
            case "7":
                int intWage;
                try {
                    intWage = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intWage = 0;
                }
                this.fullTimeInfoManager.setWageForEmployee(intWage);
            case "8":
                int intLevel;
                try {
                    intLevel = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intLevel = 0;
                }
                this.fullTimeInfoManager.setLevelForEmployee(intLevel);
        }
    }

    public void setSchedule(HashMap<String, String[]> schedule){
        this.partTimeInfoManager.setScheduleForEmployee(schedule);
    }
    public void setFullTimeInfoManager(String option, String response){
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
                this.fullTimeInfoManager.setDepartmentForEmployee(response);
            case "6":
                this.fullTimeInfoManager.setAttendenceForEmployee();
            case "7":
                int intWage;
                try {
                    intWage = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intWage = 0;
                }
                this.fullTimeInfoManager.setWageForEmployee(intWage);
            case "8":
                int intLevel;
                try {
                    intLevel = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intLevel = 0;
                }
                this.fullTimeInfoManager.setLevelForEmployee(intLevel);
            case "9":
                this.fullTimeInfoManager.setPositionForEmployee(response);
            case "10":
                this.fullTimeInfoManager.setStateForEmployee(response);
            case "11":
                int intTotalVacation;
                try {
                    intTotalVacation = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intTotalVacation = 0;
                }
                this.fullTimeInfoManager.setTotalVacationWithSalaryForEmployee(intTotalVacation);
            case "12":
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


    public boolean VerifyForThisLogin() {
        return this.managerVerifier.verifyForLogin(this.username);
    }

    public void CreateNewAccount(String option, String accountNumber, String password, String name, String phone,
                                 String address,String department, int wage, String position,  int level, String status, String id) {
        this.loginList.addUser(accountNumber, password, name, phone, address, String.valueOf(idCounter));
        this.employeeList.addEmployee(address, wage, position, level, status, id);
        idCounter += 1;
        }

    public int UserSalary(String account) {
        Userable user = managerVerifier.getEmployeeMap().get(account);
        Employee employee = managerPay.getEmployeeMap().get(user);
        return employee.getWage();
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
