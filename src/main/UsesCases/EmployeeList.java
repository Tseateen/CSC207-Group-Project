package main.UsesCases;

import main.Entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class EmployeeList implements Iterable<Employee>{

    private final List<Employee> EmployeeList;

    public EmployeeList(){
        this.EmployeeList = new ArrayList<Employee>();
    }

    public void addEmployee(String department, int wage, String position,  int level, String status, String id){
        if(status.equals("F")) {
            Employee employee = new FullTimeEmployee(department, position, wage, level, id);
        }else if(status.equals("P") && position.equals("N")){
            Employee employee = new PartTimeEmployee(department, wage, level, id);
        }

    }

    public boolean deleteEmployee(String id){
        int index = -1;

        for(int i = 0; i < this.getSize(); i ++){
            if(this.EmployeeList.get(i).getID().equals(id)){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }else{
            this.EmployeeList.remove(this.EmployeeList.get(index));
            return true;
        }
    }

    public int getSize(){
        return EmployeeList.size();
    }

    public void readInput(Employee employee){
        this.EmployeeList.add(employee);
    }


    @Override
    public Iterator<Employee> iterator() {
        return new EmployeeListIterator();
    }

    public class EmployeeListIterator implements Iterator<Employee>{

        private int curr_index = 0;

        @Override
        public boolean hasNext() {
            return curr_index < EmployeeList.size();
        }

        @Override
        public Employee next() {
            Employee employee;

            try {
                employee = EmployeeList.get(curr_index);
            } catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
            curr_index ++;
            return employee;
        }

        public void add(Employee employee){
            EmployeeList.add(employee);
        }
    }
}
