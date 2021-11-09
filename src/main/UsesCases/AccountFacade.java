package main.UsesCases;

import main.Entity.*;
import java.util.*;

public class AccountFacade {

    private final String username;
    private final Userable user;
    private final Employee employee;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final PayManager managerPay;
    private final Verifier managerVerifier;
    private static int idCounter = 0;
    private final String employeeType;

    public AccountFacade(LoginList loginList, EmployeeList employeeList, String username) {
        this.username = username;
        this.user = this.findUserHelper();
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.managerPay = new PayManager();

        this.managerVerifier = new Verifier(this.loginList);
        this.employeeType = employeeType();
        if (this.employeeType.equals("PartTimeEmployee")){
            this.employee = findPartTimeEmployeeHelper();
        }else{
            this.employee = findFullTimeEmployeeHelper();
        }
    }

    public String getEmployeeType(){
        return this.employeeType;
    }

    // return all the info at the same time (馬哥的idea)
    public ArrayList<String> partTimeEmployeeInfo(){
        ArrayList<String> info = new ArrayList<>();
        info.add(this.user.getName());
        info.add(this.user.getID());
        info.add(this.user.getUsername());
        info.add(this.user.getPassword());
        info.add(this.user.getPhone());
        info.add(this.user.getAddress());
        info.add(this.employee.getDepartment());
        return info;
    }

    public ArrayList<String> employeeInfo(){
        ArrayList<String> info = new ArrayList<>();
        if (this.employeeType.equals("PartTimeEmployee")){
            info.add(this.user.getName());
            info.add(this.user.getID());
            info.add(this.user.getUsername());
            info.add(this.user.getPassword());
            info.add(this.user.getPhone());
            info.add(this.user.getAddress());
            info.add(this.employee.getDepartment());
        }else{
            FullTimeEmployee employee = (FullTimeEmployee) this.employee;
            info.add(this.user.getName());
            info.add(this.user.getID());
            info.add(this.user.getUsername());
            info.add(this.user.getPassword());
            info.add(this.user.getPhone());
            info.add(this.user.getAddress());
            info.add(this.employee.getDepartment());
            info.add(employee.getPosition());
            info.add(employee.getState());
        }
        return info;
    }

    // return one info at a time
//    public String getPartTimeEmployeeInfo(String option) {
//        switch (option) {
//            case "1":
//                return this.partTimeInfoManager.getNameFromUser();
//            case "2":
//                return this.partTimeInfoManager.getIDFromUser();
//            case "3":
//                return this.partTimeInfoManager.getUsernameFromUser();
//            case "4":
//                return this.partTimeInfoManager.getPasswordFromUser();
//            case "5":
//                return this.partTimeInfoManager.getPhoneFromUser();
//            case "6":
//                return this.partTimeInfoManager.getAddressFromUser();
//            case "7":
//                return this.partTimeInfoManager.getDepartmentFromEmployee();
//            default :
//                return "No such option, Please choose again!";
//        }
//    }

    public HashMap<String, String[]> getSchedulefromPartTimeEmployee(){
        PartTimeEmployee employee = (PartTimeEmployee) this.employee;
        return employee.getSchedule();
    }


//    public int getPartTimeEmployeeInfoInt(String option){
//        switch (option){
//            case "9":
//                return this.partTimeInfoManager.getWageFromEmployee();
//            case "10":
//                return this.partTimeInfoManager.getLevelFromEmployee();
//            default:
//                return 0;
//        }
//    }

    // return all the info at the same time (馬哥的idea)
//    public ArrayList<String> FullTimeEmployeeInfo(){
//        ArrayList<String> info = new ArrayList<>();
//        info.add(this.fullTimeInfoManager.getNameFromUser());
//        info.add(this.fullTimeInfoManager.getIDFromUser());
//        info.add(this.fullTimeInfoManager.getUsernameFromUser());
//        info.add(this.fullTimeInfoManager.getPasswordFromUser());
//        info.add(this.fullTimeInfoManager.getPhoneFromUser());
//        info.add(this.fullTimeInfoManager.getAddressFromUser());
//        info.add(this.fullTimeInfoManager.getDepartmentFromEmployee());
//        info.add(this.fullTimeInfoManager.getPosition());
//        info.add(this.fullTimeInfoManager.getStatus());
//        return info;
//    }

    public int[] getFullTimeEmployeeInfoInt(){
        FullTimeEmployee employee = (FullTimeEmployee) this.employee;
        int[] intValue =  new int[2];
        intValue[0] = employee.getTotalVacationWithSalary();
        intValue[1] = employee.getVacationUsed();
        return intValue;
    }

    // return one info at a time
//    public String getFullTimeEmployeeInfo(String option){
//        switch (option) {
//            case "1":
//                return this.fullTimeInfoManager.getNameFromUser();
//            case "2":
//                return this.fullTimeInfoManager.getIDFromUser();
//            case "3":
//                return this.fullTimeInfoManager.getUsernameFromUser();
//            case "4":
//                return this.fullTimeInfoManager.getPasswordFromUser();
//            case "5":
//                return this.fullTimeInfoManager.getPhoneFromUser();
//            case "6":
//                return this.fullTimeInfoManager.getAddressFromUser();
//            case "7":
//                return this.fullTimeInfoManager.getDepartmentFromEmployee();
//            case "8":
//                return this.fullTimeInfoManager.getPosition();
//            case "9":
//                return this.fullTimeInfoManager.getStatus();
//            default:
//                return "No such option, Please choose again!";
//        }
//    }

//    public int getFullTimeEmployeeInfoInt(String option){
//        switch (option){
//            case "10":
//                return this.fullTimeInfoManager.getTotalVacationWithSalary();
//            case "11":
//                return this.fullTimeInfoManager.getVacationUsed();
//            default:
//                return 0;
//        }
//    }

