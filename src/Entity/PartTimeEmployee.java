package Entity;

import java.time.LocalTime;
import java.util.HashMap;
public class PartTimeEmployee extends Employee{
    private HashMap<String, LocalTime[]> schedule;


    public PartTimeEmployee(String department, String position, int wage, int level) {
        super(department, position, wage, level);
    }
    public PartTimeEmployee(String department, String position, int wage, int level, HashMap<String, LocalTime[]> schedule) {
        super(department, position, wage, level);
        this.schedule = schedule;
    }

    public HashMap<String, LocalTime[]> getSchedule(){
        return this.schedule;
    }

    public void setSchedule(HashMap<String, LocalTime[]> schedule){
        this.schedule = schedule;
    }

}
