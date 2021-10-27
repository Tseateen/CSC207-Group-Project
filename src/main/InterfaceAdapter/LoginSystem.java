package main.InterfaceAdapter;

import main.UsesCases.*;
public class LoginSystem {

    private final Loginable loginable;
    public LoginSystem(Loginable loginable){
        this.loginable = loginable;
    }

    public String runLogin(String username, String password){
        boolean match = loginable.logIn(username,password);
        if (match){
            return "Log in success";
        }else{
            return "Log in failure";
        }
    }


}
