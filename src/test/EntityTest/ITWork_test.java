package test.EntityTest;

import main.Entity.ITWork;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ITWork_test {

    ITWork ITWork_1;

    public void setUp() {
        ITWork_1 = new ITWork("Programming Work", "IT001", "2021/11/11 10:30", 1);
    }

    @Test(timeout = 100)
    public void testName() {
        assertEquals(ITWork_1.getName(), "Programming Work");
    }

    @Test(timeout = 100)
    public void testId() {
        assertEquals(ITWork_1.getId(), "IT001");
    }

    @Test(timeout = 100)
    public void testLevel() {
        assertEquals(ITWork_1.getLevel(), 1);
    }

    @Test(timeout = 100)
    public void testCreate_time() {
        assertEquals(ITWork_1.getCreate_time(), "2021/11/11 10:30");
    }

    //Todo: Implement End time in ITWork?
    @Test(timeout = 100)
    public void testEnd_time() {
        assertEquals(ITWork_1.getEnd_time(), "");
    }

    @Test(timeout = 100)
    public void testState() {
        assertEquals(ITWork_1.getState(), "Pending");
        ITWork_1.setState("In Progress");
        assertEquals(ITWork_1.getState(), "In Progress");
    }

    //Todo: Implement Extend time in ITWork?
    @Test(timeout = 100)
    public void testExtend() {
        ITWork_1.Extend("");
        assertEquals(ITWork_1.getEnd_time(), "");
    }

    @Test(timeout = 100)
    public void testStart_time() {
        ITWork_1.setStart_time("2021/11/11 12:30");
        assertEquals(ITWork_1.getStart_time(), "2021/11/11 12:30");
    }

    @Test(timeout = 100)
    public void testDescribe() {
        ITWork_1.setDescribe("Need to program something");
        assertEquals(ITWork_1.getDescribe(), "Need to program something");
    }

    @Test(timeout = 100)
    public void testRequirement() {
        ITWork_1.setRequirement("Done by 3 days");
        assertEquals(ITWork_1.getRequirement(), "Done by 3 days");
    }

}
