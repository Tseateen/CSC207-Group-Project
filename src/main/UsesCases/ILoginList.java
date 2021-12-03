package main.UsesCases;

import main.Entity.Userable;

import java.io.Serializable;

public interface ILoginList extends IReadWrite,Initializable, Serializable {
    String addUser(String name, String password, String phone, String address);
    void deleteUser(String id);
    Userable getUser(String user_id);
    int getSize();
    int getID();
}

