package main.UsesCases;

public class WorkFacade {

    private final WorkDistributor distributeWork;
    private final GroupManager managerGroup;
    private final WorkManager managerWork;

    public WorkFacade() {
        this.distributeWork = new WorkDistributor();
        this.managerGroup = new GroupManager();
        this.managerWork = new WorkManager();
    }
}
