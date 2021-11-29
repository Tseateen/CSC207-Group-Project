package main.UsesCases;

import main.Entity.Userable;

public interface ILoginList extends IReadWrite,Initializable{
    String addUser(String name, String password, String phone, String address);
    boolean deleteUser(String id);
    Userable getUser(String user_id);
    int getSize();
    int getID();
}

