package Uses_Cases;

import Entity.*;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<Userable, Employee> employeeList;
    private int totalEmployee = 0;
    private int totalPart_time = 0;
    private int totalFull_time = 0;

    public AccountManager(HashMap<Userable, Employee> list) {
        employeeList = list;
        totalEmployee = employeeList.size();
        totalFull_time = CountFulltime((HashMap)employeeList);
        totalPart_time = totalEmployee - totalFull_time;
    }

    public AccountManager(){
        employeeList = new HashMap<Userable, Employee>();
    }

    private int CountFulltime(HashMap<Userable, Employee> list) {
        int num = 0;
        for (Userable i : list.keySet()) {
            if (i instanceof FullTimeEmployee) { num++;}
        }
        return num;
    }

    public boolean createEmployee(int accountNumber, int password, String name, int phone, String address, String status,
                               String department, String position, int wage, int level, int id){
        Userable newUser = new User(accountNumber, password, name, phone, address, id);
        Employee newEmployee;
        if(status.equals("P")) {
            newEmployee = new PartTimeEmployee(department, position, wage, level);
            totalPart_time++;
            totalEmployee++;
        }
        else if (status.equals("F")) {
            newEmployee = new FullTimeEmployee(department, position, wage, level);
            totalFull_time++;
            totalEmployee++;
        }
        else {
            return false;
        }
        employeeList.put(newUser,newEmployee);
        return true;
    }

    public boolean deleteEmployee(int id) {
        for (Userable i : employeeList.keySet()) {
            if (((User)i).getID() == id) {
                employeeList.remove(i);
                return true;
            }
        }
        return false;
    }

}
