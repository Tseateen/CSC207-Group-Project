package main.UsesCases;
import main.Entity.*;

import java.io.Serializable;

public interface IEmployeeList extends IReadWrite, Initializable, Serializable {
    void addEmployee(String department, String wage, String position,
                                     String level, String status, String id);
    Employee getEmployee(String userID);
    void deleteEmployee(String id);
    int getSize();
}

