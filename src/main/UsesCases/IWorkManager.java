package main.UsesCases;

import main.Entity.Workable;

public interface IWorkManager {

    public void extendWork(Workable work, String extend_date);
    public void changeState(Workable work, String new_statue);
}
