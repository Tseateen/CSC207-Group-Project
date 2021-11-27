package main.UsesCases;

import main.Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkList implements Iterable<Workable>, Serializable, IWorkList {

    private final List<Workable> workList;

    public WorkList(){
        this.workList = new ArrayList<Workable>();
    }

    public void addWork(String name, String id, String department, String requirement, int level){
        Workable work = new Work(name,id,department, requirement,level);
        workList.add(work);
    }

    public boolean deleteWork(String id){
        int index = -1;

        for(int i = 0; i < this.getSize(); i ++){
            if(this.workList.get(i).getID().equals(id)){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }else{
            this.workList.remove(this.workList.get(index));
            return true;
        }
    }

    public int getSize(){
        return workList.size();
    }


    public void readInput(Work work) {
        this.workList.add(work);
    }

    /**
     * This method will find the Employee from the EmployeeList.
     *
     * @param work_id the work's id that needs to be found.
     * @return the Work found.
     */
    public Workable getWork(String work_id) {
        for (Workable w: this.workList) {
            if (w.getID().equals(work_id)) {
                return w;
            }
        }
        return null;
    }

    // === Iterator Design Pattern ===
    @Override
    public Iterator<Workable> iterator() {
        return new WorkListIterator();
    }


    private class WorkListIterator implements Iterator<Workable>{

        private int curr_index = 0;

        @Override
        public boolean hasNext() {
            return curr_index < workList.size();
        }

        @Override
        public Workable next() {
            Workable work;

            try {
                work = workList.get(curr_index);
            } catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
            curr_index ++;
            return work;
        }

        public void add(Work work){
            workList.add(work);
        }
    }
}