package main.Entity;

import java.io.Serializable;

public interface Userable extends Serializable {
    /**
     * An interface of User class, for reducing dependency purpose.
     *
     */

    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getID();
    public abstract String getUsername();
    public abstract void setUsername(String account);
    public abstract String getPassword();
    public abstract void setPassword(String password);
    public abstract boolean comparePassword(String password);
    public abstract String getPhone();
    public abstract void setPhone(String phone);
    public abstract String getAddress();
    public abstract void setAddress(String address);
    public abstract boolean equals(Object other);
}
