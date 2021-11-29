package main.InterfaceAdapter;

import main.UsesCases.*;

import java.io.IOException;

public class DataGateway {


    /**
     * Construct the DataGateway, managing the information from UsesCases.
     */
    public DataGateway() {

    }


    // === System methods ===

    // === Read Data From File ===
    /**
     * Read the input file from interface to the UsesCase, LoginList.
     */
    public void ReadInputFileToLoginList(ILoginList loginList){
        try {
            // Note this is ReadWrite LoginList
            loginList.readDataFromFile();
        }catch (IOException e){
            // Note this is Initialized LoginList
            loginList.initialized();
            System.out.println("The file has been initialize");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }
    }


    /**
     * Read the input file from interface to the UsesCase, EmployeeList.
     */
    public void ReadInputFileToEmployeeList(IEmployeeList EmployeeList){
        try {
            EmployeeList.readDataFromFile();
        }catch (IOException e){
            EmployeeList.initialized();
            System.out.println("The file has been initialize");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }

    }


    /**
     * Read the input file from interface to the UsesCase, WorkList.
     */
    public void  ReadInputFileToWorkList(IWorkList workList){
        try{
            workList.readDataFromFile();
        }catch(IOException e){
            System.out.println("No work data in the file so far");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }
    }


    /**
     * Read the input file from interface to the UsesCase, GroupList.
     */
    public void ReadInputFileToGroupList(IGroupList groupList){
        try{
            groupList.readDataFromFile();
        }catch(IOException e){
            System.out.println("No group data in the file so far");
        }catch (ClassNotFoundException e1){
            System.out.println("No such a class");
        }
    }


    /**
     * Write the output file from interface to the UsesCases.
     */
    public void WriteOutputFile(ILoginList loginList, IEmployeeList employeeList, IWorkList workList, IGroupList groupList){
        try {
            loginList.writeDataToFile();
            employeeList.writeDataToFile();
            workList.writeDataToFile();
            groupList.writeDataToFile();
        }catch (IOException e){
            System.out.println("The file has been initialize");
        }
    }
}