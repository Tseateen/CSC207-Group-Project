package main.Entity;

public class WorkFactory {

    public WorkFactory(){
    }

    public Work createWork( String name, String id, String department, int level){
            return new Work(name, id, department, level);
    }
}
