package main.UsesCases;

import main.Entity.UserAble;

import java.io.Serializable;

public interface ILoginList extends IReadWrite, Iterative, Serializable {
    String addUser(String name, String password, String phone, String address);
    void deleteUser(String id);
    UserAble getUser(String user_id);
    int getSize();
    int getID();
}

