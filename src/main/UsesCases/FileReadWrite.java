package main.UsesCases;

import main.Entity.Employee;
import main.Entity.Userable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileReadWrite implements ReadWrite{


    @Override
    public void writeUserEmployeeToFile(Map<Userable,Employee> employeeMap) throws IOException{
        OutputStream file = new FileOutputStream("/Data/UserEmployeeData.ser");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(employeeMap);
        output.close();
    }

    @Override
    public Map<Userable,Employee> readUserEmployeeFromFile() throws IOException, ClassNotFoundException{
        Map<Userable,Employee> employeeMap = new HashMap<Userable, Employee>();
        File file = new File("/Data/UserEmployeeData.ser");
        file.createNewFile();
        InputStream FIS = new FileInputStream(file);
        InputStream buffer = new BufferedInputStream(FIS);
        ObjectInput input = new ObjectInputStream(buffer);
        employeeMap = (Map<Userable, Employee>) input.readObject();
        input.close();
        return employeeMap;
    }



}
