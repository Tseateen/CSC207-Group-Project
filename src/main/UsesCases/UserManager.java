package main.UsesCases;

import main.Entity.Employee;
import main.Entity.PartTimeEmployee;
import main.Entity.Userable;

import java.util.ArrayList;

public class UserManager {

    /**
     * Get a list of employees that has a lower level than the target employee.
     *
     * @param level the string of level of the targeted employee.
     *
     * @return a list of employee who is lower level from the EmployeeList than the targeted employee with given id.
     *
     */
    public ArrayList<String> getLowerUsers(String level, ILoginList loginList, IEmployeeList employeeList) {
        ArrayList<String> users = new ArrayList<>();
        for (Employee e: (EmployeeList)employeeList) {
            StringBuilder user = new StringBuilder();
            if (e.getLevel() > Integer.parseInt(level)) {
                Userable u = loginList.getUser(e.getID());
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
