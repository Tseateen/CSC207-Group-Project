package main.UsesCases;

import main.Entity.Userable;

public interface ILoginList {
    public void addUser(String accountNumber, String password, String name, String phone, String address);
    public boolean deleteUser(String id);
    public Userable getUser(String user_id);
    public int getSize();
    public int getID();
}
