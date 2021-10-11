package Uses_Cases;
import Entity.*;

import java.util.HashMap;
import java.util.Map;


public class AccountManager {
    private Map<Userable, Employee> employeeList;
    private int total_number = 0;
    private int totalEmployee = 0;
    private int totalPart_time = 0;
    private int totalFull_time = 0;

    public AccountManager(){
        this.employeeList = new HashMap<Userable, Employee>();
        createEmployee("1", "0", "Admin", "0", "", "F",
                "HR", "Admin", 0, 0);
    }

    public AccountManager(HashMap<Userable, Employee> list) {
        this.employeeList = list;
        this.totalEmployee = employeeList.size();
        this.totalFull_time = CountFulltime((HashMap)employeeList);
        this.totalPart_time = totalEmployee - totalFull_time;
        this.total_number = employeeList.size();
    }

    private int CountFulltime(HashMap<Userable, Employee> list) {
        int num = 0;
        for (Userable i : list.keySet()) {
            if (i instanceof FullTimeEmployee) { num++;}
        }
        return num;
    }

    public void createEmployee(String accountNumber, String password, String name, String phone, String address, String status,
                               String department, String position, int wage, int level){
        total_number++;
        Userable newUser = new User(accountNumber, password, name, phone, address, total_number);
        Employee newEmployee;
        if(status.equals("P")) {
            newEmployee = new PartTimeEmployee(department, wage, level);
            totalPart_time++;
            totalEmployee++;
            employeeList.put(newUser,newEmployee);
        }
        else if (status.equals("F")) {
            newEmployee = new FullTimeEmployee(department, position, wage, level);
            totalFull_time++;
            totalEmployee++;
            employeeList.put(newUser,newEmployee);
        }
    }

    public boolean deleteEmployee(int id) {
        for (Userable i : employeeList.keySet()) {
            if (((User)i).getID() == id) {
                Employee j = employeeList.remove(i);
                if (j instanceof PartTimeEmployee) {
                    totalPart_time--;
                } else {
                    totalFull_time--;
                }
                totalEmployee--;
                return true;
            }
        }
        return false;
    }

    public int getTotalEmployee () {return totalEmployee;}

    public int getTotalFull_time() {
        return totalFull_time;
    }

    public int getTotalPart_time() {
        return totalPart_time;
    }

    public Map<Userable, Employee> getEmployeeList(){
        return this.employeeList;
    }
}
