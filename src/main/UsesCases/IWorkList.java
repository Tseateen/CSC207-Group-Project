package main.UsesCases;

import main.Entity.Workable;

public interface IWorkList extends IReadWrite {
    void addWork(String name, String id, String department, String requirement, int level);
    boolean deleteWork(String id);
    int getSize();
    Workable getWork(String work_id);
}
