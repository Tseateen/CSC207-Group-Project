package test.EntityTest;

import main.Entity.SaleWork;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleWork_test {

    SaleWork SaleWork_1;

    public void setUp() {
        SaleWork_1 = new SaleWork("Sale Good", "S001", "2021/11/11 10:30", 1);
    }

    @Test(timeout = 100)
    public void testName() {
        assertEquals(SaleWork_1.getName(), "Sale Good");
    }

    @Test(timeout = 100)
    public void testId() {
        assertEquals(SaleWork_1.getId(), "S001");
    }

    @Test(timeout = 100)
    public void testLevel() {
        assertEquals(SaleWork_1.getLevel(), 1);
    }

    @Test(timeout = 100)
    public void testCreate_time() {
        assertEquals(SaleWork_1.getCreate_time(), "2021/11/11 10:30");
    }

    //Todo: Implement End time in SaleWork?
    @Test(timeout = 100)
    public void testEnd_time() {
        assertEquals(SaleWork_1.getEnd_time(), "");
    }

    @Test(timeout = 100)
    public void testState() {
        assertEquals(SaleWork_1.getState(), "Pending");
        SaleWork_1.setState("In Progress");
        assertEquals(SaleWork_1.getState(), "In Progress");
    }

    //Todo: Implement Extend time in SaleWork?
    @Test(timeout = 100)
    public void testExtend() {
        SaleWork_1.Extend("");
        assertEquals(SaleWork_1.getEnd_time(), "");
    }

    @Test(timeout = 100)
    public void testStart_time() {
        SaleWork_1.setStart_time("2021/11/11 12:30");
        assertEquals(SaleWork_1.getStart_time(), "2021/11/11 12:30");
    }

    @Test(timeout = 100)
    public void testDescribe() {
        SaleWork_1.setDescribe("Need to sale something");
        assertEquals(SaleWork_1.getDescribe(), "Need to sale something");
    }

    @Test(timeout = 100)
    public void testRequirement() {
        SaleWork_1.setRequirement("Done by 5 days");
        assertEquals(SaleWork_1.getRequirement(), "Done by 5 days");
    }

}
