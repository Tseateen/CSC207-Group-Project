public class Employee implements iEmployee{
    public static int totalEmployee;
    private String name;
    private int attendance;
    private int id;

    public Employee(String name){
        this.id = Employee.totalEmployee + 1;
        this.name = name;
        Employee.totalEmployee ++;
        this.attendance = 0;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public int getAttendance(){
        return this.attendance;
    }

    @Override
    public void setAttendance(){
        this.attendance ++;
    }
}
