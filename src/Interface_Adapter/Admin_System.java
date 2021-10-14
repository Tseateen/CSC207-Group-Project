package Interface_Adapter;

import Uses_Cases.AccountManager;
import Uses_Cases.Pay_Manager;
import Uses_Cases.Verifier;


public class Admin_System {

    // === Instance Variables ===

    // TODO: Need to provide some statements for these variable below.
    // TODO: Should we make following variable as final variable?
    private Verifier managerVerifier;
    private AccountManager managerAccount;
    private Pay_Manager mangerPay;

    /**
     *  Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public Admin_System(){
        this.managerAccount = new AccountManager();
        this.managerVerifier = new Verifier(managerAccount);
        this.mangerPay = new Pay_Manager();
    }

    // === Regular methods ===

    /**
     * This method can verify the provided account whether exists or not.
     * @param accountNumber Given an account number for verify
     * @param password Given a password number for verify
     * @return Return true iff the provided account exist. Otherwise, return false.
     */
    public boolean verifyForLogin(String accountNumber, String password){
            return managerVerifier.verifyForLogin(accountNumber, password);

    }

    /**
     * This method can verify the user whether this user has authority to use this system or not.
     * @param accountNumber Given an account number for verify
     * @param password  Given a password number for verify
     * @return Return true iff the provided account have authority to use this system. Otherwise, return false.
     */
    public boolean verifyAccIsAdmin(String accountNumber, String password){
        return managerVerifier.verifyAuthority(accountNumber, password);
    }

    // TODO: 这里如果账号创建失败要不要考虑去return一个报告，比如说是因为什么原因之类的
    /**
     *  This is method that can let admin create a new user.
     * @param accountNumber Given account number for new user
     * @param password Given password for new user.
     * @param name Given name for new user.
     * @param phone Given phone number for new user.
     * @param address Given address for new user.
     * @param status Given status for new user. i.e. Full-time, Part-time
     * @param department Given department for new user.
     * @param position Given position for new user.
     * @param wage Given  minimum wage for new user.
     * @param level Given authority level for new user.
     */
    public void createUser(String accountNumber, String password, String name, String phone, String address, String status,
                           String department, String position, int wage, int level){
        managerAccount.createEmployee(accountNumber, password, name, phone, address,status,department,position,wage,level);

    }
}
