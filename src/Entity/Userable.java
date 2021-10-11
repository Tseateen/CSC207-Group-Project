package Entity;

public interface Userable {

    public abstract String getName();
    public abstract void setName(String name);
    public abstract int getID();
    public abstract String getAccount();
    public abstract void setAccount(String account);
    public abstract String getPassword();
    public abstract void setPassword(String password);
    public abstract String getPhone();
    public abstract void setPhone(String phone);
    public abstract String getAddress();
    public abstract void setAddress(String address);
    public abstract boolean equal(Object other);
    public abstract int getVaccination();
    public abstract void setVaccination(int vac);

}
