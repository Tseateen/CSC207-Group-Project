package main.UsesCases;

import main.Entity.Workable;

import java.io.Serializable;

public interface IWorkList extends IReadWrite , Serializable {
    void addWork(String name, String id, String department, String requirement, int level, String endTime);
    String FindWorkLevel(String workID);
    boolean checkWorkExist(String workID);
    int getSize();
    Workable getWork(String work_id);
}
