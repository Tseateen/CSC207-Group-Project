package Uses_Cases;

import Entity.Employee;
import Entity.Userable;

import java.util.Map;
import java.util.Set;


public class Verifier {
    private Map<Userable, Employee> employeeList;

    public Verifier(Map<Userable, Employee> employeeList){
        this.employeeList = employeeList;

    }

    public boolean verifyAccountExist(String account, String password) {
        Set<Userable> keys = this.employeeList.keySet();
        for (Userable key : keys) {
            if (key.getAccount().equals(account) && key.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyAuthority(String account, String password) {
        Set<Userable> keys = employeeList.keySet();
        for (Userable key : keys) {
            if (key.getAccount().equals(account) && key.getPassword().equals(password)) {
                if (employeeList.get(key).getLevel() == 1) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean verifyisAdmin(String account, String password){
        return account.equals("1") && password.equals("0");

    }

}
