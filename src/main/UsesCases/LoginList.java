package main.UsesCases;

import main.Entity.User;
import main.Entity.Userable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LoginList implements Iterable<Userable>, Serializable {

    private final List<Userable> UserList;

    public LoginList(){
        this.UserList = new ArrayList<Userable>();
    }

    public void addUser(String accountNumber, String password, String name, String phone, String address, String id){
        Userable user = new User(accountNumber, password, name, phone, address, id);
        this.UserList.add(user);
    }

    public void initialize(){
        //TODO: what's the highest level for admin?
        Userable admin = new User("N/A", "N/A", "Admin", "N/A", "N/A", "0");
        this.UserList.add(admin);
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

    public Userable getUser(String username){
        for(Userable user: this.UserList){
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public int getSize(){
        return UserList.size();
    }

    public void readInput(Userable user) {
        this.UserList.add(user);
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
