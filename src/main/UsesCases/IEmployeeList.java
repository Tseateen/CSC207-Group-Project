package main.UsesCases;

public interface IEmployeeList {
    public void addEmployee(String department, int wage, String position,  int level, String status);
    public boolean deleteEmployee(String id);
}
