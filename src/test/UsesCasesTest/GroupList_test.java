package test.UsesCasesTest;

import main.UsesCases.*;
import main.Entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GroupList_test {
    GroupList GL;
    Userable user1;
    Userable[] memberList;

    @Before
    public void Setup(){
        GL = new GroupList();
        Userable user1 = new User("111", "123456", "Lily", "4203456789",
                "10 King St.", "8888");

    }

//
//    @Test(timeout = 100)
//    public void testAddGroup(){
//        GL.addGroup(user1, memberList, "0001");
//        for (Group e: GL) {
//            if (e.getWorkid().equals("0001")){
//                Userable[] l = e.getMembers();
//                assertEquals(e.getLeader(), user1);
//                assertEquals(e.getMembers().length, memberList.length);
//                boolean found = false;
//                for (int i = 0; i < l.length; i++){
//                    user1.containsAny();
//                    l[i] instanceof user1;
//                    user1.getID();
//
//                }
//            }
//        }
//    }

}
