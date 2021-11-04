package test.EntityTest;

import main.Entity.ITWork;
import main.Entity.SaleWork;
import main.Entity.WorkFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WorkFactory_test {
    WorkFactory wf1;
    @Before
    public void setUp() {
        wf1 = new WorkFactory();
    }

    @Test(timeout = 100)
    public void testCreateWork() {
        ITWork itw = new ITWork("First IT", "IT001", "2021/11/11 10:30", 1);
        SaleWork sw = new SaleWork("First Sale", "S001", "2021/11/18 10:30", 2);

        assertEquals(wf1.createWork("ITWork", "First IT", "IT001", "2021/11/11 10:30", 1), itw);
        assertEquals(wf1.createWork("SaleWork", "First Sale", "S001", "2021/11/18 10:30", 2), sw);
        assertNull(wf1.createWork("HomeWork", "CSC207 homework", "H001", "2021/11/12 08:30", 10));

    }
}
