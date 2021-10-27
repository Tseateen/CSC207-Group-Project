package main.UsesCases;

import main.Entity.*;
import java.util.*;
public class LoginManager implements Loginable{
    private final LoginList users;

    public LoginManager(LoginList users){
        this.users = users;
    }

    public boolean logIn(String username, String password){
        Userable user = this.users.getUser(username);
        return user.comparePassword(password);
    }
}
