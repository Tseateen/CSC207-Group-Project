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
                assertEquals("123456",u.getPassword());
                assertEquals( "Lily",u.getName());
                assertEquals("10 King St.",u.getAddress());
                assertEquals("4203456789",u.getPhone());
            } else{
                System.out.println("user not added");
            }
        }
    }


    @Test
    public void testDeleteUser(){
        LL.deleteUser("1");
      
        }
        assertEquals(0,LL.getSize());
    }
}
