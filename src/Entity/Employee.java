package Entity;

public abstract class Employee{
    /**
     * 一个Employee的与工作相关的基本信息
     *
     * @param level 所持有权限的不同等级，0是所持有的最高权限
     */


    private String department; //工种
    private int wage; //工资
    private int attendance; //
    private int level; //

    /**
    * Create an employee with their department, position, wage, and level inputed,
    * and set attendance to 0.*/
    public Employee(String department, int wage, int level){
        this.department = department;
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

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }


    @Override
    public String toString(){
        return "This is an employee in the department of " + this.department;
    };
}
