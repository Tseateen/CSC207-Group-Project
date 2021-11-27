package main.UsesCases;

import main.Entity.Employee;
import main.Entity.User;
import main.Entity.Userable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Verifier implements IVerifier{

    public boolean userExists(String userID, ILoginList loginList) {
        return !(Objects.isNull(loginList.getUser(userID)));
    }
    /**
     * Verify if the authority level of an employee is lower than the specific authority level.
     *
     * @param level The specific authority level that the client want to check.
     * @return boolean represent if the specific authority level is higher than the employee's authority level.
     */
    @Override
    public boolean ValidToCreateThisLevel(String level, IEmployeeList employeeList, String userID) {
        if (level.length() != 1) {
            return false;
        }
        int LevelWantToCreate = Integer.parseInt(level);
        Employee employee = employeeList.getEmployee(userID);
        return LevelWantToCreate > employee.getLevel();
    }

    @Override
    public boolean validToDeleteThisUser(String targetUserID, IEmployeeList employeeList, String userID){
        Employee targetEmployee = employeeList.getEmployee(targetUserID);
        if (targetEmployee == null){
            return false;
        }else {
            Employee employee = employeeList.getEmployee(userID);
            return targetEmployee.getLevel() > employee.getLevel();
        }
    }

    /**
     * Check if the login is valid (if the username and password are correct).
     * @param userID the ID (username) of the account
     * @param password the password of the account
     * @return true iff the username and password of the user matched
     */
    @Override
    public boolean verifyForLogin(String userID, String password, ILoginList loginList) {
        for(Userable user: (LoginList) loginList){
            if(user.getID().equals(userID) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

}