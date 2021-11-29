package main.UsesCases;

public interface IVerifier {
    boolean userExists(String userID, ILoginList loginList);
    boolean ValidToCreateThisLevel(String level, IEmployeeList employeeList, String userID);
    boolean validToDeleteThisUser(String targetUserID, IEmployeeList employeeList, String userID);
    boolean verifyForLogin(String account, String password, ILoginList loginList);
    boolean verifierLeader(String userID, String workID, IGroupList groupList);
    boolean levelVerifier(int level, String userID, IEmployeeList employeeList);
    boolean verifierFullTime(String userID, IEmployeeList employeeList);
}
