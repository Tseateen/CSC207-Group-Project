package main.UsesCases;

import main.Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class EmployeeList implements Iterable<Employee>, Serializable {

    // === Instance Variables ===

    private final List<Employee> EmployeeList;

    /* === Representation Invariants ===
     * The EmployeeList should have at least one Employee inside one company having the top authority level, which is 0.
     */

    public EmployeeList(){
        this.EmployeeList = new ArrayList<Employee>();
    }


    /**
     * This method will check the type of employees i.e. whether he is full-time or part-time employee,
     * and add a new employee information into the EmployeeList.
     *
     * @param department the department of the employee.
     * @param wage the  wage of the employee.
     * @param position the position of the employee, usually it is "N".
     * @param level the level of the employee. With smaller numbers, the employee has higher authority level.
     * @param status the status of the employee, "pending" by default.
     * @param id the id of the employee.
     */
    public void addEmployee(String department, int wage, String position,  int level, String status, String id) {
        if (status.equals("F")) {
            Employee employee = new FullTimeEmployee(department, position, wage, level, id);
            this.EmployeeList.add(employee);
        } else if (status.equals("P") && position.equals("N")) {
            Employee employee = new PartTimeEmployee(department, wage, level, id);
            this.EmployeeList.add(employee);
        }
    }
    public void initialize(){
        Employee admin = new FullTimeEmployee("N/A", "N/A",0, 0, "0");
        this.EmployeeList.add(admin);
    }


    /**
     * This method will find the Employee from the EmployeeList and delete the Employee.
     *
     * @param id the employee's id that needs to be deleted.
     * @return whether the Employee has successfully deleted.
     */
    public boolean deleteEmployee(String id) {
        int index = -1;
        for(int i = 0; i < EmployeeList.size(); i ++) {
            if(this.EmployeeList.get(i).getID().equals(id)){

                index = i;
            }
        }
        if(index == -1) {
            return false;
        }else{
            this.EmployeeList.remove(this.EmployeeList.get(index));
            return true;
        }
    }


    /**
     * This method will get the size of the EmployeeList, which is the total number of employees inside one company.
     *
     * @return the int of the size of the EmployeeList.
     */
    public int getSize(){
        return this.EmployeeList.size();
    }


    /**
     * This method will read the input entered from the interface and call the add method to add a new employee
     * into the EmployeeList.
     *
     * @param employee the information of the Employee.
     */
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


        /**
         * This method will add a new employee from the read input into the EmployeeList.
         *
         * @param employee the information of the Employee.
         */
        public void add(Employee employee){
            EmployeeList.add(employee);
        }
    }
}
