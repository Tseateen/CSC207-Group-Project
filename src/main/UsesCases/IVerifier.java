package main.UsesCases;

import java.util.Objects;

public interface IVerifier {
    public boolean userExists(String userID, ILoginList loginList);
    public boolean ValidToCreateThisLevel(String level, IEmployeeList employeeList, String userID);
    public boolean validToDeleteThisUser(String targetUserID, IEmployeeList employeeList, String userID);
    public abstract  boolean verifyForLogin(String account, String password, ILoginList loginList);
    public boolean verifierLeader(String userID, String workID, IGroupList groupList);
    public boolean levelVerifier(int level, String userID, IEmployeeList employeeList);
    public boolean verifierFullTime(String userID, IEmployeeList employeeList);
}
