import Entity.Userable;
import Uses_Cases.AccountManager;
import Uses_Cases.Verifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class UseCases_test {
    AccountManager AM;
    Verifier VA;

    @Before
    public void setUp() throws Exception {
        AM = new AccountManager();
        VA = new Verifier(AM);
    }


    @Test
    public void test1() {
        for (Userable i: AM.getEmployeeMap().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }
        assertFalse(VA.verifyExist("30"));
    }

    @Test
    public void test2() {
        assertEquals(AM.getTotalEmployee(), 1);
        assertEquals(AM.getTotalFull_time(), 1);
        assertEquals(AM.getTotalPart_time(), 0);
        Object c = AM.createEmployee("2", "0", "A", "", "", "P",
                "Design", "", 0, 0);
        assertTrue(c instanceof Userable);
        assertEquals(AM.getTotalEmployee(), 2);
        assertEquals(AM.getTotalFull_time(), 1);
        assertEquals(AM.getTotalPart_time(), 1);
        VA.AddAccount((Userable)c);
        for (Userable i: AM.getEmployeeMap().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }
        assertTrue(VA.verifyForLogin("2", "0"));
        assertFalse(VA.verifyForLogin("2", "1"));
        for (Userable i: AM.getEmployeeMap().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }
        Object d = AM.deleteEmployee("1");
        assertTrue(d instanceof Userable);
        assertEquals(AM.getTotalEmployee(), 1);
        assertEquals(AM.getTotalFull_time(), 0);
        assertEquals(AM.getTotalPart_time(), 1);
        VA.AddAccount((Userable)c);
        for (Userable i: AM.getEmployeeMap().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }
        assertTrue(VA.verifyForLogin("1", "0"));
        assertFalse(VA.verifyForLogin("2", "1"));
        for (Userable i: AM.getEmployeeMap().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }

    }

}
