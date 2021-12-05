package test.EntityTest;

import main.Entity.Work;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Work_test {
    Work work;

    @Before
    public void setUp() {
        work = new Work("First Work", "456", "IT", "None", 2,"2022-01-01");
    }

    @Test(timeout = 100)
    public void testName() {
        assertEquals(work.getName(), "First Work");
    }

    @Test(timeout = 100)
    public void testID() {
        assertEquals(work.getID(), "456");
    }

    @Test(timeout = 100)
    public void testLevel() {
        assertEquals(work.getLevel(), 2);
    }

    @Test(timeout = 100)
    public void testDepartment() {
        assertEquals(work.getDepartment(), "IT");
    }

    @Test(timeout = 100)
    public void testState() {
        assertEquals(work.getState(), "Pending");
        work.setState("Working");
        assertEquals(work.getState(), "Working");
    }

    @Test(timeout = 100)
    public void testDescribe() {
        assertNull(work.getDescribe());
        work.setDescribe("This is just a test");
        assertEquals(work.getDescribe(), "This is just a test");
    }

    @Test(timeout = 100)
    public void testRequirement() {
        assertEquals(work.getRequirement(), "None");
        work.setRequirement("Be careful.");
        assertEquals(work.getRequirement(), "Be careful.");
    }

    @Test(timeout = 100)
    public void testSign() {
        assertEquals(work.getSign(), "0");
        work.setSign("1");
        assertEquals(work.getSign(), "1");
    }

}