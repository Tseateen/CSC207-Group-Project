package main.UsesCases;

public interface AccountInfoInterface {
    public abstract String getNameFromUser();
    public abstract String getIDFromUser();
    public abstract String getUsernameFromUser();
    public abstract String getPasswordFromUser();
    public abstract String getPhoneFromUser();
    public abstract String getAddressFromUser();
    public abstract String getIDFromEmployee();
    public abstract String getDepartmentFromEmployee();
    public int getWageFromEmployee();
    public int getAttendenceFromEmployee();
    public int getLevelFromEmployee();
    public void setNameForUser(String name);
    public void setUsernameForUser(String username);
    public void setPasswordForUser(String password);
    public void setPhoneForUser(String phone);
    public void setAddressForUser(String address);
    public void setAttendenceForEmployee();
    public void setWageForEmployee(int wage);
    public void setDepartmentForEmployee(String department);
    public void setLevelForEmployee(int level);
}
