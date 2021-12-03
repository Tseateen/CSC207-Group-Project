package test.UsesCasesTest;


import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Verifier_test {
    LoginList LL;
    Verifier VV;


    @Before
    public void Setup(){
        LL = new LoginList();
        Userable u1 = new User("Lily", "002", "l99999999", "498765432",
                "234 Mississauga Road");
        LL.addUser(u1.getName(), u1.getPassword(), u1.getPhone(), u1.getAddress());
        VV = new Verifier();

    }


    @Test
    public void testVerifyForLogin(){
        assertTrue(VV.verifyForLogin("002", "l99999999", LL));
        assertFalse(VV.verifyForLogin("002", "l666666", LL));

    }
}
