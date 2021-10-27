package main.UsesCases;

import main.Entity.*;
import java.util.*;
public class LoginList {
    private final Map<String, Userable> users = new HashMap<>();

    public void addUser(Userable user){
        users.put(user.getAccount(), user);
    }

    public Userable getUser(String username){
        return users.get(username);
    }
}
