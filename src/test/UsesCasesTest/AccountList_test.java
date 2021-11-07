package test.UsesCasesTest;

import main.Entity.Userable;
import main.Entity.User;

public class AccountList_test {
    AccountList aclist1;
    Userable u1 = new User("andy1234", "a12345", "Andy", "412345678","123 Mississauga Road", "001");

    public void SetUp(){
        aclist1 = new AccountList();
    }
}
