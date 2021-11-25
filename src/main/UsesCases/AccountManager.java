package main.UsesCases;

public class AccountManager {

    private final String username;
    private final LoginList loginList;
    private final EmployeeList employeeList;

    /**
     * Construct the AccountManager, managing the information from other UsesCases.
     */
    public AccountManager(LoginList loginList, EmployeeList employeeList, String username) {
        this.username = username;
        this.loginList = loginList;
        this.employeeList = employeeList;
    }


    public boolean createNewAccount(String[] userinfo) {
        try {
            this.loginList.addUser(userinfo[0], userinfo[1], userinfo[2], userinfo[3], userinfo[4]);
            this.employeeList.addEmployee(userinfo[5], Integer.parseInt(userinfo[6]), userinfo[7], Integer.parseInt(userinfo[8]), userinfo[9]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean deleteEmployee(String userID) {
        return this.employeeList.deleteEmployee(userID) && this.loginList.deleteUser(userID);
    }


}
