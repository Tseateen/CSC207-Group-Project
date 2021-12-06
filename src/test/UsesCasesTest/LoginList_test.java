package test.UsesCasesTest;


import main.Entity.UserAble;
import main.UsesCases.*;
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
        LL.addUser("Lily", "123456", "4203456789",  "10 King St.");
        for (UserAble u: LL){
            if (u.getID().equals("1")){
                assertEquals(u.getPassword(), "123456");
                assertEquals(u.getName(), "Lily");
                assertEquals(u.getAddress(), "10 King St.");
                assertEquals(u.getPhone(), "4203456789");
            } else{
                System.out.println("user not added");
            }
        }
    }


    @Test
    public void testDeleteUser(){
        LL.deleteUser("1");
        for (UserAble u: LL){
            assertNotEquals(u.getID(), "1");
        }
        assertEquals(LL.getSize(), 0);
    }
}
