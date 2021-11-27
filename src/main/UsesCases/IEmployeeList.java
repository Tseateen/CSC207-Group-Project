package main.UsesCases;
import main.Entity.*;
public interface IEmployeeList {
    public abstract void addEmployee(String department, int wage, String position,  int level, String status,String name);
    public abstract Employee getEmployee(String userID);
    public abstract boolean deleteEmployee(String id);
    public abstract int getSize();
    public void initialize();
    public abstract void readInput(Employee employee);
    public abstract int getID();
    public abstract void readID(int ID);
}

