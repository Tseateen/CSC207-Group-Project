package main.UsesCases;

import main.Entity.Userable;

import java.util.HashMap;
import java.util.Map;


public class Verifier {

    // === Instance Variables ===

    private final LoginList loginList;

    /**
     * Construct the Verifier.
     */
    public Verifier(LoginList loginList){
        this.loginList = loginList;
    }


    /**
     * Check if the login is valid (if the username and password are correct).
     * @param account the account (username) of the account
     * @param password the password of the account
     * @return true iff the username and password of the user matched
     */
    public boolean verifyForLogin(String account, String password) {
        for(Userable user: this.loginList){
            if(user.getUsername().equals(account) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}