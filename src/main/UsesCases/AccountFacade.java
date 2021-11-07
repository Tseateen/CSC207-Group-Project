package main.UsesCases;

import main.Entity.Employee;
import main.Entity.PartTimeEmployee;
import main.Entity.Userable;

public class AccountFacade {

    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final PayManager managerPay;
    private final AccountInfoManager changeAccountInfo;
    private final Verifier managerVerifier;
    private static int idCounter = 0;

    public AccountFacade(LoginList loginList, EmployeeList employeeList) {
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.managerPay = new PayManager();
        this.changeAccountInfo = new AccountInfoManager();
        this.managerVerifier = new Verifier(this.loginList);
    }

    public boolean VerifyForThisLogin(String account, String password){
        return this.managerVerifier.verifyForLogin(account, password);
    }

    public void CreateNewAccount(String accountNumber, String password, String name, String phone, String address){
        this.loginList.addUser(accountNumber, password, name, phone, address, String.valueOf(idCounter));
        idCounter += 1;

    }


    public int UserSalary(String account) {
        Userable user = managerVerifier.getEmployeeMap().get(account);
        Employee employee = managerPay.getEmployeeMap().get(user);
        return employee.getWage();
    }


}
