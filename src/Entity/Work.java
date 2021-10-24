package Entity;

public class Work {

    private String information;
    private String requirement;

    public Work(String information, String requirement){
        this.information = information;
        this.requirement = requirement;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getRequirement() {
        return requirement;
    }
}
