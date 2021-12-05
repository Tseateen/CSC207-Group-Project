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
    GroupList GL;


    @Before
    public void Setup(){
        LL = new LoginList();
        u1 = new User("Lily", "Lily0", "l99999999", "498765432",
                "234 Mississauga Road");
        u2 = new User("Cathy", "Cathy1", "33445566", "6478888888",
                "12 Apple Creek");
        LL.addUser(u1.getName(), u1.getPassword(), u1.getPhone(), u1.getAddress());
        LL.addUser(u2.getName(), u2.getPassword(), u2.getPhone(), u2.getAddress());

        VV = new Verifier();

        EL = new EmployeeList();
        e1 = new FullTimeEmployee("Human Resource", "Manager", "5000", 4, u1.getID());
        e2 = new PartTimeEmployee("Technology", "25", 8, u2.getID());
        EL.addEmployee(e1.getDepartment(),e1.getWage(),e1.getPosition(), e1.getLevel()+"", "F", "Lily");
        EL.addEmployee(e2.getDepartment(),e2.getWage(),"N", e2.getLevel()+"", "P", "Cathy");

        GL = new GroupList();
        GL.addGroup(u1.getID(), "207");
    }

    @Test
    public void testVerifyForLogin(){
        assertTrue(VV.verifyForLogin(u1.getID(), u1.getPassword(), LL));
        assertFalse(VV.verifyForLogin(u1.getID(), u2.getPassword(), LL));

    }

    @Test
    public void testVerifierFullTime(){
        assertTrue(VV.verifierFullTime(u1.getID(), EL));
        assertFalse(VV.verifierFullTime(u2.getID(), EL));
    }

    @Test
    public void testValidToCreateThisLevel(){
        assertTrue(VV.ValidToCreateThisLevel("9", EL, e1.getID()));
        assertFalse(VV.ValidToCreateThisLevel("1", EL, e1.getID()));
    }

    @Test
    public void testValidToDeleteThisUser(){
        assertTrue(VV.validToDeleteThisUser(e2.getID(), EL, e1.getID()));
        assertFalse(VV.validToDeleteThisUser(e1.getID(), EL, e2.getID()));
    }

    @Test
    public void testVerifierLeader(){
        assertTrue(VV.verifierLeader(u1.getID(), "207", GL));
        assertFalse(VV.verifierLeader(u2.getID(), "207", GL));
    }

    @Test
    public void testLevelVerifier(){
        assertTrue(VV.levelVerifier(1, u1.getID(), EL));
        assertFalse(VV.levelVerifier(9, u1.getID(), EL));
    }
}
