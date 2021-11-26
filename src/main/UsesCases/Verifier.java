package main.UsesCases;

import main.Entity.Employee;
import main.Entity.User;
import main.Entity.Userable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Verifier implements IVerifier, FindUserHelper{

    // === Instance Variables ===

    private final String username;
    private final ILoginList loginList;
    private final IEmployeeList employeeList;
    /**
     * Construct the Verifier.
     */
    public Verifier(String username, ILoginList loginList, IEmployeeList employeeList){
        this.username = username;
        this.loginList = loginList;
        this.employeeList = employeeList;
    }

    public boolean userExists(String userID) {
        return !(Objects.isNull(this.loginList.getUser(userID)));
    }
    /**
     * Verify if the authority level of an employee is lower than the specific authority level.
     *
     * @param level The specific authority level that the client want to check.
     * @return boolean represent if the specific authority level is higher than the employee's authority level.
     */
    @Override
    public boolean ValidToCreateThisLevel(String level) {
        if (level.length() != 1) {
            return false;
        }
        int LevelWantToCreate = Integer.parseInt(level);
        return LevelWantToCreate > Integer.parseInt(this.findUserHelper().getID());
    }

    @Override
    public boolean validToDeleteThisUser(String userID){
        return Integer.parseInt(findLevelHelper(userID)) > Integer.parseInt(this.findUserHelper().getID());
    }

    /**
     * Check if the login is valid (if the username and password are correct).
     * @param account the account (username) of the account
     * @param password the password of the account
     * @return true iff the username and password of the user matched
     */
    @Override
    public boolean verifyForLogin(String account, String password) {
        for(Userable user: (LoginList)this.loginList){
            if(user.getUsername().equals(account) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    // ==== helper functions ====
    /**
     * A helper function that find the correct user based on the username.
     *
     * @return a Userable that represent the target user.
     */
    @Override
    public Userable findUserHelper() {
        Userable correctUser = new User();
        for (Userable user : (LoginList) this.loginList) {
            if (user.getUsername().equals(this.username)) {
                return user;
            }
        }
        return correctUser;
    }

    /**
     * Get the user from the EmployeeList by his ID.
     *
     * @param userid the ID of the targeted employee.
     * @return the employee's level in strings.
     */
    public String findLevelHelper(String userid) {
        for (Employee employee :(EmployeeList) this.employeeList){
            if (employee.getID().equals(userid)){
                return String.valueOf(employee.getLevel());
            }
        }
        return null;
    }
}