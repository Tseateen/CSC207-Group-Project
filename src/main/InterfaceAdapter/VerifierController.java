package main.InterfaceAdapter;

import main.UsesCases.IEmployeeList;
import main.UsesCases.IGroupList;
import main.UsesCases.ILoginList;
import main.UsesCases.IVerifier;
public class VerifierController {


    // === Instance Variables ===
    private final IVerifier verifier;


    /**
     * Construct the VerifierController.
     */
    public VerifierController(IVerifier verifier) {
        this.verifier = verifier;
    }


    // === Usage: FacadeSys Worker Case (iii), (vi) ====
    /**
     * Verify if the User existed.
     *
     * @param userID the ID of the targeted user.
     *
     * @return true iff the user is existed.
     */
    public boolean verifyUserExistence(String userID, ILoginList loginList) {
        if ((userID.matches(".*\\d.*"))) {
            return this.verifier.userExists(userID, loginList);
        } else {
            return false;
        }
    }
    // ==================================================


    // === Usage: FacadeSys Worker Case (vi) ====
    /**
     * Verify if the employee can be deleted.
     *
     * @param targetUserID The ID of the User the client want to delete.
     * @param userID the ID of the User.
     *
     * @return true iff the specific authority level is higher than the employee's authority level, which means that
     * the client has the authority to delete the user.
     */
    public boolean validToDelete(String targetUserID, IEmployeeList employeeList, String userID) {
        if (targetUserID.matches(".*\\d.*")) {
            return this.verifier.validToDeleteThisUser(targetUserID, employeeList, userID);
        } else {
            return false;
        }
    }
    // ==================================================


    // === Usage: FacadeSys Worker Case (v) ====
    /**
     * Verify if the authority level of an employee is lower than the specific authority level.
     *
     * @param level The specific authority level that the client want to check.
     * @param userID the ID of the User.
     *
     * @return boolean represent if the specific authority level is higher than the employee's authority level.
     */
    public boolean validToCreate(String level, IEmployeeList employeeList, String userID) {
        if (level.matches(".*\\d.*")) {
            return this.verifier.ValidToCreateThisLevel(level, employeeList, userID);
        } else {
            return false;
        }
    }
    // ==================================================



    // === Usage: FacadeSys Case (i) ====
    /**
     * Verify the employee is full-time or not.
     *
     * @param userID the ID of employee.
     * @param employeeList the list of employees.
     *
     * @return true iff the employee is a full-time employee.
     */
    public boolean verifyFullTime(String userID, IEmployeeList employeeList) {
        return this.verifier.verifierFullTime(userID, employeeList);
    }
    // ==================================================


    /**
     * Check if the login is valid (if the username and password are correct).
     *
     * @param userID the ID (username) of the account
     * @param password the password of the account
     *
     * @return true iff the username and password of the user matched
     */
    public boolean verifyLogin(String userID, String password, ILoginList loginList) {
        return this.verifier.verifyForLogin(userID, password, loginList);
    }


    /**
     * Verify the leader of the Work.
     *
     * @param userID the ID of the Employee.
     * @param workID the ID of the Work.
     *
     * @return true iff the Employee is the Work's leader.
     */
    public boolean verifyLeader(String userID, String workID, IGroupList groupList) {
        if (workID.matches(".*\\d.*")) {
            return this.verifier.verifierLeader(userID, workID, groupList);
        } else {
            return false;
        }
    }


    // === Usage: FacadeSys Worker Case (ii), (iii) ====
    /**
     * Verify the level of the User.
     *
     * @param userID the ID of the Employee.
     * @param level the authority level of the client.
     *
     * @return true iff the targeted Employee has a lower authority level than the client.
     */
    public boolean verifyLevel(String level, String userID, IEmployeeList employeeList){
        try {
            if (level.length() != 1) {return false;}
            int num_level = Integer.parseInt(level);
            return this.verifier.levelVerifier(num_level, userID, employeeList);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    // ==================================================

}
