package main.InterfaceAdapter;
import main.UsesCases.*;
public class VerifierController {

    private IVerifier verifier;

    public VerifierController(IVerifier verifier){
        this.verifier = verifier;
    }

    public boolean verifyUserExistence(String userID, ILoginList loginList){
        if ((userID.matches(".*\\d.*"))){
            return this.verifier.userExists(userID, loginList);
        }else{
            return false;
        }

    }

    public boolean validToCreate(String level, IEmployeeList employeeList, String userID){
        if (level.matches(".*\\d.*")) {
            return this.verifier.ValidToCreateThisLevel(level, employeeList, userID);
        }else{
            return false;
        }
    }

    public boolean validToDelete(String targetUserID, IEmployeeList employeeList, String userID){
        if (targetUserID.matches(".*\\d.*")) {
            return this.verifier.validToDeleteThisUser(targetUserID, employeeList, userID);
        }else{
            return false;
        }
    }

    public boolean verifyLogin(String username, String password, ILoginList loginList){
        return this.verifier.verifyForLogin(username, password, loginList);
    }
}
