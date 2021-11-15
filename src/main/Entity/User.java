package main.Entity;


import java.io.Serializable;

public class User implements Userable, Serializable {

    // === Instance Variables ===

    // The unique id for this user.
    private final String id;
    // The username number for this user.
    private String username;
    // The password for this user.
    private String password;
    // The name of this user.
    private String name;
    // The phone number of this user.
    private String phone;
    // The living address of this user.
    private String address;

    /* === Representation Invariants ===
     * id should be unique, and it cannot be modified after initial
     */

    /**
     * Construct a User, giving them the given username number, password, name, phone number, address and unique id.
     * @param usernameNumber username number for this user.
     * @param password Password for this user.
     * @param name Name for this user.
     * @param phone Phone number for this user.
     * @param address Address for this user.
     * @param id Unique identity for this user.
     */
    public User(String usernameNumber,String password, String name, String phone, String address, String id){
        this.id = id;
        this.username = usernameNumber;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public User(){
        this.id = "";
        this.username = "";
        this.password = "";
        this.name = "";
        this.phone = "";
        this.address = ";";
    }
    // === Regular methods ===

    /**
     *
     * @return This method will return the name of this user.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name The new name for this user.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return This method will return the unique identity of this user.
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     *
     * @return This method will return the username number for this user.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @param username The new username number for this user
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return This method will return the password of this user.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param password The new password for this user.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param password The password that we want to compare
     * @return This method will return if the input password matches the password of the user.
     */
    @Override
    public boolean comparePassword(String password){
        return this.password.equals(password);
    }

    /**
     *
     * @return This method will return the phone number of this user.
     */
    @Override
    public String getPhone() {
        return this.phone;
    }

    /**
     *
     * @param phone The new phone number for this user.
     */
    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return This method will return the living address of this user.
     */
    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     *
     * @param address The new living address of this user.
     */
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @ Citation: The following code is based on the Java Note(Chapter 2 Monster.java) example on GitHub (URL: https://github.com/CSC207-UofT/207-course-notes/blob/master/code/Monster.java)
     * @param obj The object needs to compare with this user.
     * @return This method will return true iff the obj have the same Class type and id as this user.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)){
            return false;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        User other = (User) obj;

        return (this.id.equals(other.id));
    }

}