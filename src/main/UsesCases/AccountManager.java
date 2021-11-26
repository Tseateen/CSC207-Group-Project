package main.UsesCases;

import main.Entity.*;
import java.util.*;

public class AccountManager implements IAccountManager{
    private final LoginList loginList;
    private final EmployeeList employeeList;


    public AccountManager(LoginList loginList, EmployeeList employeeList) {
        this.loginList = loginList;
        this.employeeList = employeeList;

    }

    public String getUserID(String accountNo) {
        for (Userable u: this.loginList) {
            if (u.getUsername().equals(accountNo)) {
                return u.getID();
            }
        }
        return null;
    }

    public boolean userExists(String userID) {
        return !(Objects.isNull(this.loginList.getUser(userID)));
    }

    public String userLevel(String userID) {
        return String.valueOf(this.employeeList.getEmployee(userID).getLevel());
    }

    public ArrayList<String> infoCheck(String userID) {
        ArrayList<String> info = new ArrayList<>();
        Userable user = this.loginList.getUser(userID);
        Employee emp = this.employeeList.getEmployee(userID);
        info.add(user.getName());
        info.add(user.getUsername());
        info.add(user.getID());
        info.add(user.getPhone());
        info.add(user.getAddress());
        info.add(String.valueOf(emp.getWage()));
        info.add(String.valueOf(emp.getLevel()));
        info.add(emp.getDepartment());
        if (emp instanceof PartTimeEmployee) {
            info.add("Part-Time");
        } else {
            info.add(((FullTimeEmployee)emp).getPosition());
            info.add(((FullTimeEmployee)emp).getState());
        }
        return info;
    }

    public ArrayList<String> vacationInfo(String userID) {
        ArrayList<String> vac = new ArrayList<>();
        Employee e = this.employeeList.getEmployee(userID);
        if (e instanceof PartTimeEmployee) {
            return vac;
        }
        vac.add(String.valueOf(((FullTimeEmployee)e).getVacationUsed()));
        vac.add(String.valueOf(((FullTimeEmployee)e).getTotalVacationWithSalary()));
        return vac;
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

    public boolean createNewAccount(String[] userinfo) {
        try {
            this.loginList.addUser(userinfo[0], userinfo[1], userinfo[2], userinfo[3], userinfo[4]);
            this.employeeList.addEmployee(userinfo[5], Integer.parseInt(userinfo[6]), userinfo[7], Integer.parseInt(userinfo[8]), userinfo[9]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteEmployee(String userID) {
        return this.employeeList.deleteEmployee(userID) && this.loginList.deleteUser(userID);
    }

    public boolean setUserInfo(String userID, String opt, String changeTo) {
        try {
            Userable user = this.loginList.getUser(userID);
            switch (opt) {
                case "1":
                    user.setName(changeTo);
                    break;
                case "2":
                    user.setPassword(changeTo);
                    break;
                case "3":
                    user.setAddress(changeTo);
                    break;
                case "4":
                    user.setPhone(changeTo);
                    break;
                default:
                    return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setEmployeeInfo(String userID, String opt, String changeTo) {
        if (this.setUserInfo(userID, opt, changeTo)) {
            return true;
        }
        try{
            Employee emp = this.employeeList.getEmployee(userID);
            switch (opt) {
                case "5":
                    emp.setDepartment(changeTo);
                    break;
                case "6": //Todo: if hr use 6, need to compare their level
                    emp.setLevel(Integer.parseInt(changeTo));
                    break;
                case "7":
                    emp.setWage(Integer.parseInt(changeTo));
                    break;
                default:
                    return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}