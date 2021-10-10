package Interface_Adapter;

import Uses_Cases.Employee_Manager;
import Uses_Cases.Pay_Manager;

public class Admin_System {
    private Employee_Manager managerEmployee;
    private Pay_Manager mangerPay;

    public Admin_System(){
        this.managerEmployee = new Employee_Manager();
        this.mangerPay = new Pay_Manager();
    }

    public boolean verifyAccountExistence(int username, int password){
            return managerEmployee.verifyAccountExist(username, password);

    }
    public boolean verifyAuthority(int username, int password){
        return managerEmployee.verifyAuthority(username, password);
    }

    public void createEmployee(String)
}
