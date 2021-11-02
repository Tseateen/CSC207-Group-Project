package main.UsesCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.Entity.Employee;
import main.Entity.Userable;
import main.UsesCases.*;
public interface ReadWrite {

    public abstract void writeUserToFile(LoginList userList) throws IOException;
    public abstract void writeUserEmployeeToFile(EmployeeList employeeList) throws IOException;
    public abstract LoginList readUserFromFileTo(LoginList loginList) throws IOException, ClassNotFoundException;
    public abstract EmployeeList readEmployeeFromFileTo(EmployeeList employeeList) throws IOException, ClassNotFoundException;
}