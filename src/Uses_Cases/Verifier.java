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

    public boolean verifyAccountExist(int account, int password) {
        Set<Userable> keys = this.employeeList.keySet();
        for (Userable key : keys) {
            if (key.getAccount() == account && key.getPassword() == password) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyAuthority(int account, int password) {
        Set<Userable> keys = employeeList.keySet();
        for (Userable key : keys) {
            if (key.getAccount() == account && key.getPassword() == password) {
                if (employeeList.get(key).getLevel() == 1) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean verifyisAdmin(int account, int password){
        return account == 1 && password == 0;

    }

}
