package Entity;

public interface Userable {


    public abstract String getName();
    public abstract void setName(String name);
    public abstract int getID();
    public abstract int getAccount();
    public abstract void setAccount(int account);
    public abstract int getPassword();
    public abstract void setPassword(int password);
    public abstract int getPhone();
    public abstract void setPhone(int phone);
    public abstract String getAddress();
    public abstract void setAddress(String address);
    public abstract boolean equal(Object other);


}
