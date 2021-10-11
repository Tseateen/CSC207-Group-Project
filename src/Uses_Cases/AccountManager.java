package Uses_Cases;
import Entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class AccountManager {
    /**
     * 用于管理所有用户账号的工具，主要用于生成新员工和删除员工
     * 同时也会统计各种员工的数量
     *
     * @param total_number 公司一共有过多少用，包括删除的员工，用于辅助生成id
     */
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
        Userable newUser = new User(accountNumber, password, name, phone, address, String.valueOf(total_number));
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

    public boolean deleteEmployee(String id) {
        for (Userable i : employeeList.keySet()) {
            if (Objects.equals(((User) i).getID(), id)) {
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
