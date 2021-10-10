package Uses_Cases;

import Entity.Employee;
import Entity.User;

import java.util.HashMap;
import java.util.Set;

public class Employee_Manager {
    HashMap<User, Employee> employeeList;

    public boolean verifyAccountExist(int account, int password) {
        Set<User> keys = employeeList.keySet();
        for (User key : keys) {
            if (key.getAccount() == account && key.getPassword() == password) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyAuthority(int account, int password) {
        Set<User> keys = employeeList.keySet();
        for (User key : keys) {
            if (key.getAccount() == account && key.getPassword() == password) {
                if (employeeList.get(key).getLevel() == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}


