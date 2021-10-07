public class FullTimeEmployee extends Employee implements Payable{
    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public String toString(){
        return this.getName();
    }

    @Override
    public void getPay() {

    }
}
