public abstract class Employee{
    public static int totalEmployee;
    public static int totalLeaveEmployee;
    private final int id;
    private String name;
    private int attendance;

    public Employee(String name){
        // Guarantee id uniq
        this.id = Employee.totalEmployee + Employee.totalLeaveEmployee +  1;
        this.name = name;
        Employee.totalEmployee ++;
        this.attendance = 0;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public int getAttendance(){
        return this.attendance;
    }

    public void setAttendance(){
        this.attendance ++;
    }

    @Override
    public String toString(){
        return "This is Employee";
    }
}
