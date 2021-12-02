package main.InterfaceAdapter;

import main.UsesCases.*;

public class LoginListController {

    private final  ILoginList loginList;

    public LoginListController(ILoginList loginList){
        this.loginList = loginList;
    }

    public String addUser(String name, String password, String phone, String address){
        return this.loginList.addUser( name, password,  phone, address);
    }


    // === Usage: FacadeSys Worker Case (vi) ====
    public void deleteUser(String id){
        this.loginList.deleteUser(id);
    }
    // ==================================================
}
