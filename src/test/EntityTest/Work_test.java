package test.EntityTest;

import main.Entity.Work;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Work_test {

    Work Work_1;

    public void setUp() {
//        Work_1 = new Work("Programming Work", "001", "IT", 1);
    }

    @Test(timeout = 100)
    public void testName() {
        assertEquals(Work_1.getName(), "Programming Work");
    }

    @Test(timeout = 100)
    public void testId() {
        assertEquals(Work_1.getID(), "001");
    }

    @Test(timeout = 100)
    public void testLevel() {
        assertEquals(Work_1.getLevel(), 1);
    }

    @Test(timeout = 100)
    public void testDepartment() {
        assertEquals(Work_1.getDepartment(), "IT");
    }

    //Todo: Implement End time in ITWork?
    @Test(timeout = 100)
    public void testEnd_time() {
        assertEquals(Work_1.getEnd_time(), "");
    }

    @Test(timeout = 100)
    public void testState() {
        assertEquals(Work_1.getState(), "Pending");
        Work_1.setState("In Progress");
        assertEquals(Work_1.getState(), "In Progress");
    }


    //Todo: How to test start time in ITWork?
    @Test(timeout = 100)
    public void testStart_time() {
        Work_1.setStart_time("");
        assertEquals(Work_1.getStart_time(), "");
    }

    @Test(timeout = 100)
    public void testDescribe() {
        Work_1.setDescribe("Need to program something");
        assertEquals(Work_1.getDescribe(), "Need to program something");
    }

    @Test(timeout = 100)
    public void testRequirement() {
        Work_1.setRequirement("Done by 3 days");
        assertEquals(Work_1.getRequirement(), "Done by 3 days");
    }

}
