package main.InterfaceAdapter;

import main.UsesCases.*;


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
    public FacadeSys() {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.fileGateway = new DataGateway(this.loginList, this.employeeList);
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList);
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
    public void personalInfo(){}

    public void checkSalary() {
    }

    public void setPersonalInfo() {
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
