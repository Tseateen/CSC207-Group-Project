package main.InterfaceAdapter;

import main.UsesCases.*;

import java.io.IOException;

public class DataGateway {

    private final FileReadWrite fileManager;
    private final LoginList loginList;
    private final EmployeeList employeeList;

    public DataGateway(LoginList loginList, EmployeeList employeeList){
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.fileManager = new FileReadWrite();
    }

    public void ReadInputFileToLoginList(){
        // TODO: 寫入 AccountList
        // TODO: Double check the error
        try {
            System.out.println("Reading Employee File");
            this.fileManager.readUserFromFileTo(loginList);
        }catch (IOException e){
            System.out.println("No such as file");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }

    }

    public void ReadInputFileToEmployeeList(){
        // TODO: 寫入 EmployeeList
        try {
            System.out.println("Reading Employee File");
            this.fileManager.readEmployeeFromFileTo(employeeList);
        }catch (IOException e){
            System.out.println("No such as file");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }

    }

    public void WriteOutputFile(){
        System.out.println("Done for writing");
    }
}
