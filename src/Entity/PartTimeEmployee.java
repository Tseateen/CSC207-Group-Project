package Entity;

public class PartTimeEmployee extends Employee{
    private String schedule;

    public PartTimeEmployee(String department, String position, int wage, int level) {
        super(department, position, wage, level);
    }
    public PartTimeEmployee(String department, String position, int wage, int level, String schedule) {
        super(department, position, wage, level);
        this.schedule = schedule;
    }



    public String getSchedule(){return this.schedule;}

    public void setSchedule(String schedule){
        this.schedule = schedule;
    }

}
