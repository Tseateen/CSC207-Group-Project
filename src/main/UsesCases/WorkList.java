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
        if (workType.equalsIgnoreCase("IT")){
            Work work = new ITWork(name, id, createTime, level);
            WorkList.add(work);
        } else if (workType.equalsIgnoreCase("Sale")){
            Work work = new SaleWork(name, id, createTime, level);
            WorkList.add(work);
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
