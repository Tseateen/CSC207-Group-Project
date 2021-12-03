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
    public boolean verifyUserExistence(String userID, ILoginList loginList) {
        if ((userID.matches(".*\\d.*"))) {
            return this.verifier.userExists(userID, loginList);
        } else {
            return false;
        }
    }
    // ==================================================


    // === Usage: FacadeSys Worker Case (vi) ====
    public boolean validToDelete(String targetUserID, IEmployeeList employeeList, String userID) {
        if (targetUserID.matches(".*\\d.*")) {
            return this.verifier.validToDeleteThisUser(targetUserID, employeeList, userID);
        } else {
            return false;
        }
    }
    // ==================================================

    // === Usage: FacadeSys Worker Case (v) ====
    public boolean validToCreate(String level, IEmployeeList employeeList, String userID) {
        if (level.matches(".*\\d.*")) {
            return this.verifier.ValidToCreateThisLevel(level, employeeList, userID);
        } else {
            return false;
        }
    }
    // ==================================================



    // === Usage: FacadeSys Case (i) ====
    public boolean verifyFullTime(String userID, IEmployeeList employeeList) {
        return this.verifier.verifierFullTime(userID, employeeList);
    }
    // ==================================================

    public boolean verifyLogin(String userID, String password, ILoginList loginList) {
        return this.verifier.verifyForLogin(userID, password, loginList);
    }

    public boolean verifyLeader(String userID, String workID, IGroupList groupList) {
        if (workID.matches(".*\\d.*")) {
            return this.verifier.verifierLeader(userID, workID, groupList);
        } else {
            return false;
        }
    }

    // === Usage: FacadeSys Worker Case (ii), (iii) ====
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
