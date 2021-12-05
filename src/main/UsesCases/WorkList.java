package main.UsesCases;

import main.Entity.*;

import java.io.*;
import java.util.*;

public class WorkList implements Iterable<Workable>, Serializable, IWorkList {


    // === Instance Variables ===
    private final List<Workable> workList;


    /**
     * Construct the WorkList.
     */
    public WorkList(){
        this.workList = new ArrayList<>();
    }


    /**
     * Add a new Work into the WorkList.
     *
     * @param name the Work's name.
     * @param id the Work's ID.
     * @param department the Work's department.
     * @param requirement the Work's requirements.
     * @param level the Work's level.
     *
     */
    public void addWork(String name, String id, String department, String requirement, int level, String endTime){
        Workable work = new Work(name,id, department, requirement,level, endTime);
        this.workList.add(work);
    }


    /**
     * Get the authority level of the Work.
     *
     * @param workID the ID of the Work.
     * @return a string with the authority level of the Work.
     */
    public String FindWorkLevel(String workID) {
        return String.valueOf(this.getWork(workID).getLevel());
    }


    /**
     * Verify work exist or not
     *
     * @param workID the work's id which is going to be extended.
     *
     * @return work exist or not
     */
    public boolean checkWorkExist(String workID) {
        return !Objects.isNull(this.getWork(workID));
    }


    /**
     * Delete an existed Work into the WorkList.
     *
     * @param id the Work's ID.
     *
     * @return true if the Work has been deleted.
     */
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


    /**
     * Get the total number work existed from the workList.
     *
     * @return the number of work.
     */
    public int getSize(){
        return workList.size();
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
    // ===== Data ====

    @Override
    public void readDataFromFile() throws IOException, ClassNotFoundException {
        String filePath = new File("").getAbsolutePath();
        String targetFile = filePath.concat("/src/Data/WorkData.ser");
        InputStream file = new FileInputStream(targetFile);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        WorkList workFile = (WorkList) input.readObject();
        input.close();
        for(Workable work: workFile){
           this.workList.add(work);
        }
    }

    @Override
    public void writeDataToFile() throws IOException {
        String filePath = new File("").getAbsolutePath();
        String targetFile = filePath.concat("/src/Data/WorkData.ser");
        OutputStream file = new FileOutputStream(targetFile);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(this);
        output.close();
    }

    // ===============================

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