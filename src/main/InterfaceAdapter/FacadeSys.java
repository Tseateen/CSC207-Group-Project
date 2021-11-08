package main.InterfaceAdapter;

import main.UsesCases.*;

import java.util.ArrayList;
import java.util.Scanner;


public class FacadeSys {

    // === Instance Variables ===

    // === DataFile ===
    private final DataGateway fileGateway;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    // === AccountFacade ===
    private final AccountFacade accountFacade;
    // === WorkFacade ===
    private final WorkFacade workFacade;

    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSys(String username) {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.fileGateway = new DataGateway(this.loginList, this.employeeList);
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList,username);
        this.workFacade = new WorkFacade();

    }

    // === System methods ===

    public boolean systemStart(String accountNumber, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        return this.accountFacade.VerifyForThisLogin(accountNumber, password);
    }

    public void systemEnd() {
        this.fileGateway.WriteOutputFile();
    }

    // === Personal UI Method ===
    public void partTimePersonalInfo(){
        ArrayList<String> info = accountFacade.partTimeEmployeeInfo();
        String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                + "\n Department: " + info.get(6);
        System.out.println(presentInfo);
    }

    public void fullTimePersonalInfo(){
        ArrayList<String> info = accountFacade.FullTimeEmployeeInfo();
        String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                + "\n Department: " + info.get(6) + "\n Position: " + info.get(7) + "\n State: " +info.get(8);
        System.out.println(presentInfo);
    }
    public void checkPartTimeSalary() {
        String presentWage = String.valueOf(accountFacade.partTimeSalary());
        System.out.println(presentWage);
    }

    public void checkFullTimeSalary(){
        String presentWage = String.valueOf(accountFacade.fullTimeSalary());
        System.out.println(presentWage);
    }
    public void setPartTimePersonalInfo() {
        Scanner keyIn = new Scanner(System.in);
        System.out.println("i) Change your name, please type 1; " + "\n" +
                "ii) Change your password, please type 2; " + "\n" +
                "iii) Change your phone number, please type 3; " + "\n" +
                "iv) Change your address, please type 4" + "\n" +
                "v) Change you attendence, please type 5");
        String option = keyIn.nextLine();
        System.out.println("Please type the value you want to change:");
        String response = keyIn.nextLine();
        accountFacade.setPartTimeBasicInfo(option, response);
        System.out.println("Set personal info success!");
    }

    public void setFullTimePersonalInfo(){
        Scanner keyIn = new Scanner(System.in);
        System.out.println("i) Change your name, please type 1; " + "\n" +
                "ii) Change your password, please type 2; " + "\n" +
                "iii) Change your phone number, please type 3; " + "\n" +
                "iv) Change your address, please type 4" + "\n" +
                "v) Change you attendence, please type 5");
        String option = keyIn.nextLine();
        System.out.println("Please type the value you want to change:");
        String response = keyIn.nextLine();
        accountFacade.setFullTimeBasicInfo(option, response);
        System.out.println("Set personal info success!");
    }




    // === Work UI Method ===

    public void checkWorkInfo() {
        System.out.println("check Work");
    }


    public void createWork() {
    }

    public void distributeWork() {
    }
}
