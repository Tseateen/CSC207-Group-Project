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
        this.managerEmployee = new Verifier(ac);
        this.mangerPay = new Pay_Manager();
    }

    public boolean verifyAccountExistence(String username, String password){
            return managerEmployee.verifyAccountExist(username, password);

    }
    public boolean verifyAccIsAdmin(String username, String password){
        return managerEmployee.verifyAdmin(username, password);
    }

    /**
     * 这里如果账号创建失败要不要考虑去return一个报告，比如说是因为什么原因之类的
     * @param accountNumber
     * @param password
     * @param name
     * @param phone
     * @param address
     * @param status
     * @param department
     * @param position
     * @param wage
     * @param level
     */
    public void createUser(String accountNumber, String password, String name, String phone, String address, String status,
                           String department, String position, int wage, int level){
        ac.createEmployee(accountNumber, password, name, phone, address,status,department,position,wage,level);

    }
}
