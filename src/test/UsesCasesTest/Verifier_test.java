package test.UsesCasesTest;


import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Verifier_test {
    LoginList LL;
    Verifier VV;
    EmployeeList EL;
    Userable u1, u2;
    FullTimeEmployee e1;
    PartTimeEmployee e2;


    @Before
    public void Setup(){
        LL = new LoginList();
        u1 = new User("Lily", "002", "l99999999", "498765432",
                "234 Mississauga Road");
        u2 = new User("Cathy", "003", "33445566", "6478888888",
                "12 Apple Creek");
        LL.addUser(u1.getName(), u1.getPassword(), u1.getPhone(), u1.getAddress());
        LL.addUser(u2.getName(), u2.getPassword(), u2.getPhone(), u2.getAddress());
        VV = new Verifier();
        EL = new EmployeeList();
        e1 = new FullTimeEmployee("Human Resource", "Manager", "5000", 4, "002");
        e2 = new PartTimeEmployee("Technology", "25", 8, "003");
        EL.addEmployee(e1.getDepartment(),e1.getWage(),e1.getPosition(), e1.getLevel()+"", "F", "Lily");
        EL.addEmployee(e2.getDepartment(),e2.getWage(),"N", e2.getLevel()+"", "P", "Cathy");

    }


    @Test
    public void testVerifyForLogin(){
        assertTrue(VV.verifyForLogin("002", "l99999999", LL));
        assertFalse(VV.verifyForLogin("002", "l666666", LL));

    }

    @Test
    public void testVerifierFullTime(){
        assertTrue(VV.verifierFullTime("002", EL));
        assertFalse(VV.verifierFullTime("003", EL));
    }
}
