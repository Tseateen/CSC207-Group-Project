package main.UsesCases;
import main.Entity.*;
public interface IEmployeeList {
    public abstract void addEmployee(String department, String wage, String position,
                                     String level, String status, String id);
    public abstract Employee getEmployee(String userID);
    public abstract boolean deleteEmployee(String id);
    public abstract int getSize();
    public void initialize();
    public abstract void readInput(Employee employee);
}

