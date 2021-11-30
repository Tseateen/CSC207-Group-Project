package main.UsesCases;

import main.Entity.User;
import main.Entity.Userable;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LoginList implements Iterable<Userable>, Serializable, ILoginList{

    private final List<Userable> UserList;
    private int idCounter;

    public LoginList(){
        this.UserList = new ArrayList<>();
        this.idCounter = 1;
    }

    @Override
    public String addUser( String name, String password, String phone, String address){
        String id = name.concat(String.valueOf(this.idCounter));
        Userable user = new User( name, id, password, phone, address);
        this.UserList.add(user);
        this.idCounter += 1;
        return id;
    }


    @Override
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
    @Override
    public Userable getUser(String user_id){
        for(Userable user: this.UserList){
            if(user.getID().equals(user_id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public int getSize(){
        return UserList.size();
    }

    @Override
    public int getID(){
        return this.idCounter;
    }

    // ===== Data ====
    @Override
    public void initialized(){
        Userable admin = new User("Admin", "Admin", "Admin", "N/A", "N/A");
        this.UserList.add(admin);
    }


    @Override
    public void readDataFromFile() throws IOException, ClassNotFoundException {
        String filePath = new File("").getAbsolutePath();
        InputStream file = new FileInputStream(filePath.concat("/src/Data/UserData.ser"));
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        LoginList UserFile = (LoginList) input.readObject();
        input.close();
        for(Userable user: UserFile){
            this.UserList.add(user);
            this.idCounter ++;
        }
    }

    @Override
    public void writeDataToFile() throws IOException {
        String filePath = new File("").getAbsolutePath();
        OutputStream file = new FileOutputStream(filePath.concat("/src/Data/UserData.ser"));
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(this);
        output.close();
    }

    // ===============================

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