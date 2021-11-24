package main.InterfaceAdapter;

import main.Entity.Userable;
import main.UsesCases.LoginList;

public class LoginListController {

    private final  LoginList loginList;

    public LoginListController(LoginList loginList){
        this.loginList = loginList;
    }

    public void addUser(String accountNumber, String password, String name, String phone, String address){
        this.loginList.addUser(accountNumber, password, name, phone, address);
    }

    public void deleteUser(String id){
        this.loginList.deleteUser(id);
    }
}
