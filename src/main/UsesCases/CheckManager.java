package main.UsesCases;

import java.util.Objects;

public class CheckManager implements ICheckManager{
    private final LoginList loginList;
    private final EmployeeList employeeList;


    public CheckManager(LoginList loginList, EmployeeList employeeList) {
        this.loginList = loginList;
        this.employeeList = employeeList;

    }


    @Override
    public boolean userExists(String userID) {
        return !(Objects.isNull(this.loginList.getUser(userID)));
    }
}
