package Entity;


public class User implements Userable{
    private int account;
    private int password;
    private final int id;
    private String name;
    private int phone;
    private String address;
    private int vaccination;


    public User(int accountNumber,int password, String name, int phone, String address, int id){
        this.id = id;
        this.account = accountNumber;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public int getAccount() {
        return this.account;
    }

    @Override
    public void setAccount(int account) {
        this.account = account;
    }

    @Override
    public int getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public int getPhone() {
        return this.phone;
    }

    @Override
    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getVaccination(){return this.vaccination;}

    @Override
    public void setVaccination(int vac){this.vaccination = vac;}

    @Override
    public boolean equal(Object obj) {
        if(!(obj instanceof User)){
            return false;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        User other = (User) obj;

        return (this.id == other.id);
    }

    @Override
    public String toString(){
        String identity = String.valueOf(this.id);
        return "My identity is " + identity + ", and my name is " + this.name;
    }
}