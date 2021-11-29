package main.Entity;

import java.io.Serializable;

public interface Userable extends Serializable {
    /**
     * An interface of User class, for reducing dependency purpose.
     *
     */

    String getName();
    void setName(String name);
    String getID();
    String getPassword();
    void setPassword(String password);
    boolean comparePassword(String password);
    String getPhone();
    void setPhone(String phone);
    String getAddress();
    void setAddress(String address);
    boolean equals(Object other);
}