package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkList implements Iterable<Work>{

    private final List<Work> WorkList;

    public WorkList(){
        this.WorkList = new ArrayList<Work>();
    }

    public void addWork(String workType, String name, String id, String createTime, int level){
        WorkFactory workFactory = new WorkFactory();
        if (workType.equalsIgnoreCase("IT")){
            //下列這 decoupling 同時有多態產生。他其實是一個worker 但我把他的coupling全部斷開了。
            // 完全斷掉work type 調用 -> 0耦合
            WorkList.add(workFactory.createWork(workType,name,id,createTime,level));
        } else if (workType.equalsIgnoreCase("Sale")){
            // Depend on interface -> low coupling
            Work work = new SaleWork(name, id, createTime, level);
            WorkList.add(work);
            // 給一個強耦合例子: SaleWorker saleWorker = new SaleWork(name, id, createTime, level)
        }
    }

    public boolean deleteWork(String id){
        int index = -1;

        for(int i = 0; i < this.getSize(); i ++){
            if(this.WorkList.get(i).getID().equals(id)){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }else{
            this.WorkList.remove(this.WorkList.get(index));
            return true;
        }
    }

    public int getSize(){
        return WorkList.size();
    }

    public void readInput(Work work) {
        this.WorkList.add(work);
    }

    // === Iterator Design Pattern ===
    @Override
    public Iterator<Work> iterator() {
        return new WorkList.WorkListIterator();
    }


    private class WorkListIterator implements Iterator<Work>{

        private int curr_index = 0;

        @Override
        public boolean hasNext() {
            return curr_index < WorkList.size();
        }

        @Override
        public Work next() {
            Work work;

            try {
                work = WorkList.get(curr_index);
            } catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
            curr_index ++;
            return work;
        }

        public void add(Work work){
            WorkList.add(work);
        }
    }
}
