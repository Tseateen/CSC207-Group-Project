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
        LL.addUser("111", "123456", "Lily", "4203456789",
                "10 King St.", "8888");
        Userable u1 = new User("111", "123456", "Lily", "4203456789",
                 "10 King St.", "8888");
        VV = new Verifier(LL);

    }


    @Test
    public void testVerifyForLogin(){
        assertTrue(VV.verifyForLogin("111", "123456"));
        assertFalse(VV.verifyForLogin("111", "666666"));
    }
}
