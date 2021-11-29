package main.UsesCases;

import main.Entity.Userable;

public interface ILoginList extends ReadWrite{
    public abstract String addUser(String name, String password, String phone, String address);
    public abstract boolean deleteUser(String id);
    public abstract Userable getUser(String user_id);
    public abstract int getSize();
    public abstract int getID();
    public abstract void initialize();
    public void readInput(Userable user);
    public void readID(int ID);
}

