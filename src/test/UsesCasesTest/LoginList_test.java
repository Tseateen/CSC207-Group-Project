package test.UsesCasesTest;

import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginList_test {
    LoginList LL;

    @Before
    public void Setup(){
        LL = new LoginList();

    }

    @Test
    public void testAddUser(){
        LL.addUser("111", "123456", "Lily", "4203456789",
                "10 King St.", "8888");
        for (Userable u: LL){
            if (u.getID().equals("8888")){
                assertEquals(u.getPassword(), "123456");
                assertEquals(u.getName(), "Lily");
                assertEquals(u.getAddress(), "10 King St.");
                assertEquals(u.getUsername(), "111");
                assertEquals(u.getPhone(), "4203456789");
            } else{
                System.out.println("user not added");
            }
        }
    }


    @Test
    public void testDeleteUser(){
        int l = LL.getSize();
        LL.deleteUser("8888");
        for (Userable u: LL){
            assertNotEquals(u.getID(), "8888");
        }
        assertEquals(LL.getSize(), l - 1);
    }
}
