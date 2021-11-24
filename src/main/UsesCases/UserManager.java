package main.UsesCases;

import main.Entity.Employee;
import main.Entity.PartTimeEmployee;
import main.Entity.Userable;

import java.util.ArrayList;

public class UserManager {
    private final LoginList loginList;
    private final EmployeeList employeeList;

    /**
     * Construct the UserManager, managing the information from other UsesCases.
     */
    public UserManager(LoginList loginList, EmployeeList employeeList) {
        this.loginList = loginList;
        this.employeeList = employeeList;
    }

    public ArrayList<String> getLowerUsers(String level) {
        ArrayList<String> users = new ArrayList<>();
        for (Employee e: this.employeeList) {
            StringBuilder user = new StringBuilder();
            if (e.getLevel() > Integer.parseInt(level)) {
                Userable u = this.loginList.getUser(e.getID());
                user.append(u.getName()).append(" ").append(u.getID())
                        .append(" ").append(e.getLevel()).append(" ").append(e.getDepartment());
                if (e instanceof PartTimeEmployee) {
                    user.append(" Part-Time");
                }
                users.add(user.toString());
            }
        }
        return users;
    }
}
