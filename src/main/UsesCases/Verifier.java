package main.UsesCases;

import main.Entity.Userable;

import java.util.HashMap;
import java.util.Map;


public class Verifier {

    // === Instance Variables ===

    // TODO: Should we make employeeMap and managerAccount as final variable?
    // This is the employee map. Key = Userable object. Item = Employee Object
    private Map<String, Userable> employeeMap;
    // This is the variable store the AccountManager type Object.
    private AccountManager managerAccount;

    /**
     * Construct a Verifier, giving them the given mangerAccount object.
     * @param managerAccount Given the AccountManger object to manger account.
     */
    public Verifier(AccountManager managerAccount){
        this.managerAccount = managerAccount;
        employeeMap =new HashMap<String, Userable>();
        for (Userable i : managerAccount.getEmployeeMap().keySet()) {
            employeeMap.put(i.getAccount(), i);
        }
    }

    // TODO: I don't think AddAccount should put in this uses cases.
    public void AddAccount(Userable user) {
        employeeMap.put(user.getAccount(), user);
    }

    // TODO: I don't think RemoveAccount should put in this uses cases.
    public void RmAccount(Userable user) {
        employeeMap.remove(user.getAccount());
    }
    /**
     * @param account Given the account number.
     * @param password Given the password.
     * @return true iff given the correct account number and password. Otherwise, return false.
     */
    public boolean verifyForLogin(String account, String password) {
        if (employeeMap.containsKey(account)) {
            return employeeMap.get(account).getPassword().equals(password);
        }
        return false;
    }

    /**
     *
     * @param account Given the account number.
     * @return true iff account number already exists.
     */
    public boolean verifyExist(String account) {
        return employeeMap.containsKey(account);
    }
    /**
     *
     * @param account Given a user account number.
     * @param password Given a user password number.
     * @return true iff user have enough authority level to modified other user account. Otherwise, return false.
     */
    public boolean verifyAuthority(String account, String password) {
        if (this.verifyForLogin(account,password)) {
            return managerAccount.getEmployeeMap().get(employeeMap.get(account)).getLevel() < 1;
        }
        return false;
    }

    /**
     *
     * @return a string of representation. For instance, "This is a verifier."
     */
    @Override
    public String toString(){
        return "This is a verifier.";
    }
}
