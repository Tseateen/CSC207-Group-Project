package main.InterfaceAdapter;

import main.UsesCases.*;


public class FacadeSys {

    // === Instance Variables ===

    // TODO: Need to provide some statements for these variable below.
    // TODO: Should we make following variable as final variable?
    private final AccountList managerAccountList;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final PayManager mangerPay;
    private final AccountInfoManager changeAccountInfo;
    private final Verifier managerVerifier;
    private final AuthorityManager managerAuthority;
    private final WorkDistributor distributeWork;
    private final GroupManager managerGroup;
    private final WorkManager managerWork;
    private final DataGateway fileGateway;
    private String IdCounter;

    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSys() {
        this.managerAccountList = new AccountList();
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.mangerPay = new PayManager();
        this.changeAccountInfo = new AccountInfoManager();
        this.managerVerifier = new Verifier(this.managerAccountList);
        this.managerAuthority = new AuthorityManager();
        this.distributeWork = new WorkDistributor();
        this.managerGroup = new GroupManager();
        this.managerWork = new WorkManager();
        this.fileGateway = new DataGateway(loginList, employeeList);
    }

    // === System methods ===

    public boolean systemStart(String accountNumber, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        return this.managerVerifier.verifyForLogin(accountNumber, password);
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
