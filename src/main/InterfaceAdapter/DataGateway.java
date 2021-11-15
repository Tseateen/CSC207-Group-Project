package main.InterfaceAdapter;

import main.UsesCases.*;

import java.io.IOException;

public class DataGateway {

    private final FileReadWrite fileManager;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final GroupList groupList;
    private final WorkList workList;
    public DataGateway(LoginList loginList, EmployeeList employeeList, GroupList groupList, WorkList workList){
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.groupList = groupList;
        this.workList = workList;
        this.fileManager = new FileReadWrite();
    }

    public void ReadInputFileToLoginList(){
        // TODO: 寫入 AccountList
        // TODO: Double check the error
        try {
            System.out.println("Reading User File");
            this.fileManager.readUserFromFileTo(this.loginList);
        }catch (IOException e){
            this.loginList.initialize();
            System.out.println("The file has been initialize");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }

    }

    public void ReadInputFileToEmployeeList(){
        // TODO: 寫入 EmployeeList
        try {
            System.out.println("Reading Employee File");
            this.fileManager.readEmployeeFromFileTo(this.employeeList);
        }catch (IOException e){
            this.employeeList.initialize();
            System.out.println("The file has been initialize");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }

    }

    public void  ReadInputFileToWorkList(){
        try{
            System.out.println("Reading Work File");
            this.fileManager.readWorkFromFileTo(this.workList);
        }catch(IOException e){
            System.out.println("No work data in the file so far");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }
    }

    public void ReadInputFileToGroupList(){
        try{
            System.out.println("Reading Group File");
            this.fileManager.readGroupFromFileTo(this.groupList);
        }catch(IOException e){
            System.out.println("No group data in the file so far");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }
    }

    public void WriteOutputFile(){
        try {
            this.fileManager.writeUserToFile(this.loginList);
            this.fileManager.writeUserEmployeeToFile(this.employeeList);
            this.fileManager.writeWorkToFile(this.workList);
            this.fileManager.writeGroupToFile(this.groupList);
        }catch (IOException e){
            System.out.println("The file has been initialize");
        }
    }
}