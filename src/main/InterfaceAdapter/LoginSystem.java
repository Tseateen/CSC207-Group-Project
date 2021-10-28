package main.InterfaceAdapter;

import main.UsesCases.*;
public class LoginSystem {

    private final Loginable loginable;
    public LoginSystem(){
        this.loginable = new LoginManager();
    }

    public boolean runLogin(String username, String password){
        return this.loginable.logIn(username,password);

    }


}
