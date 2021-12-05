package main.UsesCases;

import java.io.Serializable;
import java.util.ArrayList;

public interface IWorkManager extends Serializable {

    void extendWork(String workID, IWorkList workList, String extend_date);
    void changeState(String workID, IWorkList workList, String new_statue);
    void changeWorkInfo(String workID, IWorkList workList , String opt, String changeTo);
    ArrayList<String> showWorkDetail(String workID, IWorkList workList);
    ArrayList<String> workOfMember(String id,IGroupList groupList, IWorkList workList);
    ArrayList<String> TheWorkLeadByThisUser(String id, IGroupList groupList, IWorkList workList);
    ArrayList<String> workOfLowerLevel(String Level, IWorkList workList);
    void autoChangeState(IWorkList workList);
}

