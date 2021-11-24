package main.UsesCases;

import main.Entity.User;
import main.Entity.Userable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LoginList implements Iterable<Userable>, Serializable, ILoginList{

    private final List<Userable> UserList;
    private int idCounter;

    public LoginList(){
        this.UserList = new ArrayList<Userable>();
        this.idCounter = 1;
    }

    public void addUser(String accountNumber, String password, String name, String phone, String address){
        Userable user = new User(accountNumber, password, name, phone, address, String.valueOf(this.idCounter));
        this.UserList.add(user);
        this.idCounter += 1;
    }


    public boolean deleteUser(String id){
        int index = -1;

        for(int i = 0; i < this.getSize(); i ++){
            if(this.UserList.get(i).getID().equals(id)){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }else{
            this.UserList.remove(this.UserList.get(index));
            return true;
        }
    }

    /**
     * This method will find the Employee from the EmployeeList.
     *
     * @param user_id the user's id that needs to be found.
     * @return the User found.
     */
    public Userable getUser(String user_id){
        for(Userable user: this.UserList){
            if(user.getID().equals(user_id)) {
                return user;
            }
        }
        return null;
    }

    public int getSize(){
        return UserList.size();
    }

    public int getID(){
        return this.idCounter;
    }

    // ===== Data ====
    public void initialize(){
        Userable admin = new User("Admin", "Admin", "Admin", "N/A", "N/A", "0");
        this.UserList.add(admin);
    }

    public void readInput(Userable user) {
        this.UserList.add(user);
    }

    public void readID(int ID) {
        this.idCounter = ID;
    }
    // === Iterator Design Pattern ===
    @Override
    public Iterator<Userable> iterator() {
        return new LoginListIterator();
    }


    private class LoginListIterator implements Iterator<Userable>{

        private int curr_index = 0;

        @Override
        public boolean hasNext() {
            return curr_index < UserList.size();
        }

        @Override
        public Userable next() {
            Userable user;

            try {
                user = UserList.get(curr_index);
            } catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
            curr_index ++;
            return user;
        }

        public void add(Userable user){
            UserList.add(user);
        }
    }
}