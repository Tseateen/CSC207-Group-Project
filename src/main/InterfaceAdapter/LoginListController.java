package main.InterfaceAdapter;

import main.UsesCases.*;

public class LoginListController {


    // === Instance Variables ===
    private final  ILoginList loginList;


    /**
     * Construct the LoginListController.
     */
    public LoginListController(ILoginList loginList){
        this.loginList = loginList;
    }


    /**
     * Add the new user to the LoginList.
     *
     * @param name the name of the new user.
     * @param password the password of the new user.
     * @param phone the phone number of the new user.
     * @param address the address of the new user.
     *
     * @return the string of ID of the new user.
     *
     */
    public String addUser(String name, String password, String phone, String address){
        return this.loginList.addUser( name, password,  phone, address);
    }


    // === Usage: FacadeSys Worker Case (vi) ====

    /**
     * Delete User from the list.
     *
     * @param ID the ID of the targeted User.
     *
     */
    public void deleteUser(String ID){
        this.loginList.deleteUser(ID);
    }
    // ==================================================
}
