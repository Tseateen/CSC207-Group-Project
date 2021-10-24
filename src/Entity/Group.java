package Entity;

public class Group{
    private Userable leader;
    private Userable[] members;
    private String project;

    public Group (Userable leader, Userable[] members, String project){
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

    public void setProject(String project) {
        this.project = project;
    }

    public String getProject() {
        return project;
    }
}
