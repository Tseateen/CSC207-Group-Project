package main.InterfaceAdapter;

import main.UsesCases.*;

public class LoginListController {

    private final  ILoginList loginList;

    public LoginListController(ILoginList loginList){
        this.loginList = loginList;
    }

    public void addUser(String name,  String password, String phone, String address){
        this.loginList.addUser( name, password,  phone, address);
    }

    public void deleteUser(String id){
        this.loginList.deleteUser(id);
    }
}
