package main.UsesCases;

public interface IAccountManager {
    public boolean createNewAccount(String[] userinfo);
    public boolean deleteEmployee(String userID);
}
