package main.UsesCases;

import main.Entity.Work;
import main.Entity.Workable;

public interface IWorkList extends ReadWrite{
    public void addWork(String name, String id, String department, String requirement, int level);
    public boolean deleteWork(String id);
    public int getSize();
    public void readInput(Work work);
    public Workable getWork(String work_id);
}
