package main.InterfaceAdapter;
import main.UsesCases.*;
public class VerifierController {

    private IVerifier verifier;

    public VerifierController(IVerifier verifier){
        this.verifier = verifier;
    }

    public boolean verifyUserExistence(String userID){
        if ((userID.matches(".*\\d.*"))){
            return this.verifier.userExists(userID);
        }else{
            return false;
        }

    }

    public boolean validToCreate(String level){
        if (level.matches(".*\\d.*")) {
            return this.verifier.ValidToCreateThisLevel(level);
        }else{
            return false;
        }
    }

    public boolean validToDelete(String userID){
        if (userID.matches(".*\\d.*")) {
            return this.verifier.validToDeleteThisUser(userID);
        }else{
            return false;
        }
    }

    public boolean verifyLogin(String username, String password){
        return this.verifier.verifyForLogin(username, password);
    }
}
