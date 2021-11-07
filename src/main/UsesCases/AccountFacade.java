package main.UsesCases;

public class AccountFacade {

    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final PayManager mangerPay;
    private final AccountInfoManager changeAccountInfo;
    private final Verifier managerVerifier;
    private static int idCounter;

    public AccountFacade(LoginList loginList, EmployeeList employeeList) {
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.mangerPay = new PayManager();
        this.changeAccountInfo = new AccountInfoManager();
        this.managerVerifier = new Verifier(this.loginList);
    }

    public boolean VerifyForThisLogin(String account, String password){
        return this.managerVerifier.verifyForLogin(account,password);
    }

    public void CreateNewAccount(){
    }
}
