package Entity;

public abstract class Employee{

    private String department;
    private String position;
    private int wage;
    private int attendance;
    private int level;

    public Employee(String department, String position, int wage, int level){
        this.department = department;
        this.position = position;
        this.wage = wage;
        this.level = level;
        this.attendance = 0;
    }

    public int getAttendance(){
        return this.attendance;
    }

    public void setAttendance(){
        this.attendance ++;
    }

    public int getWage(){
        return this.wage;
    }

    public void setWage(int wage){
        this.wage = wage;
    }

    public String getDepartment(){
        return this.department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getPosition(){
        return this.position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    @Override
    public String toString(){
        return "This is Employee";
    }
}
