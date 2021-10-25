package main.Entity;

public interface Userable {
    /**
     * An interface of User class, for reducing dependency purpose.
     *
     */

    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getID();
    public abstract String getAccount();
    public abstract void setAccount(String account);
    public abstract String getPassword();
    public abstract void setPassword(String password);
    public abstract String getPhone();
    public abstract void setPhone(String phone);
    public abstract String getAddress();
    public abstract void setAddress(String address);
    public abstract boolean equals(Object other);
}
