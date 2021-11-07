package main.UsesCases;

import main.Entity.Employee;
import main.Entity.FullTimeEmployee;
import main.Entity.PartTimeEmployee;
import main.Entity.Userable;

public class AccountFacade {

    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final PayManager managerPay;
    private final FullTimeInfoManager fullTimeInfoManager;
    private final PartTimeInfoManager partTimeInfoManager;
    private final Verifier managerVerifier;
    private static int idCounter = 0;

    public AccountFacade(LoginList loginList, EmployeeList employeeList) {
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.managerPay = new PayManager();
        this.fullTimeInfoManager = new FullTimeInfoManager();
        this.partTimeInfoManager = new PartTimeInfoManager();
        this.managerVerifier = new Verifier(this.loginList);
    }

    public String getPartTimeEmployeeInfo(Userable user, PartTimeEmployee partTimeEmployee, String option){
        switch(option){
            case "1":
                this.partTimeInfoManager
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
            case "11":
        }
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
