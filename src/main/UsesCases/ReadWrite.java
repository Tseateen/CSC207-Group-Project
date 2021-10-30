package main.UsesCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.Entity.Employee;
import main.Entity.Userable;
import main.UsesCases.*;
public interface ReadWrite {

    public abstract void writeUserToFile(LoginList userList) throws IOException;
    public abstract void writeUserEmployeeToFile(AccountManager am) throws IOException;
    public abstract LoginList readUserFromFile() throws IOException, ClassNotFoundException;
    public abstract AccountManager readUserEmployeeFromFile() throws IOException, ClassNotFoundException;
}
