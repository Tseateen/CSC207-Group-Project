package Entity;


public class User implements Userable{

    // === Instance Variables ===

    // The unique id for this user.
    private final String id;
    // The account number for this user.
    private String account;
    // The password for this user.
    private String password;
    // The name of this user.
    private String name;
    // The phone number of this user.
    private String phone;
    // The living address of this user.
    private String address;
    //TODO: Delete vacation instance variable.

    /* === Representation Invariants ===
     * id should be unique, and it cannot be modified after initial
     */

    /**
     * Construct a User, giving them the given account number, password, name, phone number, address and unique id.
     * @param accountNumber Account number for this user.
     * @param password Password for this user.
     * @param name Name for this user.
     * @param phone Phone number for this user.
     * @param address Address for this user.
     * @param id Unique identity for this user.
     */
    public User(String accountNumber,String password, String name, String phone, String address, String id){
        this.id = id;
        this.account = accountNumber;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
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
     * @return This method will return the account number for this user.
     */
    @Override
    public String getAccount() {
        return this.account;
    }

    /**
     *
     * @param account The new account number for this user
     */
    @Override
    public void setAccount(String account) {
        this.account = account;
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

    /**
     *
     * @return This method will return the string representation of this user. For instance, My identity is 1, and my
     * name is Andy.
     */
    @Override
    public String toString(){
        String identity = String.valueOf(this.id);
        return "My identity is " + identity + ", and my name is " + this.name;
    }
}
