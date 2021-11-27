package main.UsesCases;

import main.Entity.Group;
import main.Entity.Workable;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface IWorkManager {

    public void extendWork(String workID, IWorkList workList, String extend_date);
    public void changeState(String workID, IWorkList workList, String new_statue);
    public void autoChangeState(String workID, IWorkList workList);
    public boolean changeWorkInfo(String workID, IWorkList workList , String opt, String changeTo);
    public String workLevel(String workID, IWorkList workList);
    public ArrayList<String> showWorkDetail(String workID, IWorkList workList);
    public boolean workExist(String workID, IWorkList workList);
    public ArrayList<String> workOfMember(String id,IGroupList groupList, IWorkList workList);
    public ArrayList<String> workOfLed(String id,IGroupList groupList, IWorkList workList);
    public ArrayList<String> workOfLowerLevel(String Level, IWorkList workList);
}

