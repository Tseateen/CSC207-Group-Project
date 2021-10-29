package main.UsesCases;

import main.Entity.Work;

public interface WorkManager {
    public abstract void createWork(String workType, String name, String id, String createTime, int level);

    public abstract void deleteWork();

    public abstract void extendWork(Work project);

}
