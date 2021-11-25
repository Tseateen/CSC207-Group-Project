package main.UsesCases;

import java.util.Objects;

public class CheckManager {
    private final LoginList loginList;
    private final EmployeeList employeeList;


    public CheckManager(LoginList loginList, EmployeeList employeeList) {
        this.loginList = loginList;
        this.employeeList = employeeList;

    }

    public boolean userExists(String userID) {
        return !(Objects.isNull(this.loginList.getUser(userID)));
    }
}
