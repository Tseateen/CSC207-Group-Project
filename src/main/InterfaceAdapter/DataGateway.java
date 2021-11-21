package main.InterfaceAdapter;

import main.UsesCases.*;

import java.io.IOException;

public class DataGateway {

    // === DataFile ===
    private final FileReadWrite fileManager;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final GroupList groupList;
    private final WorkList workList;


    /**
     * Construct the DataGateway, managing the information from UsesCases.
     */
    public DataGateway(LoginList loginList, EmployeeList employeeList, GroupList groupList, WorkList workList){
        this.loginList = loginList;
        this.employeeList = employeeList;
        this.groupList = groupList;
        this.workList = workList;
        this.fileManager = new FileReadWrite();
    }


    // === System methods ===
    /**
     * Read the input file from interface to the UsesCase, LoginList.
     */
    public void ReadInputFileToLoginList(){
        try {
            this.fileManager.readUserFromFileTo(this.loginList);
        }catch (IOException e){
            this.loginList.initialize();
            System.out.println("The file has been initialize");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }

    }


    /**
     * Read the input file from interface to the UsesCase, EmployeeList.
     */
    public void ReadInputFileToEmployeeList(){
        try {
            this.fileManager.readEmployeeFromFileTo(this.employeeList);
        }catch (IOException e){
            this.employeeList.initialize();
            System.out.println("The file has been initialize");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }

    }


    /**
     * Read the input file from interface to the UsesCase, WorkList.
     */
    public void  ReadInputFileToWorkList(){
        try{
            this.fileManager.readWorkFromFileTo(this.workList);
        }catch(IOException e){
            System.out.println("No work data in the file so far");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }
    }


    /**
     * Read the input file from interface to the UsesCase, GroupList.
     */
    public void ReadInputFileToGroupList(){
        try{
            this.fileManager.readGroupFromFileTo(this.groupList);
        }catch(IOException e){
            System.out.println("No group data in the file so far");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }
    }


    /**
     * Write the output file from interface to the UsesCases.
     */
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