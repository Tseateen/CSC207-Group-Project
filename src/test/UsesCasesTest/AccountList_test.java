package test.UsesCasesTest;

import main.Entity.Employee;
import main.Entity.FullTimeEmployee;
import main.UsesCases.AccountList;
import main.Entity.Userable;
import main.Entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

public class AccountList_test {
    AccountList aclist1;
    Userable u1 = new User("andy1234", "a12345", "Andy", "412345678","123 Mississauga Road", "001");
    Userable u2 = new User("cathy822", "cp020822","Cathy", "6478680629","43 Morrison Cres","002");
    Employee e1 = new FullTimeEmployee("Software Development", 5000, 5,"001");
    Employee e2 = new FullTimeEmployee("Human Resources", 4000, 6, "002");

    @Before
    public void SetUp(){
        HashMap<Userable, Employee> list = new HashMap<>();
        list.put(u1, e1);
        list.put(u2, e2);
        aclist1 = new AccountList(list);
    }
    @Test(timeout = 100)
    public void testTotalEmployee(){
        assertEquals(aclist1.getTotalEmployee(), 2);
    }
}
