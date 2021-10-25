package main.Entity;

public class WorkFactory {

    public WorkFactory(){
    }

    public Work createWork(String workType, String name, String id, String createTime, int level){
        if (workType.equalsIgnoreCase("IT")){
            return new ITWork(name, id, createTime, level);
        } else if (workType.equalsIgnoreCase("Sale")){
            return new SaleWork(name, id, createTime, level);
        }
        return null;
    }
}
