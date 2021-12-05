package main.UsesCases;

import main.Entity.User;
import main.Entity.Userable;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LoginList implements Iterable<Userable>, Serializable, ILoginList{

    // === Instance Variables ===

    // The list contained all the user.
    private final List<Userable> UserList;
    // The idCounter that generated the ID for the user.
    private int idCounter;


    /**
     * Construct the LoginList, managing the login information.
     */
    public LoginList(){
        this.UserList = new ArrayList<>();
        this.idCounter = 0;
    }


    /**
     * This method will add the new User to the system.
     *
     * @param name the User's name.
     * @param password the password set by the User.
     * @param phone the phone number of User.
     * @param address the address of the User.
     *
     * @return the string of userID of the new User.
     *
     */
    @Override
    public String addUser(String name, String password, String phone, String address){
        String id = name.concat(String.valueOf(this.idCounter));
        this.idCounter += 1;
        Userable user = new User( name, id, password, phone, address);
        this.UserList.add(user);
        return id;
    }


    /**
     * This method will delete the odd User from the system.
     *
     * @param id the User's id.
     */
    @Override
    public void deleteUser(String id){
        Userable user = this.getUser(id);
        this.UserList.remove(user);
    }

    /**
     * This method will find the User information from the UserList.
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


    /**
     * This method will get the amount of the total users in the system.
     *
     * @return the int of size of the UserList, i.e. the total number of users.
     *
     */
    @Override
    public int getSize(){
        return UserList.size();
    }


    /**
     * This method will get the ID users from the system.
     *
     * @return the int of ID of the User.
     *
     */
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
        String targetFile = filePath.concat("/src/Data/UserData.ser");
        InputStream file = new FileInputStream(targetFile);
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
        String targetFile = filePath.concat("/src/Data/UserData.ser");
        OutputStream file = new FileOutputStream(targetFile);
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