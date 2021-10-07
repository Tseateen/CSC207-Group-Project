public class PartTimeEmployee extends Employee implements Payable{
    public PartTimeEmployee(String name) {
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
