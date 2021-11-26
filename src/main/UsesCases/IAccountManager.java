package main.UsesCases;

import java.util.ArrayList;

public interface IAccountManager {
    public boolean createNewAccount(String[] userinfo);
    public boolean deleteEmployee(String userID);
    public String getUserID(String username);
    public boolean userExists(String userID);
    public String userLevel(String userID);
    public ArrayList<String> infoCheck(String userID);
    public ArrayList<String> vacationInfo(String userID);
    public ArrayList<String> getLowerUsers(String level);
    public boolean setUserInfo(String userID, String opt, String changeTo);
    public boolean setEmployeeInfo(String userID, String opt, String changeTo);

}
