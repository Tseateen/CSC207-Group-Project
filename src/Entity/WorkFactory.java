package Entity;

public class WorkFactory {

    public WorkFactory(){
    }

    public Work createWork(String workType, String name, String id, String createTime, int level){
        if (workType.equalsIgnoreCase("IT")){
            return new IT_Work(name, id, createTime, level);
        } else if (workType.equalsIgnoreCase("Sale")){
            return new Sale_Work(name, id, createTime, level);
        }
        return null;
    }
}
