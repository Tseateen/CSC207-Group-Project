package main.UsesCases;

import main.Entity.Userable;

import java.util.HashMap;
import java.util.Map;


public class Verifier {

    // === Instance Variables ===

    private final LoginList loginList;

    public Verifier(LoginList loginList){
        this.loginList = loginList;
    }

    public boolean verifyForLogin(String account, String password) {
        for(Userable user: this.loginList){
            if(user.getAccount().equals(account) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
