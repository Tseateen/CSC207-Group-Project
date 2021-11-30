package main.UsesCases;

import main.Entity.Workable;

import java.io.Serializable;

public interface IWorkList extends IReadWrite , Serializable {
    void addWork(String name, String id, String department, String requirement, int level);
    String FindWorkLevel(String workID);
    public boolean checkWorkExist(String workID);
    int getSize();
    Workable getWork(String work_id);
}
