package main.UsesCases;

import java.util.ArrayList;

public interface IWorkManager {

    void extendWork(String workID, IWorkList workList, String extend_date);
    void changeState(String workID, IWorkList workList, String new_statue);
    void autoChangeState(String workID, IWorkList workList);
    boolean changeWorkInfo(String workID, IWorkList workList , String opt, String changeTo);
    String checkWorkLevel(String workID, IWorkList workList);
    ArrayList<String> showWorkDetail(String workID, IWorkList workList);
    boolean workExist(String workID, IWorkList workList);
    ArrayList<String> workOfMember(String id,IGroupList groupList, IWorkList workList);
    ArrayList<String> workOfLed(String id,IGroupList groupList, IWorkList workList);
    ArrayList<String> workOfLowerLevel(String Level, IWorkList workList);
}

