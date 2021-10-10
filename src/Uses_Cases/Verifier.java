package Uses_Cases;

import Entity.*;

import java.util.HashMap;
import java.util.Set;

public class Verifier {
    private HashMap<Userable, Employee> employeeList;

    public Verifier(){
        Userable init_admin = new User(1,0, "Admin", 0, "", 1);
        Employee init_employee = new FullTimeEmployee("HR", "Admin",0, 1);
        this.employeeList = new HashMap<>();
        employeeList.put(init_admin,init_employee);

    }

    public boolean verifyAccountExist(int account, int password) {
        Set<Userable> keys = employeeList.keySet();
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
        if (account == 1 && password == 0){
            return true;
        }return false;

    }

}
