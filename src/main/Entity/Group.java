package main.Entity;

public class Group{
    private Userable leader;
    private Userable[] members;
    private Work project;

    public Group (Userable leader, Userable[] members, Work project){
        this.leader = leader;
        this.members = members;
        this.project = project;
    }

    public void setLeader(Userable leader){
        this.leader = leader;
    }

    public Userable getLeader(){
        return this.leader;
    }

    public void setMembers(Userable[] members) {
        this.members = members;
    }

    public Userable[] getMembers() {
        return members;
    }

    public void setProject(Work project) {
        this.project = project;
    }

    public Work getProject() {
        return project;
    }
}
