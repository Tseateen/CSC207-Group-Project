package test.EntityTest;

import main.Entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class User_test {
    User U1;
    @Before
    public void setUp() {
        U1 = new User("andy1234", "a12345", "Andy", "412345678",
                "123 Mississauga Road", "001");
    }

    @Test(timeout = 100)
    public void testName() {
        assertEquals(U1.getName(), "Andy");
        U1.setName("Luke");
        assertEquals(U1.getName(), "Luke");
    }

    @Test(timeout = 100)
    public void testId() {
        assertEquals(U1.getID(), "001");
    }

    @Test(timeout = 100)
    public void testUserName() {
        assertEquals(U1.getUsername(), "andy1234");
        U1.setUsername("luke1234");
        assertEquals(U1.getUsername(), "luke1234");
    }

    @Test(timeout = 100)
    public void testPassword() {
        assertEquals(U1.getPassword(), "a12345");
        U1.setPassword("l12345");
        assertEquals(U1.getPassword(), "l12345");
        assertTrue(U1.comparePassword("l12345"));
        assertFalse(U1.comparePassword("a12345"));
    }

    @Test(timeout = 100)
    public void testPhone() {
        assertEquals(U1.getPhone(), "412345678");
        U1.setPhone("487654321");
        assertEquals(U1.getPhone(), "487654321");
    }

    @Test(timeout = 100)
    public void testAddress() {
        assertEquals(U1.getAddress(), "123 Mississauga Road");
        U1.setAddress("001 Mississauga Road");
        assertEquals(U1.getAddress(), "001 Mississauga Road");
    }

}
