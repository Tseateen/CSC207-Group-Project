package test.EntityTest;

import main.Entity.Work;
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
        Work w = new Work("First Work", "001", "IT", 1);

        assertEquals(wf1.createWork("First Work", "001", "IT", 1), w);

    }
}
