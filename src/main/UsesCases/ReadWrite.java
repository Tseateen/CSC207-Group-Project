package main.UsesCases;

import main.Entity.Employee;
import main.Entity.Userable;

import java.io.IOException;
import java.util.Map;

public interface ReadWrite {

    public abstract void writeUserEmployeeToFile(Map<Userable,Employee> employeeMap) throws IOException;
    public abstract Map<Userable, Employee> readUserEmployeeFromFile() throws IOException, ClassNotFoundException;
}
