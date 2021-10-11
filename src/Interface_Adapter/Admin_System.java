package Interface_Adapter;

import Uses_Cases.AccountManager;
import Uses_Cases.Pay_Manager;
import Uses_Cases.Verifier;

public class Admin_System {
    private Verifier managerEmployee;
    private AccountManager ac;
    private Pay_Manager mangerPay;

    public Admin_System(){
        this.ac = new AccountManager();
        this.managerEmployee = new Verifier(ac.getEmployeeList());
        this.mangerPay = new Pay_Manager();
    }

    public boolean verifyAccountExistence(int username, int password){
            return managerEmployee.verifyAccountExist(username, password);

    }
    public boolean verifyAccIsAdmin(int username, int password){
        return managerEmployee.verifyisAdmin(username, password);
    }

    public void createUser(int accountNumber, int password, String name, int phone, String address, String status,
                           String department, String position, int wage, int level){
        ac.createEmployee(accountNumber, password, name, phone, address,status,department,position,wage,level);

    }
}