    public void setPartTimeBasicInfo(String option, String response){
        PartTimeEmployee employee = (PartTimeEmployee) this.employee;
        switch (option){
            case "1":
                this.user.setName(response);
            case "2":
                this.user.setPassword(response);
            case "3":
                this.user.setPhone(response);
            case "4":
                this.user.setAddress(response);
            case "5":
                employee.setAttendance();
        }
    }

    public void setPartTimeAdvancedInfo(String option, String response){
        PartTimeEmployee employee = (PartTimeEmployee) this.employee;
        switch (option){
            case "1":
                employee.setDepartment(response);
            case "2":
                int intWage;
                try {
                    intWage = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intWage = 0;
                }
                employee.setWage(intWage);
            case "3":
                int intLevel;
                try {
                    intLevel = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intLevel = 0;
                }
                employee.setLevel(intLevel);
        }
    }

    public void setSchedule(HashMap<String, String[]> schedule){
        PartTimeEmployee employee = (PartTimeEmployee) this.employee;
        employee.setSchedule(schedule);
    }
    public void setFullTimeBasicInfo(String option, String response){
        FullTimeEmployee employee = (FullTimeEmployee) this.employee;
        switch (option){
            case "1":
                this.user.setName(response);
            case "2":
                this.user.setPassword(response);
            case "3":
                this.user.setPhone(response);
            case "4":
                this.user.setAddress(response);
            case "5":
               employee.setAttendance();

        }
    }

    public void setFullTimeAdvancedInfo(String option, String response){
        FullTimeEmployee employee = (FullTimeEmployee) this.employee;
        switch (option){
            case "1":
                employee.setDepartment(response);
            case "2":
                int intWage;
                try {
                    intWage = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intWage = 0;
                }
                employee.setWage(intWage);
            case "3":
                int intLevel;
                try {
                    intLevel = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intLevel = 0;
                }
                employee.setLevel(intLevel);
            case "4":
                employee.setPosition(response);
            case "5":
               employee.setState(response);
            case "6":
                int intTotalVacation;
                try {
                    intTotalVacation = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intTotalVacation = 0;
                }
                employee.setTotalVacationWithSalary(intTotalVacation);
            case "7":
                int intVacationUsed;
                try {
                    intVacationUsed = Integer.parseInt(response);
                }
                catch (NumberFormatException e)
                {
                    intVacationUsed = 0;
                }
                employee.setVacationUsed(intVacationUsed);
        }
    }

    public int user_Level(){
        if (employeeType.equals("PartTimeEmployee")){
            PartTimeEmployee employee = (PartTimeEmployee) this.employee;
            return employee.getLevel();
        }else{
            FullTimeEmployee employee = (FullTimeEmployee) this.employee;
            return employee.getLevel();
        }
    }

    private boolean levelVerifier(String level) {
        try {
            if (level.length() != 1) {
                return false;
            }
            int a = Integer.parseInt(level);
            return a > user_Level();
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void CreateNewAccount(String username, String password, String name, String phone,
                                        String address, String department, int wage, String position, int level, String status) {
        String id = String.valueOf(idCounter);
        this.loginList.addUser(username, password, name, phone, address, id);
        this.employeeList.addEmployee(department, wage, position, level, status, id);
        idCounter += 1;
        }

    public int checkSalary() {
        if (this.employeeType.equals("PartTimeEmployee")) {
            PartTimeEmployee employee = (PartTimeEmployee) this.employee;
            return employee.getWage();
        } else {
            return employee.getWage();
        }
    }


    private Userable findUserHelper() {
        Userable correctUser = new User();
        for (Userable user : this.loginList) {
            if (user.getUsername().equals(this.username)) {
                correctUser = user;
            }
        }
        return correctUser;
    }

    private PartTimeEmployee findPartTimeEmployeeHelper(){
        Employee correctEmployee = new PartTimeEmployee();
        for(Employee partTimeEmployee: this.employeeList){
            if (partTimeEmployee.getID().equals(this.user.getID())){
                correctEmployee = partTimeEmployee;
            }
        }
        return (PartTimeEmployee) correctEmployee;
    }

    private FullTimeEmployee findFullTimeEmployeeHelper(){
        Employee correctEmployee = new FullTimeEmployee();
        for(Employee fullTimeEmployee: this.employeeList){
            if (fullTimeEmployee.getID().equals(this.user.getID())){
                correctEmployee = fullTimeEmployee;
            }
        }
        return (FullTimeEmployee) correctEmployee;
    }

    public String employeeType(){
        String typeEmployee = "N/A";
        for(Employee employee: this.employeeList){
            if (employee.getID().equals(this.user.getID())){
                if (employee instanceof PartTimeEmployee) {
                    typeEmployee = "PartTimeEmployee";
                }else{
                    typeEmployee = "FullTimeEmployee";
                }
            }
        }
        return typeEmployee;
    }




    public String employeeTypeByID(Userable user){
        String typeEmployee = new String();
        for(Employee employee: this.employeeList){
            if (employee.getID().equals(user.getID())){
                if (employee instanceof PartTimeEmployee) {
                    typeEmployee = "PartTimeEmployee";
                }else{
                    typeEmployee = "FullTimeEmployee";
                }
            }
        }
        return typeEmployee;
    }
}
