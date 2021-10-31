package main.InterfaceAdapter;

import main.UsesCases.AccountList;
import main.UsesCases.FileReadWrite;
import main.UsesCases.ReadWrite;

public class DataGateway {

    private final FileReadWrite fileManager;
    private final AccountList managerAccountList;

    public DataGateway(AccountList managerAccountList){
        this.managerAccountList = managerAccountList;
        this.fileManager = new FileReadWrite();
    }

    public void ReadInputFile(){
        // TODO: 寫入 AccountList
        System.out.println("Reading File");
    }

    public void WriteOutputFile(){
        System.out.println("Done for writing");
    }
}
