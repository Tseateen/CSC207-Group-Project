package main.UsesCases;

import main.Entity.Employee;
import main.Entity.Userable;

import java.io.*;
import java.util.HashMap;

public class FileReadWrite implements ReadWrite{

    @Override
    public void writeUserToFile(LoginList userList) throws IOException{
        OutputStream file = new FileOutputStream("/Data/UserAccountData");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(userList);
        output.close();
    }

    @Override
    public void writeUserEmployeeToFile(EmployeeList employeeList) throws IOException{
        OutputStream file = new FileOutputStream("/Data/UserEmployeeData");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(employeeList);
        output.close();
    }
    @Override
    public LoginList readUserFromFileTo(LoginList loginList) throws IOException, ClassNotFoundException{
        InputStream file = new FileInputStream("/Data/UserAccountData");
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        LoginList UserFile = (LoginList) input.readObject();
        input.close();
        for(Userable user: UserFile){
            loginList.readInput(user);
        }
        return UserFile;
    }

    @Override
    public EmployeeList readEmployeeFromFileTo(EmployeeList employeeList) throws IOException, ClassNotFoundException{
        InputStream file = new FileInputStream("/Data/UserEmployeeData");
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        EmployeeList EmployeeFile = (EmployeeList) input.readObject();
        input.close();
        for(Employee employee: EmployeeFile){
            employeeList.readInput(employee);
        }
        return employeeList;
    }



}