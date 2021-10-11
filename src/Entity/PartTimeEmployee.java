package Entity;

import java.time.LocalTime;
import java.util.HashMap;
public class PartTimeEmployee extends Employee{
    /**
     * A Part Time Employee without position and vacation, but have a work schedule 用于查看他的工作时间
     *
     * @param schedule keys:星期几，item:时段（是否应该改成string去储存时段？
     */

    private HashMap<String, String[]> schedule;


    public PartTimeEmployee(String department, int wage, int level) {
        super(department, wage, level);
    }
    public PartTimeEmployee(String department, int wage, int level, HashMap<String, String[]> schedule) {
        super(department, wage, level);
        this.schedule = schedule;
    }

    public HashMap<String, String[]> getSchedule(){
        return this.schedule;
    }

    public void setSchedule(HashMap<String, String[]> schedule){
        this.schedule = schedule;
    }

    public String toString() {
        return  "Part time worker of" + this.getDepartment() + this.getLevel() + "level.";
    }
}
