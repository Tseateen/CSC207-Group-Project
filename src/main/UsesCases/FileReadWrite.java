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
    public void writeUserEmployeeToFile(AccountManager am) throws IOException{
        OutputStream file = new FileOutputStream("/Data/UserEmployeeData");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(am);
        output.close();
    }
    @Override
    public LoginList readUserFromFile() throws IOException, ClassNotFoundException{
        InputStream file = new FileInputStream("/Data/UserAccountData");
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        LoginList userList = (LoginList) input.readObject();
        input.close();
        return userList;
    }

    @Override
    public AccountManager readUserEmployeeFromFile() throws IOException, ClassNotFoundException{
        InputStream file = new FileInputStream("/Data/UserEmployeeData");
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        AccountManager userList = (AccountManager) input.readObject();
        input.close();
        return userList;
    }



}
