import Entity.*;
import Uses_Cases.*;
import org.junit.Before;
import org.junit.Test;


import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class Entity_test {
    AccountManager AM;
    Verifier VA;

    @Before
    public void setUp() throws Exception {
        AM = new AccountManager();
        VA = new Verifier(AM);
    }

    /**
     * 这部分是个最简单的测试，仅仅是测试初始化
     */
    @Test
    public void test1() {
        for (Userable i: AM.getEmployeeList().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }
        assertFalse(VA.verifyExist("30"));
    }

    /**
     * 这部分主要是测试账号的创建，添加，删除
     * 还有verifier中的一些验证功能
     */
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
        for (Userable i: AM.getEmployeeList().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }
        assertTrue(VA.verifyAccountExist("2", "0"));
        assertFalse(VA.verifyAccountExist("2", "1"));
        for (Userable i: AM.getEmployeeList().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }
        Object d = AM.deleteEmployee("1");
        assertTrue(d instanceof Userable);
        assertEquals(AM.getTotalEmployee(), 1);
        assertEquals(AM.getTotalFull_time(), 0);
        assertEquals(AM.getTotalPart_time(), 1);
        VA.AddAccount((Userable)c);
        for (Userable i: AM.getEmployeeList().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }
        assertFalse(VA.verifyAccountExist("1", "0"));
        assertFalse(VA.verifyAccountExist("2", "1"));
        for (Userable i: AM.getEmployeeList().keySet()) {
            assertTrue(VA.verifyExist(i.getAccount()));
        }

    }

}
