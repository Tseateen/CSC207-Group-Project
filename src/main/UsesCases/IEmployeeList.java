package main.UsesCases;
import main.Entity.*;
public interface IEmployeeList extends IReadWrite, Initializable{
    void addEmployee(String department, String wage, String position,
                                     String level, String status, String id);
    Employee getEmployee(String userID);
    boolean deleteEmployee(String id);
    int getSize();
}

